package com.exalead.derangement_pfe.Service.UserService;

import com.exalead.derangement_pfe.Entity.ChangePasswordRequest;
import com.exalead.derangement_pfe.Entity.User;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

public interface IUserService {

     User addUser(User user);
     void deleteUser(Long idUser);
     User updateUser(User u);
     public List<User> getAllUsers();
     public User getUser(Long idUser);
     public Optional<User> getUserById(long idUser);

     public void changePassword(ChangePasswordRequest request, Principal connectedUser);

     public void affecterOffreAUtilisateurs(List<Long> userIds, Long idOffre);
}
