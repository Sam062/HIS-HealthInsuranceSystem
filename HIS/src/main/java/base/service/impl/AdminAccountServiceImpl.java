package base.service.impl;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import base.constents.AdminAccountConstents;
import base.entity.AdminAccountEntity;
import base.model.AccountModel;
import base.repository.AdminAccountMasterRepo;
import base.service.AdminAccountService;

@Service
public class AdminAccountServiceImpl implements AdminAccountService{
	@Autowired
	private AdminAccountMasterRepo repo;

	@Override
	public String saveAdminDetails(AccountModel model) {
		AdminAccountEntity entity=new AdminAccountEntity();
		BeanUtils.copyProperties(model, entity);
		entity.setAccountStatus(AdminAccountConstents.INACTIVE.toString());
		entity.setDeleteStatus(AdminAccountConstents.INACTIVE.toString());
		AdminAccountEntity isSaved = repo.save(entity);
		if(isSaved!=null)
			return isSaved.getFname()+" DETAILS SAVED.";
		else
			return "DETAILS COULDN'T SAVE.";
	}
}
