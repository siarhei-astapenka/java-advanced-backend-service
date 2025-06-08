package com.epam.learn.javaadvanced.service.setup;

import com.epam.learn.javaadvanced.service.RakutenItemService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataSetup implements CommandLineRunner {

    private final RakutenItemService rakutenItemService;

    public DataSetup(RakutenItemService rakutenItemService) {
        this.rakutenItemService = rakutenItemService;
    }

    @Override
    public void run(String... args) {
        // Initial fetch of items when application starts
        rakutenItemService.fetchAndSaveItems("sport")
                .subscribe(
                        item -> System.out.println("Saved item: " + item),
                        error -> System.err.println("Error fetching items: " + error),
                        () -> System.out.println("Initial item fetch completed")
                );
         }
}
