package alkomat;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.File;
import java.io.FileNotFoundException;
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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JProgressBar;
import javax.swing.JToggleButton;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import bildschirmtastatur.gui.TastaturPanel;;

public class MainFrame{
	JFrame f = new JFrame("Alkomat");
	@SuppressWarnings("unused")
	private static final long serialVersionUID = 1L;
	private boolean pwchanger = false;
	String stern = "*";
	int x = 0, y = 0, b = 165, h = 40, lab = 1;
	int fuellmengeGlas = 150;
	JButton close = alkomat.closeButton.button("Close");
	JLabel wartenOben = new JLabel("Bitte Warten!");
	JLabel wartenUnten = new JLabel("Cocktail wird gemixt!");
	JLabel passwordField = new JLabel("", JLabel.CENTER);
	JLabel pwChangeLabel = new JLabel("", JLabel.CENTER);
	JLabel behaelter1 = new JLabel("Behälter 1:", JLabel.CENTER);
	JLabel behaelter2 = new JLabel("Behälter 2:", JLabel.CENTER);
	JLabel behaelter3 = new JLabel("Behälter 3:", JLabel.CENTER);
	JLabel behaelter4 = new JLabel("Behälter 4:", JLabel.CENTER);
	JLabel behaelter5 = new JLabel("Behälter 5:", JLabel.CENTER);
	JLabel behaelter6 = new JLabel("Behälter 6:", JLabel.CENTER);
	JLabel altesPW = new JLabel("<html><center>Bitte alte PIN<br>eingeben!</center></html>", JLabel.CENTER);
	JLabel neuesPW = new JLabel("<html><center>Bitte neue PIN<br>eingeben!</center></html>", JLabel.CENTER);
	JLabel fuellmengenEingabe = new JLabel("<html><center>Bitte Füllmenge<br>auswählen<br>(in ml):</center></html>", JLabel.CENTER);
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
	JPanel reinigungsPanel = new JPanel();
	JButton n1 = zahlButton("1");
	JButton n2 = zahlButton("2");
	JButton n3 = zahlButton("3");
	JButton n4 = zahlButton("4");
	JButton n5 = zahlButton("5");
	JButton n6 = zahlButton("6");
	JButton n7 = zahlButton("7");
	JButton n8 = zahlButton("8");
	JButton n9 = zahlButton("9");
	JButton cocktails = new JButton("Cocktails");
	JButton reinigung = new JButton("Reinigungsprogramm");
	JButton menue;
	JButton cancel;
	JButton sperren;
	JButton fuellmenge150;// = fuellmenge150("150",120,120,40,60);
	JButton fuellmenge200;// = fuellmenge150("150",160,120,40,60);
	JButton fuellmenge250;// = fuellmenge150("150",200,120,40,60);
	JButton fuellmenge300;// = fuellmenge150("150",240,120,40,60);
	JPasswordField pass = new JPasswordField(4);
	// JButton[] zahlfeld = new JButton[10];
	String PIN = "";
	String cocktail = "";
	String auswahl = "";
	String pwChange = "";

	String[] zutatenliste= ((new ZutatenListeLesen()).zutatenAusCocktailListe()).toArray(new String[0]);;// = { "", "Coca-Cola", "Orangensaft", "Wodka", "Rum", "Tequila", "Maracujasaft", "Grenadine", "Zitronensaft", "Gin", "Soda" };
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
	JComboBox<Integer> fuellmenge = new JComboBox<Integer>();
	
	
	Icon loadingGif = new ImageIcon(
			Toolkit.getDefaultToolkit().getImage(getClass().getClassLoader().getResource("res/giphy2.gif")));
	JLabel loadingLabel = new JLabel(loadingGif);
	Icon naechsteSeite = new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getClassLoader().getResource("res/SeitenChangeButtonVorwärts.png")));
	Icon vorherigeSeite = new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getClassLoader().getResource("res/SeitenChangeButtonZurück.png")));
	Icon cocktailsIcon = new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getClassLoader().getResource("res/cocktails.jpg")));
	Icon zutatenIcon = new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getClassLoader().getResource("res/zutaten.jpg")));
	Icon reinigungsIcon = new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getClassLoader().getResource("res/reinigung.jpg")));
	Icon passwortIcon = new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getClassLoader().getResource("res/passwort1.jpg")));
	
	Icon cocktailButtonIcon = new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getClassLoader().getResource("res/CocktailButton.png")));
	Icon menueButtonIcon = new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getClassLoader().getResource("res/MenueButton.png")));
	Icon auswahlButtonIcon = new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getClassLoader().getResource("res/AuswahlButton.png")));
	Icon zahlButtonIcon = new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getClassLoader().getResource("res/ZahlButton.png")));
	Icon backgroundIcon = new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getClassLoader().getResource("res/background.jpg")));
	ImageIcon glasVoll = new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getClassLoader().getResource("res/glasvoll.png")));
	ImageIcon glasLeer = new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getClassLoader().getResource("res/glasleer.png")));
	
	Icon fuellmenge150IconVoll = new ImageIcon(glasVoll.getImage().getScaledInstance(45, 68,java.awt.Image.SCALE_SMOOTH));
	Icon fuellmenge150IconLeer = new ImageIcon(glasLeer.getImage().getScaledInstance(45, 68,java.awt.Image.SCALE_SMOOTH));
	Icon fuellmenge200IconVoll = new ImageIcon(glasVoll.getImage().getScaledInstance(50, 76,java.awt.Image.SCALE_SMOOTH));
	Icon fuellmenge200IconLeer = new ImageIcon(glasLeer.getImage().getScaledInstance(50, 76,java.awt.Image.SCALE_SMOOTH));
	Icon fuellmenge250IconVoll = new ImageIcon(glasVoll.getImage().getScaledInstance(55, 84,java.awt.Image.SCALE_SMOOTH));
	Icon fuellmenge250IconLeer = new ImageIcon(glasLeer.getImage().getScaledInstance(55, 84,java.awt.Image.SCALE_SMOOTH));
	Icon fuellmenge300IconVoll = new ImageIcon(glasVoll.getImage().getScaledInstance(60, 91,java.awt.Image.SCALE_SMOOTH));
	Icon fuellmenge300IconLeer = new ImageIcon(glasLeer.getImage().getScaledInstance(60, 91,java.awt.Image.SCALE_SMOOTH));
	
	
	JLabel background = new JLabel();
		
	File zutaten = new File("./res/Zutaten.txt");
	File hash = new File("./res/hash.txt");
	FileWriter writer;

	List<JPanel> cpanels = new ArrayList<JPanel>();
	
	PwCheck pwcheck = new PwCheck();
	
	PumpenAnsteuerung pumpenAnsteuerung = new PumpenAnsteuerung();
	
	MyBorder buttonBorder = new MyBorder();
	
	CocktailPruefen cocktailPruefen = new CocktailPruefen();
	
	TastaturPanel tastatur = new TastaturPanel();
	
	JButton menueButton(String a, int x, int y, int b, int h) // MenüButton zum rückkehren ins Hauptmenü
	{
		JButton cb = new JButton(a, menueButtonIcon);
		cb.setHorizontalTextPosition(JButton.CENTER);
		cb.setVerticalTextPosition(JButton.CENTER);
		cb.setOpaque(false);
		cb.setContentAreaFilled(false);
		cb.setBounds(x, y, b, h);
		cb.setBorderPainted(false);
		

		ActionListener al = new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				menue.setVisible(false);
				aktPanel.setVisible(false);
				panelMenue.setVisible(true);
				PIN="";
				aktPanel=panelMenue;
			}
		};
		cb.addActionListener(al);
		return cb;

	}
	
	JButton cancelButton(String a, int x, int y, int b, int h) // CancelButton zum abbrechen eines Mixvorgangs
	{
		JButton cb = new JButton(a, menueButtonIcon);
		cb.setHorizontalTextPosition(JButton.CENTER);
		cb.setVerticalTextPosition(JButton.CENTER);
		cb.setOpaque(false);
		cb.setContentAreaFilled(false);
		cb.setBounds(x, y, b, h);
		cb.setBorderPainted(false);
		

		ActionListener al = new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				cancel.setVisible(false);
				pumpenAnsteuerung.cancelTimer(aktPanel, panelWait);
				
			}
		};
		cb.addActionListener(al);
		return cb;

	}

	JButton menueButtonCocktails(String a, int x, int y, int b, int h) // Button um das Cocktail-Menü aufzurufen
	{
		
		JButton cb = new JButton(a, cocktailsIcon);
		cb.setHorizontalTextPosition(JButton.CENTER);
		cb.setVerticalTextPosition(JButton.CENTER);
		cb.setBounds(x, y, b, h);
		
		ActionListener al = new ActionListener(){
			public void actionPerformed(ActionEvent e){
				panelMenue.setVisible(false);
				cpanels.get(0).setVisible(true);
				menue.setVisible(true);
				aktPanel = cpanels.get(0);
			}
			
		};
		cb.addActionListener(al);
		return cb;
		
	}
	
	JButton menueButtonZutaten(String a, int x, int y, int b, int h){
		
		JButton cb = new JButton(a, zutatenIcon);
		cb.setHorizontalTextPosition(JButton.CENTER);
		cb.setVerticalTextPosition(JButton.CENTER);
		cb.setBounds(x, y, b, h);
		
		ActionListener al = new ActionListener(){
			public void actionPerformed(ActionEvent e){
				panelZutatenAuswahl.setVisible(true);
				panelMenue.setVisible(false);
				aktPanel = panelZutatenAuswahl;
			}
			
		};
		cb.addActionListener(al);
		return cb;
		
	}

	JButton menueButtonReinigung(String a, int x, int y, int b, int h){
		
		JButton cb = new JButton(a, reinigungsIcon);
		cb.setHorizontalTextPosition(JButton.CENTER);
		cb.setVerticalTextPosition(JButton.CENTER);
		cb.setBounds(x, y, b, h);
		
		ActionListener al = new ActionListener(){
			public void actionPerformed(ActionEvent e){
				panelMenue.setVisible(false);
				reinigungsPanel.setVisible(true);
				menue.setVisible(true);
				aktPanel=reinigungsPanel;
			}
			
		};
		cb.addActionListener(al);
		return cb;
		
	}
	
	JButton menueButtonPasswortAendern(String a, int x, int y, int b, int h){
		
		JButton cb = new JButton(a, passwortIcon);
		cb.setHorizontalTextPosition(JButton.CENTER);
		cb.setVerticalTextPosition(JButton.CENTER);
		cb.setBounds(x, y, b, h);
		
		ActionListener al = new ActionListener(){
			public void actionPerformed(ActionEvent e){
				panelMenue.setVisible(false);
				panelPW.setVisible(true);
				menue.setVisible(true);
				aktPanel = panelPW;
				pwchanger=true;
				altesPW.setVisible(true);
			}
			
		};
		cb.addActionListener(al);
		return cb;
		
	}
	
	JButton auswahlButton(String a, int x, int y, int b, int h) //
	{
		JButton cb;
		cb = new JButton(a, auswahlButtonIcon);
		cb.setBounds(x, y, b, h);
		cb.setHorizontalTextPosition(JButton.CENTER);
		cb.setVerticalTextPosition(JButton.CENTER);
		cb.setOpaque(false);
		cb.setContentAreaFilled(false);
		cb.setBorderPainted(false);
		
		final String[] ca = { "" };
		ca[0] = a;

		ActionListener al = new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				auswahl = ca[0];

				if (auswahl.equals("Speichern")) {
					panelZutatenAuswahl.setVisible(false);
					panelMenue.setVisible(true);
					menue.setVisible(false);
					aktPanel = panelMenue;
					auswahl_zutat_akt = auswahl_zutat.clone();
					for (String s : auswahl_zutat_akt)
						System.out.println(s);

					try {
						cocktailsmoeglichList = cocktailPruefen.loadCocktail(auswahl_zutat_akt);
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
						cpanels.get(0).removeAll();
						cpanels.get(0).revalidate();
						cpanels.get(0).repaint();
						System.out.println("keine Cocktails möglich!");
						JOptionPane.showMessageDialog(f, "Keine Cocktails möglich!","Keine Cocktails möglich", JOptionPane.WARNING_MESSAGE);
					}
					try {
						String x = null;
						FileUtils.writeStringToFile(zutaten, StringUtils.join(auswahl_zutat_akt, ","), x);
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					bar1.setString("1 - "+auswahl_zutat_akt[0]);
					bar2.setString("2 - "+auswahl_zutat_akt[1]);
					bar3.setString("3 - "+auswahl_zutat_akt[2]);
					bar4.setString("4 - "+auswahl_zutat_akt[3]);
					bar5.setString("5 - "+auswahl_zutat_akt[4]);
					bar6.setString("6 - "+auswahl_zutat_akt[5]);

				}
				if (auswahl.equals("Abbrechen")) {
					for (String s : auswahl_zutat_akt)
						System.out.println(s);
					panelZutatenAuswahl.setVisible(false);
					panelMenue.setVisible(true);
					menue.setVisible(false);
					aktPanel = panelMenue;
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
					sperren.setVisible(true);
				}
				if(auswahl.equals(" Abbrechen ")){
					panelChangePW.setVisible(false);
					panelMenue.setVisible(true);
					aktPanel = panelMenue;
					pwChange="";
					pwchanger=false;
					pwChangeLabel.setText("");
					sperren.setVisible(true);
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
				cpanels.get(seitencounter).add(cocktailButton(cocktails[buttoncounter - 1],
						(startcoordsX + breite + zwischenraum) * odd + 40, startcoordsY, breite, höhe));
				buttoncounter++;
				odd++;
			}
			if ((buttoncounter % 2 == 0) && (buttoncounter <= anzahlButtons)) {
				System.out.println(buttoncounter);
				cpanels.get(seitencounter)
						.add(cocktailButton(cocktails[buttoncounter - 1],
								(startcoordsX + breite + zwischenraum) * even + 40, startcoordsY + höhe + zwischenraum,
								breite, höhe));
				even++;
				if ((buttoncounter % 6 == 0) && (buttoncounter <= anzahlButtons)) {
					odd = 0;
					even = 0;
					seitencounter++;
					cpanels.add(new JPanel());
					cpanels.get(seitencounter).setBounds(40, 80, 1024 - 80, 460);
					cpanels.get(seitencounter).setOpaque(false);
					//cpanels.get(seitencounter).setBackground(new Color(255, 149, 14));
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

	JButton cocktailButton(final Cocktail a, int x, int y, int b, int h) // zur
																			// Erzeugung
																			// der
																			// Cocktail-Buttons
	{
		JButton cb = new JButton(a.getName(), cocktailButtonIcon);
		cb.setHorizontalTextPosition(JButton.CENTER);
		cb.setVerticalTextPosition(JButton.CENTER);
		cb.setBounds(x, y, b, h);
		cb.setOpaque(false);
		cb.setContentAreaFilled(false);
		cb.setBorderPainted(true);
		cb.setFocusPainted(false);
		
		
		ActionListener al = new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				Double[] mengenGeordnet = (new MengenPruefen()).mengenOrdnen(a.getRezept(), auswahl_zutat_akt,
						a.getMengen());
				for(int i=0; i<mengenGeordnet.length;i++)
				System.out.println(mengenGeordnet[i].toString());
				
				pumpenAnsteuerung.start(mengenGeordnet,(Integer) fuellmengeGlas, aktPanel, panelWait, cancel, menue, sperren);
				menue.setVisible(false);
				sperren.setVisible(false);
				CancelButtonProperties();
			}

		};

		cb.addActionListener(al);
		return cb;
	}

	JButton zahlButton(String a) // zur Erzeugung der PIN-Abfrage-Buttons
	{
		JButton b = new JButton(a, zahlButtonIcon);
		b.setHorizontalTextPosition(JButton.CENTER);
		b.setVerticalTextPosition(JButton.CENTER);
		b.setOpaque(false);
		b.setContentAreaFilled(false);
		b.setBorderPainted(true);
		
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
							altesPW.setVisible(false);
							menue.setVisible(false);
							sperren.setVisible(false);
							aktPanel=panelChangePW;
						}
						else{
						panelPW.setVisible(false);
						panelMenue.setVisible(true);
						aktPanel=panelMenue;
						sperren.setVisible(true);
						}
					}
					
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

				try {
					if (pwcheck.checkLength(PIN)) {
						if(!pwcheck.checkPW(PIN))
							JOptionPane.showMessageDialog(f, "Falsche PIN!","Falsche PIN eingegeben!", JOptionPane.WARNING_MESSAGE);
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
		JButton cb = new JButton(naechsteSeite);
		cb.setBounds(904, 0, 40, 460);
		cb.setOpaque(false);
		cb.setContentAreaFilled(false);
		cb.setBorderPainted(true);
		
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
		JButton cb = new JButton(vorherigeSeite);
		cb.setBounds(0, 0, 40, 460);
		cb.setOpaque(false);
		cb.setContentAreaFilled(false);
		cb.setBorderPainted(true);
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
		JButton cb = new JButton(a, zahlButtonIcon);
		cb.setHorizontalTextPosition(JButton.CENTER);
		cb.setVerticalTextPosition(JButton.CENTER);
		cb.setBounds(x, y, b, h);
		cb.setOpaque(false);
		cb.setContentAreaFilled(false);
		cb.setBorderPainted(false);
		
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
	
	JButton sperrButton(String a, int x, int y, int b, int h){
		JButton cb = new JButton(a, menueButtonIcon);
		cb.setBounds(x, y, b, h);
		cb.setHorizontalTextPosition(JButton.CENTER);
		cb.setVerticalTextPosition(JButton.CENTER);
		cb.setOpaque(false);
		cb.setContentAreaFilled(false);
		cb.setBorderPainted(false);
	
		ActionListener al =new ActionListener(){
			public void actionPerformed(ActionEvent e){
				aktPanel.setVisible(false);
				panelPW.setVisible(true);
				aktPanel=panelPW;
				menue.setVisible(false);
				altesPW.setVisible(false);
				sperren.setVisible(false);
			}
		};
		cb.addActionListener(al);
		return cb;
	}
	
	JToggleButton behälterReinigungsButton(String a, int x,int y, int b, int h, int behälter){
		JToggleButton cb = new JToggleButton(a, cocktailButtonIcon);
		cb.setHorizontalTextPosition(JButton.CENTER);
		cb.setVerticalTextPosition(JButton.CENTER);
		cb.setBounds(x,y,b,h);
		cb.setOpaque(false);
		cb.setContentAreaFilled(false);
		cb.setBorderPainted(true);
		
		final int[] be = {0};
		be[0]=behälter;
		final String[] ai ={""};
		ai[0]="<html><center>"+a;
		ItemListener il = new ItemListener(){
			
				
			@Override
			public void itemStateChanged(ItemEvent ev) {
				JToggleButton jtgb = (JToggleButton)ev.getSource();
				if(ev.getStateChange()==ItemEvent.SELECTED){
					pumpenAnsteuerung.reinigungStart(be[0]);
					jtgb.setText(ai[0]+"<br>Läuft gerade!</center></html>");
				} else if(ev.getStateChange()==ItemEvent.DESELECTED){
					pumpenAnsteuerung.reinigungStop(be[0]);
					jtgb.setText(ai[0]+"</center></html>");
				}
				
				
			}
		};
		cb.addItemListener(il);
		return cb;
	}
	
	void fuellmenge150(String a, int x, int y, int b, int h){
		fuellmenge150 = new JButton(a,fuellmenge150IconLeer);
		fuellmenge150.setBounds(x, y, b, h);
		fuellmenge150.setOpaque(false);
		fuellmenge150.setContentAreaFilled(false);
		fuellmenge150.setBorderPainted(false);
		fuellmenge150.setVerticalAlignment(SwingConstants.BOTTOM);
		fuellmenge150.setHorizontalTextPosition(JButton.CENTER);
		fuellmenge150.setVerticalTextPosition(JButton.CENTER);
		
		ActionListener al = new ActionListener(){
			public void actionPerformed(ActionEvent e){
				fuellmengeGlas=150;
				fuellmenge150.setIcon(fuellmenge150IconVoll);
				fuellmenge200.setIcon(fuellmenge200IconLeer);
				fuellmenge250.setIcon(fuellmenge250IconLeer);
				fuellmenge300.setIcon(fuellmenge300IconLeer);
			}
		};
		fuellmenge150.addActionListener(al);
	
	}
	
	void fuellmenge200(String a, int x, int y, int b, int h){
		fuellmenge200 = new JButton(a,fuellmenge200IconLeer);
		fuellmenge200.setBounds(x, y, b, h);
		fuellmenge200.setOpaque(false);
		fuellmenge200.setContentAreaFilled(false);
		fuellmenge200.setBorderPainted(false);
		fuellmenge200.setVerticalAlignment(SwingConstants.BOTTOM);
		fuellmenge200.setHorizontalTextPosition(JButton.CENTER);
		fuellmenge200.setVerticalTextPosition(JButton.CENTER);
		
		ActionListener al = new ActionListener(){
			public void actionPerformed(ActionEvent e){
				fuellmengeGlas=200;
				fuellmenge150.setIcon(fuellmenge150IconLeer);
				fuellmenge200.setIcon(fuellmenge200IconVoll);
				fuellmenge250.setIcon(fuellmenge250IconLeer);
				fuellmenge300.setIcon(fuellmenge300IconLeer);
			}
		};
		fuellmenge200.addActionListener(al);
	
	}
	
	void fuellmenge250(String a, int x, int y, int b, int h){
		fuellmenge250 = new JButton(a,fuellmenge250IconLeer);
		fuellmenge250.setBounds(x, y, b, h);
		fuellmenge250.setOpaque(false);
		fuellmenge250.setContentAreaFilled(false);
		fuellmenge250.setBorderPainted(false);
		fuellmenge250.setVerticalAlignment(SwingConstants.BOTTOM);
		fuellmenge250.setHorizontalTextPosition(JButton.CENTER);
		fuellmenge250.setVerticalTextPosition(JButton.CENTER);
		
		ActionListener al = new ActionListener(){
			public void actionPerformed(ActionEvent e){
				fuellmengeGlas=250;
				fuellmenge150.setIcon(fuellmenge150IconLeer);
				fuellmenge200.setIcon(fuellmenge200IconLeer);
				fuellmenge250.setIcon(fuellmenge250IconVoll);
				fuellmenge300.setIcon(fuellmenge300IconLeer);
			}
		};
		fuellmenge250.addActionListener(al);
	
	}
	
	void fuellmenge300(String a, int x, int y, int b, int h){
		fuellmenge300 = new JButton(a,fuellmenge300IconLeer);
		fuellmenge300.setBounds(x, y, b, h);
		fuellmenge300.setOpaque(false);
		fuellmenge300.setContentAreaFilled(false);
		fuellmenge300.setBorderPainted(false);
		fuellmenge300.setVerticalAlignment(SwingConstants.BOTTOM);
		fuellmenge300.setHorizontalTextPosition(JButton.CENTER);
		fuellmenge300.setVerticalTextPosition(JButton.CENTER);
		
		ActionListener al = new ActionListener(){
			public void actionPerformed(ActionEvent e){
				fuellmengeGlas=300;
				fuellmenge150.setIcon(fuellmenge150IconLeer);
				fuellmenge200.setIcon(fuellmenge200IconLeer);
				fuellmenge250.setIcon(fuellmenge250IconLeer);
				fuellmenge300.setIcon(fuellmenge300IconVoll);
			}
		};
		fuellmenge300.addActionListener(al);
	
	}
	
	JButton neuerCocktail(int x, int y, int b, int h){
		JButton cb = new JButton("<html><center>Neuen Cocktail<br>hinzufügen</center></html>");
		cb.setBounds(x,y,b,h);
		ActionListener al = new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				panelMenue.setVisible(false);
				tastatur.setVisible(true);
				aktPanel=tastatur;
			}
			
		};
		cb.addActionListener(al);
		return cb;
	}
	
	MainFrame()  throws UnsupportedEncodingException, IOException {
		System.out.println(getClass().getClassLoader().getResource("res/background.jpg"));
		
		menue = menueButton("Menü", 345, h, b, h);
		cancel = cancelButton("Cancel", 514, h, b, h);
		sperren = sperrButton("Sperren",176,y+h,165,h);
		n1 = zahlButton("1");
		n2 = zahlButton("2");
		n3 = zahlButton("3");
		n4 = zahlButton("4");
		n5 = zahlButton("5");
		n6 = zahlButton("6");
		n7 = zahlButton("7");
		n8 = zahlButton("8");
		n9 = zahlButton("9");
		
		fuellmenge150("<html><center>150<br>ml</center></html>",40,175,60,95);
		fuellmenge200("<html><center>200<br>ml</center></html>",100,175,60,95);
		fuellmenge250("<html><center>250<br>ml</center></html>",160,175,60,95);
		fuellmenge300("<html><center>300<br>ml</center></html>",220,175,60,95);
		background.setIcon(backgroundIcon);
		background.setLayout(new BorderLayout());
		f.setContentPane(background);
		
		fuellmenge.addItem(150);
		fuellmenge.addItem(200);
		fuellmenge.addItem(250);
		fuellmenge.addItem(300);
		fuellmenge.setBounds(120,200,160,50);
		fuellmenge.setOpaque(false);
		fuellmenge.setFont(new Font(null, Font.PLAIN,20));
		fuellmengenEingabe.setBounds(120,120,160,80);
		
					
		f.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getClassLoader().getResource("res/Icon.png")));
		close.setBounds(852, h, b, h);
		f.getContentPane().add(close);
		f.getContentPane().add(sperren);
		sperren.setVisible(false);
		
		cpanels.add(new JPanel());
		cpanels.get(0).setBounds(40, 80, 1024 - 80, 460);
		cpanels.get(0).setOpaque(false);
		//cpanels.get(0).setBackground(new Color(255, 149, 14));
		cpanels.get(0).setVisible(false);
		cpanels.get(0).setLayout(null);
		f.getContentPane().add(cpanels.get(0));

		bar1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		bar1.setStringPainted(true);
		f.getContentPane().add(bar1);
		bar1.setBounds(7, y, 165, h);
		bar1.setString("Behaelter 1");
		bar1.setOpaque(false);

		bar2.setFont(new Font("Tahoma", Font.PLAIN, 18));
		bar2.setStringPainted(true);
		f.getContentPane().add(bar2);
		bar2.setBounds(176, y, 165, h);
		bar2.setString("Behaelter 2");
		bar2.setOpaque(false);

		bar3.setFont(new Font("Tahoma", Font.PLAIN, 18));
		bar3.setStringPainted(true);
		f.getContentPane().add(bar3);
		bar3.setBounds(345, y, 165, h);
		bar3.setString("Behaelter 3");
		bar3.setOpaque(false);

		bar4.setFont(new Font("Tahoma", Font.PLAIN, 18));
		bar4.setStringPainted(true);
		f.getContentPane().add(bar4);
		bar4.setBounds(514, y, 165, h);
		bar4.setString("Behaelter 4");
		bar4.setOpaque(false);

		bar5.setFont(new Font("Tahoma", Font.PLAIN, 18));
		bar5.setStringPainted(true);
		f.getContentPane().add(bar5);
		bar5.setBounds(683, y, 165, h);
		bar5.setString("Behaelter 5");
		bar5.setOpaque(false);
		
		bar6.setFont(new Font("Tahoma", Font.PLAIN, 18));
		bar6.setStringPainted(true);
		f.getContentPane().add(bar6);
		bar6.setBounds(852, y, 165, h);
		bar6.setString("Behaelter 6");
		bar6.setOpaque(false);
		
		panelPW.setLayout(null);
		panelPW.setBounds(40, 80, 1024 - 80, 460);
		panelPW.setOpaque(false);
		//panelPW.setBackground(new Color(255, 149, 14));

		f.getContentPane().add(menue);
		menue.setVisible(false);
		
		f.getContentPane().add(cancel);
		cancel.setVisible(false);
		
		panelMenue.setBounds(40, 80, 1024 - 80, 460);
		panelMenue.setOpaque(false);
		//panelMenue.setBackground(new Color(255, 149, 14));
		panelMenue.setVisible(false);
		panelMenue.setLayout(null);
		f.getContentPane().add(panelMenue);

		panelZutatenAuswahl.setBounds(40, 80, 1024 - 80, 460);
		panelZutatenAuswahl.setOpaque(false);
		//panelZutatenAuswahl.setBackground(new Color(255, 149, 14));
		panelZutatenAuswahl.setVisible(false);
		panelZutatenAuswahl.setLayout(null);
		
		f.getContentPane().add(panelZutatenAuswahl);
		
		panelChangePW.setBounds(40, 80, 1024 - 80, 460);
		panelChangePW.setOpaque(false);
		//panelChangePW.setBackground(new Color(255, 149, 14));
		panelChangePW.setVisible(false);
		panelChangePW.setLayout(null);
		f.getContentPane().add(panelChangePW);

		panelWait.setBounds(40, 80, 1024 - 80, 460);
		panelWait.setOpaque(false);
		//panelWait.setBackground(new Color(255, 149, 14));
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
		f.getContentPane().add(panelWait);

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
		altesPW.setBounds(352,140,240,40);
		altesPW.setVisible(false);
		
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
		panelPW.add(altesPW);
		
		panelMenue.setLayout(null);
		panelMenue.add(menueButtonCocktails("Cocktails", 315, 50, 157, 175));
		panelMenue.add(menueButtonZutaten("<html>Zutaten<br>ändern</html>", 472, 50, 157, 175));
		panelMenue.add(menueButtonReinigung("Reinigungsmodus", 315, 225, 157, 175));
		panelMenue.add(menueButtonPasswortAendern("<html><center>Passwort<br>ändern</center></html>", 472,225,157,175));
		panelMenue.add(fuellmenge150);
		panelMenue.add(fuellmenge200);
		panelMenue.add(fuellmenge250);
		panelMenue.add(fuellmenge300);
		panelMenue.add(neuerCocktail(664,185,160,80));
		//panelMenue.add(fuellmenge);
		//panelMenue.add(fuellmengenEingabe);

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
		comboBox_1.setFont(new Font(null, Font.PLAIN,25));
		comboBox_2.setFont(new Font(null, Font.PLAIN,25));
		comboBox_3.setFont(new Font(null, Font.PLAIN,25));
		comboBox_4.setFont(new Font(null, Font.PLAIN,25));
		comboBox_5.setFont(new Font(null, Font.PLAIN,25));
		comboBox_6.setFont(new Font(null, Font.PLAIN,25));
		
		
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
		
		panelZutatenAuswahl.add(auswahlButton("Speichern", 312, 380, 160, 80));
		panelZutatenAuswahl.add(auswahlButton("Abbrechen", 472, 380, 160, 80));
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
		
		panelChangePW.add(auswahlButton(" Speichern ", 312,380,160,80));
		panelChangePW.add(auswahlButton(" Abbrechen ", 472, 380, 160, 80));
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
		neuesPW.setBounds(352,60,240,40);
		panelChangePW.add(pwChangeLabel);
		panelChangePW.add(neuesPW);
		
		reinigungsPanel.setBounds(40, 80, 1024 - 80, 460);
		reinigungsPanel.add(behälterReinigungsButton("Behälter 1",0,0,864/3,230,1 ));
		reinigungsPanel.add(behälterReinigungsButton("Behälter 2",864/3,0,864/3,230,2 ));
		reinigungsPanel.add(behälterReinigungsButton("Behälter 3",2*(864/3),0,864/3,230,3 ));
		reinigungsPanel.add(behälterReinigungsButton("Behälter 4",0,230,864/3,230,4 ));
		reinigungsPanel.add(behälterReinigungsButton("Behälter 5",864/3,230,864/3,230,5 ));
		reinigungsPanel.add(behälterReinigungsButton("Behälter 6",2*(864/3),230,864/3,230,6 ));
		reinigungsPanel.setVisible(false);
		reinigungsPanel.setOpaque(false);
		reinigungsPanel.setLayout(null);
		
		tastatur.setBounds(40, 80, 864, 460);
		f.add(tastatur);
		tastatur.setVisible(false);
		
		f.getContentPane().add(reinigungsPanel);
		
		f.getContentPane().add(panelPW);

		f.setExtendedState(JFrame.MAXIMIZED_BOTH); //funktioniert nicht in
		// Kombination mit setResizeable(false)
		f.getContentPane().setLayout(null);
		f.setResizable(false);
		f.setUndecorated(true); //entfernt Programmleiste, für echtes
		// Vollbild
		f.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		f.setSize(1024, 600);
		f.setVisible(true);
		
	}

	public static void main(String[] args) throws IOException{
		/*try { 
			  UIManager.setLookAndFeel( "com.sun.java.swing.plaf.gtk.GTKLookAndFeel"); 
			} catch( Exception e ) { e.printStackTrace(); }
		*/
		File dir = new File("./res");
		if(!dir.exists())
			dir.mkdir();
		dir.setWritable(true, false);
		MainFrame GUI = new MainFrame();
		Fuellstand fuellstand = new Fuellstand(GUI.bar1,GUI.bar2,GUI.bar3,GUI.bar4,GUI.bar5,GUI.bar6);
		fuellstand.execute();
		//GUI.pumpenAnsteuerung.bildschirmOn();
		GUI.zutaten = new File("./res/Zutaten.txt");
		GUI.hash = new File("./res/hash.txt");
		GUI.fuellmenge150.setIcon(GUI.fuellmenge150IconVoll);
		if(!GUI.hash.exists()){
			List<String> hashcodes = new ArrayList<String>();
			hashcodes.add(GUI.pwcheck.get_SHA_512_SecurePassword("1234", "alkomat"));
			hashcodes.add(GUI.pwcheck.get_SHA_512_SecurePassword(String.valueOf("4"), "alkomat"));
			FileUtils.writeLines(GUI.hash, hashcodes);
			System.out.println("Standard-Passwort gesetzt");
			JOptionPane.showMessageDialog(GUI.f, "Standard-Passwort gesetzt!","Standard-Passwort gesetzt!", JOptionPane.WARNING_MESSAGE);
		}
		GUI.auswahl_zutat_akt = (new ZutatenLesen()).leseZutaten();
		GUI.bar1.setString("1 - "+GUI.auswahl_zutat_akt[0]);
		GUI.bar2.setString("2 - "+GUI.auswahl_zutat_akt[1]);
		GUI.bar3.setString("3 - "+GUI.auswahl_zutat_akt[2]);
		GUI.bar4.setString("4 - "+GUI.auswahl_zutat_akt[3]);
		GUI.bar5.setString("5 - "+GUI.auswahl_zutat_akt[4]);
		GUI.bar6.setString("6 - "+GUI.auswahl_zutat_akt[5]);
		for (String s : GUI.auswahl_zutat_akt)
			System.out.println(s);
		
		GUI.cocktailsmoeglichList = (GUI.cocktailPruefen).loadCocktail(GUI.auswahl_zutat_akt);
				
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
			JOptionPane.showMessageDialog(GUI.f, "Keine Cocktails möglich!","Keine Cocktails möglich", JOptionPane.WARNING_MESSAGE);
		}
		GUI.hash.setWritable(true, false);
		}
}
