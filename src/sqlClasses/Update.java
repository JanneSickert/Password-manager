package sqlClasses;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * @version 1.4
 * @author Janne
 *
 */
public class Update {

	public static void updateMail(int kid, String newMail) {
		String[] strarr = Insert.getAllMails();
		int mailIndex = -1;
		for (int i = 0; i < strarr.length; i++) {
			if (strarr[i].equals(newMail)) {
				mailIndex = i;
				break;
			}
		}
		if (mailIndex >= 0) {
			updateMidInKeys(kid, mailIndex + 1);
		} else {
			int mid = strarr.length + 1;
			Insert.writeInMails(mid, newMail, "null");
			updateMidInKeys(kid, mid);
		}
	}
	
	private static void updateMidInKeys(int kid, int mid) {
		Connection con = DbConnector.connect();
		PreparedStatement ps = null;
		try {
			String sql = "UPDATE Keys SET Mid = ? WHERE Kid = ? ";
			ps = con.prepareStatement(sql);
			ps.setInt(1, mid);
			ps.setInt(2, kid);
			ps.execute();
		} catch (SQLException e) {
			System.out.println(e.toString());
		}
	}

	public static void updateMailPasswort(int mid, String newPasswort, String masterPasswort) {
		Connection con = DbConnector.connect();
		PreparedStatement ps = null;
		try {
			String sql = "UPDATE Mails SET Passwort = ? WHERE Mid = ? ";
			ps = con.prepareStatement(sql);
			ps.setString(1, enAndDecryption.MainPort.encrypt(newPasswort, masterPasswort));
			ps.setInt(2, mid);
			ps.execute();
		} catch (SQLException e) {
			System.out.println(e.toString());
		}
	}

	public static void update(String entitet, int primaryId, String newEntry, String masterPasswort) {
		Connection con = DbConnector.connect();
		PreparedStatement ps = null;
		try {
			String sql = "UPDATE Keys SET " + entitet + " = ? WHERE Kid = ? ";
			ps = con.prepareStatement(sql);
			String nextEntry = (masterPasswort == null) ? newEntry : enAndDecryption.MainPort.encrypt(newEntry, masterPasswort);
			ps.setString(1, nextEntry);
			ps.setInt(2, primaryId);
			ps.execute();
		} catch (SQLException e) {
			System.out.println(e.toString());
		}
	}

	public static void updateWebsite(int primaryId, String newEntry) {
		Connection con = DbConnector.connect();
		PreparedStatement ps = null;
		try {
			String sql = "UPDATE Keys SET Web = ? WHERE Kid = ? ";
			ps = con.prepareStatement(sql);
			ps.setString(1, newEntry);
			ps.setInt(2, primaryId);
			ps.execute();
		} catch (SQLException e) {
			System.out.println(e.toString());
		}
	}
}
