package sqlClasses;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @version 1.4
 * @author Janne
 *
 */
public class Insert {
	
	public static void writeInKeys(int kid, String username, String passwort, int mid, String web, String pathToDb) {
		Connection con = DbConnector.connect(pathToDb);
		PreparedStatement ps = null;
		try {
			String sql = "INSERT INTO Keys(Kid, Username, Passwort, Mid, Web) VALUES(?,?,?,?,?) ";
			ps = con.prepareStatement(sql);
			ps.setInt(1, kid);
			ps.setString(2, username);
			ps.setString(3, passwort);
			ps.setInt(4, mid);
			ps.setString(5, web);
			ps.execute();
		} catch (SQLException e) {
			System.out.println(e.toString());
		}
	}
	
	public static void addNewMail(String mail, String passwort, String masterPasswort, String pathToDb) {
		int len = GetNrOfEntries.getNrOfEntrys("Mails", pathToDb);
		String crypted_passwort = enAndDecryption.MainPort.encrypt(passwort, masterPasswort);
		writeInMails(len + 1, mail, crypted_passwort, pathToDb);
	}
	
	public static void writeInMails(int mid, String mail, String passwort, String pathToDb) {
		Connection con = DbConnector.connect(pathToDb);
		PreparedStatement ps = null;
		try {
			String sql = "INSERT INTO Mails(Mid, Mail, Passwort) VALUES(?,?,?) ";
			ps = con.prepareStatement(sql);
			ps.setInt(1, mid);
			ps.setString(2, mail);
			ps.setString(3, passwort);
			ps.execute();
		} catch (SQLException e) {
			System.out.println(e.toString());
		}
	}
	
	public static String[] getAllMails(String pathToDb) {
		int len = GetNrOfEntries.getNrOfEntrys("Mails", pathToDb);
		String[] mails = null;
		if (len == 0) {
			return mails;
		} else {
			mails = new String[len];
			Connection con = DbConnector.connect(pathToDb);
			PreparedStatement ps = null;
			ResultSet rs = null;
			try {
				int index = 0;
				String sql = "SELECT Mail FROM Mails";
				ps = con.prepareStatement(sql);
				rs = ps.executeQuery();
				while (rs.next()) {
					mails[index] = rs.getString("Mail");
					index++;
				}
			} catch (SQLException e) {
				System.out.println(e.toString());
			} finally {
				try {// close connection
					rs.close();
					ps.close();
					con.close();
				} catch (SQLException e) {
					System.out.println(e.toString());
				}
			}
			return mails;
		}
	}
	
	private static String getRandomString() {
		char a = (char) Math.round(Math.random() * enAndDecryption.primeNumbers.FindPrimes.MAX_CHAR_INDEX);
		char b = (char) Math.round(Math.random() * enAndDecryption.primeNumbers.FindPrimes.MAX_CHAR_INDEX);
		return (new String(
				new char[] {
						a, b
				}
				));
	}
	
	public static void write(String username, String passwort, String mail, String web, String masterPasswort, String pathToDb) {
		String randomString = getRandomString();
		int len = GetNrOfEntries.getNrOfEntrys("Keys", pathToDb);
		int kid = len + 1;
		String cry_passwort = enAndDecryption.MainPort.encrypt(passwort, masterPasswort);
		String[] storedMails = getAllMails(pathToDb);
		if (storedMails == null) {
			writeInMails(1, mail, randomString, pathToDb);
			writeInKeys(1, username, cry_passwort, 1, web, pathToDb);
		} else {
			boolean mailVorhanden = false;
			for (int mail_id = 0; mail_id < storedMails.length; mail_id++) {
				if (storedMails[mail_id].equals(mail)) {
					writeInKeys(kid, username, cry_passwort, (mail_id + 1), web, pathToDb);
					mailVorhanden = true;
					break;
				}
			}
			if (!mailVorhanden) {
				int id = GetNrOfEntries.getNrOfEntrys("Mails", pathToDb) + 1;
				writeInMails(id, mail, randomString, pathToDb);
				writeInKeys(kid, username, cry_passwort, id, web, pathToDb);
			}
		}
	}
}