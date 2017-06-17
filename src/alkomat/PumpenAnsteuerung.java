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
			System.out.println(mengenGeordnet[i]);
			if(!(mengenGeordnet[i]==0.0))
				pumpenStatus[i]=true;
			else
				pumpenStatus[i]=false;
		}
		System.out.println(fuellmenge);
		//this.mengenDouble = new Integer[mengenGeordnet.length];
					
		Timer timer = new Timer();
		for(int i=0;i<mengenGeordnet.length;i++){
			if(i==0){
				mengenGeordnet[i]=((fuellmenge*mengenGeordnet[i])/7.67*10)+6200;
			}
			if(i==1)
				mengenGeordnet[i]=((fuellmenge*mengenGeordnet[i])/6.66*10)+6000;
			if(i==2)
				mengenGeordnet[i]=((fuellmenge*mengenGeordnet[i])/8.33*10)+5700;
			if(i==3)
				mengenGeordnet[i]=((fuellmenge*mengenGeordnet[i])/6.33*10)+7000;
			if(i==4)
				mengenGeordnet[i]=((fuellmenge*mengenGeordnet[i])/7.33*10)+5500;
			if(i==5)
				mengenGeordnet[i]=((fuellmenge*mengenGeordnet[i])/8.00*10)+5500;
			if(mengenGeordnet[i]>maxMenge){
				maxMenge=mengenGeordnet[i];
				pumpeMaxMenge=i;
			}
		}
		System.out.println(Math.round(mengenGeordnet[0])+", " +Math.round(mengenGeordnet[1])+", " +Math.round(mengenGeordnet[2])+", " +Math.round(mengenGeordnet[3])+", " +Math.round(mengenGeordnet[4])+", " +Math.round(mengenGeordnet[5])+", " +maxMenge);
		if(pumpenStatus[0]){	
		pin1.low();
		timer.schedule((new setHigh(pin1)), Math.round(mengenGeordnet[0]));
		timer.schedule((new setLow(pin7)), Math.round(mengenGeordnet[0])+250);
		timer.schedule((new setHigh(pin7)), Math.round(mengenGeordnet[0])+6200);
		}
		if(pumpenStatus[1]){	
		timer.schedule((new setLow(pin2)),200);
		timer.schedule((new setHigh(pin2)), Math.round(mengenGeordnet[1]));
		timer.schedule((new setLow(pin8)), Math.round(mengenGeordnet[1])+250);
		timer.schedule((new setHigh(pin8)), Math.round(mengenGeordnet[1])+6000);
		}
		if(pumpenStatus[2]){
		timer.schedule((new setLow(pin3)), 400);
		timer.schedule((new setHigh(pin3)), Math.round(mengenGeordnet[2]));
		timer.schedule((new setLow(pin9)), Math.round(mengenGeordnet[2])+250);
		timer.schedule((new setHigh(pin9)), Math.round(mengenGeordnet[2])+5700);
		}
		if(pumpenStatus[3]){	
		timer.schedule((new setLow(pin4)),600);
		timer.schedule((new setHigh(pin4)), Math.round(mengenGeordnet[3]));
		timer.schedule((new setLow(pin10)), Math.round(mengenGeordnet[3])+250);
		timer.schedule((new setHigh(pin10)), Math.round(mengenGeordnet[3])+7000);
		}
		if(pumpenStatus[4]){
		timer.schedule((new setLow(pin5)), 800);
		timer.schedule((new setHigh(pin5)), Math.round(mengenGeordnet[4]));
		timer.schedule((new setLow(pin11)), Math.round(mengenGeordnet[4])+250);
		timer.schedule((new setHigh(pin11)), Math.round(mengenGeordnet[4])+5500);
		}
		if(pumpenStatus[5]){	
		timer.schedule((new setLow(pin6)), 1000);
		timer.schedule((new setHigh(pin6)), Math.round(mengenGeordnet[5]));
		timer.schedule((new setLow(pin12)), Math.round(mengenGeordnet[5])+250);
		timer.schedule((new setHigh(pin12)), Math.round(mengenGeordnet[5])+5500);
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
