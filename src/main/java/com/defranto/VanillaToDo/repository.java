package com.defranto.VanillaToDo;

import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface repository extends ListCrudRepository<entry, Integer>{
	 
}
