package com.springrest.springrest.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.springrest.springrest.dto.CourseDto;
import com.springrest.springrest.entity.Course;
import com.springrest.springrest.services.CourseService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/api/v1")
//@ApiIgnore        // ignore controller
public class CourseController {
	@Autowired
	private CourseService courseService;

	@GetMapping("/courses")
//	@ApiIgnore                                 // ignore that api , did show on ui page
	public List<CourseDto> getCourses(
			@RequestParam(value = "pageNo",defaultValue = "0",required = false) int pageNo,
			@RequestParam(value = "pageSize", defaultValue = "10",required = false) int pageSize ) {
		return this.courseService.getCourses(pageNo, pageSize);
	}

	@GetMapping("/courses/{courseId}")
	@ApiOperation(value = "Get a course by id", notes = "Returns a course as per the id")
	@ApiResponses(value = {
	  @ApiResponse(code = 200, message = "Successfully retrieved"),
	  @ApiResponse(code = 404, message = "Not found - The product was not found")
	})
//	public EntityModel<	CourseDto> getCourseById(@PathVariable Long courseId) {
//		CourseDto course = this.courseService.getCourseById(courseId);
//		EntityModel<CourseDto> resource = EntityModel.of(course);
//		WebMvcLinkBuilder link=WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getCourses(0, 10)); 
//		resource.add(link.withRel("all-courses")); 
//		return resource;
//	}
	public CourseDto getCourseById(@PathVariable Long courseId) {
		CourseDto course = this.courseService.getCourseById(courseId);
		EntityModel<CourseDto> resource = EntityModel.of(course);
		WebMvcLinkBuilder link=WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getCourses(0, 10)); 
		resource.add(link.withRel("all-courses")); 
		return course;
	}

	@PostMapping("/courses")
	@ApiOperation(value = "add course", notes = "add course all detials")
	@ApiResponses(value = {
			@ApiResponse(code =201, message = "successfully record created"),
			@ApiResponse(code = 500, message = "Internal server error"),
			@ApiResponse(code=406,message = "bad request")
	})
	public ResponseEntity<Object> addCourse(@Valid @RequestBody Course course) {
		Course savedCourse = this.courseService.addCourse(course);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(savedCourse.getId()).toUri();
		return ResponseEntity.created(location).build();
	}

	@PutMapping("/courses")
	@ApiOperation(value = "update course", notes = "update course detials")
	@ApiResponses(value = {
			@ApiResponse(code =201, message = "successfully record created"),
			@ApiResponse(code = 500, message = "Internal server error"),
			@ApiResponse(code=406,message = "bad request")
	})
	public Course updateCourse(@Valid @RequestBody Course course) {
		return this.courseService.updateCourse(course);
	}

	@DeleteMapping("/courses/{courseId}")
	@ApiOperation(value = "delete mapping" )
//	@ApiOperation(value = "delete mapping" , hidden = true)
	public void deleteCourse(@PathVariable long courseId) {

		this.courseService.deleteCourse(courseId);

	}
	
	@Value("${file.upload-dir}")
	String FILE_DIR;
	@PostMapping("/fileUpload")
	public ResponseEntity<Object> fileUploadApi(@RequestParam("File") MultipartFile file) throws IOException{
		File myFile = new File(FILE_DIR+file.getOriginalFilename());
		myFile.createNewFile();
		FileOutputStream fos = new FileOutputStream(myFile);
		fos.write(file.getBytes());
		fos.close();
		return new ResponseEntity<Object>("this file upload successfully",HttpStatus.OK);
	}
}
