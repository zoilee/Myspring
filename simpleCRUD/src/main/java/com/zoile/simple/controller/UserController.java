package com.zoile.simple.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zoile.simple.mapper.UserMapper;
import com.zoile.simple.model.User;

/**
 * Handles requests for the application home page.
 */
@Controller
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserMapper userMapper;

    // 모든 사용자 목록 조회
    @GetMapping
    public String listUsers(Model model) {
        List<User> users = userMapper.getAllUsers();
        model.addAttribute("users", users);
        return "user-list";  // user-list.jsp를 반환
    }

    // 사용자 추가 폼 표시
    @GetMapping("/add")
    public String addUserForm(Model model) {
        model.addAttribute("user", new User());
        return "add-user";  // add-user.jsp를 반환
    }

    // 사용자 추가 처리
    @PostMapping("/add")
    public String addUser(@ModelAttribute User user) {
        userMapper.insertUser(user);
        return "redirect:/users";
    }

    // 사용자 수정 폼 표시
    @GetMapping("/edit/{id}")
    public String editUserForm(@PathVariable int id, Model model) {
        User user = userMapper.getUserById(id);
        model.addAttribute("user", user);
        return "edit-user";  // edit-user.jsp를 반환
    }

    // 사용자 수정 처리
    @PostMapping("/edit/{id}")
    public String updateUser(@PathVariable int id, @ModelAttribute User user) {
        user.setId(id);
        userMapper.updateUser(user);
        return "redirect:/users";
    }

    // 사용자 삭제
    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable int id) {
        userMapper.deleteUser(id);
        return "redirect:/users";
    }
}