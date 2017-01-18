import java.util.HashSet;
import java.util.List;
import java.util.Properties;
import java.util.Set;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class MailSender {
	public static Set<String> usedKeyword;
	public static List<List<Object>> spreadsheet;
	
	
	public static void SendMail(List<List<Object>> values) {
		spreadsheet = values;
		usedKeyword = new HashSet<>();
		final String username = "Input-Gmail-ID@gmail.com";
		final String password = "Input-Password";

		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");

		Session session = Session.getInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		});

		try {

			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress("from-email@gmail.com"));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse("Receiver-email@gmail.com"));

			String subject = makeSubject();
			String text = makeText();
			
			message.setSubject(subject);
			message.setText(text);
//			message.setContent("<img src = 'http://blog.jinbo.net/attach/615/200937431.jpg'>", "text/html");
			Transport.send(message);

			System.out.println("Done");

		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
	}
	public static String makeSubject(){
		StringBuilder res = new StringBuilder();
		for(int i = 0; i < 3; ){
			int index = (int)(Math.random()*spreadsheet.size());
			String keyword = (String)spreadsheet.get(index).get(0);
			if(usedKeyword.add(keyword)){
				res.append(keyword + " ");
				i++;
			}
		}
		return res.toString();
	}
	public static String makeText(){
		StringBuilder res = new StringBuilder();
		for(int i = 0; i < 10; ){
			int index = (int)(Math.random()*spreadsheet.size());
			String keyword = (String)spreadsheet.get(index).get(0);
			if(usedKeyword.add(keyword)){
				res.append(keyword + " ");
				i++;
			}
		}
		return res.toString();
	}
}