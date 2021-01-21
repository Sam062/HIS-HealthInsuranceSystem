package base.service;

import java.util.List;

import base.model.AccountModel;
import base.model.UnlockAccountModel;

public interface AdminAccountService {
	public Boolean saveAdminDetails(AccountModel model);
	public AccountModel findByEmail(String email);
	public AccountModel findByEmailAndPwd(String email,String pwd);
	public Boolean updateAccount(UnlockAccountModel umodel);
	public List<AccountModel> findAllAccounts();
	public AccountModel findByadminId(Integer id);
	public Boolean save(AccountModel model);
}
