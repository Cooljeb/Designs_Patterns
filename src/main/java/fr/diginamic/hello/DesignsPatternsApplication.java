package fr.diginamic.hello;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@SpringBootApplication
@EnableScheduling
public class DesignsPatternsApplication {

    public static void main(String[] args) {
        SpringApplication.run(DesignsPatternsApplication.class, args);
    }

    @Scheduled(cron = " * * * * * *")
    public void tictic(){
        System.out.println("Tic-Tac-Tac"+System.currentTimeMillis());
    }

}
