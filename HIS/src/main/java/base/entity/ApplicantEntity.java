package base.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
@Entity
public class ApplicantEntity {
	@Id
	@GeneratedValue
	private Integer applicantId;
	private String fname;
	private String lname;
	private String gender;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date dateOfBirth;

	private String ssnNumber;
	private String email;

	@CreationTimestamp
	@Column(name = "CREATED_DATE", updatable = false)
	private Date createdDate;
	@UpdateTimestamp
	@Column(name = "UPDATED_DATE", insertable = false)
	private Date updatedDate;

}
