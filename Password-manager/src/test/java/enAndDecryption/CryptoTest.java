package enAndDecryption;

import org.junit.Test;
import static org.junit.Assert.*;

public class CryptoTest {
	
	private final String TEXT = "MyCryptedText - my text to encrypt";
	
	@Test
	public void testEncryptionAndDecryptionWriteWrongKey() {
		String encrypted = enAndDecryption.MainPort.encrypt(TEXT, "myKey or MyPassword123#66$32");
		String decryptedWithWrongPassword = enAndDecryption.MainPort.decrypt(encrypted, "wrong key");
		assertFalse(TEXT.equals(decryptedWithWrongPassword));
	}
	
	@Test
	public void testEncryptionAndDecryptionWriteRightKey() {
		String encrypted = enAndDecryption.MainPort.encrypt(TEXT, "myKey or MyPassword123#66$32");
		String decrypted = enAndDecryption.MainPort.decrypt(encrypted, "myKey or MyPassword123#66$32");
		assertTrue(TEXT.equals(decrypted));
	}
}
