package CrudApplication.Personal_Application.controllers;

import CrudApplication.Personal_Application.models.Role;
import CrudApplication.Personal_Application.models.User;
import CrudApplication.Personal_Application.models.UserDetails;
import CrudApplication.Personal_Application.models.UserRole;
import CrudApplication.Personal_Application.repositories.DetailsRepository;
import CrudApplication.Personal_Application.repositories.RoleRepository;
import CrudApplication.Personal_Application.repositories.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Slf4j
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    DetailsRepository detailsRepository;


    @PostMapping("/users/createUsers")
    public ResponseEntity<UserDetails> createUsers(@RequestBody UserDetails users) {
        try {
            UserDetails newUser = new UserDetails();
            newUser.setName(users.getName());
            newUser.setAge(users.getAge());
            newUser.setAddress(users.getAddress());
            newUser.setEmail(users.getEmail());
            newUser.setPhoneNumber(users.getPhoneNumber());
            newUser.setGender(users.getGender());

            UserDetails savedUser = detailsRepository.save(newUser);

            return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @GetMapping("/users")
    public ResponseEntity<List<UserDetails>> getUsersDetails(@RequestParam(required = false) String name) {
        try {
            List<UserDetails> details = new ArrayList<UserDetails>();

            if (name == null)
                detailsRepository.findAll().forEach(details::add);
            else
                detailsRepository.findByName(name).forEach(details::add);

            if (details.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(details, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<UserDetails> getUsers(@PathVariable("id") String id) {
        Optional<UserDetails> usersData = detailsRepository.findById(id);

        if (usersData.isPresent()) {
            return new ResponseEntity<>(usersData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/users/{id}")
    public ResponseEntity<UserDetails> updateUsers(@PathVariable("id") String id, @RequestBody UserDetails user) {
        Optional<UserDetails> userData = detailsRepository.findById(id);

        if (userData.isPresent()) {
            UserDetails _user = userData.get();
            _user.setName(user.getName());
            _user.setEmail(user.getEmail());
            _user.setAge(user.getAge());
            _user.setPhoneNumber(user.getPhoneNumber());
            return new ResponseEntity<>(detailsRepository.save(_user), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/user/{id}")
    public ResponseEntity<HttpStatus> deleteUsers(@PathVariable("id") String id) {

        try {
            detailsRepository.deleteById(id);

            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
