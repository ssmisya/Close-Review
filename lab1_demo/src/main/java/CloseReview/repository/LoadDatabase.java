package CloseReview.repository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import CloseReview.user.*;
@Configuration
class LoadDatabase {

    private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

    @Bean
    CommandLineRunner initUser(UserRepository repository) {
        if(repository.findAll().isEmpty()){
            return args -> {
                log.info("Preloading " + repository.save(new User("fd23120240001","Bilbo Baggins", "123456","bilbo@m.fudan.edu.cn","Fudan University","China")));
                log.info("Preloading " + repository.save(new User("fd23120240002","Frodo Baggins", "123456","frodo@m.fudan.edu.cn","Tsinghua University","China")));
                log.info("Preloading " + repository.save(new User("fd23120240003","Wenxuan", "123456","wenxuan@somemail.com","MSRA","China")));
                log.info("Preloading " + repository.save(new User("fd23120240004","Xiaoming", "asdzxc","mingx@somemail.com","SHLAB","China")));
                log.info("Preloading " + repository.save(new User("fd23120240005","Cristiano Ronaldo", "asdzxc","rolnaldo@somemail.com","MIT","US")));
            };
        }
        else{
            return args -> {
                log.info("Already Preloaded");
            };
        }
    }

    @Bean
    CommandLineRunner initUserRole(UserRoleRepository repository) {
        if (repository.findAll().isEmpty()){
            return args -> {
                log.info("Preloading " + repository.save(new UserRole("fd23120240001","Administer")));
                log.info("Preloading " + repository.save(new UserRole("fd23120240002","chair")));
                log.info("Preloading " + repository.save(new UserRole("fd23120240003","PC member")));
                log.info("Preloading " + repository.save(new UserRole("fd23120240004","author")));
                log.info("Preloading " + repository.save(new UserRole("fd23120240005","author")));
            };
        }
        else{
            return args -> {
                log.info("Already Preloaded");
            };
        }
    }
}