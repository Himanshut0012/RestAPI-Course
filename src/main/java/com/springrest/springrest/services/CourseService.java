package com.springrest.springrest.services;

import java.util.List;

import com.springrest.springrest.dto.CourseDto;
import com.springrest.springrest.entity.Course;

public interface CourseService 
{
  public List<Course> getCourses(int pageNo, int pageSize);
  
  public Course getCourseById(long courseId);
  
  public Course addCourse(Course course);
  
  public Course updateCourse(Course course);
  
  public void deleteCourse(long parseLong);
}
