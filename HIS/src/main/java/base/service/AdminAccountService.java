package base.service;

import base.model.AccountModel;

public interface AdminAccountService {
	public Boolean saveAdminDetails(AccountModel model);

	public String findByEmail(String email);

}
