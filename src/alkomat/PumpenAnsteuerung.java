package alkomat;

import java.util.Map;
import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JButton;
import javax.swing.JPanel;

import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.PinState;
import com.pi4j.io.gpio.RaspiPin;

import alkomat.PumpenAnsteuerung.setHigh;

class PumpenAnsteuerung // zur Ansteuerung der IO-Pins
{

	
	private JPanel panelList;
	private JPanel panelWait;
	private Double maxMenge = 0.0;
	
	private JButton cancel;
	private JButton menue;
	private JButton sperren;
	
	
	private boolean cancelled = false;
	
	private GpioController gpio;
	private GpioPinDigitalOutput pin1;
	private GpioPinDigitalOutput pin2;
	private GpioPinDigitalOutput pin3;
	private GpioPinDigitalOutput pin4;
	private GpioPinDigitalOutput pin5;
	private GpioPinDigitalOutput pin6;
	private GpioPinDigitalOutput pin7;
	
	private setHigh taskPin1;
	private setHigh taskPin2; 
	private setHigh taskPin3;
	private setHigh taskPin4;
	private setHigh taskPin5;
	private setHigh taskPin6;
	private setHigh taskPin7;
	
	private panelRemove panelRemove;
	
	private Unprovision unprovision;
	
	private Unprovision7 unprovision7;
	
	private Map<Integer, setHigh> pinTasks = new HashMap<Integer, setHigh>();
	private Map<Integer, GpioPinDigitalOutput> pins = new HashMap<Integer, GpioPinDigitalOutput>();
	
	
	public PumpenAnsteuerung(){
		this.gpio = GpioFactory.getInstance();
			
	}
	
	public void bildschirmOn(){
		this.pin7 = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_07, "Bildschirm", PinState.HIGH);
		taskPin7 = new setHigh(pin7);
		pin7.low();
		Timer timer = new Timer();
		unprovision7 = new Unprovision7();
		timer.schedule(taskPin7, 10);
		timer.schedule(unprovision7, 10);
		
	}
	
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
			sperren.setVisible(true);
			menue.setVisible(true);
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
	class Unprovision7 extends TimerTask{
		
		Unprovision7(){
			
		};
		
		public void run(){
			if(cancelled==false){
			gpio.shutdown();
			gpio.unprovisionPin(pin7);
			}
		}
		
	}
	
	public void start(Double[] mengenGeordnet,Integer fuellmenge, JPanel panelList, JPanel panelWait, JButton cancel, JButton menue, JButton sperren){
		this.panelWait = panelWait;
		this.panelList = panelList;
		this.cancel = cancel;
		this.menue = menue;
		this.sperren = sperren;
		cancelled = false;
		
		this.pin1 = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_01, "Pumpe 1", PinState.HIGH);
		this.pin2 = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_02, "Pumpe 2", PinState.HIGH);
		this.pin3 = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_03, "Pumpe 3", PinState.HIGH);
		this.pin4 = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_04, "Pumpe 4", PinState.HIGH);
		this.pin5 = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_05, "Pumpe 5", PinState.HIGH);
		this.pin6 = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_06, "Pumpe 6", PinState.HIGH);
		//this.mengenDouble = new Integer[mengenGeordnet.length];
		
		Timer timer = new Timer();
		for(int i=0;i<mengenGeordnet.length;i++){
			mengenGeordnet[i]=((mengenGeordnet[i]*fuellmenge)/8*10);
			if(mengenGeordnet[i]>maxMenge)
				maxMenge=mengenGeordnet[i];
		}
		System.out.println(mengenGeordnet[0]+", " +mengenGeordnet[1].intValue()+", " +mengenGeordnet[2]+", " +mengenGeordnet[3]+", " +mengenGeordnet[4]+", " +mengenGeordnet[5]+", " +maxMenge);
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
		timer.schedule(taskPin1, mengenGeordnet[0].intValue());
		timer.schedule(taskPin2, mengenGeordnet[1].intValue());
		timer.schedule(taskPin3, mengenGeordnet[2].intValue());
		timer.schedule(taskPin4, mengenGeordnet[3].intValue());
		timer.schedule(taskPin5, mengenGeordnet[4].intValue());
		timer.schedule(taskPin6, mengenGeordnet[5].intValue());
		timer.schedule(panelRemove, maxMenge.intValue());
		timer.schedule(unprovision, maxMenge.intValue());
				
	}
	
	public void reinigung(int i){
		this.pins.put(1,gpio.provisionDigitalOutputPin(RaspiPin.GPIO_01, "Pumpe 1", PinState.HIGH));
		this.pins.put(2,gpio.provisionDigitalOutputPin(RaspiPin.GPIO_02, "Pumpe 2", PinState.HIGH));
		this.pins.put(3,gpio.provisionDigitalOutputPin(RaspiPin.GPIO_03, "Pumpe 3", PinState.HIGH));
		this.pins.put(4,gpio.provisionDigitalOutputPin(RaspiPin.GPIO_04, "Pumpe 4", PinState.HIGH));
		this.pins.put(5,gpio.provisionDigitalOutputPin(RaspiPin.GPIO_05, "Pumpe 5", PinState.HIGH));
		this.pins.put(6,gpio.provisionDigitalOutputPin(RaspiPin.GPIO_06, "Pumpe 6", PinState.HIGH));
		
		this.pinTasks.put(1, new setHigh(pin1));
		this.pinTasks.put(2, new setHigh(pin2));
		this.pinTasks.put(3, new setHigh(pin3));
		this.pinTasks.put(4, new setHigh(pin4));
		this.pinTasks.put(5, new setHigh(pin5));
		this.pinTasks.put(6, new setHigh(pin6));
		
		Timer timer = new Timer();
		
		panelRemove = new panelRemove();
		
		unprovision = new Unprovision();
		
		pins.get(i).low();
		timer.schedule(pinTasks.get(i), 100*1000/8*10);
		
		
	}
	
	
}
