package vn.duongvct.test.epl_app.service.impl;

import org.springframework.stereotype.Service;

import vn.duongvct.test.epl_app.domain.User;
import vn.duongvct.test.epl_app.domain.response.ResCreateUserDTO;
import vn.duongvct.test.epl_app.repository.UserRepository;
import vn.duongvct.test.epl_app.service.UserService;

@Service
public class UserServiceImpl implements UserService{
    private UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public boolean isEmailExists(String email) {
        return userRepository.existsByEmail(email);
    }

    @Override
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

    @Override
    public User handleSaveUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public User getUserByUsername(String email) {
        return this.userRepository.findByEmail(email);
    }
    
    
}
