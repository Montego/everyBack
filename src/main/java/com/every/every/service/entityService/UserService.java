package com.every.every.service.entityService;

import com.every.every.entity.User;
import com.every.every.repository.UserRepository;
//import com.every.every.service.util.GeneratorOfUniqueAcceptCode;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

@Service
public class UserService{
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        User user = userRepository.findByUsername(username);
////        User user = userRepository.findByEmail();
//        if(user == null){
//            throw new UsernameNotFoundException("User not found");
//        }
//        return user;
//    }

    public boolean isUserExistInDB(String username, String email){
        User userByUsernameAndEmail = userRepository.findByUsernameAndEmail(username,email);
        return userByUsernameAndEmail != null;
    }


}
