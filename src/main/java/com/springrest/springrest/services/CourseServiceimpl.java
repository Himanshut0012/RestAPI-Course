package com.springrest.springrest.services;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.springrest.springrest.dao.CourseDao;
import com.springrest.springrest.entity.Course;

@Service
public class CourseServiceimpl implements CourseService {
	
//	List<Course>list;
	@Autowired
	private CourseDao courseDao;
	
	public CourseServiceimpl()
	{
//		list = new ArrayList<>();
//		list.add(new Course(125,"Java Core","Basic Java"));
//		list.add(new Course(126,"Rest API","Create Rest API"));

	}

	@Override
	public List<Course> getCourses() {
		
		return courseDao.findAll();
	}

	@Override
	public Course getCourse(long courseId) {
		
//		Course c = null;
//		for(Course course:list)
//		{
//			if(course.getId()==courseId)
//			{
//				c = course;
//				break;
//			}
//		}
		return courseDao.getOne(courseId);
	}

	@Override
	public Course addCourse(Course course) {
		
//		list.add(course);
		courseDao.save(course);
		return course;
	}

	@Override
	public Course updateCourse(Course course) {
//		list.forEach(e ->{
//			if (e.getId()==course.getId())
//			{
//				e.setTitle(course.getTitle());
//				e.setDescription(course.getDescription());
//			}
//		});
		courseDao.save(course);
		return course;
	}

	@Override
	public void deleteCourse(long parseLong) 
	{
	Course entity = courseDao.getOne(parseLong);
	courseDao.delete(entity);
	}

}
