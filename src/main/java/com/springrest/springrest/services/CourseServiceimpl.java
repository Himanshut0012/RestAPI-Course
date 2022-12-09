package com.springrest.springrest.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springrest.springrest.dao.CourseDao;
import com.springrest.springrest.entity.Course;
import com.springrest.springrest.exception.CourseNotFoundException;

@Service
public class CourseServiceimpl implements CourseService {
	
	@Autowired
	private CourseDao courseDao;
	


	@Override
	public List<Course> getCourses() {
		
		return courseDao.findAll();
	}

	@Override
	public Course getCourseById(long courseId) {
		Course course=  courseDao.findById(courseId).orElse(null);
		 if(course==null)
				throw new CourseNotFoundException("course id "+courseId+" not present");
		 return course;
		
		
			

	}

	@Override
	public Course addCourse(Course course) {
		
		courseDao.save(course);
		return course;
	}

	@Override
	public Course updateCourse(Course course) {

		courseDao.save(course);
		return course;
	}

	@Override
	public void deleteCourse(long parseLong) 
	{
	Course entity = courseDao.findById(parseLong).orElse(null);
	if(entity==null)
		throw new CourseNotFoundException("course id "+parseLong+" not present");
	courseDao.delete(entity);
	}

}
