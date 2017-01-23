import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.google.api.services.sheets.v4.Sheets;
import com.google.api.services.sheets.v4.SheetsScopes;
import com.google.api.services.sheets.v4.model.ValueRange;

public class SpreadsheetReader {
	private final String APPLICATION_NAME = "Stibee Homework by Ryu sungjin";
	private final java.io.File DATA_STORE_DIR = new java.io.File(System.getProperty("user.home"),
			".credentials/stibeeHW");

	private FileDataStoreFactory DATA_STORE_FACTORY;

	private final JsonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();

	private HttpTransport HTTP_TRANSPORT;

	private final List<String> SCOPES = Arrays.asList(SheetsScopes.SPREADSHEETS_READONLY);

	private Credential CREDENTIAL;

	{
		try {
			HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
			DATA_STORE_FACTORY = new FileDataStoreFactory(DATA_STORE_DIR);
		} catch (Throwable t) {
			t.printStackTrace();
			System.exit(1);
		}
	}

	SpreadsheetReader() throws IOException {
		authorize();
	}

	public Credential authorize() throws IOException {
		// Load client secrets.
		InputStream in = SpreadsheetReader.class.getResourceAsStream("/client_secret.json");
		GoogleClientSecrets clientSecrets = GoogleClientSecrets.load(JSON_FACTORY, new InputStreamReader(in));

		// Build flow and trigger user authorization request.
		GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(HTTP_TRANSPORT, JSON_FACTORY,
				clientSecrets, SCOPES).setDataStoreFactory(DATA_STORE_FACTORY).setAccessType("offline").build();
		Credential credential = new AuthorizationCodeInstalledApp(flow, new LocalServerReceiver()).authorize("user");
		System.out.println("Credentials saved to " + DATA_STORE_DIR.getAbsolutePath());
		CREDENTIAL = credential;
		return credential;
	}

	public List<List<Object>> getValue(String spreadsheetId, String range) throws IOException {
		Sheets service = new Sheets.Builder(HTTP_TRANSPORT, JSON_FACTORY, CREDENTIAL)
				.setApplicationName(APPLICATION_NAME).build();
		ValueRange response = service.spreadsheets().values().get(spreadsheetId, range).execute();
		List<List<Object>> values = response.getValues();
		if (values == null || values.size() == 0) {
			throw new IOException("No data found.");
		}
		return values;
	}

}