package alkomat;

import javax.swing.*;
//import java.awt.*;
import java.awt.event.*;

class closeButton {

	static JButton button(String a){
		JButton b = new JButton(a);
		
		ActionListener al = new ActionListener(){
			public void actionPerformed(ActionEvent e){
				System.exit(0);
			}
		};
		
		b.addActionListener(al);
		return b;
	}
	
}
