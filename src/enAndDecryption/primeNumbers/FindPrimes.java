package enAndDecryption.primeNumbers;

import java.util.ArrayList;

public class FindPrimes {

	public static final char MAX_CHAR_INDEX = 65535;
	private static ArrayList<Integer> list = new ArrayList<Integer>();
	
	private static boolean isPrime(int nr) {
		boolean isP = true;
		for (int i = 2; i < nr; i++) {
			if (nr % i == 0) {
				isP = false;
				break;
			}
		}
		return isP;
	}
	
	public static void startFindPrimes() {
		int i = 3;
		do {
			if (isPrime(i)) {
				list.add(i);
			}
			i++;
		} while (list.size() <= MAX_CHAR_INDEX);
	}
	
	public static int get(char index) {
		int ii = (int) index;
		int nr = (int) list.get(ii);
		return nr;
	}
}