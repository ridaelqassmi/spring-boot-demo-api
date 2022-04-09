package com.facebook.demo.controller;


import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.facebook.demo.entity.User;
import com.facebook.demo.repository.UserRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;



@RestController
@RequestMapping("/users")
@CrossOrigin

public class UserController {
	@Autowired
	UserRepository userRepository;
	
	
	@GetMapping
	public List<User> findAllUsers(){
		return userRepository.findAll();
	}
	
	public static String uploadDir = System.getProperty("user.dir")+"\\src\\main\\java\\com\\facebook\\demo\\images";
	
	@PostMapping
	public User saveUser(@RequestPart("file") MultipartFile datafile,@RequestParam(value="User") String user) throws IOException {
		System.out.println("the file name is "+datafile.getOriginalFilename()+"the content type"+datafile.getContentType());
		ObjectMapper objectMapper = new ObjectMapper();
		User user1 = null ;
		//turning json to a java class using objectMapper
		try {
			user1 = objectMapper.readValue(user, User.class);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//substring to get the last 4 caracteres of the uploaded file
		
		
		String filename=user1.getName()+"-"+user1.getPhone()+""+datafile.getOriginalFilename().substring(datafile.getOriginalFilename().length()-4);
		/*Path 
		 * Path p5 = Paths.get(System.getProperty("user.home"),"logs", "foo.log");
		 * C:\joe\logs\foo.log
		 * system.getProperty("user.home") give you the root path c:// 
		 * instead of writing something like this 
		 * p5 = "c:\\jeo\\logs\\foo.log"
		 * this method make it easier to make such thing 
		 * 
		 * */
		Path fileNameAndPath = Paths.get(uploadDir,filename);
		/*
		 * AND HERE IS A LIVE EXEMPLE Paths.get(uploadDir,filename) => uploadDir+"\\"+filename
		 * 
		 * */
		
		try {
			Files.write(fileNameAndPath, datafile.getBytes());
			/*
			 * Using the Files class, we can create, move, copy, and delete files and directories. It can also be used to read and write to a file:
			 * hear in the first place we just make the Path but nothing in it yet 
			 * so we used this method to write the bytes into the filename
			 * Files.copy would have a much simple thing but it is what it is 
			 * */
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		File file = new File(uploadDir+"\\"+filename);
		byte[] picInBytes = new byte[(int) file.length()];

		FileInputStream fileInputStream = new FileInputStream(file);
		
		fileInputStream.read(picInBytes);
		fileInputStream.close();
		
		
		user1.setImage(picInBytes);
		file.delete();
		return userRepository.save(user1);
		
		
	
	}
	@GetMapping("/search/{id}")
	public User findUserById(@PathVariable(value="id") Long Userid) {
		
		return userRepository.findUserById(Userid);
		
	}
	@GetMapping("/delete/{id}")
	public String deleteUser(@PathVariable(value="id") long Userid) {
		 userRepository.deleteById(Userid);
		 return "halo world";
		
		
	}
	@GetMapping("/contain/{input}")
	public List<User> findALLnameContain(@PathVariable(value="input") String input){
		
		return userRepository.contain(input);
	}
	
	
	@PostMapping("/edit/{id}")
	public User updateUser(@PathVariable(value="id") long id,@RequestBody User newUser) {
		System.out.println("the user send is "+newUser.toString());
		User user = userRepository.getById(id);
		System.out.println("the user FOUND is "+user.toString());
		user.setEmail(newUser.getName());
		user.setName(newUser.getEmail());
		user.setPhone(newUser.getPhone());
		user = userRepository.save(user);
		return user;
	}
	
	

}
