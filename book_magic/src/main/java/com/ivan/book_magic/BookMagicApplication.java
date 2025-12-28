package com.ivan.book_magic;

import com.ivan.book_magic.model.BookOffer;
import com.ivan.book_magic.scraper.CielaScraper;
import com.ivan.book_magic.service.BookDiscoveryService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class BookMagicApplication {

    public static void main(String[] args) {
        SpringApplication.run(BookMagicApplication.class, args);

    }
    @Bean
    CommandLineRunner discoverNewBooks(BookDiscoveryService bookDiscoveryService) {
        return args -> bookDiscoveryService.discoverNewBooks(
                    "holly-black"
            );
    }
}
