package spiritedtechie;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import spiritedtechie.data.Customer;
import spiritedtechie.data.CustomerRepository;

@Configuration
class LoadDatabase {

    private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

    @Bean
    CommandLineRunner initDatabase(CustomerRepository repository) {

        return args -> {
            log.info("Preloading " + repository.save(new Customer("Bilbo", "Baggins")));
            log.info("Preloading " + repository.save(new Customer("Frodo", "Baggins")));
        };
    }
}
