package person;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class LoadDatabase {

    private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

    @Bean
    CommandLineRunner initDatabase(EmployeeRepository repository) {

        return args -> {
            log.info("Preloading " + repository.save(new Employee("Bilbo Baggins", "M",23L)));
            log.info("Preloading " + repository.save(new Employee("Frodo Baggins", "F",21L)));
            log.info("Preloading " + repository.save(new Employee("Wenxuan", "F",21L)));
            log.info("Preloading " + repository.save(new Employee("Xiaoming", "M",26L)));
            log.info("Preloading " + repository.save(new Employee("Cristiano Ronaldo", "M",38L)));
        };
    }
}