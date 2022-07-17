package sqlClasses;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class GetNrOfEntries {
	public static int getNrOfEntrys(String tableName, String pathToDb) {
		int entryNr = -1;
		Connection con = DbConnector.connect(pathToDb);
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			String sql = "SELECT COUNT(*) FROM " + tableName + " ";
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			entryNr = rs.getInt(1);
		} catch (SQLException e) {
			System.out.println(e.toString());
		} finally {
			try {// close connection
				rs.close();
				ps.close();
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return entryNr;
	}
}
