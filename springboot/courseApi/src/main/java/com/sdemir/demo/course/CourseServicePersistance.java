package com.sdemir.demo.course;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CourseServicePersistance {
	
	@Autowired
	private CourseRepository courseRepository;
	
	
	public List<Course> getAllCourses(String topicId){
		List<Course> topics = new ArrayList<>();
		courseRepository.findByTopicId(topicId)
				.forEach(topics::add);
		return topics;
	}
	
	public Course getCourse(String id) {
		return courseRepository.findById(id).orElse(null);
	}

	public void addCourse(Course topic) {
		courseRepository.save(topic);
		
	}

	public void updateCourse(Course topic) {
		courseRepository.save(topic);
	}

	public void deleteCourse(String id) {
		courseRepository.deleteById(id);
	}


}