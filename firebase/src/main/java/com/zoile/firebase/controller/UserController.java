package com.zoile.firebase.controller;

import java.util.List;
import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.google.cloud.firestore.Firestore;
import com.zoile.firebase.dao.UserDao;
import com.zoile.firebase.dto.User;

@Controller
@RequestMapping("/users")
public class UserController {
	
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private Firestore db;
	
	@GetMapping("/create")
	public String createForm() {
		return "create";
	}
	
	@PostMapping("/create")
	public String createPost(
			@RequestParam String id,
			@RequestParam String name,
			@RequestParam String title,
			@RequestParam String content){
		User user = new User();
		user.setId(id);
		user.setName(name);
		user.setTitle(title);
		user.setContent(content);
		
		try {
			userDao.createUser(user);
		} catch (ExecutionException e) {
			
			e.printStackTrace();
		} catch (InterruptedException e) {
			
			e.printStackTrace();
		}
		return "redirect:/";
	}
	@GetMapping("/list")
	public String listUsers(Model model) {
		try {
			List<User> users = userDao.getAllUsers();
			model.addAttribute("users",users);
		} catch (ExecutionException e) {
			
			e.printStackTrace();
		} catch (InterruptedException e) {
			
			e.printStackTrace();
		}
		return "list";
	}
	
	@GetMapping("/view/{id}")
	public String getUserbyId(@PathVariable String id, Model model) {
		try {
			User user = userDao.getUser(id);
			model.addAttribute("user", user);
		} catch (ExecutionException | InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "view";
	}
	
	@GetMapping("/edit/{id}")
	public String getEditById(@PathVariable String id, Model model) {
		try {
			User user = userDao.getUser(id);
			model.addAttribute("user",user);
		} catch (ExecutionException | InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "edit";
	}
	
	@PostMapping("/edit/{id}")
	public String postEditById(
			@RequestParam String id,
			@RequestParam String name,
			@RequestParam String title,
			@RequestParam String content) {
		
		User user = new User();
		user.setId(id);
		user.setName(name);
		user.setTitle(title);
		user.setContent(content);
		try {
			userDao.updateUser(user);
		}catch (ExecutionException | InterruptedException e) {
			e.printStackTrace();
		}
		return "/view"+id;
	}
	
	
	
	
	@GetMapping("/delete/{id}")
	public String deleteById(@PathVariable String id) {
		try {
			userDao.deleteUserById(id);
		} catch (ExecutionException | InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "redirect:/users/list";
	}
}
