package base.utils;

import org.springframework.context.annotation.Configuration;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.rest.api.v2010.account.Message.Status;
import com.twilio.type.PhoneNumber;

@Configuration
public class TwilloMsg {
	static {
		Twilio.init("AC4f6a98210eb7b32a3a96bcd6343eb47a", "3b07adcb22e5c32c08285b39bde2e604");
	}
	public static Status sendMsg(String number,String msg){
		Message message = Message.creator(
				new PhoneNumber(number),
				new PhoneNumber("+17015018625"),
				msg)
				.create();
		return message.getStatus();
	}
}
