import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertyManager {

	private final static String MAIL_PROPERTIES_FILE = "mail.properties";
	private final static String SHEETS_PROPERTIES_FILE = "sheets.properties";

	public static Properties getSheetProperties() throws IOException {
		Properties props = new Properties();
		props.load(new FileInputStream(SHEETS_PROPERTIES_FILE));
		return props;
	}

	public static Properties getMailProperties() throws IOException{
		Properties props = new Properties();
		props.load(new FileInputStream(MAIL_PROPERTIES_FILE));
		return props;
	}
	
}
