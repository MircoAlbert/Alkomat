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
	private GpioPinDigitalOutput pin8;
	private GpioPinDigitalOutput pin9;
	private GpioPinDigitalOutput pin10;
	private GpioPinDigitalOutput pin11;
	private GpioPinDigitalOutput pin12;
	
	private setHigh taskPin1;
	private setHigh taskPin2; 
	private setHigh taskPin3;
	private setHigh taskPin4;
	private setHigh taskPin5;
	private setHigh taskPin6;
	private setHigh taskPin7;
	private setHigh taskPin8;
	private setHigh taskPin9;
	private setHigh taskPin10;
	private setHigh taskPin11;
	private setHigh taskPin12;

	
	private panelRemove panelRemove;
	
	private Unprovision unprovision;
	
	
	
	private Map<Integer, setHigh> pinTasks = new HashMap<Integer, setHigh>();
	private Map<Integer, GpioPinDigitalOutput> pins = new HashMap<Integer, GpioPinDigitalOutput>();
	
	
	public PumpenAnsteuerung(){
		this.gpio = GpioFactory.getInstance();
			
	}
	
	public void cancelTimer(JPanel panelList, JPanel panelWait){
		cancelled=true;
		pin1.high();
		pin2.high();
		pin3.high();
		pin4.high();
		pin5.high();
		pin6.high();
		pin7.high();
		pin8.high();
		pin9.high();
		pin10.high();
		pin11.high();
		pin12.high();
		gpio.shutdown();
		gpio.unprovisionPin(pin1);
		gpio.unprovisionPin(pin2);
		gpio.unprovisionPin(pin3);
		gpio.unprovisionPin(pin4);
		gpio.unprovisionPin(pin5);
		gpio.unprovisionPin(pin6);
		gpio.unprovisionPin(pin7);
		gpio.unprovisionPin(pin8);
		gpio.unprovisionPin(pin9);
		gpio.unprovisionPin(pin10);
		gpio.unprovisionPin(pin11);
		gpio.unprovisionPin(pin12);
		unprovision.cancel();
		taskPin1.cancel();
		taskPin2.cancel();
		taskPin3.cancel();
		taskPin4.cancel();
		taskPin5.cancel();
		taskPin6.cancel();
		taskPin7.cancel();
		taskPin8.cancel();
		taskPin9.cancel();
		taskPin10.cancel();
		taskPin11.cancel();
		taskPin12.cancel();
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
			gpio.unprovisionPin(pin7);
			gpio.unprovisionPin(pin8);
			gpio.unprovisionPin(pin9);
			gpio.unprovisionPin(pin10);
			gpio.unprovisionPin(pin11);
			gpio.unprovisionPin(pin12);
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
		
		this.pin1 = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_00, "Pumpe 1-vorwärts", PinState.HIGH);
		this.pin2 = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_01, "Pumpe 2-vorwärts", PinState.HIGH);
		this.pin3 = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_02, "Pumpe 3-vorwärts", PinState.HIGH);
		this.pin4 = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_03, "Pumpe 4-vorwärts", PinState.HIGH);
		this.pin5 = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_04, "Pumpe 5-vorwärts", PinState.HIGH);
		this.pin6 = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_05, "Pumpe 6-vorwärts", PinState.HIGH);
		this.pin7 = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_06, "Pumpe 1-rückwärts", PinState.HIGH);
		this.pin8 = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_07, "Pumpe 2-rückwärts", PinState.HIGH);
		this.pin9 = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_08, "Pumpe 3-rückwärts", PinState.HIGH);
		this.pin10 = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_09, "Pumpe 4-rückwärts", PinState.HIGH);
		this.pin11 = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_10, "Pumpe 5-rückwärts", PinState.HIGH);
		this.pin12 = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_11, "Pumpe 6-rückwärts", PinState.HIGH);
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
		taskPin7 = new setHigh(pin7);
		taskPin8 = new setHigh(pin8);
		taskPin9 = new setHigh(pin9);
		taskPin10 = new setHigh(pin10);
		taskPin11 = new setHigh(pin11);
		taskPin12 = new setHigh(pin12);
		
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
		timer.schedule((new setLow(pin7)), mengenGeordnet[0].intValue()+10);
		timer.schedule((new setLow(pin8)), mengenGeordnet[1].intValue()+10);
		timer.schedule((new setLow(pin9)), mengenGeordnet[2].intValue()+10);
		timer.schedule((new setLow(pin10)), mengenGeordnet[3].intValue()+10);
		timer.schedule((new setLow(pin11)), mengenGeordnet[4].intValue()+10);
		timer.schedule((new setLow(pin12)), mengenGeordnet[5].intValue()+10);
		timer.schedule(taskPin7, mengenGeordnet[0].intValue()+4010);
		timer.schedule(taskPin8, mengenGeordnet[1].intValue()+4010);
		timer.schedule(taskPin9, mengenGeordnet[2].intValue()+4010);
		timer.schedule(taskPin10, mengenGeordnet[3].intValue()+4010);
		timer.schedule(taskPin11, mengenGeordnet[4].intValue()+4010);
		timer.schedule(taskPin12, mengenGeordnet[5].intValue()+4010);
		timer.schedule(panelRemove, maxMenge.intValue()+4010);
		timer.schedule(unprovision, maxMenge.intValue()+4010);
				
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
