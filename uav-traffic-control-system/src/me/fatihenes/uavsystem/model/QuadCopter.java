package me.fatihenes.uavsystem.model;

public class QuadCopter extends Drone {
	private final int rotorCount;
	private double payloadCapacity = 0;

	public static final double MAX_PAYLOAD_CAPACITY = 50;
	public static final double MIN_PAYLOAD_CAPACITY = 0;
	public static final double BATTERY_USAGE = 0.05;

	public QuadCopter(String id, double batteryLevel, Coordinate currentPosition, double payloadCapacity) {
		super(id, batteryLevel, currentPosition);
		this.rotorCount = 4;
		this.payloadCapacity = payloadCapacity;
	}

//	@Override
//	public void moveTo(Coordinate target) {
//		System.out.println("Quadcopter (ID: " + getId() + ") is performing vertical takeoff and orientation...");
//
//		Coordinate current = getCurrentPosition();
//		double distance = current.distanceTo(target);
//
//		System.out.println("Distance: " + distance);
//
//		double neededBattery = calculateRequiredBattery(distance);
//
//		if (getBatteryLevel() < neededBattery) {
//			throw new InsufficientBatteryException(
//					"Flight cancelled! Required battery: " + neededBattery + "%, Avaible: " + getBatteryLevel() + "%");
//		}
//
//		// new coordinate
//		setCurrentPosition(target);
//		setBatteryLevel(getBatteryLevel() - neededBattery);
//
//	}

	@Override
	public double calculateRequiredBattery(double distance) {
		double payloadBatteryUsage = getPayloadCapacity() / 300;
		double neededBattery = (distance * BATTERY_USAGE) + (distance * payloadBatteryUsage);

		return neededBattery;
	}

	public int getRotorCount() {
		return rotorCount;
	}

	public double getPayloadCapacity() {
		return payloadCapacity;
	}

	public void setPayloadCapacity(double payloadCapacity) {
		if (payloadCapacity > MAX_PAYLOAD_CAPACITY) {
			throw new IllegalArgumentException("Overweight! Max capacity is 50kg.");
		}
		if (payloadCapacity < MIN_PAYLOAD_CAPACITY) {
			this.payloadCapacity = MIN_PAYLOAD_CAPACITY;
			throw new IllegalArgumentException(
					"Payload capacity cannot have a negative value. It can take a minimum of 0kg. It is set to minimum capacity.");
		}
		this.payloadCapacity = payloadCapacity;
	}

}
