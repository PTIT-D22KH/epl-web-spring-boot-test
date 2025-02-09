package vn.duongvct.test.epl_app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import vn.duongvct.test.epl_app.domain.Club;

@Repository
public interface ClubRepository extends JpaRepository<Club, Long> , JpaSpecificationExecutor<Club>{

}
