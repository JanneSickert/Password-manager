package test;

import passwortManager.XmlParser;

public class CryptoTest {
	
	private static void p(String str) {
		System.out.println(str);
	}
	
	public static void main(String[] args) {
		final int START_TEST = 2;
		Test[] tests = new Test[] {
			new Test() {
				@Override
				public boolean test() {
					String text = "MyCryptedText";
					String encrypted = enAndDecryption.MainPort.encrypt(text, "myKey");
					p(encrypted);
					String decrypted = enAndDecryption.MainPort.decrypt(encrypted, "myKey");
					p(decrypted);
					return (text.equals(decrypted));
				}
			},
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
