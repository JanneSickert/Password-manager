package enAndDecryption;

import enAndDecryption.abstractClasses.AbstractCalculator;

class Encryption extends AbstractCalculator {

	@Override
	public char addOrSub(char a, char b) {
		int ia = (int) a;
		int ib = (int) b;
		int sum = ia + ib;
		char erg;
		if (sum > UNI_MAX) {
			sum = sum - UNI_MAX;
			erg = (char) sum;
		} else {
			erg = (char) sum;
		}
		return erg;
	}
}