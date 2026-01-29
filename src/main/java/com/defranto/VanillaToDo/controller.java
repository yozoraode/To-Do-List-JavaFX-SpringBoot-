package com.defranto.VanillaToDo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
public class controller {
	
	//private static ArrayList<entry> entries = new ArrayList<>();
	private final repository repository;
	
	@Autowired
	public controller(repository repository) {
		this.repository = repository;
	}
	
	@GetMapping("/entries")
	public List<entry> getEntries(){
		
		return repository.findAll();
		
	}
	
	@PostMapping("/entries")
	public void addEntry(@RequestBody entry entry) {
		
		repository.save(entry);
		
	}
	
	@PutMapping("/entries/{entrynumber}")
	public void editEntry(@PathVariable int entrynumber, @RequestParam String newContent) {
		try {
			if(!repository.existsById(entrynumber)) {
				throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Content not Found");
			}
			entry entry = repository.findById(entrynumber).orElseThrow(() -> new RuntimeException("entry not found"));
			
			entry.setContent(newContent);
			
			repository.save(entry);
		}
		catch (Exception e){
			System.out.println("Something went wrong");
		}
	}
	
	@DeleteMapping("/entries/{entrynumber}")
	public void deleteEntry(@PathVariable int entrynumber) {
		
		try {
			repository.deleteById(entrynumber);
		}
		catch (Exception e) {
			System.out.println("Something went wrong");
		}
	}
}
