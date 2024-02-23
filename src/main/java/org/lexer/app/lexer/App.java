package org.lexer.app.lexer;

import java.awt.EventQueue;

import org.lexer.app.view.Fr_Principal;

/**
 * Hello world!
 *
 */
public class App {
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Fr_Principal frame = new Fr_Principal();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
