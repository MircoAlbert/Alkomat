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
	
	
	
	private Map<Integer, setHigh> pinTasks = new HashMap<Integer, setHigh>();
	private Map<Integer, GpioPinDigitalOutput> pins = new HashMap<Integer, GpioPinDigitalOutput>();
	
	
	public PumpenAnsteuerung(){
		this.gpio = GpioFactory.getInstance();
		this.pin1 = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_00, "Pumpe 1-vorw�rts", PinState.HIGH);
		this.pin2 = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_01, "Pumpe 2-vorw�rts", PinState.HIGH);
		this.pin3 = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_02, "Pumpe 3-vorw�rts", PinState.HIGH);
		this.pin4 = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_03, "Pumpe 4-vorw�rts", PinState.HIGH);
		this.pin5 = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_04, "Pumpe 5-vorw�rts", PinState.HIGH);
		this.pin6 = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_05, "Pumpe 6-vorw�rts", PinState.HIGH);
		this.pin7 = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_06, "Pumpe 1-r�ckw�rts", PinState.HIGH);
		this.pin8 = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_07, "Pumpe 2-r�ckw�rts", PinState.HIGH);
		this.pin9 = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_08, "Pumpe 3-r�ckw�rts", PinState.HIGH);
		this.pin10 = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_09, "Pumpe 4-r�ckw�rts", PinState.HIGH);
		this.pin11 = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_10, "Pumpe 5-r�ckw�rts", PinState.HIGH);
		this.pin12 = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_11, "Pumpe 6-r�ckw�rts", PinState.HIGH);
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
		this.panelWait = panelWait;
		this.panelList = panelList;
		this.cancel = cancel;
		this.menue = menue;
		this.sperren = sperren;
		cancelled = false;
		
		
		//this.mengenDouble = new Integer[mengenGeordnet.length];
		
		Timer timer = new Timer();
		for(int i=0;i<mengenGeordnet.length;i++){
			mengenGeordnet[i]=((mengenGeordnet[i]*fuellmenge)/8*10)+(i*10);
			if(mengenGeordnet[i]>maxMenge)
				maxMenge=mengenGeordnet[i];
		}
		System.out.println(mengenGeordnet[0]+", " +mengenGeordnet[1].intValue()+", " +mengenGeordnet[2]+", " +mengenGeordnet[3]+", " +mengenGeordnet[4]+", " +mengenGeordnet[5]+", " +maxMenge);
				
		pin1.low();
		pin2.low();
		pin3.low();
		pin4.low();
		pin5.low();
		pin6.low();
		panelList.setVisible(false);
		panelWait.setVisible(true);
		panelRemove = new panelRemove();
		timer.schedule((new setHigh(pin1)), mengenGeordnet[0].intValue());
		timer.schedule((new setHigh(pin2)), mengenGeordnet[1].intValue());
		timer.schedule((new setHigh(pin3)), mengenGeordnet[2].intValue());
		timer.schedule((new setHigh(pin4)), mengenGeordnet[3].intValue());
		timer.schedule((new setHigh(pin5)), mengenGeordnet[4].intValue());
		timer.schedule((new setHigh(pin6)), mengenGeordnet[5].intValue());
		timer.schedule((new setLow(pin7)), mengenGeordnet[0].intValue()+250);
		timer.schedule((new setLow(pin8)), mengenGeordnet[1].intValue()+250);
		timer.schedule((new setLow(pin9)), mengenGeordnet[2].intValue()+250);
		timer.schedule((new setLow(pin10)), mengenGeordnet[3].intValue()+250);
		timer.schedule((new setLow(pin11)), mengenGeordnet[4].intValue()+250);
		timer.schedule((new setLow(pin12)), mengenGeordnet[5].intValue()+250);
		timer.schedule((new setHigh(pin7)), mengenGeordnet[0].intValue()+4250);
		timer.schedule((new setHigh(pin8)), mengenGeordnet[1].intValue()+4250);
		timer.schedule((new setHigh(pin9)), mengenGeordnet[2].intValue()+4250);
		timer.schedule((new setHigh(pin10)), mengenGeordnet[3].intValue()+4250);
		timer.schedule((new setHigh(pin11)), mengenGeordnet[4].intValue()+4250);
		timer.schedule((new setHigh(pin12)), mengenGeordnet[5].intValue()+4250);
		timer.schedule(panelRemove, maxMenge.intValue()+4250);
		
				
	}
	
	public void reinigungStart(int i){
				
		pins.get(i).low();
		
	}
	
	public void reinigungStop(int i){
		
		
		pins.get(i).high();
		
		
	}
	
	
}
