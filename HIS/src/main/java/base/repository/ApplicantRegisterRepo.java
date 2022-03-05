package base.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import base.entity.ApplicantEntity;

@Repository
public interface ApplicantRegisterRepo extends JpaRepository<ApplicantEntity, Serializable>{
	

}
