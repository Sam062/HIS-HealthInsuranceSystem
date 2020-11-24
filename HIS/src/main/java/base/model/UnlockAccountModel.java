package base.model;

import lombok.Data;

@Data
public class UnlockAccountModel {
	private String email;
	private String tempPwd;
	private String newPwd;
	private String confirmNewPwd;
}
