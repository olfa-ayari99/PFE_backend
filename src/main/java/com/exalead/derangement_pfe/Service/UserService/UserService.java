package com.exalead.derangement_pfe.Service.UserService;

import com.exalead.derangement_pfe.Entity.*;
import com.exalead.derangement_pfe.Exceptions.UnauthorizedUserException;
import com.exalead.derangement_pfe.Repository.OffreRepository;
import com.exalead.derangement_pfe.Repository.TokenRepository;
import com.exalead.derangement_pfe.Repository.UserRepository;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


import java.security.Principal;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

@Service
@Slf4j
@AllArgsConstructor
public class UserService implements IUserService {

        @Autowired
        UserRepository userRepository;
        @Autowired
        OffreRepository offreRepository;
        @Autowired
        TokenRepository tokenRepository;


        private final PasswordEncoder passwordEncoder;





        public void changePassword(ChangePasswordRequest request, Principal connectedUser) {

                var user = (User) ((UsernamePasswordAuthenticationToken) connectedUser).getPrincipal();

                // check if the current password is correct
                if (!passwordEncoder.matches(request.getCurrentPassword(), user.getPassword())) {
                        throw new IllegalStateException("Wrong password");
                }
                // check if the two new passwords are the same
                if (!request.getNewPassword().equals(request.getConfirmationPassword())) {
                        throw new IllegalStateException("Password are not the same");
                }

                // update the password
                user.setPassword(passwordEncoder.encode(request.getNewPassword()));

                // save the new password
                userRepository.save(user);
        }

        @Override
        public User addUser(User user){
                User u =userRepository.save(user);
                return u;
        }


        @Override
        @Transactional
        public void deleteUser(Long idUser){
                tokenRepository.deleteByUserIdUser(idUser);
                userRepository.deleteById(idUser);
        }

        @Override
        public User updateUser(User u){
                return userRepository.save(u);
        }

        @Override
        public List<User> getAllUsers(){
                List<User> users= userRepository.findAll();
                return users;
        }

        @Override
        public User getUser(Long idUser){
                User u = userRepository.findById(idUser).orElse(null);
                return u;
        }

        public Optional<User> getUserById(long idUser) {
                return userRepository.findById(idUser);
        }

        @Override
        public void affecterOffreAUtilisateurs(Long idUser, List<Long> offreIds) {
                User u = userRepository.findById(idUser).orElseThrow(() -> new RuntimeException("User non trouvé"));
                Set<Offre> offres = new HashSet<>();
                for (Long idOffre : offreIds) {
                        Offre o = offreRepository.findById(idOffre).orElseThrow(() -> new RuntimeException("Offre non trouvé"));
                        offres.add(o);
                }
                u.getOffres().addAll(offres);
                userRepository.save(u);
        }



        public Page<User> getUsers(int page, int size) {
                Pageable pageable = PageRequest.of(page, size);
                return userRepository.findAll(pageable);
        }
}





