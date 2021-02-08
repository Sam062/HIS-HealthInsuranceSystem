package base.service;

import java.util.List;

import base.model.AccountModel;
import base.model.UnlockAccountModel;

public interface AdminAccountService {
	public Boolean saveAdminDetails(AccountModel model);
	public AccountModel findByEmail(String email);
	public AccountModel findByEmailAndPwd(String email,String pwd);
	public Boolean updateAccountStatus(UnlockAccountModel umodel);
	public List<AccountModel> findAllAccounts(String role);
	public AccountModel findByadminId(Integer id);
	
	//changes in update allover
	public Boolean update(AccountModel model);
}
