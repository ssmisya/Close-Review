package review.repository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import review.entity.Review;

@Configuration
class LoadDatabase {

    private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

    @Bean
    CommandLineRunner initUser(ReviewRepository repository) {
        if(repository.findAll().isEmpty()||repository.findAll().size()<9){
            return args -> {
                log.info("Preloading " + repository.save(new Review("999996","fd23120240003","c00002")));
                log.info("Preloading " + repository.save(new Review("999996","fd23120240004","c00002")));
                log.info("Preloading " + repository.save(new Review("999996","fd23120240005","c00002")));
                log.info("Preloading " + repository.save(new Review("999997","fd23120240002","c00002")));
                log.info("Preloading " + repository.save(new Review("999997","fd23120240003","c00002")));
                log.info("Preloading " + repository.save(new Review("999997","fd23120240004","c00002")));
                log.info("Preloading " + repository.save(new Review("999998","fd23120240002","c00002")));
                log.info("Preloading " + repository.save(new Review("999998","fd23120240003","c00002")));
                log.info("Preloading " + repository.save(new Review("999998","fd23120240005","c00002")));
            };
        }
        else{
            return args -> {
                log.info("Already Preloaded");
            };
        }
    }
}