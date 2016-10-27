package myproject.model;

/**
 * A light has a boolean state.
 */
public class Light implements Agent {
	Light() { } // Created only by this package

	private boolean state;

	public boolean getState() {
		return state; // implement state pattern to change lights, has four different states
	}
	public void run(double time) {
		if (time%40==0) { // remove, doesnt make sense
			state = !state;
		}
	}
}

