package enAndDecryption.primeNumbers;

class Encryption extends Crypto {

	@Override
	protected char calculate(char a, char b) {
		int ia = (int) a;
		int ib = (int) b;
		int sum = ia + ib;
		char erg;
		if (sum > FindPrimes.MAX_CHAR_INDEX) {
			sum = sum - FindPrimes.MAX_CHAR_INDEX;
			erg = (char) sum;
		} else {
			erg = (char) sum;
		}
		return erg;
	}
}