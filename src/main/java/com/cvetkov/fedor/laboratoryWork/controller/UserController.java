package com.cvetkov.fedor.laboratoryWork.controller;

import com.cvetkov.fedor.laboratoryWork.dto.request.TicketRequest;
import com.cvetkov.fedor.laboratoryWork.dto.request.UserRequest;
import com.cvetkov.fedor.laboratoryWork.dto.response.TicketResponse;
import com.cvetkov.fedor.laboratoryWork.dto.response.UserResponse;
import com.cvetkov.fedor.laboratoryWork.dto.update.TicketUpdate;
import com.cvetkov.fedor.laboratoryWork.dto.update.UserUpdate;
import com.cvetkov.fedor.laboratoryWork.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/v1/user")
public class UserController {

    private final UserService userService;

    @GetMapping
    public Page<UserResponse> getAllUsers(@PageableDefault(size = 5) Pageable pageable) {
        return userService.getAllPage(pageable);
    }

    @GetMapping("/all-user")
    public List<UserResponse> getAllUsers() {
        return userService.getAllList();
    }

    @GetMapping("/{id}")
    public UserResponse getUserById(@PathVariable Long id) {
        return userService.findById(id);
    }

    @PostMapping
    public void addUser(@Valid @RequestBody UserRequest userRequest) {
        userService.save(userRequest);
    }

    @PutMapping
    public void updateUser(@Valid @RequestBody UserUpdate userUpdate) {
        userService.update(userUpdate);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
        userService.deleteById(id);
    }
}
