package passwortManager;

import java.util.Scanner;
import sqlClasses.*;

public class PasswortManager {

	private static final Scanner sc = new Scanner(System.in);
	private static final String UNK = "undefind input!";

	private static void p(String str) {
		System.out.println(str);
	}

	private static String faq(String quest) {
		System.out.print(quest + ":");
		String res = sc.nextLine();
		return res;
	}

	public static void main(String[] args) {
		p("Start password manager");
		start();
	}

	private static void start() {
		p("To select a option write one of the numbers");
		int nr = Integer.parseInt(
						 faq(" 0 to read data"
						+ "\n 1 to write new data"
						+ "\n 2 to update data"
						+ "\n 3 for mail data"
						+ "\n 4 for Expanded options"
						+ "\n 5 to close the programm"
						+ "\n"));
		switch (nr) {
		case 0:
			read();
			break;
		case 1:
			write();
			break;
		case 2:
			update();
			break;
		case 3:
			readMailData();
			break;
		case 4:
			erweiterteOptionen();
			break;
		case 5:
			ende();
			break;
		default:
			p(UNK);
		}
		start();
	}
	
	private static void erweiterteOptionen() {
		int nr = Integer.parseInt(faq(" Write 0 to delete all data"
							      + "\n Write 1 to export data"
							      + "\n Write 2 to import data, this will overwrite the current entrys"));
		String masterPasswort;
		switch (nr) {
		case 0:
			Delete.deleteAll();
			break;
		case 1:
			masterPasswort = faq("master password");
			XmlParser.export(masterPasswort);
			break;
		case 2:
			masterPasswort = faq("new master password");
			XmlParser.inport(masterPasswort);
			break;
		default:
			p(UNK);
		}
	}

	private static void ende() {
		p("exit Password-Manager");
		System.exit(0);
	}

	private static void readMailData() {
		String masterPasswort = faq("master password");
		String[][] mailData = Read.getMailData(masterPasswort);
		for (int i = 0; i < mailData.length; i++) {
			p("Mid:" + (i + 1) + " mail:" + mailData[i][0] + " password:" + mailData[i][1]);
		}
	}

	private static void update() {
		int selection = (int) Integer.parseInt(faq(""
				+   " 0 to update data"
				+ "\n 1 to update mail data"));
		String masterPasswort = null;
		switch (selection) {
		case 0:
			final String[] ENTITETEN = { "Username", "Passwort", "Mail", "Web" };
			for (int i = 0; i < ENTITETEN.length; i++) {
				p("" + i + ":" + ENTITETEN[i]);
			}
			int snr = (int) Integer.parseInt(faq("Select the number of the column"));
			int pid = (int) Integer.parseInt(faq("Select the primary-id of the line. The primary id starts by one"));
			String newEntry = faq("new entry");
			switch (snr) {
			case 0:
				Update.update(ENTITETEN[snr], pid, newEntry, null);
				break;
			case 1:
				masterPasswort = faq("master password");
				Update.update(ENTITETEN[snr], pid, newEntry, masterPasswort);
				break;
			case 2:
				Update.updateMail(pid, newEntry);
				break;
			case 3:
				Update.updateWebsite(pid, newEntry);
			}
			break;
		case 1:
			int mid = (int) Integer.parseInt(faq("primary-id of the mail"));
			String newPasswort = faq("new password");
			masterPasswort = faq("master password");
			Update.updateMailPasswort(mid, newPasswort, masterPasswort);
			break;
		}
		p("data updated");
	}

	private static void write() {
		int selection = (int) Integer.parseInt(faq(""
				+   " 0 to add user data"
				+ "\n 1 to add mails"));
		switch (selection) {
		case 0:
			String username, passwort, mail, web, masterPasswort;
			username = faq("username");
			passwort = faq("password");
			mail = faq("mail");
			web = faq("name of the website");
			masterPasswort = faq("master password");
			Insert.write(username, passwort, mail, web, masterPasswort);
			break;
		case 1:
			String m_mail = faq("mail"), m_passwort = faq("password"), m_masterPasswort = faq("master password");
			Insert.addNewMail(m_mail, m_passwort, m_masterPasswort);
			break;
		default:
			p(UNK);
			write();
		}
	}

	private static void read() {
		p("To select write the number in front of the webpage name");
		String[] webPageNames = Read.readKeysWeb();
		for (int i = 0; i < webPageNames.length; i++) {
			p("" + i + ":" + webPageNames[i]);
		}
		int nr = (int) Integer.parseInt(faq("Nr"));
		String masterPasswort = faq("master password");
		final String[] NAMES = { "website", "username", "password", "mail address" };
		String[] data = Read.getData(nr + 1, masterPasswort);
		for (int k = 0; k < 4; k++) {
			p(NAMES[k] + ":" + data[k]);
		}
	}
}