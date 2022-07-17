package sqlClasses;

import org.junit.Test;
import static org.junit.Assert.*;

public class CrudTest {

	private static final String PATH_TO_DB = "src/test/resources/database.db";
	
	class Container {
		String
		username,
		password,
		mail,
		webseiteName,
		masterPassword;
	}
	
	@Test
	public void testReadAndWrite() {
		Container c = new Container();
		c.username = "testUsername";
		c.password = "testPassword";
		c.mail = "testPassword";
		c.webseiteName = "BeispielWebseite";
		c.masterPassword = "Password1234";
		Insert.write(c.username, c.password, c.mail, c.webseiteName, c.masterPassword, PATH_TO_DB);
		String[] result = Read.getData(1, c.masterPassword, PATH_TO_DB);//web, username password, mail
		assertEquals(result[0], c.webseiteName);
		assertEquals(result[1], c.username);
		assertEquals(result[2], c.password);
		assertEquals(result[3], c.mail);
		for (String s : result) {
			System.out.println(s);
		}
	}
}