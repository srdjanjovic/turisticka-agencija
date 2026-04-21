package com.turisticka_agencija.dodatne_aktivnosti.config;

import com.turisticka_agencija.dodatne_aktivnosti.model.*;
import com.turisticka_agencija.dodatne_aktivnosti.repository.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;

@Configuration
public class DataLoader {

    @Bean
    CommandLineRunner loadData(
            CustomerRepository customerRepo,
            CategoryRepository categoryRepo,
            AdditionalActivityRepository activityRepo,
            ArrangementRepository arrangementRepo
    ) {
        return args -> {

            if (customerRepo.count() > 0) return;

            Category adventure = new Category();
            adventure.setName("Adventure");

            Category wellness = new Category();
            wellness.setName("Wellness");

            Category culture = new Category();
            culture.setName("Culture");

            categoryRepo.save(adventure);
            categoryRepo.save(wellness);
            categoryRepo.save(culture);

            Arrangement a1 = new Arrangement();
            a1.setName("Letovanje Krf");
            a1.setDestination("Krf");
            a1.setStartDate(LocalDate.of(2026,7,10));
            a1.setEndDate(LocalDate.of(2026,7,20));
            a1.setPrice(650.0);
            a1.setCapacity(30);

            Arrangement a2 = new Arrangement();
            a2.setName("Zlatibor Vikend");
            a2.setDestination("Zlatibor");
            a2.setStartDate(LocalDate.of(2026,8,5));
            a2.setEndDate(LocalDate.of(2026,8,8));
            a2.setPrice(220.0);
            a2.setCapacity(20);

            arrangementRepo.save(a1);
            arrangementRepo.save(a2);

            AdditionalActivity rafting = new AdditionalActivity();
            rafting.setName("Rafting");
            rafting.setCategory(adventure);
            rafting.setArrangement(a2);

            AdditionalActivity spa = new AdditionalActivity();
            spa.setName("Spa");
            spa.setCategory(wellness);
            spa.setArrangement(a1);

            activityRepo.save(rafting);
            activityRepo.save(spa);

            Customer marko = new Customer();
            marko.setFirstName("Marko");
            marko.setEmail("marko@gmail.com");
            marko.getFavoriteCategories().add(adventure);
            marko.getBookedArrangements().add(a2);
            marko.getRegistrations().add(
                    new Registration(rafting, LocalDate.now(), 2)
            );

            Customer ana = new Customer();
            ana.setFirstName("Ana");
            ana.setEmail("ana@gmail.com");
            ana.getFavoriteCategories().add(wellness);
            ana.getBookedArrangements().add(a1);

            customerRepo.save(marko);
            customerRepo.save(ana);
        };
    }
}