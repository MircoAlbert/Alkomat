package alkomat;

import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JButton;
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
	private Integer[] mengenGeordnet = new Integer[0];
	private JButton cancel;
	
	private Timer timer;
	private boolean cancelled = false;
	
	private GpioController gpio;
	private GpioPinDigitalOutput pin1;
	private GpioPinDigitalOutput pin2;
	private GpioPinDigitalOutput pin3;
	private GpioPinDigitalOutput pin4;
	private GpioPinDigitalOutput pin5;
	private GpioPinDigitalOutput pin6;
	
	private setHigh taskPin1;
	private setHigh taskPin2; 
	private setHigh taskPin3;
	private setHigh taskPin4;
	private setHigh taskPin5;
	private setHigh taskPin6;
	
	private panelRemove panelRemove;
	
	private Unprovision unprovision;
	 
	public void cancelTimer(JPanel panelList, JPanel panelWait){
		cancelled=true;
		pin1.high();
		pin2.high();
		pin3.high();
		pin4.high();
		pin5.high();
		pin6.high();
		gpio.shutdown();
		gpio.unprovisionPin(pin1);
		gpio.unprovisionPin(pin2);
		gpio.unprovisionPin(pin3);
		gpio.unprovisionPin(pin4);
		gpio.unprovisionPin(pin5);
		gpio.unprovisionPin(pin6);
		unprovision.cancel();
		taskPin1.cancel();
		taskPin2.cancel();
		taskPin3.cancel();
		taskPin4.cancel();
		taskPin5.cancel();
		taskPin6.cancel();
		panelRemove.cancel();
		panelList.setVisible(true);
		panelWait.setVisible(false);
		
	}
	
	class panelRemove extends TimerTask {
		
		public void run() {
			if(cancelled==false)
			{
			panelWait.setVisible(false);
			panelList.setVisible(true);
			cancel.setVisible(false);
			}
		}
	}

	class setHigh extends TimerTask {
		private GpioPinDigitalOutput pin;

		setHigh(GpioPinDigitalOutput pin) {
			this.pin = pin;
		}

		public void run() {
			if(cancelled==false)
				pin.high();
			
		}

	}

	class setLow extends TimerTask {
		private GpioPinDigitalOutput pin;

		setLow(GpioPinDigitalOutput pin) {
			this.pin = pin;
		}

		public void run() {
			if(cancelled==false)
				pin.low();

		}
		
	}
	
	class Unprovision extends TimerTask{
		
		Unprovision(){
			
		};
		
		public void run(){
			if(cancelled==false){
			gpio.shutdown();
			gpio.unprovisionPin(pin1);
			gpio.unprovisionPin(pin2);
			gpio.unprovisionPin(pin3);
			gpio.unprovisionPin(pin4);
			gpio.unprovisionPin(pin5);
			gpio.unprovisionPin(pin6);
			}
		}
		
	}
	
	public void start(Integer[] mengenGeordnet, JPanel panelList, JPanel panelWait, JButton cancel){
		this.panelWait = panelWait;
		this.panelList = panelList;
		this.cancel = cancel;
		cancelled = false;
		this.gpio = GpioFactory.getInstance();
		this.pin1 = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_01, "Pumpe 1", PinState.HIGH);
		this.pin2 = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_02, "Pumpe 2", PinState.HIGH);
		this.pin3 = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_03, "Pumpe 3", PinState.HIGH);
		this.pin4 = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_04, "Pumpe 4", PinState.HIGH);
		this.pin5 = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_05, "Pumpe 5", PinState.HIGH);
		this.pin6 = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_06, "Pumpe 6", PinState.HIGH);
		
		Timer timer = new Timer();
		for(int i=0;i<mengenGeordnet.length;i++){
			mengenGeordnet[i]=mengenGeordnet[i]*200;
			if(mengenGeordnet[i]>maxMenge)
				maxMenge=mengenGeordnet[i];
		}
		System.out.println(mengenGeordnet[0]+", " +mengenGeordnet[1]+", " +mengenGeordnet[2]+", " +mengenGeordnet[3]+", " +mengenGeordnet[4]+", " +mengenGeordnet[5]+", " +maxMenge);
		taskPin1 = new setHigh(pin1);
		taskPin2 = new setHigh(pin2);
		taskPin3 = new setHigh(pin3);
		taskPin4 = new setHigh(pin4);
		taskPin5 = new setHigh(pin5);
		taskPin6 = new setHigh(pin6);
		
		panelRemove = new panelRemove();
		
		unprovision = new Unprovision();
		
		
		
		pin1.low();
		pin2.low();
		pin3.low();
		pin4.low();
		pin5.low();
		pin6.low();
		panelList.setVisible(false);
		panelWait.setVisible(true);
		timer.schedule(taskPin1, mengenGeordnet[0]);
		timer.schedule(taskPin2, mengenGeordnet[1]);
		timer.schedule(taskPin3, mengenGeordnet[2]);
		timer.schedule(taskPin4, mengenGeordnet[3]);
		timer.schedule(taskPin5, mengenGeordnet[4]);
		timer.schedule(taskPin6, mengenGeordnet[5]);
		timer.schedule(panelRemove, maxMenge);
		timer.schedule(unprovision, maxMenge);
				
	}
	
	PumpenAnsteuerung(){
		
			

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
