package com.example.talkversity;

import com.example.talkversity.Entities.Roles;
import com.example.talkversity.Entities.Users;
import com.example.talkversity.Infrastructure.RolesRepository;
import com.example.talkversity.Infrastructure.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.sql.Date;
import java.util.List;

@SpringBootApplication
public class TalkversityApplication implements CommandLineRunner  {
	@Autowired
	BCryptPasswordEncoder bCryptPasswordEncoder;

	@Autowired
	private RolesRepository rolesRepository;

	@Autowired
	private UsersRepository usersRepository;

	public static void main(String[] args) {

		SpringApplication.run(TalkversityApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		if (rolesRepository.findAll().isEmpty()){

			// create roles and save them at the database (DB)
			Roles admin= new Roles("ADMIN") ;
			Roles student= new Roles("STUDENT") ;
			Roles mainAdmin= new Roles("MAIN_ADMIN") ;
			rolesRepository.saveAll(List.of(admin,student,mainAdmin));

			// create a default admin account
			Date date = new Date(1997,11,1);
			Users admin1= new Users ("Ahmad","Ali","Ali",date,"male","admin", bCryptPasswordEncoder.encode("1234567"));
			Roles admin1Role= rolesRepository.findRolesByName("ADMIN");
			admin1.addRole(admin1Role);
			usersRepository.save(admin1);

			// create a default main admin account
			Date date2 = new Date(1997,11,1);
			Users Mainadmin= new Users ("Ahmad","Ali","Ali",date2,"male","main_admin",bCryptPasswordEncoder.encode("12345"));
			Roles MainadminRole= rolesRepository.findRolesByName("MAIN_ADMIN");
			Mainadmin.addRole(MainadminRole);
			usersRepository.save(Mainadmin);

		}

	}
}
