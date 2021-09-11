package com.example.talkversity.Controller;

import com.example.talkversity.Entities.Course;
import com.example.talkversity.Infrastructure.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class StudentsController {
    @Autowired
    CourseRepository courseRepository;

    @GetMapping("/courses")
    public String adminPage(Model m){
        List<Course> courses= courseRepository.findAll();
        m.addAttribute("courses",courses);
        return "courses.html";

    }
}
