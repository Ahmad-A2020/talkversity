package com.example.talkversity.Controller;

import com.example.talkversity.Entities.Course;
import com.example.talkversity.Infrastructure.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;

@Controller
public class AdminController {
    @Autowired
    CourseRepository courseRepository;

    /**
     * this response of this endpoint is return the all courses exist at the DB
     * @param m
     * @return
     */
    @GetMapping("/admin")
    public String adminPage(Model m){
        List<Course> courses= courseRepository.findAll();
        m.addAttribute("courses",courses);
        return "admin.html";

    }

    /**
     * create new course and save it at the database using the following data come with
     * the following params:
     * @param courseName
     * @param instructor
     * @param creditNumber
     * @param price
     * @param lectureDays
     * @return
     */

    @PostMapping("/addcourse")
    public RedirectView addCourse(
            @RequestParam String courseName,
            @RequestParam String instructor,
            @RequestParam Integer creditNumber,
            @RequestParam Integer price,
            @RequestParam String lectureDays
    ){
        Course course = new Course(courseName,instructor,creditNumber,price,lectureDays);
        courseRepository.save(course);
        return new RedirectView("/admin");
    }

    /**
     * the delete button create request that hold variable path
     * the id of the course at the database, on which it  used to track the course and delete it
     * from the database
     * @param id
     * @return
     */
    @GetMapping("/deleteCourse/{id}")
    public RedirectView deleteCourse(@PathVariable Long id){
        courseRepository.deleteById(id);

       return new RedirectView("/admin");

    }

}
