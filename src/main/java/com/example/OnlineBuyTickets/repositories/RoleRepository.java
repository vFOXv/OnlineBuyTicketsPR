package com.example.OnlineBuyTickets.repositories;

import com.example.OnlineBuyTickets.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
}
