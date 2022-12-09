package com.springrest.springrest.services;

import java.util.List;
import java.util.Optional;

import com.springrest.springrest.entity.Course;

public interface CourseService 
{
  public List<Course> getCourses();
  
  public Course  getCourseById(long courseId);
  
  public Course addCourse(Course course);
  
  public Course updateCourse(Course course);
  
  public void deleteCourse(long parseLong);
}
