package vn.duongvct.test.epl_app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import vn.duongvct.test.epl_app.domain.Coach;

@Repository
public interface CoachRepository extends JpaRepository<Coach, Long>, JpaSpecificationExecutor<Coach>{

}
