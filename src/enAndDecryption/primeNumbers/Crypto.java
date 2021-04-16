package enAndDecryption.primeNumbers;

abstract class Crypto {
	
	private char[] getLongerKey(char[] key, int len) {
		String str = "";
		for (int i = 0; i < key.length; i++) {
			str = str + FindPrimes.get(key[i]);
		}
		while (str.length() < len * 4) {
			str = str + str;
		}
		char[][] arr = new char[len][4];
		int index = 0;
		for (int i = 0; i < len; i++) {
			for (int k = 0; k < 4; k++) {
				arr[i][k] = str.charAt(index);
				index++;
			}
		}
		char[] result = new char[len];
		for (int i = 0; i < len; i++) {
			int iii = Integer.parseInt(new String(arr[i]));
			result[i] = (char) iii;
		}
		return result;
	}
	
	protected abstract char calculate(char a, char b);
	
	char[] crypt(char[] text, char[] key) {
		int len = text.length;
		char[] summant = getLongerKey(key, len), crypted = new char[len];
		for (int k = 0; k < len; k++) {
			crypted[k] = calculate(text[k], summant[k]);
		}
		return crypted;
	}
}