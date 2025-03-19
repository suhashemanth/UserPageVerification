package com.suhas.UserPageVerification.user;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class Controller {


    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    @GetMapping("/hello")
    public String sayHello()
    {
        return "Hello";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/addBook")
    public String addABook()
    {
        return "BookAdded";
    }

    @PreAuthorize("hasRole('USER')")
    @GetMapping("/readBook")
    public String readABook()
    {
        return "BookRead";
    }
}
