package com.sdemir.demo.course;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

// CrudRepository<Topic,String>  = Course is object type and String is primary key type

public interface CourseRepository extends CrudRepository<Course,String>{
	
	 public List<Course> findByTopicId(String topicId); 

}
