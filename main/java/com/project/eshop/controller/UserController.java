package com.project.eshop.controller;

import com.project.eshop.business.abstracts.UserService;
import com.project.eshop.core.utilities.results.DataResult;
import com.project.eshop.entities.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/all")
    public DataResult<List<UserDto>> getAllUsers() {
        return userService.getAll();
    }

    @GetMapping("/{id}")
    public DataResult<UserDto> getUserById(@PathVariable long id) {
        return userService.getById(id);
    }

    @PostMapping("/add")
    public DataResult<UserDto> addUser(@RequestBody UserDto userDto) {
        return userService.add(userDto);
    }

    @PutMapping("/update")
    public DataResult<UserDto> updateUser(@RequestBody UserDto userDto) {
        return userService.update(userDto);
    }

    @DeleteMapping("/delete/{id}")
    public DataResult<String> deleteUser(@PathVariable long id) {
        return userService.delete(id);
    }
}
