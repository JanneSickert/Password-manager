package enAndDecryption.primeNumbers;

import java.util.ArrayList;

abstract class Crypto {
	
	protected final char MAX_CHAR_INDEX = 65535;
	
	
	private class FindPrimes{
		
		private ArrayList<Integer> list = new ArrayList<Integer>();
		
		public FindPrimes() {
			int i = 3;
			do {
				if (isPrime(i)) {
					list.add(i);
				}
				i++;
			} while (list.size() <= MAX_CHAR_INDEX);
		}
		
		private boolean isPrime(int nr) {
			boolean isP = true;
			for (int i = 2; i < nr; i++) {
				if (nr % i == 0) {
					isP = false;
					break;
				}
			}
			return isP;
		}
		
		public int get(char index) {
			int ii = (int) index;
			int nr = (int) list.get(ii);
			return nr;
		}
	}
	
	
	private char[] getLongerKey(char[] key, int len) {
		FindPrimes pr = new FindPrimes();
		String str = "";
		for (int i = 0; i < key.length; i++) {
			str = str + pr.get(key[i]);
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