package com.ivan.book_magic;

import com.ivan.book_magic.model.BookOffer;
import com.ivan.book_magic.scraper.CielaScraper;
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
    CommandLineRunner testAuthorScraping() {
        return args -> {
            CielaScraper scraper = new CielaScraper();
            List<BookOffer> books = scraper.scrapeBooksByAuthor("holly-black");

            books.forEach(book -> System.out.println(book));
        };
    }
}
