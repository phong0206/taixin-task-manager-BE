package io.github.phongnv.restful_spring_boot.domain.repositories;

import io.github.phongnv.restful_spring_boot.infrastruture.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

@Repository
public interface UserRepository extends JpaRepository<User, Serializable> {

}
