package com.epam.learn.javaadvanced.service.setup;

import com.epam.learn.javaadvanced.service.RakutenItemService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataSetup implements CommandLineRunner {

    @Value("${rakuten.api.keyword}")
    private String keyWord;

    private final RakutenItemService rakutenItemService;

    public DataSetup(RakutenItemService rakutenItemService) {
        this.rakutenItemService = rakutenItemService;
    }

    @Override
    public void run(String... args) {
        rakutenItemService.fetchAndSaveItems(keyWord)
                .subscribe(
                        item -> System.out.println("Saved item: " + item),
                        error -> System.err.println("Error fetching items: " + error),
                        () -> System.out.println("Initial item fetch completed")
                );
         }
}
