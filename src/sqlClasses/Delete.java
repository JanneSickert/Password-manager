package sqlClasses;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Delete {

	public static void deleteAll() {
		final String[] SQL = {"DELETE FROM Keys", "DELETE FROM Mails"};
		Connection con = DbConnector.connect();
		PreparedStatement ps = null;
		try {
			ps = con.prepareStatement(SQL[0]);
			ps.execute();
			ps = con.prepareStatement(SQL[1]);
			ps.execute();
		} catch (SQLException e) { 
			System.out.println(e.toString());
		}
	}
}
