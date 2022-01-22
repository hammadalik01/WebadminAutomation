package utilities;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

public class TestEmail_Functionality {

	public static void main(String[] args) throws AddressException, MessagingException {
		
		MonitoringMail mmail=new MonitoringMail();
		mmail.sendMail(TestConfig.server, TestConfig.from, TestConfig.to, TestConfig.subject, TestConfig.messageBody,TestConfig.attachmentPath,TestConfig.attachmentName);
       
		
	}

}
