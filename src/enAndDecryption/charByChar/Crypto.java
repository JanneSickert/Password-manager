package enAndDecryption.charByChar;

import enAndDecryption.abstractClasses.AbstractCalculator;
import enAndDecryption.abstractClasses.AbstractCrypto;

public class Crypto extends AbstractCrypto{

	@Override
	public char[] crypt(char[] text, char[] key, AbstractCalculator calculator) {
		char[] cryText = new char[text.length];
		int i = 0;
		int k = 0;
		while (i < text.length) {
			while (k < key.length) {
				if (i < text.length) {
					cryText[i] = calculator.addOrSub(text[i], key[k]);
					i++;
				}
				k++;
			}
			k = 0;
		}
		return cryText;
	}
}
