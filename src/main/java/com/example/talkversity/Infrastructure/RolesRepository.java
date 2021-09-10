package com.example.talkversity.Infrastructure;

import com.example.talkversity.Entities.Roles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.management.relation.Role;
import java.util.List;

@Repository
public interface RolesRepository extends JpaRepository<Roles,Long> {
    Roles findRolesByName(String role);

}
