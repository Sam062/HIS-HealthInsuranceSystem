package base.model;

import lombok.Data;

@Data
public class AccountModel {
	private String fname;
	private String lname;
	private String email;
	private String mobileNo;
	private Character gender;
	private Character role;
	private String pwd;
	private String accountStatus;
}
