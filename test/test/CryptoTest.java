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
					enAndDecryption.primeNumbers.Port pr = new enAndDecryption.primeNumbers.Port();
					String text = "MySecondCryptedText";
					String key = "MyMasKey";
					char[] ctext = new char[text.length()];
					for (int i = 0; i < text.length(); i++) {
						ctext[i] = text.charAt(i);
					}
					char[] ckey = new char[key.length()];
					for (int i = 0; i < key.length(); i++) {
						ckey[i] = key.charAt(i);
					}
					char[] en = pr.encrypt(ctext, ckey);
					System.out.println("Encrypted text: " + new String(en));
					char[] de = pr.decrypt(en, ckey);
					System.out.println("Decrypted text: " + new String(de));
					return (new String(de).equals(text));
				}
			},
			new Test() {
				@Override
				public boolean test() {
					enAndDecryption.primeNumbers.Port pr = new enAndDecryption.primeNumbers.Port();
					String text = "MySecondCryptedText";
					String key = "MyMasKey";
					char[] ctext = new char[text.length()];
					for (int i = 0; i < text.length(); i++) {
						ctext[i] = text.charAt(i);
					}
					char[] ckey = new char[key.length()];
					for (int i = 0; i < key.length(); i++) {
						ckey[i] = key.charAt(i);
					}
					char[] en = pr.encrypt(ctext, ckey);
					System.out.println("Encrypted text: " + new String(en));
					key = "wrongPass";
					ckey = new char[key.length()];
					for (int i = 0; i < key.length(); i++) {
						ckey[i] = key.charAt(i);
					}
					char[] de = pr.decrypt(en, ckey);
					System.out.println("Decrypted text: " + new String(de));
					return (!(new String(de).equals(text)));
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
