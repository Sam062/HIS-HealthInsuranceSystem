package base.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import base.constents.AdminAccountConstents;
import base.entity.AdminAccountEntity;
import base.model.AccountModel;
import base.model.UnlockAccountModel;
import base.repository.AdminAccountMasterRepo;
import base.service.AdminAccountService;
import base.utils.EmailUtils;
import base.utils.RandomPwdGenerater;

@Service
@Transactional
public class AdminAccountServiceImpl implements AdminAccountService{
	@Autowired
	private AdminAccountMasterRepo repo;
	@Autowired
	private EmailUtils emailUtils;

	@Override
	public Boolean saveAdminDetails(AccountModel model) {
		if(!model.getMobileNo().contains("+91"))
			model.setMobileNo("+91"+model.getMobileNo());

		model.setPwd(RandomPwdGenerater.randomAlphaNumeric(5));
		AdminAccountEntity entity=new AdminAccountEntity();
		BeanUtils.copyProperties(model, entity);
		entity.setAccountStatus(AdminAccountConstents.INACTIVE.toString());
		entity.setDeleteStatus(AdminAccountConstents.INACTIVE.toString());
		try {
			AdminAccountEntity isSaved = repo.save(entity);
			if(isSaved!=null) {
				emailUtils.sendUserUnlockEmail(model);
				return true;
			}
			else
				return false;
		} catch (Exception e) {
			// Rollback if mail not sent or any issues
			//			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
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
	public Boolean updateAccountStatus(UnlockAccountModel umodel) {
		AdminAccountEntity findByEmail = repo.findByEmail(umodel.getEmail());
		if(findByEmail!=null) {
			findByEmail.setPwd(umodel.getNewPwd());
			findByEmail.setAccountStatus(AdminAccountConstents.ACTIVE.toString());
			findByEmail.setDeleteStatus(AdminAccountConstents.ACTIVE.toString());
			repo.save(findByEmail);
			return true;
		}
		return false;
	}
	@Override
	public List<AccountModel> findAllAccounts(String role) {
		List<AdminAccountEntity> findAll = repo.findByRole(role);
		if(findAll.isEmpty()) 
			return null;
		List<AccountModel> accModelList=new ArrayList<>();
		findAll.stream().forEach(a->{
			AccountModel accModel=new AccountModel();
			BeanUtils.copyProperties(a, accModel);
			accModelList.add(accModel);
		});
		return accModelList;
	}
	@Override
	public AccountModel findByadminId(Integer id) {
		Optional<AdminAccountEntity> findByAdminId = repo.findById(id);
		if(findByAdminId!=null && findByAdminId.isPresent()) {
			AccountModel model=new AccountModel();
			BeanUtils.copyProperties(findByAdminId.get(), model);
			return model;
		}
		return null;
	}

	@Override
	public Boolean update(AccountModel model) {
		Optional<AdminAccountEntity> findById = repo.findById(model.getAdminId());
		AdminAccountEntity entity=null;
		if(findById!=null && findById.isPresent()) {
			entity=findById.get();
			entity.setFname(model.getFname());
			entity.setLname(model.getLname());
			entity.setEmail(model.getEmail());
			entity.setMobileNo(model.getMobileNo());
			entity.setGender(model.getGender());
			entity.setRole(model.getRole());
			if(null==model.getDeleteStatus())
				entity.setDeleteStatus(entity.getDeleteStatus());
			else
				entity.setDeleteStatus(model.getDeleteStatus());
			AdminAccountEntity save = repo.save(entity);
			if(save!=null&&save.getAdminId()!=null)
				return true;
		}
		return false;
	}
}
