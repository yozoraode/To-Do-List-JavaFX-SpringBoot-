package com.defranto.VanillaToDo;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import javafx.collections.FXCollections;

@Service
public class UI {
	
	private static controller control;
	
	@Autowired
	public UI(controller controller) {
		this.control = controller;
	}
	
	public static void uiLogic(Stage stage) {
		
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
		
		ArrayList<Integer> IdList = new ArrayList<>();
		
		IdList.clear();
		
		for(entry entryx: control.getEntries()) {
			
			var listElement = new Label(entryx.getId() + ". " + entryx.getContent());
			list.getChildren().add(listElement);
			IdList.add(entryx.getId());
			
			
		}
		
		addButton.setOnAction(e -> {
			
			additionalPrompts.getChildren().clear();
			
			String textInput = textField.getText();
			control.addEntry(new entry(textInput));
			
			list.getChildren().clear();
			
			IdList.clear();
			
			for(entry entryx: control.getEntries()) {
				
				var listElement = new Label(entryx.getId() + ". " + entryx.getContent());
				list.getChildren().add(listElement);
				IdList.add(entryx.getId());
				
			}
			
		});
		
		editButton.setOnAction(e -> {
			
			if(IdList.size() > 0) {
				
				additionalPrompts.getChildren().clear();
				
				var spinnerPrompt = new Label("Which entry would you like to edit?");
				
				Spinner<Integer> spinner = new Spinner<>();
				
				SpinnerValueFactory<Integer> valueFactory = new SpinnerValueFactory.ListSpinnerValueFactory<>(FXCollections.observableArrayList(IdList));
		        spinner.setValueFactory(valueFactory);
				
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
					
					IdList.clear();
					
					for(entry entryx: control.getEntries()) {
						
						var listElement = new Label(entryx.getId() + ". " + entryx.getContent());
						list.getChildren().add(listElement);
						IdList.add(entryx.getId());
						
					}	
				});
			}
		});
		
		deleteButton.setOnAction(e -> {
			
			if(IdList.size() > 0) {
				
				additionalPrompts.getChildren().clear();
				
				var spinnerPrompt = new Label("Which entry would you like to delete?");
				
				Spinner<Integer> spinner = new Spinner<>();
				
				SpinnerValueFactory<Integer> valueFactory = new SpinnerValueFactory.ListSpinnerValueFactory<>(FXCollections.observableArrayList(IdList));
		        spinner.setValueFactory(valueFactory);
				
				var doneButton = new Button("Done");
				
				additionalPrompts.getChildren().addAll(spinnerPrompt, spinner, doneButton);
				
				doneButton.setOnAction(f -> {
					
					int spinnerValue = spinner.getValue();
					
					additionalPrompts.getChildren().clear();
					
					control.deleteEntry(spinnerValue);
					
					list.getChildren().clear();
					
					IdList.clear();
					
					for(entry entryx: control.getEntries()) {
						
						var listElement = new Label(entryx.getId() + ". " + entryx.getContent());
						list.getChildren().add(listElement);
						IdList.add(entryx.getId());
						
					}
					
				});
			}
		});
		
		var scene = new Scene(root, 400, 400);
		stage.setTitle("Springboot + JavaFX To Do List");
		stage.setScene(scene);
		stage.show();
	}
	
}
