package alkomat;

import java.util.HashMap;
import java.util.Map;
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
	
	private int pumpeMaxMenge;
	
	private Map<Integer, GpioPinDigitalOutput> pins = new HashMap<Integer, GpioPinDigitalOutput>();
	private Map<Integer, Integer> rückwärtsDelay = new HashMap<Integer, Integer>();
	
	
	public PumpenAnsteuerung(){
		this.gpio = GpioFactory.getInstance();
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
		this.pins.put(1,pin1);
		this.pins.put(2,pin2);
		this.pins.put(3,pin3);
		this.pins.put(4,pin4);
		this.pins.put(5,pin5);
		this.pins.put(6,pin6);
		this.rückwärtsDelay.put(0,6200);
		this.rückwärtsDelay.put(1,6000);
		this.rückwärtsDelay.put(2,5700);
		this.rückwärtsDelay.put(3,7000);
		this.rückwärtsDelay.put(4,5500);
		this.rückwärtsDelay.put(5,5500);
					
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
		
	public void start(Double[] mengenGeordnet,Integer fuellmenge, JPanel panelList, JPanel panelWait, JButton cancel, JButton menue, JButton sperren){
		Double maxMenge = 0.0;
		this.panelWait = panelWait;
		this.panelList = panelList;
		this.cancel = cancel;
		this.menue = menue;
		this.sperren = sperren;
		cancelled = false;
		boolean[] pumpenStatus= new boolean[6];
		for(int i=0;i<mengenGeordnet.length;i++){
			if(!(mengenGeordnet[i]==0.0))
				pumpenStatus[i]=true;
			else
				pumpenStatus[i]=false;
		}
		
		//this.mengenDouble = new Integer[mengenGeordnet.length];
					
		Timer timer = new Timer();
		for(int i=0;i<mengenGeordnet.length;i++){
			if(i==0)
				mengenGeordnet[i]=mengenGeordnet[i]*7.67*1000+5200;
			if(i==1)
				mengenGeordnet[i]=mengenGeordnet[i]*6.66*1000+5000;
			if(i==2)
				mengenGeordnet[i]=mengenGeordnet[i]*8.33*1000+4700;
			if(i==3)
				mengenGeordnet[i]=mengenGeordnet[i]*6.33*1000+6000;
			if(i==4)
				mengenGeordnet[i]=mengenGeordnet[i]*7.33*1000+4500;
			if(i==5)
				mengenGeordnet[i]=mengenGeordnet[i]*8.00*1000+4500;
			if(mengenGeordnet[i]>maxMenge){
				maxMenge=mengenGeordnet[i];
				pumpeMaxMenge=i;
			}
		}
		System.out.println(mengenGeordnet[0]+", " +mengenGeordnet[1].intValue()+", " +mengenGeordnet[2]+", " +mengenGeordnet[3]+", " +mengenGeordnet[4]+", " +mengenGeordnet[5]+", " +maxMenge);
		if(pumpenStatus[0]){	
		pin1.low();
		timer.schedule((new setHigh(pin1)), mengenGeordnet[0].intValue());
		timer.schedule((new setLow(pin7)), mengenGeordnet[0].intValue()+250);
		timer.schedule((new setHigh(pin7)), mengenGeordnet[0].intValue()+6200);
		}
		if(pumpenStatus[1]){	
		timer.schedule((new setLow(pin2)),200);
		timer.schedule((new setHigh(pin2)), mengenGeordnet[1].intValue());
		timer.schedule((new setLow(pin8)), mengenGeordnet[1].intValue()+250);
		timer.schedule((new setHigh(pin8)), mengenGeordnet[1].intValue()+6000);
		}
		if(pumpenStatus[2]){
		timer.schedule((new setLow(pin3)), 400);
		timer.schedule((new setHigh(pin3)), mengenGeordnet[2].intValue());
		timer.schedule((new setLow(pin9)), mengenGeordnet[2].intValue()+250);
		timer.schedule((new setHigh(pin9)), mengenGeordnet[2].intValue()+5700);
		}
		if(pumpenStatus[3]){	
		timer.schedule((new setLow(pin4)),600);
		timer.schedule((new setHigh(pin4)), mengenGeordnet[3].intValue());
		timer.schedule((new setLow(pin10)), mengenGeordnet[3].intValue()+250);
		timer.schedule((new setHigh(pin10)), mengenGeordnet[3].intValue()+7000);
		}
		if(pumpenStatus[4]){
		timer.schedule((new setLow(pin5)), 800);
		timer.schedule((new setHigh(pin5)), mengenGeordnet[4].intValue());
		timer.schedule((new setLow(pin11)), mengenGeordnet[4].intValue()+250);
		timer.schedule((new setHigh(pin11)), mengenGeordnet[4].intValue()+5500);
		}
		if(pumpenStatus[5]){	
		timer.schedule((new setLow(pin6)), 1000);
		timer.schedule((new setHigh(pin6)), mengenGeordnet[5].intValue());
		timer.schedule((new setLow(pin12)), mengenGeordnet[5].intValue()+250);
		timer.schedule((new setHigh(pin12)), mengenGeordnet[5].intValue()+5500);
		}
		panelList.setVisible(false);
		panelWait.setVisible(true);
		panelRemove = new panelRemove();
				
		timer.schedule(panelRemove, maxMenge.intValue()+rückwärtsDelay.get(pumpeMaxMenge));
		
				
	}
	
	public void reinigungStart(int i){
				
		pins.get(i).low();
		
	}
	
	public void reinigungStop(int i){
		
		
		pins.get(i).high();
		
		
	}
	
	
}
