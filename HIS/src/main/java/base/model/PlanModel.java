package base.model;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PlanModel {
	private Integer planId;
	private String planName;
	private String planDescription;
	private String delStatus;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date createdDate;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date expiryDate;
}
