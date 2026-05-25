package me.fatihenes.uavsystem.model;

public class FixedWing extends Drone {

	public static final double BATTERY_USAGE = 0.02;

	public FixedWing(String id, double batteryLevel, Coordinate currentPosition) {
		super(id, batteryLevel, currentPosition);
	}

//	@Override
//	public void moveTo(Coordinate target) {
//		System.out.println("FixedWing (ID: " + this.getId() + ") is performing a turning maneuver...");
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
		double neededBattery = distance * BATTERY_USAGE;

		return neededBattery;
	}

}
