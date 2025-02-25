package io.github.phongnv.restful_spring_boot.usecases;

import io.github.phongnv.restful_spring_boot.infrastruture.entities.User;
import io.github.phongnv.restful_spring_boot.infrastruture.services.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
@Slf4j(topic = "USER-USECASE")
public class UserUsecase {

    private final UserService userService;

    public Optional<User> getUserById(int id) {
        Optional<User> user = userService.assignGetRepository().findById(id);
        log.info("user id: {} ", user);
        return user;
    }

}
