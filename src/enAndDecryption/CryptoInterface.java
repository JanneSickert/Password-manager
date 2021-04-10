package enAndDecryption;

public interface CryptoInterface {

	public char[] encrypt(char[] text, char[] key);
	
	public char[] decrypt(char[] text, char[] key);
}
