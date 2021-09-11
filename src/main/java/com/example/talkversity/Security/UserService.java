package com.example.talkversity.Security;

import com.example.talkversity.Entities.Users;
import com.example.talkversity.Infrastructure.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {
    @Autowired
    private UsersRepository usersRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users user = usersRepository.findUsersByUsername(username);
        System.out.println("username: "+user.getUsername());
        System.out.println("Authority: "+user.getAuthorities().toString());

        if (user == null){
            System.out.println("user not found");
            throw new UsernameNotFoundException(username+ " not found");
        }
        return user;
    }


}
