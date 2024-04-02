package estudos.ProjetoCrud.controlllers;

import estudos.ProjetoCrud.domain.User;
import estudos.ProjetoCrud.dto.UserDTO;
import estudos.ProjetoCrud.repositories.UserRepositories;
import estudos.ProjetoCrud.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@Validated
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<User>createUser(@RequestBody UserDTO user){
        User newUser = userService.createUser(user);
        return new ResponseEntity<>(newUser, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<User>>getAllUsers(){
        List<User> users = userService.getAllUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User>getOneUser(Long id) throws Exception {
        User user = userService.findByIdUser(id);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }
}
