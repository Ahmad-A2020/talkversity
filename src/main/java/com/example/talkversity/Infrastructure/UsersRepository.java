package com.example.talkversity.Infrastructure;

import com.example.talkversity.Entities.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersRepository extends CrudRepository<Users,Long> {
    Users findUsersByUsername(String username);


}
