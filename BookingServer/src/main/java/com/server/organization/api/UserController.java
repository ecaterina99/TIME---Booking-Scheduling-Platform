package com.server.organization.api;

import com.server.organization.application.CreateUserCommand;
import com.server.organization.application.CreateUserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final CreateUserService createUserService;

    public UserController(CreateUserService createUserService) {
        this.createUserService = createUserService;
    }

    @PostMapping
    public int register(@RequestBody CreateUserCommand command) {
        return createUserService.createUser(command);
    }
}