package com.springrest.springrest.controller;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.springrest.springrest.entity.Course;
import com.springrest.springrest.services.CourseService;

@RestController
@RequestMapping("/api/v1")
public class CourseController {
	@Autowired
	private CourseService courseService;

	@GetMapping("/courses")
	public List<Course> getCourses() {
		return this.courseService.getCourses();
	}

	@GetMapping("/courses/{courseId}")
	public Course getCourseById(@PathVariable Long courseId) {
		return  this.courseService.getCourseById(courseId);
		 
		 
	}

	@PostMapping("/courses")
	public ResponseEntity<Object> addCourse(@Valid @RequestBody Course course) {
		Course savedCourse= this.courseService.addCourse(course);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedCourse.getId()).toUri();
		return ResponseEntity.created(location).build();
	}

	@PutMapping("/courses")
	public Course updateCourse(@Valid @RequestBody Course course) {
		return this.courseService.updateCourse(course);
	}

	@DeleteMapping("/courses/{courseId}")
	public void deleteCourse(@PathVariable long courseId) {

		this.courseService.deleteCourse(courseId);

	}
}
