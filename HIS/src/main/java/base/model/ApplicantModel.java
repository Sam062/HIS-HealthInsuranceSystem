package base.model;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class ApplicantModel {
	private Integer applicantId;
	private String fname;
	private String lname;
	
	private String gender;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date dateOfBirth;

	private String ssnNumber;
	private String email;
}
