package base.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.Data;

@Data
@Entity
@Table(name = "Admin_Account")
public class AdminAccountEntity {
	@Id
	@GeneratedValue
	@Column(name = "ADMIN_ID")
	private Integer adminId;

	@Column(name = "FNAME")
	@NotBlank(message = "First name must not be empty.")
	private String fname;

	@Column(name = "LNAME")
	@NotBlank(message = "Last name must not be empty.")
	private String lname;

	@Column(name = "EMAIL")
	@NotBlank(message = "Email must not be empty.")
	@Pattern(regexp = "^[A-Za-z0-9+_.-]+@(.+)$",message = "Not a valid email.")
	private String email;
	
	@Column(name = "MOB_NO")
	private String mobileNo;

	@Column(name = "GENDER")
	private Character gender;

	@Column(name = "ROLE")
	private String role;

	@Column(name = "ACC_STATUS")
	private String accountStatus;

	@Column(name = "DEL_STATUS")
	private String deleteStatus;
	
	@Column(name = "PWD")
	private String pwd;

	@CreationTimestamp
	@Column(name = "CREATED_DATE", updatable = false)
	private Date createdDate;
	@UpdateTimestamp
	@Column(name = "UPDATED_DATE", insertable = false)
	private Date updatedDate;

}
