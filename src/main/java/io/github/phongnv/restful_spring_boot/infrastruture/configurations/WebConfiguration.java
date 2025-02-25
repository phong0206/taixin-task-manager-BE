package io.github.phongnv.restful_spring_boot.infrastruture.configurations;

import io.github.phongnv.restful_spring_boot.infrastruture.common.constants.UserRole;
import io.github.phongnv.restful_spring_boot.infrastruture.entities.User;
import io.github.phongnv.restful_spring_boot.infrastruture.services.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.Example;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Optional;

@Configuration
@RequiredArgsConstructor
@Slf4j(topic = "INIT-APPLICATION")
public class WebConfiguration implements WebMvcConfigurer {

    private final UserService userService;


    @Bean
    @ConditionalOnProperty(
            prefix = "spring",
            value = "datasource.driver-class-name",
            havingValue = "com.mysql.cj.jdbc.Driver")
    ApplicationRunner initApplication() {
        log.info("Initializing application.....");

        User userExample = new User();
        userExample.setRole(UserRole.ADMIN);
        Example<User> example = Example.of(userExample);

        return args -> {
            Optional<User> user = userService.assignGetRepository().findOne(example);
            log.info("{}", user.isEmpty());
            if(user.isEmpty()) {
                userService.save(User.builder()
                        .name("admin")
                        .role(UserRole.ADMIN)
                        .email("admin@gmail.com")
                        .password("TODO: hash pass")
                        .build());
            }
        };
    }
}
