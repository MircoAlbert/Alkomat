package alkomat;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JProgressBar;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;

public class MainFrame{
	JFrame f = new JFrame("Alkomat");
	@SuppressWarnings("unused")
	private static final long serialVersionUID = 1L;
	private boolean pwchanger = false;
	String stern = "*";
	int x = 0, y = 0, b = 165, h = 40, lab = 1;
	JButton close = alkomat.closeButton.button("Close");
	JLabel wartenOben = new JLabel("Bitte Warten!");
	JLabel wartenUnten = new JLabel("Cocktail wird gemixt!");
	JLabel passwordField = new JLabel("", JLabel.CENTER);
	JLabel pwChangeLabel = new JLabel("", JLabel.CENTER);
	JLabel behaelter1 = new JLabel("Behaelter 1:", JLabel.CENTER);
	JLabel behaelter2 = new JLabel("Behaelter 2:", JLabel.CENTER);
	JLabel behaelter3 = new JLabel("Behaelter 3:", JLabel.CENTER);
	JLabel behaelter4 = new JLabel("Behaelter 4:", JLabel.CENTER);
	JLabel behaelter5 = new JLabel("Behaelter 5:", JLabel.CENTER);
	JLabel behaelter6 = new JLabel("Behaelter 6:", JLabel.CENTER);
	Border border = LineBorder.createGrayLineBorder();
	JProgressBar bar1 = new JProgressBar(0, 100);
	JProgressBar bar2 = new JProgressBar(0, 100);
	JProgressBar bar3 = new JProgressBar(0, 100);
	JProgressBar bar4 = new JProgressBar(0, 100);
	JProgressBar bar5 = new JProgressBar(0, 100);
	JProgressBar bar6 = new JProgressBar(0, 100);
	JPanel panelPW = new JPanel();
	JPanel panelMenue = new JPanel();
	JPanel panelZutatenAuswahl = new JPanel();
	JPanel panelWait = new JPanel();
	JPanel panelChangePW = new JPanel();
	JPanel aktPanel;
	JButton n1 = ZahlButton("1");
	JButton n2 = ZahlButton("2");
	JButton n3 = ZahlButton("3");
	JButton n4 = ZahlButton("4");
	JButton n5 = ZahlButton("5");
	JButton n6 = ZahlButton("6");
	JButton n7 = ZahlButton("7");
	JButton n8 = ZahlButton("8");
	JButton n9 = ZahlButton("9");
	JButton cocktails = new JButton("Cocktails");
	JButton reinigung = new JButton("Reinigungsprogramm");
	JButton menue = MenueButton("Menue", 345, h, b, h);
	JButton cancel = CancelButton("Cancel", 514, h, b, h);
	JPasswordField pass = new JPasswordField(4);
	// JButton[] zahlfeld = new JButton[10];
	String PIN = "";
	String cocktail = "";
	String auswahl = "";
	String pwChange = "";

	String[] zutatenliste = { "", "Coca-Cola", "Orangensaft", "Wodka", "Rum", "Tequila", "Maracujasaft", "Grenadine",
			"Zitronensaft", "Gin", "Soda" };
	String[] auswahl_zutat = new String[6];
	String[] auswahl_zutat_akt = new String[6];
	Cocktail[] cocktailsmoeglich;
	String[] cocktailsmoeglichString;
	List<Cocktail> cocktailsmoeglichList = new ArrayList<Cocktail>();
	int[] index = new int[6];
	SortedComboBoxModel<String> model1 = new SortedComboBoxModel<String>(zutatenliste);
	SortedComboBoxModel<String> model2 = new SortedComboBoxModel<String>(zutatenliste);
	SortedComboBoxModel<String> model3 = new SortedComboBoxModel<String>(zutatenliste);
	SortedComboBoxModel<String> model4 = new SortedComboBoxModel<String>(zutatenliste);
	SortedComboBoxModel<String> model5 = new SortedComboBoxModel<String>(zutatenliste);
	SortedComboBoxModel<String> model6 = new SortedComboBoxModel<String>(zutatenliste);
	JComboBox<String> comboBox_1 = new JComboBox<String>(model1);
	JComboBox<String> comboBox_2 = new JComboBox<String>(model2);
	JComboBox<String> comboBox_3 = new JComboBox<String>(model3);
	JComboBox<String> comboBox_4 = new JComboBox<String>(model4);
	JComboBox<String> comboBox_5 = new JComboBox<String>(model5);
	JComboBox<String> comboBox_6 = new JComboBox<String>(model6);
	Icon loadingGif = new ImageIcon(
			Toolkit.getDefaultToolkit().getImage(getClass().getClassLoader().getResource("res/giphy2.gif")));
	JLabel loadingLabel = new JLabel(loadingGif);

	File zutaten;
	File hash = new File("./hash.txt");
	FileWriter writer;

	List<JPanel> cpanels = new ArrayList<JPanel>();
	
	PwCheck pwcheck = new PwCheck();
	
	PumpenAnsteuerung pumpenAnsteuerung = new PumpenAnsteuerung();

	JButton MenueButton(String a, int x, int y, int b, int h) // MenüButton
	{
		JButton cb = new JButton(a);
		cb.setBounds(x, y, b, h);

		ActionListener al = new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				menue.setVisible(false);
				aktPanel.setVisible(false);
				panelMenue.setVisible(true);
			}
		};
		cb.addActionListener(al);
		return cb;

	}
	
	JButton CancelButton(String a, int x, int y, int b, int h) // MenüButton
	{
		JButton cb = new JButton(a);
		cb.setBounds(x, y, b, h);

		ActionListener al = new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				cancel.setVisible(false);
				pumpenAnsteuerung.cancelTimer(aktPanel, panelWait);
				
			}
		};
		cb.addActionListener(al);
		return cb;

	}

	JButton AuswahlButton(String a, int x, int y, int b, int h) //
	{
		JButton cb = new JButton(a);
		cb.setBounds(x, y, b, h);
		final String[] ca = { "" };
		ca[0] = a;

		ActionListener al = new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				auswahl = ca[0];

				if (auswahl.equals("Cocktails")) {
					panelMenue.setVisible(false);
					cpanels.get(0).setVisible(true);
					menue.setVisible(true);
					aktPanel = cpanels.get(0);
				}
				if (auswahl.equals("Reinigungsmodus")) {

					// bar1.setValue(serialcomm.comm()[0]);
				}

				if (auswahl.equals("<html><center>Zutaten<br>aendern</center></html>")) {
					panelZutatenAuswahl.setVisible(true);
					panelMenue.setVisible(false);
					aktPanel = panelZutatenAuswahl;
				}
				if (auswahl.equals("Speichern")) {
					panelZutatenAuswahl.setVisible(false);
					panelMenue.setVisible(true);
					menue.setVisible(false);
					aktPanel = panelMenue;
					auswahl_zutat_akt = auswahl_zutat.clone();
					for (String s : auswahl_zutat_akt)
						System.out.println(s);

					try {
						cocktailsmoeglichList = (new CocktailPruefen()).loadCocktail(auswahl_zutat_akt);
					} catch (IOException e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
					}

					if (!(cocktailsmoeglichList==null)) {
						cocktailsmoeglichString = new String[cocktailsmoeglichList.size()];
						for (int i = 0; i < cocktailsmoeglichList.size(); i++)
							cocktailsmoeglichString[i] = cocktailsmoeglichList.get(i).getName();
						for (String s : cocktailsmoeglichString)
							System.out.println(s);

						CocktailButtonProperties(cocktailsmoeglichList.toArray(new Cocktail[0]));
					} else {
						System.out.println("keine Cocktails möglich!");
					}
					try {
						String x = null;
						FileUtils.writeStringToFile(zutaten, StringUtils.join(auswahl_zutat_akt, ","), x);
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

				}
				if (auswahl.equals("Abbrechen")) {
					for (String s : auswahl_zutat_akt)
						System.out.println(s);
					panelZutatenAuswahl.setVisible(false);
					panelMenue.setVisible(true);
					menue.setVisible(false);
					aktPanel = panelMenue;
				}
				
				if(auswahl.equals("<html><center>Passwort<br>aendern</center></html>")){
					panelMenue.setVisible(false);
					panelPW.setVisible(true);
					aktPanel = panelPW;
					pwchanger=true;
				}
				
				if(auswahl.equals(" Speichern ")){
					panelChangePW.setVisible(false);
					panelMenue.setVisible(true);
					aktPanel = panelMenue;
					try {
						pwcheck.changePW(pwChange);
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					pwChange="";
					pwchanger=false;
					pwChangeLabel.setText("");
				}
				if(auswahl.equals(" Abbrechen ")){
					panelChangePW.setVisible(false);
					panelMenue.setVisible(true);
					aktPanel = panelMenue;
					pwChange="";
					pwchanger=false;
					pwChangeLabel.setText("");
				}

			}
		};
		cb.addActionListener(al);
		return cb;

	}

	void CancelButtonProperties(){
		if(panelWait.isVisible()==true){
			cancel.setVisible(true);
		}
		else
			cancel.setVisible(false);
	}
	
	void CocktailButtonProperties(Cocktail[] cocktails) {
		int anzahlButtons = cocktails.length;
		int buttoncounter = 1, seitencounter = 0;
		int startcoordsX = 0, startcoordsY = 0;
		int breite = 864 / 3, höhe = 460 / 2, zwischenraum = 1;
		int odd = 0, even = 0;
		int i = 0;
		cpanels.get(0).removeAll();
		cpanels.get(0).revalidate();
		cpanels.get(0).repaint();

		System.out.println(anzahlButtons);
		while (i <= anzahlButtons) {

			if ((buttoncounter % 2 == 1) && (buttoncounter <= anzahlButtons)) {
				System.out.println(buttoncounter + "%2=1");
				cpanels.get(seitencounter).add(CocktailButton(cocktails[buttoncounter - 1],
						(startcoordsX + breite + zwischenraum) * odd + 40, startcoordsY, breite, höhe));
				buttoncounter++;
				odd++;
			}
			if ((buttoncounter % 2 == 0) && (buttoncounter <= anzahlButtons)) {
				System.out.println(buttoncounter);
				cpanels.get(seitencounter)
						.add(CocktailButton(cocktails[buttoncounter - 1],
								(startcoordsX + breite + zwischenraum) * even + 40, startcoordsY + höhe + zwischenraum,
								breite, höhe));
				even++;
				if ((buttoncounter % 6 == 0) && (buttoncounter <= anzahlButtons)) {
					odd = 0;
					even = 0;
					seitencounter++;
					cpanels.add(new JPanel());
					cpanels.get(seitencounter).setBounds(40, 80, 1024 - 80, 460);
					cpanels.get(seitencounter).setBackground(new Color(255, 149, 14));
					cpanels.get(seitencounter).setVisible(false);
					cpanels.get(seitencounter).setLayout(null);
					f.add(cpanels.get(seitencounter));
					cpanels.get(seitencounter).add(vorherigeSeiteButton(seitencounter - 1, seitencounter));
					cpanels.get(seitencounter - 1).add(naechsteSeiteButton(seitencounter, seitencounter - 1));

					System.out.println("Neue Seite");
				}
				buttoncounter++;

			}
			i++;
		}
	}

	JButton CocktailButton(final Cocktail a, int x, int y, int b, int h) // zur
																			// Erzeugung
																			// der
																			// Cocktail-Buttons
	{
		JButton cb = new JButton(a.getName());
		cb.setBounds(x, y, b, h);

		ActionListener al = new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				Double[] mengenGeordnet = (new MengenPruefen()).mengenOrdnen(a.getRezept(), auswahl_zutat_akt,
						a.getMengen());
				for(int i=0; i<mengenGeordnet.length;i++)
				System.out.println(mengenGeordnet[i].toString());
				
				pumpenAnsteuerung.start(mengenGeordnet, aktPanel, panelWait, cancel, menue);
				menue.setVisible(false);		
				CancelButtonProperties();
			}

		};

		cb.addActionListener(al);
		return cb;
	}

	JButton ZahlButton(String a) // zur Erzeugung der PIN-Abfrage-Buttons
	{
		JButton b = new JButton(a);
		final String[] c = { "" };
		c[0] = a;

		ActionListener al = new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				PIN = PIN + c[0];
				passwordField.setText(stern);

				try {
					if (pwcheck.checkPW(PIN)) 
					{
						if(pwchanger){
							panelPW.setVisible(false);
							panelChangePW.setVisible(true);
						}
						else{
						panelPW.setVisible(false);
						panelMenue.setVisible(true);
						}
					}
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

				try {
					if (pwcheck.checkLength(PIN)) {
						passwordField.setText("");
						PIN="";
						stern="";

					}
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				stern = stern + "*";

			}
		};

		b.addActionListener(al);
		return b;
	}

	JButton naechsteSeiteButton(final int next, final int akt) {
		JButton cb = new JButton("V");
		cb.setBounds(904, 0, 40, 460);
		ActionListener al = new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				cpanels.get(next).setVisible(true);
				cpanels.get(akt).setVisible(false);
				aktPanel = cpanels.get(next);
			}
		};
		cb.addActionListener(al);
		return cb;

	}

	JButton vorherigeSeiteButton(final int last, final int akt) {
		JButton cb = new JButton("Z");
		cb.setBounds(0, 0, 40, 460);
		ActionListener al = new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				cpanels.get(last).setVisible(true);
				cpanels.get(akt).setVisible(false);
				aktPanel = cpanels.get(last);
			}
		};
		cb.addActionListener(al);
		return cb;

	}

	JButton pwChangeButton(String a, int x, int y, int b, int h){
		JButton cb = new JButton(a);
		cb.setBounds(x, y, b, h);
		final String[] c = { "" };
		c[0] = a;
		
		ActionListener al = new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				pwChange = pwChange+c[0];
				pwChangeLabel.setText(pwChange);
			}
			
		};
		cb.addActionListener(al);
		return cb;
	}
	
	MainFrame()  throws UnsupportedEncodingException, IOException {
		
			
		f.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getClassLoader().getResource("res/Icon.png")));
		close.setBounds(852, h, b, h);
		f.getContentPane().add(close);

		cpanels.add(new JPanel());
		cpanels.get(0).setBounds(40, 80, 1024 - 80, 460);
		cpanels.get(0).setBackground(new Color(255, 149, 14));
		cpanels.get(0).setVisible(false);
		cpanels.get(0).setLayout(null);
		f.getContentPane().add(cpanels.get(0));

		bar1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		bar1.setStringPainted(true);
		f.getContentPane().add(bar1);
		bar1.setBounds(7, y, 165, h);
		bar1.setString("Behaelter 1");

		bar2.setFont(new Font("Tahoma", Font.PLAIN, 18));
		bar2.setStringPainted(true);
		f.getContentPane().add(bar2);
		bar2.setBounds(176, y, 165, h);
		bar2.setString("Behaelter 2");

		bar3.setFont(new Font("Tahoma", Font.PLAIN, 18));
		bar3.setStringPainted(true);
		f.getContentPane().add(bar3);
		bar3.setBounds(345, y, 165, h);
		bar3.setString("Behaelter 3");

		bar4.setFont(new Font("Tahoma", Font.PLAIN, 18));
		bar4.setStringPainted(true);
		f.getContentPane().add(bar4);
		bar4.setBounds(514, y, 165, h);
		bar4.setString("Behaelter 4");

		bar5.setFont(new Font("Tahoma", Font.PLAIN, 18));
		bar5.setStringPainted(true);
		f.getContentPane().add(bar5);
		bar5.setBounds(683, y, 165, h);
		bar5.setString("Behaelter 5");

		bar6.setFont(new Font("Tahoma", Font.PLAIN, 18));
		bar6.setStringPainted(true);
		f.getContentPane().add(bar6);
		bar6.setBounds(852, y, 165, h);
		bar6.setString("Behaelter 6");

		panelPW.setLayout(null);
		panelPW.setBounds(40, 80, 1024 - 80, 460);
		panelPW.setBackground(new Color(255, 149, 14));

		f.getContentPane().add(menue);
		menue.setVisible(false);
		
		f.getContentPane().add(cancel);
		cancel.setVisible(false);
		
		panelMenue.setBounds(40, 80, 1024 - 80, 460);
		panelMenue.setBackground(new Color(255, 149, 14));
		panelMenue.setVisible(false);
		panelMenue.setLayout(null);
		f.getContentPane().add(panelMenue);

		panelZutatenAuswahl.setBounds(40, 80, 1024 - 80, 460);
		panelZutatenAuswahl.setBackground(new Color(255, 149, 14));
		panelZutatenAuswahl.setVisible(false);
		panelZutatenAuswahl.setLayout(null);
		f.getContentPane().add(panelZutatenAuswahl);
		
		panelChangePW.setBounds(40, 80, 1024 - 80, 460);
		panelChangePW.setBackground(new Color(255, 149, 14));
		panelChangePW.setVisible(false);
		panelChangePW.setLayout(null);
		f.getContentPane().add(panelChangePW);

		panelWait.setBounds(40, 80, 1024 - 80, 460);
		panelWait.setBackground(new Color(255, 149, 14));
		panelWait.setVisible(false);
		wartenOben.setHorizontalAlignment(JLabel.CENTER);
		wartenOben.setVerticalAlignment(JLabel.CENTER);
		wartenUnten.setHorizontalAlignment(JLabel.CENTER);
		wartenUnten.setVerticalAlignment(JLabel.CENTER);
		loadingLabel.setHorizontalAlignment(JLabel.CENTER);
		loadingLabel.setVerticalAlignment(JLabel.CENTER);
		panelWait.setLayout(new BorderLayout());
		panelWait.add(wartenOben, BorderLayout.PAGE_START);
		panelWait.add(wartenUnten, BorderLayout.PAGE_END);
		panelWait.add(loadingLabel, BorderLayout.CENTER);
		f.add(panelWait);

		n1.setBounds(0 + 352, 0 + 220, 80, 80);
		n2.setBounds(80 + 352, 0 + 220, 80, 80);
		n3.setBounds(160 + 352, 0 + 220, 80, 80);
		n4.setBounds(0 + 352, 80 + 220, 80, 80);
		n5.setBounds(80 + 352, 80 + 220, 80, 80);
		n6.setBounds(160 + 352, 80 + 220, 80, 80);
		n7.setBounds(0 + 352, 160 + 220, 80, 80);
		n8.setBounds(80 + 352, 160 + 220, 80, 80);
		n9.setBounds(160 + 352, 160 + 220, 80, 80);
		passwordField.setBounds(352, 180, 240, 40);

		panelPW.add(n1);
		panelPW.add(n2);
		panelPW.add(n3);
		panelPW.add(n4);
		panelPW.add(n5);
		panelPW.add(n6);
		panelPW.add(n7);
		panelPW.add(n8);
		panelPW.add(n9);
		panelPW.add(passwordField);
		
		panelMenue.setLayout(null);
		panelMenue.add(AuswahlButton("Cocktails", 315, 50, 157, 175));
		panelMenue.add(AuswahlButton("<html><center>Zutaten<br>aendern</center></html>", 472, 50, 157, 175));
		panelMenue.add(AuswahlButton("Reinigungsmodus", 315, 225, 157, 175));
		panelMenue.add(AuswahlButton("<html><center>Passwort<br>aendern</center></html>", 472,225,157,175));

		comboBox_1.addItemListener(new MyItemListener(model2, model3, model4, model5, model6, 0, auswahl_zutat));
		comboBox_2.addItemListener(new MyItemListener(model1, model3, model4, model5, model6, 1, auswahl_zutat));
		comboBox_3.addItemListener(new MyItemListener(model2, model1, model4, model5, model6, 2, auswahl_zutat));
		comboBox_4.addItemListener(new MyItemListener(model2, model3, model1, model5, model6, 3, auswahl_zutat));
		comboBox_5.addItemListener(new MyItemListener(model2, model3, model4, model1, model6, 4, auswahl_zutat));
		comboBox_6.addItemListener(new MyItemListener(model2, model3, model4, model5, model1, 5, auswahl_zutat));
		comboBox_1.setBounds(20, 99, 288, 66);
		comboBox_2.setBounds(328, 99, 288, 66);
		comboBox_3.setBounds(636, 99, 288, 66);
		comboBox_4.setBounds(20, 250, 288, 66);
		comboBox_5.setBounds(328, 250, 288, 66);
		comboBox_6.setBounds(636, 250, 288, 66);
		
		behaelter1.setBounds(20, 50, 288, 66);
		behaelter2.setBounds(328, 50, 288, 66);
		behaelter3.setBounds(636, 50, 288, 66);
		behaelter4.setBounds(20, 201, 288, 66);
		behaelter5.setBounds(328, 201, 288, 66);
		behaelter6.setBounds(636, 201, 288, 66);
		behaelter1.setFont(new Font(null, Font.BOLD, 30));
		behaelter2.setFont(new Font(null, Font.BOLD, 30));
		behaelter3.setFont(new Font(null, Font.BOLD, 30));
		behaelter4.setFont(new Font(null, Font.BOLD, 30));
		behaelter5.setFont(new Font(null, Font.BOLD, 30));
		behaelter6.setFont(new Font(null, Font.BOLD, 30));
		
		panelZutatenAuswahl.add(AuswahlButton("Speichern", 312, 380, 160, 80));
		panelZutatenAuswahl.add(AuswahlButton("Abbrechen", 472, 380, 160, 80));
		panelZutatenAuswahl.add(behaelter1);
		panelZutatenAuswahl.add(behaelter2);
		panelZutatenAuswahl.add(behaelter3);
		panelZutatenAuswahl.add(behaelter4);
		panelZutatenAuswahl.add(behaelter5);
		panelZutatenAuswahl.add(behaelter6);
		panelZutatenAuswahl.add(comboBox_1);
		panelZutatenAuswahl.add(comboBox_2);
		panelZutatenAuswahl.add(comboBox_3);
		panelZutatenAuswahl.add(comboBox_4);
		panelZutatenAuswahl.add(comboBox_5);
		panelZutatenAuswahl.add(comboBox_6);
		
		panelChangePW.add(AuswahlButton(" Speichern ", 312,380,160,80));
		panelChangePW.add(AuswahlButton(" Abbrechen ", 472, 380, 160, 80));
		panelChangePW.add(pwChangeButton("1",352,140,80,80));
		panelChangePW.add(pwChangeButton("2",432,140,80,80));
		panelChangePW.add(pwChangeButton("3",512,140,80,80));
		panelChangePW.add(pwChangeButton("4",352,220,80,80));
		panelChangePW.add(pwChangeButton("5",432,220,80,80));
		panelChangePW.add(pwChangeButton("6",512,220,80,80));
		panelChangePW.add(pwChangeButton("7",352,300,80,80));
		panelChangePW.add(pwChangeButton("8",432,300,80,80));
		panelChangePW.add(pwChangeButton("9",512,300,80,80));
		pwChangeLabel.setBounds(352, 100, 240, 40);
		panelChangePW.add(pwChangeLabel);
		
		f.getContentPane().add(panelPW);

		// f.setExtendedState(JFrame.MAXIMIZED_BOTH); //funktioniert nicht in
		// Kombination mit setResizeable(false)
		f.getContentPane().setLayout(null);
		f.setResizable(false);
		//f.setUndecorated(true); //entfernt Programmleiste, für echtes
		// Vollbild
		f.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		f.setSize(1024, 600);
		f.setVisible(true);

	}

	public static void main(String[] args) throws IOException {
		MainFrame GUI = new MainFrame();
		// new Fuellstand(GUI.bar1, GUI.bar2, GUI.bar3, GUI.bar4, GUI.bar5, GUI.bar6);
		GUI.zutaten = new File("./Zutaten.txt");
		GUI.hash = new File("./hash.txt");
		if(!GUI.hash.exists()){
			List<String> hashcodes = new ArrayList<String>();
			hashcodes.add(GUI.pwcheck.get_SHA_512_SecurePassword("1234", "alkomat"));
			hashcodes.add(GUI.pwcheck.get_SHA_512_SecurePassword(String.valueOf("4"), "alkomat"));
			FileUtils.writeLines(GUI.hash, hashcodes);
			System.out.println("Standard-Passwort gesetzt");
		}
		GUI.auswahl_zutat_akt = (new ZutatenLesen()).leseZutaten();
		for (String s : GUI.auswahl_zutat_akt)
			System.out.println(s);
		GUI.cocktailsmoeglichList = (new CocktailPruefen()).loadCocktail(GUI.auswahl_zutat_akt);
		
		if (!(GUI.cocktailsmoeglichList==null)) {
			System.out.println(GUI.cocktailsmoeglichList.size());
			GUI.cocktailsmoeglichString = new String[GUI.cocktailsmoeglichList.size()];
			for (int i = 0; i < GUI.cocktailsmoeglichList.size(); i++)
				GUI.cocktailsmoeglichString[i] = GUI.cocktailsmoeglichList.get(i).getName();
			for (String s : GUI.cocktailsmoeglichString)
				System.out.println(s);
			GUI.CocktailButtonProperties(GUI.cocktailsmoeglichList.toArray(new Cocktail[0]));
			
		} else {
			System.out.println("keine Cocktails möglich!");
		}
	}
}
