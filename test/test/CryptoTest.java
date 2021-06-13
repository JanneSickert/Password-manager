package test;

import passwortManager.XmlParser;

public class CryptoTest {
	
	private static void p(String str) {
		System.out.println(str);
	}
	
	public static void main(String[] args) {
		p("START_TEST");
		final int START_TEST = 2;
		Test[] tests = new Test[] {
				new Test() {
					@Override
					public boolean test() {
						XmlParser.export("myKey");
						return true;
					}
				},
				new Test() {
					@Override
					public boolean test() {
						XmlParser.inport("myKey");
						return true;
					}
				},
			new Test() {
				@Override
				public boolean test() {
					String text = "MyCryptedText - my text to encrypt";
					String encrypted = enAndDecryption.MainPort.encrypt(text, "myKey or MyPassword123#66$32");
					p(encrypted);
					String decryptedWithWrongPassword = enAndDecryption.MainPort.decrypt(encrypted, "wrong key");
					p("decryptedWithWrongPassword:" + decryptedWithWrongPassword);
					String decrypted = enAndDecryption.MainPort.decrypt(encrypted, "myKey or MyPassword123#66$32");
					p(decrypted);
					return (text.equals(decrypted));
				}
			}
		};
		for (int i = START_TEST; i < tests.length; i++) {
			if (tests[i].test()) {
				p("Test " + i + " complete.");
			} else {
				p("Test " + i + " faild.");
			}
		}
	}
}
