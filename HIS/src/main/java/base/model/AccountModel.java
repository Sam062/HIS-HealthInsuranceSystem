package base.model;

import lombok.Data;

@Data
public class AccountModel {
	private Integer adminId;
	private String fname;
	private String lname;
	private String email;
	private String mobileNo;
	private Character gender;
	private String role;
	private String pwd;
	private String accountStatus;
	private String deleteStatus;
}
