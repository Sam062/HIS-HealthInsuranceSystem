package base.service.impl;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import base.entity.ApplicantEntity;
import base.model.ApplicantModel;
import base.repository.ApplicantRegisterRepo;

@Service
@Transactional
public class ApplicantRegisterService {

	@Autowired
	private ApplicantRegisterRepo appRegRepo;

	public Integer saveApplicantDetails(ApplicantModel applicantModel) {
		ApplicantEntity applicantEntity = new ApplicantEntity();
		BeanUtils.copyProperties(applicantModel, applicantEntity);

		ApplicantEntity isSaved = appRegRepo.save(applicantEntity);
		if(isSaved!=null)
			return isSaved.getApplicantId();
		return 0;
	}

	public String getStateNameBySSN(String ssn) {
		String state=null;
		try {
			state=validateSsnFromSsaWeb(ssn);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return state;
	}

	// Develop Rest Client to communicate with SSA-Web Application to validate SSN
	private String validateSsnFromSsaWeb(String ssn) { 
		return "NJ";
	}


}
