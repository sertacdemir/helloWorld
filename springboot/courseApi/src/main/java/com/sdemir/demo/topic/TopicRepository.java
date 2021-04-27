package com.sdemir.demo.topic;

import org.springframework.data.repository.CrudRepository;

// CrudRepository<Topic,String>  = Topic is object type and String is primary key type

public interface TopicRepository extends CrudRepository<Topic,String>{
	
	 

}
