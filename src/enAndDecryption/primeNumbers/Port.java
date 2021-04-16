package enAndDecryption.primeNumbers;

import enAndDecryption.CryptoInterface;

public class Port implements CryptoInterface{

	private static Crypto[] cry = {new Encryption(), new Decrypt()};
	
	@Override
	public char[] encrypt(char[] text, char[] key) {
		return (cry[0].crypt(text, key));
	}

	@Override
	public char[] decrypt(char[] text, char[] key) {
		return (cry[1].crypt(text, key));
	}
}
