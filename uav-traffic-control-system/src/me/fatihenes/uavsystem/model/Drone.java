package me.fatihenes.uavsystem.model;

public abstract class Drone {
	private String id;
	private double batteryLevel;
	private Coordinate currentPosition;
	private FlightPlan flightPlan;
	private double speed = 5;
	private Coordinate targetWaypoint;

	private static final double MAX_BATTERY_LEVEL = 100;
	private static final double MIN_BATTERY_LEVEL = 0;

	public Drone(String id, double batteryLevel, Coordinate currentPosition) {
		this.id = id;
		setBatteryLevel(batteryLevel);
		this.currentPosition = currentPosition;
	}

	public void moveTo(Coordinate target) {
		this.targetWaypoint = target;
	}

	public void updatePosition() {
		if (targetWaypoint == null)
			return;

		Coordinate current = getCurrentPosition();
		double distance = current.distanceTo(targetWaypoint);
		System.out.println(this.getId() + " - ETA Distance: " + distance + " m  | Target: " + targetWaypoint.getX());

		if (distance <= speed) {
			setCurrentPosition(targetWaypoint);
			targetWaypoint = null;
			System.out.println(this.getId() + " reached to goal.");
			return;
		}

		// vectors
		double dx = targetWaypoint.getX() - current.getX();
		double dy = targetWaypoint.getY() - current.getY();
		double dz = targetWaypoint.getZ() - current.getZ();

		double newX = current.getX() + ((dx / distance) * speed);
		double newY = current.getY() + ((dy / distance) * speed);
		double newZ = current.getZ() + ((dz / distance) * speed);

		setCurrentPosition(new Coordinate(newX, newY, newZ));
	}

	public boolean isFlying() {
		return this.targetWaypoint != null;
	}

	public abstract double calculateRequiredBattery(double distance);

	public String getId() {
		return id;
	}

	public double getBatteryLevel() {
		return batteryLevel;
	}

	public void setBatteryLevel(double batteryLevel) {
		if (batteryLevel > MAX_BATTERY_LEVEL) {
			throw new IllegalArgumentException("The battery level cannot be more than 100%.");
		}
		if (batteryLevel < MIN_BATTERY_LEVEL) {
			throw new IllegalArgumentException("The battery level cannot be negative.");
		}

		this.batteryLevel = batteryLevel;
	}

	public Coordinate getCurrentPosition() {
		return currentPosition;
	}

	public void setCurrentPosition(Coordinate currentPosition) {
		this.currentPosition = currentPosition;
	}

	public FlightPlan getFlightPlan() {
		return flightPlan;
	}

	public void setFlightPlan(FlightPlan flightPlan) {
		this.flightPlan = flightPlan;
	}

}
