package io.github.phongnv.restful_spring_boot.infrastruture.controllers;

import io.github.phongnv.restful_spring_boot.infrastruture.entities.User;
import io.github.phongnv.restful_spring_boot.infrastruture.services.UserService;
import io.github.phongnv.restful_spring_boot.usecases.UserUsecase;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@Builder
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
public class UserController {

    private final UserUsecase userUsecase;

    @GetMapping("/{id}")
    public Optional<User> getUserById(@PathVariable int id) {
        return this.userUsecase.getUserById(id);
    }
}
