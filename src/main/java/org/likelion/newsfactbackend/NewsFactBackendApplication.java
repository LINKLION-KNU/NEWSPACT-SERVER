package org.likelion.newsfactbackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class NewsFactBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(NewsFactBackendApplication.class, args);
    }

}
