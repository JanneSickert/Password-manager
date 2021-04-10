package test;

public class CryptoTest {
	
	private static void p(String str) {
		System.out.println(str);
	}
	
	public static void main(String[] args) {
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
			}	
		};
		
		for (int i = 0; i < tests.length; i++) {
			if (tests[i].test()) {
				p("Test " + i + " complete.");
			} else {
				p("Test " + i + " faild.");
			}
		}
	}
}
