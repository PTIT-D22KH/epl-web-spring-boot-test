package vn.duongvct.test.epl_app.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import vn.duongvct.test.epl_app.domain.User;
import vn.duongvct.test.epl_app.domain.response.ResCreateUserDTO;
import vn.duongvct.test.epl_app.domain.response.ResponseUserDTO;
import vn.duongvct.test.epl_app.repository.UserRepository;

@Service
public class UserService{
    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public boolean isEmailExists(String email) {
        return userRepository.existsByEmail(email);
    }

    
    public ResCreateUserDTO convertUserToResCreateUserDTO(User user) {
        ResCreateUserDTO resCreateUserDTO = new ResCreateUserDTO();
        resCreateUserDTO.setAddress(user.getAddress());
        resCreateUserDTO.setAge(user.getAge());
        resCreateUserDTO.setCreatedAt(user.getCreatedAt());
        resCreateUserDTO.setEmail(user.getEmail());
        resCreateUserDTO.setId(user.getId());
        resCreateUserDTO.setName(user.getName());
        resCreateUserDTO.setNation(user.getNation());
        return resCreateUserDTO;
    }

    
    public User handleSaveUser(User user) {
        return userRepository.save(user);
    }

    
    public User getUserByUsername(String email) {
        return this.userRepository.findByEmail(email);
    }

    
    public void updateUserToken(String token, String email) {
        User currentUser = this.getUserByUsername(email);
        if (currentUser != null) {
            currentUser.setRefreshtoken(token);
            this.userRepository.save(currentUser);
        }
    }

    
    public User getUserByRefreshTokenAndEmail(String refreshToken, String email) {
        return this.userRepository.findByRefreshtokenAndEmail(refreshToken, email);
    }

    public Optional<User> getUserById(Long id) {
        return this.userRepository.findById(id);
    }

    public ResponseUserDTO convertUserToResponseUserDTO(User user) {
        ResponseUserDTO responseUserDTO = new ResponseUserDTO();
        responseUserDTO.setId(user.getId());
        responseUserDTO.setName(user.getName());
        responseUserDTO.setEmail(user.getEmail());
        responseUserDTO.setAddress(user.getAddress());
        responseUserDTO.setAge(user.getAge());
        responseUserDTO.setCreatedAt(user.getCreatedAt());
        responseUserDTO.setGender(user.getGender());
        responseUserDTO.setNation(user.getNation());
        responseUserDTO.setUpdatedAt(user.getUpdatedAt());
        return responseUserDTO;
    }
    
    
}
