package com.example.talkversity.Controller;

import com.example.talkversity.Entities.Users;
import com.example.talkversity.Infrastructure.RolesRepository;
import com.example.talkversity.Infrastructure.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.WebAttributes;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.Date;
import java.util.ArrayList;

@Controller
public class MainController {
    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    UsersRepository userRepository;

    @Autowired
    RolesRepository rolesRepository;

    // home page endpoint

    @GetMapping("/home")
    public String homePage(){
        return "home.html";
    }

    @GetMapping("/")
    public String homePage2(){
        return "home.html";
    }

    // login
    @GetMapping("/login")
    public String logIn(){
        return "login.html";
    }

    @GetMapping("/signup")
    public String getSignupPage(Model m){
        String [] faculties=  {"Engineering","Medicine", "Nursing","Pharmacy","Agriculture","Science and Arts",
                "Computer and Information Technology", "Applied Medical Sciences",  "Architecture and Design"
               };
        m.addAttribute("faculties",faculties) ;
        return "signup";
    }

    // create new student account (role STUDENT),
    // as there is one admin, the account set up at the command runner
    // if there is need to add more than one admin then another role with main-admin will
    // be allowed to reach such feature

    @PostMapping("/signup")
    public RedirectView regester(@RequestParam String firstname,
                                  @RequestParam String midname,
                                  @RequestParam String lastname,
                                  @RequestParam String username,
                                  @RequestParam String password,
                                  @RequestParam Date dateOfBirth,
                                  @RequestParam String gender,
                                  @RequestParam String faculty){
        Users newUser = new Users(firstname,midname,lastname,dateOfBirth,gender,faculty,username,bCryptPasswordEncoder.encode(password));
        newUser.addRole(rolesRepository.findRolesByName("STUDENT"));
        userRepository.save(newUser);

        UsernamePasswordAuthenticationToken authentication= new UsernamePasswordAuthenticationToken(newUser,null,new ArrayList<>());
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return new RedirectView("/home");
    }

    // catch the log in errors
    @GetMapping("/login-error")
    public String login(HttpServletRequest request, Model model) {
        HttpSession session = request.getSession(false);
        String errorMessage = null;
        if (session != null) {
            AuthenticationException ex = (AuthenticationException) session
                    .getAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
            if (ex != null) {
                errorMessage = ex.getMessage();
            }
        }
        model.addAttribute("errorMessage", errorMessage);
        return "login";
    }

}
