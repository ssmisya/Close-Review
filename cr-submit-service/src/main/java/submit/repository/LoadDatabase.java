package submit.repository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import submit.entity.Paper;

@Configuration
class LoadDatabase {

    private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

    @Bean
    CommandLineRunner initConference(PaperRepository repository) {
        if(repository.findAll().isEmpty()||repository.findAll().size()<3){
            return args -> {
                log.info("Preloading " + repository.save(new Paper("999996","Test Conference Name 2","c00002", "Wenxuan","/tmp/conference/c00002/99996.pdf","Wenxuan@somemail.com","submit","","","abstract","CV")));
                log.info("Preloading " + repository.save(new Paper("999997","Test Conference Name 2","c00002","Xiaoming","/tmp/conference/c00002/99996.pdf","Xiaoming@somemail.com","submit","","","abstract","NLP")));
                log.info("Preloading " + repository.save(new Paper("999998","Test Conference Name 2","c00002","Tom","/tmp/conference/c00002/99996.pdf","Tom@somemail.com","submit","","","abstract","CV;NLP")));
            };
        }
        else{
            return args -> {
                log.info("Already Preloaded");
            };
        }
    }

}