package alkomat;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;

class MyItemListener implements ItemListener {
	private DefaultComboBoxModel<String> cbm1 = new DefaultComboBoxModel<String>();
	private DefaultComboBoxModel<String> cbm2 = new DefaultComboBoxModel<String>();
	private DefaultComboBoxModel<String> cbm3 = new DefaultComboBoxModel<String>();
	private DefaultComboBoxModel<String> cbm4 = new DefaultComboBoxModel<String>();
	private DefaultComboBoxModel<String> cbm5 = new DefaultComboBoxModel<String>();
	private int index_int;
	private String[] auswahl_zutat;

	MyItemListener(DefaultComboBoxModel<String> cb1, DefaultComboBoxModel<String> cb2,
			DefaultComboBoxModel<String> cb3, DefaultComboBoxModel<String> cb4, DefaultComboBoxModel<String> cb5,
			int index, String[] auswahl_zutat) 
	{
		this.cbm1 = cb1;
		this.cbm2 = cb2;
		this.cbm3 = cb3;
		this.cbm4 = cb4;
		this.cbm5 = cb5;
		this.index_int = index;
		this.auswahl_zutat = auswahl_zutat;
	}

	// This method is called only if a new item has been selected.
	public void itemStateChanged(ItemEvent evt) {
		JComboBox cb = (JComboBox) evt.getSource();

		String item = (String) evt.getItem();

		if (item != "")
			if (evt.getStateChange() == ItemEvent.SELECTED) {

				auswahl_zutat[index_int] = item;
				cbm1.removeElement(item);
				cbm2.removeElement(item);
				cbm3.removeElement(item);
				cbm4.removeElement(item);
				cbm5.removeElement(item);
				// System.out.println(auswahl_zutat[index_int]);
			}

		if (item != "")
			if (evt.getStateChange() == ItemEvent.DESELECTED) {
				cbm1.addElement(item);
				cbm2.addElement(item);
				cbm3.addElement(item);
				cbm4.addElement(item);
				cbm5.addElement(item);

			}
	}

}
