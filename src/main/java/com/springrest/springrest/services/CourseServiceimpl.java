package com.springrest.springrest.services;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.springrest.springrest.dao.CourseDao;
import com.springrest.springrest.dto.CourseDto;
import com.springrest.springrest.entity.Course;
import com.springrest.springrest.exception.CourseNotFoundException;


@Service
public class CourseServiceimpl implements CourseService {
	
	@Autowired
	private CourseDao courseDao;
	@Autowired
	private ModelMapper modelMapper;
	


	@Override
	public List<Course> getCourses(int pageNo, int pageSize) {
//        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
//		modelMapper.getConfiguration().setAmbiguityIgnored(true);
//		List<Course> courses= courseDao.findAll();
//		List<CourseDto> courseDto = courses.stream()
//				.map( course -> modelMapper.map(course, CourseDto.class)).collect(Collectors.toList());
//		
//		System.out.println(courseDto);
//		int pageNo =1;
//		int pageSize=5;
		Pageable p = PageRequest.of(pageNo, pageSize);
		Page<Course> pageCourse = courseDao.findAll(p);
		List<Course> courses = pageCourse.getContent();
		return courses;
	}

	@Override
	public Course getCourseById(long courseId) {
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		Course course=  courseDao.findById(courseId).orElse(null);
//		CourseDto courseDto = modelMapper.map(course, CourseDto.class);
//		System.out.println(courseDto);
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
