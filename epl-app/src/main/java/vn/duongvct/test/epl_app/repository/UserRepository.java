package vn.duongvct.test.epl_app.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import vn.duongvct.test.epl_app.domain.User;
import java.util.List;



@Repository
public interface UserRepository extends JpaRepository<User,Long>{
    boolean existsByEmail(String email);
    User findByEmail(String email);
    User findByRefreshtokenAndEmail(String refreshToken, String email);
}
