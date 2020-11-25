package base.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import base.entity.AdminAccountEntity;

@Repository
public interface AdminAccountMasterRepo extends JpaRepository<AdminAccountEntity, Serializable>{
	AdminAccountEntity findByEmail(String email);
	AdminAccountEntity findByEmailAndPwd(String email, String pwd);
}
