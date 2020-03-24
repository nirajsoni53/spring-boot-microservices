package com.rest.webservices.restwebservices.user.resource;

import com.rest.webservices.restwebservices.exception.UserNotFoundException;
import com.rest.webservices.restwebservices.user.dto.User;
import com.rest.webservices.restwebservices.user.service.UserService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/user")
public class UserResource {

    @Autowired
    private UserService userService;

    private static void accept(User value) {
        value.removeLinks().add(linkTo(methodOn(UserResource.class).findAll()).withSelfRel());
    }

    @ApiOperation(value = "View a list of available users", response = User[].class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved list"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    }
    )
    @GetMapping("/users")
    public ResponseEntity<?> findAll() {
        return ResponseEntity.ok().body(userService.findAll());
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<?> findOne(@PathVariable("id") long id) {
        Optional<User> user = userService.findOne(id);
        user.ifPresent(UserResource::accept);
        return ResponseEntity.ok().body(user
                .orElseThrow(() -> new UserNotFoundException("User Not Found with Id = " + id)));
    }

    @PostMapping("/users")
    public ResponseEntity<?> save(@Valid @RequestBody User user) {
        User savedUser = userService.save(user);
        return ResponseEntity
                .created(URI.create("/persons/" + savedUser.getId()))
                .body(savedUser);
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") long id) {
        userService.deleteById(id);
        return ResponseEntity.ok("User Deleted Successfully");
    }
}
