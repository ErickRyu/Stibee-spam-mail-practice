import java.io.IOException;
import java.util.List;
import java.util.Properties;

public class Main {
	public static void main(String[] args) {
		try {
			Properties sheetProperties = PropertyManager.getSheetProperties();

			// Get keywords
			String spreadsheetId = sheetProperties.getProperty("keywordsSheetId");
			String range = sheetProperties.getProperty("keywordsRange");
			List<List<Object>> keywords = SpreadsheetReader.getValue(spreadsheetId, range);

			// Get recipents's email
			// https://docs.google.com/spreadsheets/d/1hm5SOn4Sch8ulN-IlF1nDFvjyVVZ_g309CwaMIYCYbs/edit?usp=sharing
			spreadsheetId = sheetProperties.getProperty("recipentsSheetId");
			range = sheetProperties.getProperty("recipentsRange");
			List<List<Object>> recipents = SpreadsheetReader.getValue(spreadsheetId, range);

			MailSender mailsender = new MailSender(keywords, recipents);
			mailsender.SendMail();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
