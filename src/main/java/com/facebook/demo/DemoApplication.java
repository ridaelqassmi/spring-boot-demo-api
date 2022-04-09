package com.facebook.demo;

import java.io.File;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.facebook.demo.controller.UserController;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		new File(UserController.uploadDir).mkdir();
		SpringApplication.run(DemoApplication.class, args);
	}
	

}

