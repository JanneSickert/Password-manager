package enAndDecryption;

import enAndDecryption.abstractClasses.AbstractCalculator;
import enAndDecryption.abstractClasses.AbstractCrypto;

public class Port<CRYPTO extends AbstractCrypto> {

	AbstractCalculator encryptObject;
	AbstractCalculator decryptObject;
	CRYPTO crypt;
	
	public Port(AbstractCalculator encryptObject, AbstractCalculator decryptObject, CRYPTO crypt) {
		this.encryptObject = encryptObject;
		this.decryptObject = decryptObject;
		this.crypt = crypt;
	}
	
	public char[] encrypt(char[] text, char[] key) {
		return (crypt.crypt(text, key, encryptObject));
	}

	public char[] decrypt(char[] text, char[] key) {
		return (crypt.crypt(text, key, decryptObject));
	}
}