package io.github.phongnv.restful_spring_boot.infrastruture.services;

import io.github.phongnv.restful_spring_boot.infrastruture.entities.User;
import io.github.phongnv.restful_spring_boot.domain.repositories.UserRepository;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class UserService extends BaseService<User> {
    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    protected UserService(UserRepository userRepository) {
        super(userRepository);
    }

    @Override
    protected String getNotFoundMessage() {
        return "User not found.";
    }
}
