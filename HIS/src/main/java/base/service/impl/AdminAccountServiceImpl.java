package base.service.impl;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import base.constents.AdminAccountConstents;
import base.entity.AdminAccountEntity;
import base.model.AccountModel;
import base.model.UnlockAccountModel;
import base.repository.AdminAccountMasterRepo;
import base.service.AdminAccountService;
import base.utils.EmailUtils;
import base.utils.RandomPwdGenerater;

@Service
public class AdminAccountServiceImpl implements AdminAccountService{
	@Autowired
	private AdminAccountMasterRepo repo;
	@Autowired
	private EmailUtils emailUtils;

	@Override
	public Boolean saveAdminDetails(AccountModel model) {
		model.setPwd(RandomPwdGenerater.randomAlphaNumeric(5));
		AdminAccountEntity entity=new AdminAccountEntity();
		BeanUtils.copyProperties(model, entity);
		entity.setAccountStatus(AdminAccountConstents.INACTIVE.toString());
		entity.setDeleteStatus(AdminAccountConstents.INACTIVE.toString());
		try {
			AdminAccountEntity isSaved = repo.save(entity);
			if(isSaved!=null) {
				return emailUtils.sendUserUnlockEmail(model);
			}
			else
				return false;
		} catch (org.springframework.transaction.TransactionSystemException e) {
			return false;
		}
	}

	@Override
	public AccountModel findByEmail(String email) {
		AdminAccountEntity findByEmail = repo.findByEmail(email);
		AccountModel accModel=null;
		if(findByEmail!=null) {
			accModel=new AccountModel();
			BeanUtils.copyProperties(findByEmail, accModel);
			return accModel;
		}
		return accModel;
	}
	@Override
	public AccountModel findByEmailAndPwd(String email, String pwd) {
		AdminAccountEntity adminAccount = repo.findByEmailAndPwd(email, pwd);
		AccountModel model=null;
		if(adminAccount!=null) {
			model=new AccountModel();
			BeanUtils.copyProperties(adminAccount, model);
			return model;
		}
		return model;
	}
	@Override
	public Boolean updateAccount(UnlockAccountModel umodel) {
		AdminAccountEntity findByEmail = repo.findByEmail(umodel.getEmail());
		if(findByEmail!=null) {
			findByEmail.setPwd(umodel.getNewPwd());
			findByEmail.setAccountStatus(AdminAccountConstents.ACTIVE.toString());
			repo.save(findByEmail);
			return true;
		}
		return false;
	}
}
