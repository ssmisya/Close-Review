package conference.repository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import conference.entity.Conference;

@Configuration
class LoadDatabase {

    private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

    @Bean
    CommandLineRunner initConference(ConferenceRepository repository) {
        if(repository.findAll().isEmpty()||repository.findAll().size()<3){
            return args -> {
                log.info("Preloading " + repository.save(new Conference("c00001","Test Conference Name 1","TC1 2024", "CV","register","2024.10.1","2024.5.20","Shanghai",0L)));
                log.info("Preloading " + repository.save(new Conference("c00002","Test Conference Name 2", "TC2 2024","CV;NLP","open_submit","2024.5.1","2023.12.20","Chicago", 0L)));
                log.info("Preloading " + repository.save(new Conference("c00003","The 42nd International Conference on Software Engineering", "ICSE 2020","NLP;AI4SCI;BIGMODEL;","open_submit","2024.6.1","2023.12.20","Beijing",0L)));
            };
        }
        else{
            return args -> {
                log.info("Already Preloaded");
            };
        }
    }

}