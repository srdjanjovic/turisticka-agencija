package com.turisticka_agencija.dodatne_aktivnosti.config;

import com.turisticka_agencija.dodatne_aktivnosti.model.AdditionalActivity;
import com.turisticka_agencija.dodatne_aktivnosti.model.Arrangement;
import com.turisticka_agencija.dodatne_aktivnosti.model.Category;
import com.turisticka_agencija.dodatne_aktivnosti.model.Customer;
import com.turisticka_agencija.dodatne_aktivnosti.model.Registration;
import com.turisticka_agencija.dodatne_aktivnosti.repository.AdditionalActivityRepository;
import com.turisticka_agencija.dodatne_aktivnosti.repository.ArrangementRepository;
import com.turisticka_agencija.dodatne_aktivnosti.repository.CategoryRepository;
import com.turisticka_agencija.dodatne_aktivnosti.repository.CustomerRepository;
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

            // =========================
            // CATEGORIES
            // =========================
            Category adventure = new Category();
            adventure.setName("Adventure");

            Category wellness = new Category();
            wellness.setName("Wellness");

            Category culture = new Category();
            culture.setName("Culture");

            Category gastronomy = new Category();
            gastronomy.setName("Gastronomy");

            Category nature = new Category();
            nature.setName("Nature");

            Category entertainment = new Category();
            entertainment.setName("Entertainment");

            Category sport = new Category();
            sport.setName("Sport");

            Category relax = new Category();
            relax.setName("Relax");

            categoryRepo.save(adventure);
            categoryRepo.save(wellness);
            categoryRepo.save(culture);
            categoryRepo.save(gastronomy);
            categoryRepo.save(nature);
            categoryRepo.save(entertainment);
            categoryRepo.save(sport);
            categoryRepo.save(relax);

            // =========================
            // ARRANGEMENTS - FEWER, MORE ACTIVITIES
            // =========================
            Arrangement krf = new Arrangement();
            krf.setName("Letovanje Krf");
            krf.setDestination("Krf");
            krf.setStartDate(LocalDate.of(2026, 7, 10));
            krf.setEndDate(LocalDate.of(2026, 7, 20));
            krf.setPrice(650.0);
            krf.setCapacity(30);

            Arrangement zlatibor = new Arrangement();
            zlatibor.setName("Zlatibor Vikend");
            zlatibor.setDestination("Zlatibor");
            zlatibor.setStartDate(LocalDate.of(2026, 8, 5));
            zlatibor.setEndDate(LocalDate.of(2026, 8, 8));
            zlatibor.setPrice(220.0);
            zlatibor.setCapacity(20);

            Arrangement rim = new Arrangement();
            rim.setName("Prolecni Rim");
            rim.setDestination("Rim");
            rim.setStartDate(LocalDate.of(2026, 5, 12));
            rim.setEndDate(LocalDate.of(2026, 5, 18));
            rim.setPrice(540.0);
            rim.setCapacity(25);

            arrangementRepo.save(krf);
            arrangementRepo.save(zlatibor);
            arrangementRepo.save(rim);

            // =========================
            // ACTIVITIES - KRF
            // =========================
            AdditionalActivity krfBoatTour = new AdditionalActivity();
            krfBoatTour.setName("Boat Tour Paxos");
            krfBoatTour.setDescription("Jednodnevni izlet brodom do obliznjih ostrva.");
            krfBoatTour.setPrice(55.0);
            krfBoatTour.setLocation("Krf");
            krfBoatTour.setDuration(8);
            krfBoatTour.setMaxCapacity(25);
            krfBoatTour.setCategory(adventure);
            krfBoatTour.setArrangement(krf);

            AdditionalActivity krfSpa = new AdditionalActivity();
            krfSpa.setName("Spa & Relax");
            krfSpa.setDescription("Celodnevni pristup spa centru i bazenu.");
            krfSpa.setPrice(35.0);
            krfSpa.setLocation("Krf");
            krfSpa.setDuration(5);
            krfSpa.setMaxCapacity(20);
            krfSpa.setCategory(wellness);
            krfSpa.setArrangement(krf);

            AdditionalActivity krfOliveTour = new AdditionalActivity();
            krfOliveTour.setName("Olive Farm Tour");
            krfOliveTour.setDescription("Obilazak maslinjaka i degustacija lokalnih proizvoda.");
            krfOliveTour.setPrice(28.0);
            krfOliveTour.setLocation("Krf");
            krfOliveTour.setDuration(3);
            krfOliveTour.setMaxCapacity(18);
            krfOliveTour.setCategory(gastronomy);
            krfOliveTour.setArrangement(krf);

            AdditionalActivity krfBeachYoga = new AdditionalActivity();
            krfBeachYoga.setName("Beach Yoga");
            krfBeachYoga.setDescription("Jutarnja joga na plazi.");
            krfBeachYoga.setPrice(18.0);
            krfBeachYoga.setLocation("Krf");
            krfBeachYoga.setDuration(2);
            krfBeachYoga.setMaxCapacity(15);
            krfBeachYoga.setCategory(relax);
            krfBeachYoga.setArrangement(krf);

            AdditionalActivity krfSnorkeling = new AdditionalActivity();
            krfSnorkeling.setName("Snorkeling Adventure");
            krfSnorkeling.setDescription("Istraživanje podvodnog sveta uz vodiča.");
            krfSnorkeling.setPrice(40.0);
            krfSnorkeling.setLocation("Krf");
            krfSnorkeling.setDuration(3);
            krfSnorkeling.setMaxCapacity(12);
            krfSnorkeling.setCategory(sport);
            krfSnorkeling.setArrangement(krf);

            // =========================
            // ACTIVITIES - ZLATIBOR
            // =========================
            AdditionalActivity zlatiborZipLine = new AdditionalActivity();
            zlatiborZipLine.setName("Zip Line Avantura");
            zlatiborZipLine.setDescription("Spust zip line stazom uz profesionalni nadzor.");
            zlatiborZipLine.setPrice(28.0);
            zlatiborZipLine.setLocation("Zlatibor");
            zlatiborZipLine.setDuration(1);
            zlatiborZipLine.setMaxCapacity(5);
            zlatiborZipLine.setCategory(adventure);
            zlatiborZipLine.setArrangement(zlatibor);

            AdditionalActivity zlatiborHorse = new AdditionalActivity();
            zlatiborHorse.setName("Jahanje prirodom");
            zlatiborHorse.setDescription("Rekreativno jahanje za pocetnike i iskusne.");
            zlatiborHorse.setPrice(32.0);
            zlatiborHorse.setLocation("Zlatibor");
            zlatiborHorse.setDuration(2);
            zlatiborHorse.setMaxCapacity(8);
            zlatiborHorse.setCategory(nature);
            zlatiborHorse.setArrangement(zlatibor);

            AdditionalActivity zlatiborHiking = new AdditionalActivity();
            zlatiborHiking.setName("Planinarenje do vidikovca");
            zlatiborHiking.setDescription("Vodjena pesacka tura kroz prirodu.");
            zlatiborHiking.setPrice(20.0);
            zlatiborHiking.setLocation("Zlatibor");
            zlatiborHiking.setDuration(3);
            zlatiborHiking.setMaxCapacity(15);
            zlatiborHiking.setCategory(nature);
            zlatiborHiking.setArrangement(zlatibor);

            AdditionalActivity zlatiborQuad = new AdditionalActivity();
            zlatiborQuad.setName("Quad Adventure");
            zlatiborQuad.setDescription("Vožnja kvadovima kroz planinske staze.");
            zlatiborQuad.setPrice(50.0);
            zlatiborQuad.setLocation("Zlatibor");
            zlatiborQuad.setDuration(2);
            zlatiborQuad.setMaxCapacity(5);
            zlatiborQuad.setCategory(sport);
            zlatiborQuad.setArrangement(zlatibor);

            AdditionalActivity zlatiborFolkNight = new AdditionalActivity();
            zlatiborFolkNight.setName("Etno Vece");
            zlatiborFolkNight.setDescription("Tradicionalna muzika, hrana i zabavni program.");
            zlatiborFolkNight.setPrice(22.0);
            zlatiborFolkNight.setLocation("Zlatibor");
            zlatiborFolkNight.setDuration(3);
            zlatiborFolkNight.setMaxCapacity(30);
            zlatiborFolkNight.setCategory(entertainment);
            zlatiborFolkNight.setArrangement(zlatibor);

            // =========================
            // ACTIVITIES - RIM
            // =========================
            AdditionalActivity rimMuseum = new AdditionalActivity();
            rimMuseum.setName("Vatikan i muzeji");
            rimMuseum.setDescription("Strucno vodjenje kroz muzeje i istorijske lokacije.");
            rimMuseum.setPrice(50.0);
            rimMuseum.setLocation("Rim");
            rimMuseum.setDuration(6);
            rimMuseum.setMaxCapacity(20);
            rimMuseum.setCategory(culture);
            rimMuseum.setArrangement(rim);

            AdditionalActivity rimPasta = new AdditionalActivity();
            rimPasta.setName("Italian Pasta Workshop");
            rimPasta.setDescription("Radionica pravljenja tradicionalne paste.");
            rimPasta.setPrice(40.0);
            rimPasta.setLocation("Rim");
            rimPasta.setDuration(3);
            rimPasta.setMaxCapacity(12);
            rimPasta.setCategory(gastronomy);
            rimPasta.setArrangement(rim);

            AdditionalActivity rimWalking = new AdditionalActivity();
            rimWalking.setName("Historical Walking Tour");
            rimWalking.setDescription("Pesacki obilazak istorijskih znamenitosti.");
            rimWalking.setPrice(27.0);
            rimWalking.setLocation("Rim");
            rimWalking.setDuration(3);
            rimWalking.setMaxCapacity(20);
            rimWalking.setCategory(culture);
            rimWalking.setArrangement(rim);

            AdditionalActivity rimWine = new AdditionalActivity();
            rimWine.setName("Wine & Cheese Evening");
            rimWine.setDescription("Degustacija vina i sireva uz lokalnog vodica.");
            rimWine.setPrice(36.0);
            rimWine.setLocation("Rim");
            rimWine.setDuration(2);
            rimWine.setMaxCapacity(16);
            rimWine.setCategory(gastronomy);
            rimWine.setArrangement(rim);

            AdditionalActivity rimStreetShow = new AdditionalActivity();
            rimStreetShow.setName("Roman Night Show");
            rimStreetShow.setDescription("Vecernji zabavni program i performans.");
            rimStreetShow.setPrice(24.0);
            rimStreetShow.setLocation("Rim");
            rimStreetShow.setDuration(2);
            rimStreetShow.setMaxCapacity(30);
            rimStreetShow.setCategory(entertainment);
            rimStreetShow.setArrangement(rim);

            activityRepo.save(krfBoatTour);
            activityRepo.save(krfSpa);
            activityRepo.save(krfOliveTour);
            activityRepo.save(krfBeachYoga);
            activityRepo.save(krfSnorkeling);

            activityRepo.save(zlatiborZipLine);
            activityRepo.save(zlatiborHorse);
            activityRepo.save(zlatiborHiking);
            activityRepo.save(zlatiborQuad);
            activityRepo.save(zlatiborFolkNight);

            activityRepo.save(rimMuseum);
            activityRepo.save(rimPasta);
            activityRepo.save(rimWalking);
            activityRepo.save(rimWine);
            activityRepo.save(rimStreetShow);

            // =========================
            // CUSTOMERS - MORE OVERLAP
            // =========================
            Customer marko = new Customer();
            marko.setFirstName("Marko");
            marko.setLastName("Markovic");
            marko.setAge(27);
            marko.setEmail("marko@gmail.com");
            marko.setContact("061111111");
            marko.getFavoriteCategories().add(adventure);
            marko.getFavoriteCategories().add(nature);
            marko.getFavoriteCategories().add(sport);
            marko.getBookedArrangements().add(zlatibor);
            marko.getBookedArrangements().add(krf);
            marko.getRegistrations().add(new Registration(zlatiborZipLine, LocalDate.of(2026, 4, 10), 2));
            marko.getRegistrations().add(new Registration(zlatiborHiking, LocalDate.of(2026, 4, 11), 3));
            marko.getRegistrations().add(new Registration(krfBoatTour, LocalDate.of(2026, 4, 12), 2));

            Customer ana = new Customer();
            ana.setFirstName("Ana");
            ana.setLastName("Anic");
            ana.setAge(25);
            ana.setEmail("ana@gmail.com");
            ana.setContact("062222222");
            ana.getFavoriteCategories().add(wellness);
            ana.getFavoriteCategories().add(relax);
            ana.getFavoriteCategories().add(gastronomy);
            ana.getBookedArrangements().add(krf);
            ana.getBookedArrangements().add(rim);
            ana.getRegistrations().add(new Registration(krfSpa, LocalDate.of(2026, 4, 12), 2));
            ana.getRegistrations().add(new Registration(krfBeachYoga, LocalDate.of(2026, 4, 13), 1));
            ana.getRegistrations().add(new Registration(rimPasta, LocalDate.of(2026, 4, 14), 2));

            Customer nikola = new Customer();
            nikola.setFirstName("Nikola");
            nikola.setLastName("Nikolic");
            nikola.setAge(31);
            nikola.setEmail("nikola@gmail.com");
            nikola.setContact("063333333");
            nikola.getFavoriteCategories().add(culture);
            nikola.getFavoriteCategories().add(gastronomy);
            nikola.getFavoriteCategories().add(entertainment);
            nikola.getBookedArrangements().add(rim);
            nikola.getBookedArrangements().add(krf);
            nikola.getRegistrations().add(new Registration(rimMuseum, LocalDate.of(2026, 4, 14), 2));
            nikola.getRegistrations().add(new Registration(rimPasta, LocalDate.of(2026, 4, 14), 4));
            nikola.getRegistrations().add(new Registration(rimWine, LocalDate.of(2026, 4, 15), 2));
            nikola.getRegistrations().add(new Registration(krfOliveTour, LocalDate.of(2026, 4, 16), 2));

            Customer milica = new Customer();
            milica.setFirstName("Milica");
            milica.setLastName("Milic");
            milica.setAge(29);
            milica.setEmail("milica@gmail.com");
            milica.setContact("064444444");
            milica.getFavoriteCategories().add(nature);
            milica.getFavoriteCategories().add(adventure);
            milica.getFavoriteCategories().add(sport);
            milica.getBookedArrangements().add(zlatibor);
            milica.getBookedArrangements().add(krf);
            milica.getRegistrations().add(new Registration(zlatiborZipLine, LocalDate.of(2026, 4, 16), 2));
            milica.getRegistrations().add(new Registration(zlatiborQuad, LocalDate.of(2026, 4, 16), 4));
            milica.getRegistrations().add(new Registration(krfBoatTour, LocalDate.of(2026, 4, 17), 3));
            milica.getRegistrations().add(new Registration(krfSnorkeling, LocalDate.of(2026, 4, 17), 2));

            Customer jovan = new Customer();
            jovan.setFirstName("Jovan");
            jovan.setLastName("Jovanovic");
            jovan.setAge(35);
            jovan.setEmail("jovan@gmail.com");
            jovan.setContact("065555555");
            jovan.getFavoriteCategories().add(gastronomy);
            jovan.getFavoriteCategories().add(culture);
            jovan.getFavoriteCategories().add(entertainment);
            jovan.getBookedArrangements().add(rim);
            jovan.getBookedArrangements().add(zlatibor);
            jovan.getRegistrations().add(new Registration(rimPasta, LocalDate.of(2026, 4, 17), 3));
            jovan.getRegistrations().add(new Registration(rimStreetShow, LocalDate.of(2026, 4, 18), 4));
            jovan.getRegistrations().add(new Registration(zlatiborFolkNight, LocalDate.of(2026, 4, 18), 2));

            Customer jelena = new Customer();
            jelena.setFirstName("Jelena");
            jelena.setLastName("Jelic");
            jelena.setAge(24);
            jelena.setEmail("jelena@gmail.com");
            jelena.setContact("066666666");
            jelena.getFavoriteCategories().add(wellness);
            jelena.getFavoriteCategories().add(relax);
            jelena.getFavoriteCategories().add(nature);
            jelena.getBookedArrangements().add(krf);
            jelena.getBookedArrangements().add(zlatibor);
            jelena.getRegistrations().add(new Registration(krfSpa, LocalDate.of(2026, 4, 18), 2));
            jelena.getRegistrations().add(new Registration(krfBeachYoga, LocalDate.of(2026, 4, 18), 2));
            jelena.getRegistrations().add(new Registration(zlatiborHorse, LocalDate.of(2026, 4, 19), 1));
            jelena.getRegistrations().add(new Registration(zlatiborHiking, LocalDate.of(2026, 4, 19), 2));

            Customer stefan = new Customer();
            stefan.setFirstName("Stefan");
            stefan.setLastName("Stefanovic");
            stefan.setAge(33);
            stefan.setEmail("stefan@gmail.com");
            stefan.setContact("067777777");
            stefan.getFavoriteCategories().add(adventure);
            stefan.getFavoriteCategories().add(culture);
            stefan.getFavoriteCategories().add(gastronomy);
            stefan.getBookedArrangements().add(rim);
            stefan.getBookedArrangements().add(krf);
            stefan.getRegistrations().add(new Registration(rimMuseum, LocalDate.of(2026, 4, 19), 2));
            stefan.getRegistrations().add(new Registration(rimWalking, LocalDate.of(2026, 4, 19), 2));
            stefan.getRegistrations().add(new Registration(krfBoatTour, LocalDate.of(2026, 4, 20), 3));
            stefan.getRegistrations().add(new Registration(krfOliveTour, LocalDate.of(2026, 4, 20), 2));

            customerRepo.save(marko);
            customerRepo.save(ana);
            customerRepo.save(nikola);
            customerRepo.save(milica);
            customerRepo.save(jovan);
            customerRepo.save(jelena);
            customerRepo.save(stefan);
        };
    }
}