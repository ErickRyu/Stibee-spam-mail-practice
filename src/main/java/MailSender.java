import java.io.IOException;
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
	final int SUBJECT_KEYWORDS_NUMBER = 3;
	final int TEXT_KEYWORDS_NUMBER = 10;

	Set<String> usedKeyword;
	List<List<Object>> keywords;
	List<List<Object>> recipents;
	Properties props;

	MailSender(List<List<Object>> keywords, List<List<Object>> recipents) {
		this.keywords = keywords;
		this.recipents = recipents;
		usedKeyword = new HashSet<>();
	}

	public void SendMail() {

		try {
			Properties props = PropertyManager.getMailProperties();

			Session session = Session.getInstance(props, new javax.mail.Authenticator() {
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(props.getProperty("gmail"), props.getProperty("password"));

				}
			});

			String subject = makeCombination(SUBJECT_KEYWORDS_NUMBER);
			String text = makeCombination(TEXT_KEYWORDS_NUMBER);

			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(props.getProperty("gmail")));
			message.setSubject(subject);
			message.setText(text);

			for (List<Object> recipent : recipents) {
				String to = (String) recipent.get(0);
				message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
				Transport.send(message);
			}

			System.out.println("Done");
		} catch (MessagingException e) {
			throw new RuntimeException(e);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public String makeCombination(int numOfKeywords) {
		StringBuilder res = new StringBuilder();
		for (int i = 0; i < numOfKeywords;) {
			int index = (int) (Math.random() * keywords.size());
			String keyword = (String) keywords.get(index).get(0);
			if (usedKeyword.add(keyword)) {
				res.append(keyword + " ");
				i++;
			}
		}
		return res.toString();
	}
}