package enAndDecryption.sinus;

import enAndDecryption.abstractClasses.AbstractCalculator;
import enAndDecryption.abstractClasses.AbstractCrypto;

public class Crypto extends AbstractCrypto{
	
	protected final char MAX_CHAR_INDEX = 65535;
	
	private double makeKeyToPin(char[] key) {
		double erg = 0;
		for (char c : key) {
			double d = (double) c;
			erg = erg + d;
			if (erg < 0) {// 64 bits were exceeded
				erg = Double.MAX_VALUE;
				erg = ((erg - d) / (d * Math.PI));
			}
		}
		double sin = Math.sin(erg);
		return sin;
	}

	@Override
	public char[] crypt(char[] text, char[] key, AbstractCalculator calculator) {
		int len = text.length;
		char[] summant = new char[len], crypted = new char[len];
		double sin = makeKeyToPin(key);
		String s = "" + sin;
		int index = 0;
		while (index < len) {
			for (int i = 0; i < s.length(); i++) {
				if (index < len) {
					summant[index] = (char) s.charAt(i);
					index++;
				}
			}
		}
		for (int k = 0; k < len; k++) {
			crypted[k] = calculator.addOrSub(text[k], summant[k]);
		}
		return crypted;
	}
}