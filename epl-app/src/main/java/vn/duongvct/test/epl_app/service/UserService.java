package vn.duongvct.test.epl_app.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import vn.duongvct.test.epl_app.domain.User;
import vn.duongvct.test.epl_app.domain.request.RequestRegisterUserDTO;
import vn.duongvct.test.epl_app.domain.response.ResponseCreateUserDTO;
import vn.duongvct.test.epl_app.domain.response.ResponseUpdateUserDTO;
import vn.duongvct.test.epl_app.domain.response.ResponseUserDTO;
import vn.duongvct.test.epl_app.domain.response.ResultPaginationDTO;
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

    
    public ResponseCreateUserDTO convertUserToResCreateUserDTO(User user) {
        ResponseCreateUserDTO resCreateUserDTO = new ResponseCreateUserDTO();
        resCreateUserDTO.setCreatedAt(user.getCreatedAt());
        resCreateUserDTO.setEmail(user.getEmail());
        resCreateUserDTO.setId(user.getId());
        resCreateUserDTO.setName(user.getName());
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
        responseUserDTO.setCreatedAt(user.getCreatedAt());
        responseUserDTO.setUpdatedAt(user.getUpdatedAt());
        return responseUserDTO;
    }
    
    public void handleDeleteUser(Long id) {
        this.userRepository.deleteById(id);
    }

    public User handleUpdateUser(User user) {
        User currentUser = this.userRepository.findById(user.getId()).get();
        if (currentUser != null) {
            currentUser.setName(user.getName());
        }
        return this.userRepository.save(currentUser);
    }

    public ResponseUpdateUserDTO convertUserToResponseUpdateUserDTO(User user) {
        ResponseUpdateUserDTO responseUpdateUserDTO = new ResponseUpdateUserDTO();
        responseUpdateUserDTO.setId(user.getId());
        responseUpdateUserDTO.setEmail(user.getEmail());
        responseUpdateUserDTO.setName(user.getName());
        responseUpdateUserDTO.setUpdatedAt(user.getUpdatedAt());
        responseUpdateUserDTO.setUpdatedBy(user.getUpdatedBy());
        return responseUpdateUserDTO;
    }
    public ResultPaginationDTO fetchAllUsers(Specification<User> spec, Pageable pageable) {
        Page<User> pageUser = this.userRepository.findAll(pageable);

        ResultPaginationDTO result = new ResultPaginationDTO();
        ResultPaginationDTO.Meta meta = new ResultPaginationDTO.Meta();

        meta.setPage(pageable.getPageNumber() + 1);
        meta.setPageSize(pageable.getPageSize());
        meta.setPages(pageUser.getTotalPages());
        meta.setTotal(pageUser.getTotalElements());

        result.setMeta(meta);

        //convert
        List<ResponseUserDTO> list = pageUser.getContent()
                                    .stream().map(item -> this.convertUserToResponseUserDTO(item))
                                    .collect(Collectors.toList());
        result.setResult(list);
        return result;
    }
    public User convertRequestRegisterDTOtoUser(RequestRegisterUserDTO requestRegisterUserDTO) {
        User user = new User();
        user.setEmail(requestRegisterUserDTO.getEmail());
        user.setName(requestRegisterUserDTO.getName());
        user.setPassword(requestRegisterUserDTO.getPassword());
        return user;
    }
}
