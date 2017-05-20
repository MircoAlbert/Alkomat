package alkomat;

import java.awt.Color;
import java.util.List;
import java.util.Scanner;
import javax.swing.JProgressBar;
import javax.swing.SwingWorker;
import com.fazecast.jSerialComm.SerialPort;

class Fuellstand extends SwingWorker<Void, String> {
	// Serial Port einstellen;
	private SerialPort serialPort = SerialPort.getCommPort("/dev/ttyACM0");
	// Daten lesen + ausgeben;
	private Scanner data;
	private JProgressBar bar1;
	private JProgressBar bar2;
	private JProgressBar bar3;
	private JProgressBar bar4;
	private JProgressBar bar5;
	private JProgressBar bar6;

	Fuellstand(JProgressBar bar1, JProgressBar bar2, JProgressBar bar3, JProgressBar bar4, JProgressBar bar5,
			JProgressBar bar6) {
		this.bar1 = bar1;
		this.bar2 = bar2;
		this.bar3 = bar3;
		this.bar4 = bar4;
		this.bar5 = bar5;
		this.bar6 = bar6;
		serialPort.openPort();
		serialPort.setComPortTimeouts(SerialPort.TIMEOUT_SCANNER, 0, 0);
		data = new Scanner(serialPort.getInputStream());
	}

	@Override
	protected Void doInBackground() {

		while (true) {
			
			String line = data.nextLine();
			publish(line);
			//System.out.println(line);
			
			
		}
	}

	@Override
	protected void process(List<String> fs) {
		
		String[] fuell = (fs.get(fs.size() - 1)).split(",");
		
		int[] DataLineInt = new int[fuell.length];
		for (int i = 0; i < fuell.length; i++)
			DataLineInt[i] = Integer.parseInt(fuell[i]);
		// System.out.println(Integer.toString(DataLineInt[0]));
		
	
		if(DataLineInt[0]<=30)
			bar1.setForeground(Color.RED);
		if(DataLineInt[1]<=30)
			bar2.setForeground(Color.RED);
		if(DataLineInt[2]<=30)
			bar3.setForeground(Color.RED);
		if(DataLineInt[3]<=30)
			bar4.setForeground(Color.RED);
		if(DataLineInt[4]<=30)
			bar5.setForeground(Color.RED);
		if(DataLineInt[5]<=30)
			bar6.setForeground(Color.RED);
		
		else if((30<DataLineInt[0])&&(DataLineInt[0]<=50))
			bar1.setForeground(Color.ORANGE);
		else if((30<DataLineInt[1])&&(DataLineInt[1]<=50))
			bar2.setForeground(Color.ORANGE);
		else if((30<DataLineInt[2])&&(DataLineInt[2]<=50))
			bar3.setForeground(Color.ORANGE);
		else if((30<DataLineInt[3])&&(DataLineInt[3]<=50))
			bar4.setForeground(Color.ORANGE);
		else if((30<DataLineInt[4])&&(DataLineInt[4]<=50))
			bar5.setForeground(Color.ORANGE);
		else if((30<DataLineInt[5])&&(DataLineInt[5]<=50))
			bar6.setForeground(Color.ORANGE);
		
		else{
			bar1.setForeground(Color.GREEN);
			bar2.setForeground(Color.GREEN);
			bar3.setForeground(Color.GREEN);
			bar4.setForeground(Color.GREEN);
			bar5.setForeground(Color.GREEN);
			bar6.setForeground(Color.GREEN);
		}
		bar1.setValue(DataLineInt[0]);
		bar2.setValue(DataLineInt[1]);
		bar3.setValue(DataLineInt[2]);
		bar4.setValue(DataLineInt[3]);
		bar5.setValue(DataLineInt[4]);
		bar6.setValue(DataLineInt[5]);
	}

}
