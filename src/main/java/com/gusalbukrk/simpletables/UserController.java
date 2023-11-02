package com.gusalbukrk.simpletables;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/users")
public class UserController {
  private UserRepository userRepository;

  public UserController(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @GetMapping("")
  public List<User> getUsers() {
    Iterable<User> iterable = userRepository.findAll();
    List<User> list = StreamSupport.stream(iterable.spliterator(), false).collect(Collectors.toList());
    return list;
  }

  @PostMapping("")
  public User createUser(@RequestBody User user) {
    userRepository.save(user);
    return user;
  }

  @GetMapping("/find/{email}")
  public User findUserByEmail(@PathVariable String email) {
    return userRepository.findById(email).orElseThrow(
      () -> new ResponseStatusException(HttpStatus.NOT_FOUND)
    );
  }
}
