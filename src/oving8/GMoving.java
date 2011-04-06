package oving8;

import acm.graphics.GDimension;

public interface GMoving {
	/**
	 * Returns the speed as a GDimension. Changing the returned object should not affect this GMoving object.
	 * @return the speed as a GDimension
	 */
	public GDimension getSpeed();
	
	/**
	 * Sets the speed of this object to the specified vector
	 * @param xSpeed the speed in x-direction
	 * @param ySpeed the speed in y-direction
	 */
	
	public void setSpeed(double xSpeed, double ySpeed);
	
	/**
	 * Accelerates this object, i.e. changes the speed 
	 * @param xAcc the change in speed in x-direction
	 * @param yAcc the change in speed in y-direction
	 */
	public void accelerate(double xAcc, double yAcc);
	
	/**
	 * Move this object according to its speed
	 */
	public void move();
}
