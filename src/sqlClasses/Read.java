package sqlClasses;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Read {

	/**
	 * 
	 * @param masterPasswort
	 * @return [Zeile][0:Mail, 1:Passwort]
	 */
	public static String[][] getMailData(String masterPasswort) {
		int len = GetNrOfEntries.getNrOfEntrys("Mails");
		String[][] res = null;
		if (len != 0) {
			res = new String[len][2];
			Connection con = DbConnector.connect();
			PreparedStatement ps = null;
			ResultSet rs = null;
			try {
				String sql = "SELECT Mail, Passwort FROM Mails";
				ps = con.prepareStatement(sql);
				rs = ps.executeQuery();
				int index = 0;
				while (rs.next()) {
					res[index][0] = rs.getString("Mail");
					res[index][1] = rs.getString("Passwort");
					index++;
				}
				for (int i = 0; i < len; i++) {
					if (!(res[i][1].equals("null"))) {
						res[i][1] = enAndDecryption.MainPort.decrypt(res[i][1], masterPasswort);
					}
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
		}
		return res;
	}

	public static String[] readKeysWeb() {
		String[] webPageNames = null;
		Connection con = DbConnector.connect();
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			webPageNames = new String[GetNrOfEntries.getNrOfEntrys("Keys")];
			String sql = "SELECT * FROM Keys";
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			int index = 0;
			while (rs.next()) {
				webPageNames[index] = rs.getString("Web");
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
		return webPageNames;
	}

	public static String[] getData(int primaryId, String masterPasswort) {
		String[] data = new String[4];
		Connection con = DbConnector.connect();
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			String sql = "SELECT Web, Username, K.Passwort, Mail FROM Keys K, Mails M WHERE M.Mid = K.Mid AND Kid = ? ";
			ps = con.prepareStatement(sql);
			ps.setInt(1, primaryId);
			rs = ps.executeQuery();
			data[0] = rs.getString("Web");
			data[1] = rs.getString("Username");
			data[2] = rs.getString("Passwort");
			data[3] = rs.getString("Mail");
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
		data[2] = enAndDecryption.MainPort.decrypt(data[2], masterPasswort);
		return data;
	}

	public static String[][] readTableKeys(String masterPasswort) {
		int size = GetNrOfEntries.getNrOfEntrys("Keys");
		String[][] table = new String[size][5];
		for (int line = 0; line < size; line++) {
			Connection con = DbConnector.connect();
			PreparedStatement ps = null;
			ResultSet rs = null;
			try {
				String sql = "SELECT * FROM Keys WHERE Kid = ? ";
				ps = con.prepareStatement(sql);
				ps.setInt(1, line + 1);
				rs = ps.executeQuery();
				table[line][0] = "" + (line + 1);
				table[line][1] = rs.getString("Username");
				table[line][2] = rs.getString("Passwort");
				table[line][3] = "" + rs.getInt("Mid");
				table[line][4] = rs.getString("Web");
				table[line][2] = enAndDecryption.MainPort.decrypt(table[line][2], masterPasswort);
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
		}
		return table;
	}
	
	public static String[][] readTableMails(String masterPasswort) {
		int size = GetNrOfEntries.getNrOfEntrys("Mails");
		String[][] table = new String[size][3];
		for (int line = 0; line < size; line++) {
			Connection con = DbConnector.connect();
			PreparedStatement ps = null;
			ResultSet rs = null;
			try {
				String sql = "SELECT * FROM Mails WHERE Mid = ? ";
				ps = con.prepareStatement(sql);
				ps.setInt(1, line + 1);
				rs = ps.executeQuery();
				table[line][0] = "" + (line + 1);
				table[line][1] = rs.getString("Mail");
				table[line][2] = rs.getString("Passwort");
				table[line][2] = enAndDecryption.MainPort.decrypt(table[line][2], masterPasswort);
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
		}
		return table;
	}
}