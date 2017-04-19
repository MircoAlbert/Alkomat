package alkomat;

import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JPanel;

import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.PinState;
import com.pi4j.io.gpio.RaspiPin;

class PumpenAnsteuerung // zur Ansteuerung der IO-Pins
{

	private JPanel panelList;
	private JPanel panelWait;
	private int maxMenge = 0;

	PumpenAnsteuerung(Integer[] mengenGeordnet, JPanel panelList, JPanel panelWait) throws InterruptedException {

		
		final GpioController gpio = GpioFactory.getInstance();
		final GpioPinDigitalOutput pin1 = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_01, "Pumpe 1", PinState.HIGH);
		final GpioPinDigitalOutput pin2 = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_02, "Pumpe 2", PinState.HIGH);
		final GpioPinDigitalOutput pin3 = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_03, "Pumpe 3", PinState.HIGH);
		final GpioPinDigitalOutput pin4 = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_04, "Pumpe 4", PinState.HIGH);
		final GpioPinDigitalOutput pin5 = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_05, "Pumpe 5", PinState.HIGH);
		final GpioPinDigitalOutput pin6 = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_06, "Pumpe 6", PinState.HIGH);
		
		
		this.panelList = panelList;
		this.panelWait = panelWait;
		
		
		class panelRemove extends TimerTask {
			private JPanel panelWait1, panel;

			panelRemove(JPanel panelWait1, JPanel panel) {
				this.panel = panel;
				this.panelWait1 = panelWait1;
			}

			public void run() {
				panelWait1.setVisible(false);
				panel.setVisible(true);
			}
		}

		class setHigh extends TimerTask {
			private GpioPinDigitalOutput pin;

			setHigh(GpioPinDigitalOutput pin) {
				this.pin = pin;
			}

			public void run() {
				pin.high();
				gpio.unprovisionPin(pin); // gibt pin wieder frei

			}

		}

		class setLow extends TimerTask {
			private GpioPinDigitalOutput pin;

			setLow(GpioPinDigitalOutput pin) {
				this.pin = pin;
			}

			public void run() {
				pin.low();

			}

		}
		
		for(int i=0;i<mengenGeordnet.length;i++){
			mengenGeordnet[i]=mengenGeordnet[i]*200;
			if(mengenGeordnet[i]>maxMenge)
				maxMenge=mengenGeordnet[i];
		}
		System.out.println(maxMenge);
		
		/*
		Timer timer = new Timer();
		pin1.low();
		pin2.low();
		pin3.low();
		pin4.low();
		pin5.low();
		pin6.low();
		panelList.setVisible(false);
		panelWait.setVisible(true);
		timer.schedule(new setHigh(pin1), mengenGeordnet[0]);
		timer.schedule(new setHigh(pin2), mengenGeordnet[1]);
		timer.schedule(new setHigh(pin3), mengenGeordnet[2]);
		timer.schedule(new setHigh(pin4), mengenGeordnet[3]);
		timer.schedule(new setHigh(pin5), mengenGeordnet[4]);
		timer.schedule(new setHigh(pin6), mengenGeordnet[5]);
		timer.schedule(new panelRemove(panelWait, panelList), maxMenge);
		gpio.shutdown();
		*/
		

		/*if (a.equals("Touchdown")) {

			Timer timer = new Timer();
			pin1.low();
			pin2.low();
			pin3.low();
			pin4.low();
			pin5.low();
			pin6.low();
			panelList.setVisible(false);
			panelWait.setVisible(true);
			timer.schedule(new setHigh(pin1), 2000);
			timer.schedule(new setHigh(pin2), 4000);
			timer.schedule(new setHigh(pin3), 6000);
			timer.schedule(new setHigh(pin4), 8000);
			timer.schedule(new setHigh(pin5), 10000);
			timer.schedule(new setHigh(pin6), 12000);
			timer.schedule(new panelRemove(panelWait, panelList), 12000);
			gpio.shutdown();

		}
		if (a.equals("Zombie")) {
			Timer timer = new Timer();
			pin1.low();
			pin2.low();
			timer.schedule(new setHigh(pin1), 2000);
			timer.schedule(new setHigh(pin2), 1000);

			gpio.shutdown();
		}
		if (a.equals("Tequila Sunrise")) {
			Timer timer = new Timer();
			pin1.low();
			timer.schedule(new setHigh(pin1), 2000);
			timer.schedule(new setLow(pin2), 2000);
			timer.schedule(new setHigh(pin2), 4000);

			gpio.shutdown();
		}
		if (a.equals("Sex on the Beach")) {
			int count = 5;
			while (count > 0) {
				pin1.low();
				Thread.sleep(1000);
				pin1.high();
				Thread.sleep(1000);

				count--;
			}
			gpio.shutdown();
			gpio.unprovisionPin(pin1);
			gpio.unprovisionPin(pin2);
			gpio.unprovisionPin(pin3);
			gpio.unprovisionPin(pin4);
			gpio.unprovisionPin(pin5);
			gpio.unprovisionPin(pin6);
		}
		if (a.equals("Planters Punch")) {
			int count = 6;
			while (count > 0) {
				pin1.low();
				Thread.sleep(1000);
				pin1.high();
				Thread.sleep(1000);

				count--;
			}
			gpio.shutdown();
			gpio.unprovisionPin(pin1);
			gpio.unprovisionPin(pin2);
			gpio.unprovisionPin(pin3);
			gpio.unprovisionPin(pin4);
			gpio.unprovisionPin(pin5);
			gpio.unprovisionPin(pin6);
		}
		if (a.equals("Bahama Mama")) {
			Timer timer = new Timer();
			panelList.setVisible(false);
			panelWait.setVisible(true);
			timer.schedule(new panelRemove(panelWait, panelList), 5000);
			gpio.shutdown();
			gpio.unprovisionPin(pin1);
			gpio.unprovisionPin(pin2);
			gpio.unprovisionPin(pin3);
			gpio.unprovisionPin(pin4);
			gpio.unprovisionPin(pin5);
			gpio.unprovisionPin(pin6);
		}
		*/

		// Wichtig
		// Wenn ihr die GPIOs nicht mehr braucht dann solltet ihr diese auch
		// schliessen... Oeffnen koennt ihr wieder mit dem
		// GpioFactory.getInstance() welches
		// euch ein neuen GpioController zur verfuegung stellt

	}
}
