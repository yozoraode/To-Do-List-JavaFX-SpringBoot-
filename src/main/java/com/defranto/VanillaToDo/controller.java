package com.defranto.VanillaToDo;

import java.util.ArrayList;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class controller {
	
	private static ArrayList<entry> entries = new ArrayList<>();
	
	@GetMapping("/entries")
	public ArrayList<entry> getEntries(){
		
		return entries;
		
	}
	
	@PostMapping("/entries")
	public void addEntry(@RequestParam(name = "new-entry") String contents) {
		
		try {
			entry newEntry = new entry(entries.size()+1, contents);
			entries.add(newEntry);
		}
		catch(Exception e) {
			System.out.println("Something went wrong");
		}
		
	}
	
	@PutMapping("/entries/{entrynumber}")
	public void editEntry(@PathVariable int entrynumber, @RequestParam(name = "new-entry") String newContents) {
		
		try {
			entries.get(entrynumber-1).setContent(newContents);
		}
		catch (Exception e) {
			System.out.println("Something went wrong");
		}
		
	}
	
	@DeleteMapping("/entries/{entrynumber}")
	public void deleteEntry(@PathVariable int entrynumber) {
		
		try {
			entries.remove(entrynumber-1);
			
			int count = 1;
			
			for(entry entryx: entries) {
				
				entryx.setId(count);
				count++;
				
			}
		}
		catch (Exception e) {
			System.out.println("Something went wrong");
		}
	}
}
