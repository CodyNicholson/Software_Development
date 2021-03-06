package myproject.model;

import java.awt.Color;
import java.util.ArrayList;

/**
 * A light has a boolean state.
 */
public class Light implements Agent
{	
	private ArrayList<Car> observers = new ArrayList<>();
	private Color clr = Color.green;
	private LightState state;
	private LStateGreenNSRedEW NSgreenEWred;
	private LStateYellowNSRedEW NSyellowEWred;
	private LStateRedNSGreenEW NSredEWgreen;
	private LStateRedNSYellowEW NSredEWyellow;
	
	public Light()
	{
		double randomGreenLightLen = (Math.random() * (MP.greenLightLenMin - MP.greenLightLenMax) + MP.greenLightLenMin);
		double randomYellowLightLen = (Math.random() * (MP.yellowLightLenMin - MP.yellowLightLenMax) + MP.yellowLightLenMin);
		int rand1234 = (int) (Math.random() * 4);
		this.NSgreenEWred = new LStateGreenNSRedEW(randomGreenLightLen);
		this.NSyellowEWred = new LStateYellowNSRedEW(randomYellowLightLen);
		this.NSredEWgreen = new LStateRedNSGreenEW(randomGreenLightLen);
		this.NSredEWyellow = new LStateRedNSYellowEW(randomYellowLightLen);
		this.state = this.NSgreenEWred;
		switch(rand1234)
		{
			case 0: state = this.NSgreenEWred;
				break;
			case 1: state = this.NSyellowEWred;
				break;
			case 2: state = this.NSredEWgreen;
				break;
			case 3: state = this.NSredEWyellow;
				break;
		}
	}
	
	public void subscribeCar(Car c)
	{
		observers.add(c);
	}
	
	public void unsubscribeCar(Car c)
	{
		observers.remove(c);
	}

	public Color getDrawColor()
	{
		return clr;
	}
	
	public LightColor getLightColor(Car c)
	{
		return state.getLightColor(c);
	}
	
	public void addObservingCar(Car c)
	{
		observers.add(c);
	}
	

	
	public void update(double time)
	{
		state.handle(this, time);
	}
	
	public LStateGreenNSRedEW getLStateGreenNSRedEW()
	{
		return NSgreenEWred;
	}
	
	public LStateYellowNSRedEW getLStateYellowNSRedEW()
	{
		return NSyellowEWred;
	}
	
	public LStateRedNSGreenEW getLStateRedNSGreenEW()
	{
		return NSredEWgreen;
	}
	
	public LStateRedNSYellowEW getLStateRedNSYellowEW()
	{
		return NSredEWyellow;
	}
	
	public void setState(LightState s, double time)
	{
		this.state = s;
		this.state.setSwitchTime(time);
		clr = this.state.getDrawColor();
		
	}

	@Override
	public void run(double time)
	{
		// Allows my to add intersections to agents in Model
	}
}

