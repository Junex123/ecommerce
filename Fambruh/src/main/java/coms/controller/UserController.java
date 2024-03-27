package coms.controller;

import java.net.URI;
import java.util.HashSet;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import coms.service.UserService;
import coms.model.*;
import coms.model.user.Role;
import coms.model.user.User;
import coms.model.user.UserRole;


@RestController
@CrossOrigin(origins = "*")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	//init admin user
	@PostConstruct
	public void createAdmin(){
		User foundUser = userService.getByUsername("fambruh@army");
		if(foundUser == null) {
		    User admin = new User();
		    admin.setUsername("fambruh@army");
		    admin.setPassword("admin123");
		    admin.setEmail("shaikhjunaidgh@gmail.com"); // Set the email
		    
		    Role role = new Role();
		    role.setRoleId(101L);
		    role.setRoleName("ADMIN");
		    UserRole ur = new UserRole();
		    ur.setUser(admin);
		    ur.setRole(role);
		    Set<UserRole> userRole = new HashSet<>();
		    userRole.add(ur);
		    User adminCreated = this.userService.createUser(admin, userRole);
		    System.out.println("Admin username: "+adminCreated.getUsername());
		}
		else if(foundUser.getUsername().equals("fambruh@army")) {
			System.out.println("ATTENTION: fambruh@army ALREADY CREATED");
		}
	}

	
	//create new user
	@PostMapping("/user/signup")
	public ResponseEntity<?> createNewUser(@Valid @RequestBody User user){
		Role role = new Role();
		role.setRoleId(102L);
		role.setRoleName("USER");
		UserRole ur = new UserRole();
		ur.setUser(user);
		ur.setRole(role);
		Set<UserRole> userRole = new HashSet<>();
		userRole.add(ur);
		if(this.userService.getByUsername(user.getUsername())!=null) {
			System.out.println("Username already exists!");
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}else {
			User newUser = this.userService.createUser(user, userRole);
			URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newUser.getUserId()).toUri();
			return ResponseEntity.created(location).build();
		}
	}
	

}
