package passwortManager;

import java.awt.event.KeyListener;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import java.awt.event.KeyEvent;

class PasswordInput extends JFrame{
	
	private static final long serialVersionUID = -229696129372892975L;
	private char[] password = null;
	
	String askPassword() {
		JPanel panel = new JPanel();
		final JPasswordField pf = new JPasswordField();
		final int X = 300, Y = 80;
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
	    setLocation(400, 50);
		setTitle("Password input field");
		setSize(X, Y);
		panel.setLayout(null);
		pf.setLocation(0, 0);
		pf.setSize(X, Y);
		pf.addKeyListener(new KeyListener() {
			
			public void keyPressed(KeyEvent arg0) {
				switch (arg0.getKeyCode()) {
					case KeyEvent.VK_ENTER:
						password = pf.getPassword();
						break;
				}
			}

			public void keyReleased(KeyEvent arg0) {
			}

			public void keyTyped(KeyEvent arg0) {
			}
		});
		add(pf);
		setVisible(true);
		while (password == null) {
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		dispose();
		return (new String(password));
	}
}