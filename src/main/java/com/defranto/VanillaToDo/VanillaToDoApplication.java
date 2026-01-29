package com.defranto.VanillaToDo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;

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
		ConfigurableApplicationContext context = SpringApplication.run(VanillaToDoApplication.class);
	}
	
	@Override
	public void start(Stage stage) {
		
		UI.uiLogic(stage);
		
	}
	public static void main(String[] args) {
		Application.launch(VanillaToDoApplication.class,args);
	}
	
}
