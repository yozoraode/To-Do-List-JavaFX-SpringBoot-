package com.defranto.VanillaToDo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

@SpringBootApplication
public class VanillaToDoApplication extends Application {

	@Override
	public void init() throws Exception {
		SpringApplication.run(VanillaToDoApplication.class);
	}
	
	@Override
	public void start(Stage stage) {
		
		var root = new VBox(10);
		root.setPadding(new Insets(20, 20, 20, 20));
		
		var buttonRow = new HBox(10);
		var list = new VBox(10);
		var additionalPrompts = new VBox(10);
		
		var mainPrompt = new Label("Type Something...");
		var textField = new TextField();
		
		var addButton = new Button("Add");
		var editButton = new Button("Edit");
		var deleteButton = new Button("Delete");
		
		root.getChildren().addAll(mainPrompt, textField, buttonRow, additionalPrompts, list);
		
		buttonRow.getChildren().addAll(addButton, editButton, deleteButton);
		
		controller control = new controller();
		
		addButton.setOnAction(e -> {
			
			additionalPrompts.getChildren().clear();
			
			String textInput = textField.getText();
			control.addEntry(textInput);
			
			list.getChildren().clear();
			
			for(entry entryx: control.getEntries()) {
				
				var listElement = new Label(entryx.getId() + ". " + entryx.getContent());
				list.getChildren().add(listElement);
				
			}
			
		});
		
		editButton.setOnAction(e -> {
			
			additionalPrompts.getChildren().clear();
			
			Spinner<Integer> spinner;
			
			if(control.getEntries().size() < 1) {
				spinner = new Spinner<>(0, 0, 0, 0);
			}
			else if(control.getEntries().size() == 1) {
				spinner = new Spinner<>(1, 1, 1, 0);
			}
			else {
				spinner = new Spinner<>(1, control.getEntries().size(), 1, 1);
			}
			
			var spinnerPrompt = new Label("Which entry would you like to edit?");
			
			var editPrompt = new Label("Edit:");
			
			var editField = new TextField();
			
			var doneButton = new Button("Done");
			
			additionalPrompts.getChildren().addAll(spinnerPrompt, spinner, editPrompt, editField, doneButton);
			
			doneButton.setOnAction(f -> {
				
				int spinnerValue = spinner.getValue();
				
				String editedEntry = editField.getText();
				
				additionalPrompts.getChildren().clear();
				
				control.editEntry(spinnerValue, editedEntry);
				
				list.getChildren().clear();
				
				for(entry entryx: control.getEntries()) {
					
					var listElement = new Label(entryx.getId() + ". " + entryx.getContent());
					list.getChildren().add(listElement);
					
				}
				
			});
			
		});
		
		deleteButton.setOnAction(e -> {
			
			additionalPrompts.getChildren().clear();
			
			Spinner<Integer> spinner;
			
			if(control.getEntries().size() < 1) {
				spinner = new Spinner<>(0, 0, 0, 0);
			}
			else if(control.getEntries().size() == 1) {
				spinner = new Spinner<>(1, 1, 1, 0);
			}
			else {
				spinner = new Spinner<>(1, control.getEntries().size(), 1, 1);
			}
			
			var spinnerPrompt = new Label("Which entry would you like to delete?");
			
			var doneButton = new Button("Done");
			
			additionalPrompts.getChildren().addAll(spinnerPrompt, spinner, doneButton);
			
			doneButton.setOnAction(f -> {
				
				int spinnerValue = spinner.getValue();
				
				additionalPrompts.getChildren().clear();
				
				control.deleteEntry(spinnerValue);
				
				list.getChildren().clear();
				
				for(entry entryx: control.getEntries()) {
					
					var listElement = new Label(entryx.getId() + ". " + entryx.getContent());
					list.getChildren().add(listElement);
					
				}
				
			});
		});
		
		var scene = new Scene(root, 400, 400);
		stage.setTitle("Springboot + JavaFX To Do List");
		stage.setScene(scene);
		stage.show();
		
	}
	public static void main(String[] args) {
		Application.launch(VanillaToDoApplication.class,args);
	}

}
