package enAndDecryption;

public class MainPort{

	private static final CryptoInterface[] ci = {
			new enAndDecryption.sinus.Port(),
			new enAndDecryption.primeNumbers.Port()
	};
	
	private static final int LENGTH = ci.length;
	private static char[] text;
	private static int i = 0;
	
	private static char[] encrypt(char[] text, char[] key) {
		if (i < LENGTH) {
			MainPort.text = text;
			MainPort.text = ci[i].encrypt(MainPort.text, key);
			i++;
			return (encrypt(MainPort.text, key));
		} else {
			i = 0;
			return MainPort.text;
		}
	}

	private static char[] decrypt(char[] text, char[] key) {
		if (i < LENGTH) {
			MainPort.text = text;
			MainPort.text = ci[i].decrypt(MainPort.text, key);
			i++;
			return (decrypt(MainPort.text, key));
		} else {
			i = 0;
			return MainPort.text;
		}
	}
	
	private static char[] makeStringToCharArray(String str) {
		char[] ca = new char[str.length()];
		for (int i = 0; i < ca.length; i++) {
			ca[i] = str.charAt(i);
		}
		return ca;
	}
	
	public static String encrypt(String text, String key) {
		String s = new String((encrypt(makeStringToCharArray(text), makeStringToCharArray(key))));
		return s;
	}

	public static String decrypt(String text, String key) {
		String s = new String((decrypt(makeStringToCharArray(text), makeStringToCharArray(key))));
		return s;
	}
}