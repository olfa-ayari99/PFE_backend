package com.exalead.derangement_pfe.Controller;

import com.exalead.derangement_pfe.Entity.ChangePasswordRequest;
import com.exalead.derangement_pfe.Entity.User;
import com.exalead.derangement_pfe.Repository.UserRepository;
import com.exalead.derangement_pfe.Service.UserService.IUserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

@RestController
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/v1/user")
@Tag(name = "user")
public class UserController {

    @Autowired
   private IUserService userService;






    @PostMapping("/addUser")
    @ResponseBody
    public User addUser(@RequestBody User user){
        return userService.addUser(user);
    }

    @PutMapping("/updateUser")
    public User updateUser(@RequestBody User u){
        return userService.updateUser(u);
    }
    @DeleteMapping("/deleteUser/{idUser}")
    public void deleteUser(@PathVariable("idUser") Long idUser){
    userService.deleteUser(idUser);

    }

    @GetMapping("/getlUser/{idUser}")
    @ResponseBody
    public User getUser(@PathVariable("idUser") Long idUser){
        return userService.getUser(idUser);

    }

    @GetMapping("/getAllUsers")
    @ResponseBody
    public List<User> getAllUsers(){
        List<User> users=userService.getAllUsers();
        return users;
    }


    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable("id") long idUser) {
        Optional<User> user = userService.getUserById(idUser);
        return user.map(value -> ResponseEntity.ok().body(value)).orElseGet(() -> ResponseEntity.notFound().build());
    }


    @PatchMapping("/changePassword")
    public ResponseEntity<?> changePassword(
            @RequestBody ChangePasswordRequest request,
            Principal connectedUser
    ) {
        userService.changePassword(request, connectedUser);
        return ResponseEntity.ok().build();
    }

    @PostMapping ("/affecterOffre/{idUser}")
    public ResponseEntity<String> affecterOffreAUtilisateurs(
            @PathVariable Long idUser,
            @RequestBody List<Long> offreIds) {

        userService.affecterOffreAUtilisateurs(idUser, offreIds);

        return ResponseEntity.ok("L'offre a été affectée avec succès aux utilisateurs.");
    }



    @GetMapping("/api/users")
    public Page<User> getUsers(@RequestParam(defaultValue = "0") int page,
                               @RequestParam(defaultValue = "10") int size) {
        return userService.getUsers(page, size);
    }




}
