package vn.duongvct.test.epl_app.service;

import vn.duongvct.test.epl_app.domain.User;
import vn.duongvct.test.epl_app.domain.response.ResCreateUserDTO;

public interface UserService {
    public boolean isEmailExists(String email);

    public User handleSaveUser(User user);

    public ResCreateUserDTO convertUserToResCreateUserDTO(User user);
    public User getUserByUsername(String email);
}
