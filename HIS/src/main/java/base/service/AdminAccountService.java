package base.service;

import base.model.AccountModel;
import base.model.UnlockAccountModel;

public interface AdminAccountService {
	public Boolean saveAdminDetails(AccountModel model);
	public AccountModel findByEmail(String email);
	public AccountModel findByEmailAndPwd(String email,String pwd);
	public Boolean updateAccount(UnlockAccountModel umodel);
}
