package base.model;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class PlanModel {
	private String planId;
	private String planName;
	private String planDescription;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date createdDate;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date expiryDate;
	private String delStatus;
}
