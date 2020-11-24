package base.utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import base.model.AccountModel;


@Component
public class EmailUtils {
	@Autowired
	private JavaMailSender mailSender;

	public Boolean sendUserUnlockEmail(AccountModel accModel) {
		boolean isSent=false;
		try {
			//Using Mime we can send extra Functioned Emails which is better than using SimpleMail
			MimeMessage mimeMessage = mailSender.createMimeMessage();
			MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage);

			mimeMessageHelper.setTo(accModel.getEmail());
			mimeMessageHelper.setSubject("Unlock Your Account");
			mimeMessageHelper.setText(getUnlockAccEmailBody(accModel),true);

			mailSender.send(mimeMessage);
			isSent=true;	
		} catch (Exception e) {
			e.printStackTrace();
		}
		return isSent;
	}

	private String getUnlockAccEmailBody(AccountModel accModel) throws IOException {
		StringBuffer sb=new StringBuffer();

		FileReader fr=new FileReader("UNLOCK-ACC-EMAIL-BODY-TEMPLATE.txt");
		BufferedReader br=new BufferedReader(fr);

		String line=br.readLine();
		while(line!=null) {
			sb.append(line);
			line=br.readLine();
		}
		br.close();
		//FORMAT MAIL BODY
		String mailBody=sb.toString();
		mailBody = mailBody.replace("{FNAME}", accModel.getFname());
		mailBody = mailBody.replace("{LNAME}", accModel.getLname());
		mailBody = mailBody.replace("{TEMP-PWD}", accModel.getPwd());
		mailBody = mailBody.replace("{EMAIL}", accModel.getEmail());

		return mailBody;
	}
}
