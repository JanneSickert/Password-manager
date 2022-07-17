package enAndDecryption;

import enAndDecryption.abstractClasses.AbstractCalculator;

class Decrypt extends AbstractCalculator{

	@Override
	public char addOrSub(char a, char b) {
		int ia = (int) a;
		int ib = (int) b;
		int sum = ia - ib;
		char erg;
		if (sum < 0) {
			sum = UNI_MAX + sum;
			erg = (char) sum;
		} else {
			erg = (char) sum;
		}
		return erg;
	}
}