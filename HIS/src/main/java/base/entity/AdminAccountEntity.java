package base.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

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
	private String fname;
	
	@Column(name = "LNAME")
	private String lname;
	
	@Column(name = "EMAIL")
	private String email;
	
	@Column(name = "GENDER")
	private Character gender;
	
	@Column(name = "ROLE")
	private Character role;
	
	@Column(name = "ACC_STATUS")
	private String accountStatus;
	
	@Column(name = "DEL_STATUS")
	private String deleteStatus;
	
	@CreationTimestamp
	@Column(name = "CREATED_DATE", updatable = false)
	private Date createdDate;
	@UpdateTimestamp
	@Column(name = "UPDATED_DATE", insertable = false)
	private Date updatedDate;

}
