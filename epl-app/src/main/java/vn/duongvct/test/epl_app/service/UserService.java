package vn.duongvct.test.epl_app.service;

import org.springframework.stereotype.Service;

import vn.duongvct.test.epl_app.domain.User;
import vn.duongvct.test.epl_app.domain.response.ResCreateUserDTO;
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
    
    
}
