package io.github.phongnv.restful_spring_boot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication()
@EnableJpaAuditing
public class RestfulSpringBootApplication {
    public static void main(String[] args) {
        System.out.println("Ứng dụng Spring Boot đã chạy thành công!");
        SpringApplication.run(RestfulSpringBootApplication.class, args);
    }
}
