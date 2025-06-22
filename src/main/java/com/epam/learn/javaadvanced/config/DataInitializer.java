package com.epam.learn.javaadvanced.config;

import com.epam.learn.javaadvanced.entity.User;
import com.epam.learn.javaadvanced.repository.UserRepository;
import com.epam.learn.javaadvanced.service.PasswordService;
import lombok.AllArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;
import org.yaml.snakeyaml.Yaml;

import java.io.InputStream;
import java.util.List;
import java.util.Map;

@Component
@AllArgsConstructor
public class DataInitializer implements ApplicationRunner {

    private final UserRepository userRepository;
    private final PasswordService passwordService;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        ClassPathResource resource = new ClassPathResource("users.yml");
        Yaml yaml = new Yaml();

        try (InputStream inputStream = resource.getInputStream()) {
            Map<String, Object> yamlData = yaml.load(inputStream);
            List<Map<String, String>> users = (List<Map<String, String>>) yamlData.get("users");

            List<UserCredential> userCredentials = users.stream()
                .map(user -> new UserCredential(
                    user.get("username"),
                    user.get("password")))
                .toList();

            for (UserCredential cred : userCredentials) {
                if (userRepository.findByUsername(cred.username()) == null) {
                    User user = new User();
                    user.setUsername(cred.username());
                    user.setPassword(passwordService.hashPassword(cred.password()));
                    userRepository.save(user);
                }
            }
        }
    }

    private record UserCredential(String username, String password) {}
}



