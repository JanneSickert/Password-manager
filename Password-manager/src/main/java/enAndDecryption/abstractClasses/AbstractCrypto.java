package enAndDecryption.abstractClasses;

public abstract class AbstractCrypto {

	public abstract char[] crypt(char[] text, char[] key, AbstractCalculator calculator);
}