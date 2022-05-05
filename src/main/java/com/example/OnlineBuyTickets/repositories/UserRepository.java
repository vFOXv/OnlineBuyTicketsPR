package com.example.OnlineBuyTickets.repositories;

import com.example.OnlineBuyTickets.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    //Обязательно Username это имя юзает Spring Security ----> findByUsername()
    User findByUsername(String username);

}
