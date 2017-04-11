package alkomat;

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
		this.bar2 = bar3;
		this.bar2 = bar4;
		this.bar2 = bar5;
		this.bar2 = bar6;
		serialPort.openPort();
		serialPort.setComPortTimeouts(SerialPort.TIMEOUT_SCANNER, 0, 0);
		data = new Scanner(serialPort.getInputStream());
	}

	@Override
	protected Void doInBackground() {

		while (true) {
			String line = data.nextLine();
			publish(line);
			System.out.println(line);
		}
	}

	@Override
	protected void process(List<String> fs) {
		String[] fuell = (fs.get(fs.size() - 1)).split(",");
		int[] DataLineInt = new int[fuell.length];
		for (int i = 0; i < fuell.length; i++)
			DataLineInt[i] = Integer.parseInt(fuell[i]);
		// System.out.println(Integer.toString(DataLineInt[0]));
		bar1.setValue(DataLineInt[0]);
		bar2.setValue(DataLineInt[1]);
		bar3.setValue(DataLineInt[2]);
		bar4.setValue(DataLineInt[3]);
		bar5.setValue(DataLineInt[4]);
		bar6.setValue(DataLineInt[5]);
	}

}
