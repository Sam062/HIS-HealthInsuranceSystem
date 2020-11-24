package base.service.impl;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import base.constents.AdminAccountConstents;
import base.entity.AdminAccountEntity;
import base.model.AccountModel;
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
	public String findByEmail(String email) {
		List<AdminAccountEntity> findByEmail = repo.findByEmail(email);
		if(findByEmail.isEmpty())
			return "unique";
		else
			return "duplicate";
	}
}
