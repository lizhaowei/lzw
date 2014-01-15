package lzw.cn.spell;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;

public class CnToSpell2GUI extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4925972733328591871L;
	private CnToSpell2GUI c = null;

	public CnToSpell2GUI() {
		super("Cn to Spell");
		setSize(800, 100);
		getContentPane().setLayout(new FlowLayout());
		// component layout
		JTextArea from = new JTextArea(5, 20);
		JTextArea to = new JTextArea(5, 20);
		JButton b = new JButton("cn to pinyin");
		getContentPane().add(new JLabel("From:"));
		getContentPane().add(from);
		getContentPane().add(b);
		getContentPane().add(new JLabel("To:"));
		getContentPane().add(to);
		// action handle
		b.addActionListener(new Cn2PinyinActionListener(from, to));
		setVisible(true);
		// set this for pack
		c = this;
	}

	/**
	 * button action listener to convert text to pinyin from one textbox to
	 * another textbox
	 */
	class Cn2PinyinActionListener implements ActionListener {

		private JTextArea from = null;
		private JTextArea to = null;

		public Cn2PinyinActionListener(JTextArea from, JTextArea to) {
			this.from = from;
			this.to = to;
		}

		public void actionPerformed(ActionEvent e) {
			if (from.getText().length() == 0) {
				JOptionPane.showMessageDialog(from, "From text is empty!",
						"Warning", JOptionPane.WARNING_MESSAGE);
			}
			String text = from.getText();
			to.setText(CNToSpell.getFullSpell(text));
			c.pack();
		}
	}

	public static void main(String[] args) {
		CnToSpell2GUI g = new CnToSpell2GUI();
		g.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
