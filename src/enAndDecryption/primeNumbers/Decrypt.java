package enAndDecryption.primeNumbers;

class Decrypt extends Crypto{

	@Override
	protected char calculate(char a, char b) {
		int ia = (int) a;
		int ib = (int) b;
		int sum = ia - ib;
		char erg;
		if (sum < 0) {
			sum = MAX_CHAR_INDEX + sum;
			erg = (char) sum;
		} else {
			erg = (char) sum;
		}
		return erg;
	}
}