package me.fatihenes.uavsystem.model;

public class Coordinate {
	private double x; // latitude
	private double y; // longitude
	private double z; // altitude

	private Drone drone;

	public static final double MAX_ALTITUDE = 120;
	public static final double MIN_ALTITUDE = 0;

	public Coordinate(double x, double y, double z) {
		this.x = x;
		this.y = y;
		setZ(z);
	}

	public double distanceTo(Coordinate other) {
		double dx = other.getX() - this.getX();
		double dy = other.getY() - this.getY();
		double dz = other.getZ() - this.getZ();

		return Math.sqrt(dx * dx + dy * dy + dz * dz);
	}

	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}

	public double getZ() {
		return z;
	}

	public void setZ(double z) {
		if (z > MAX_ALTITUDE) {
			this.z = MAX_ALTITUDE;
			throw new IllegalArgumentException(
					"Violation: According to DGCA regulations, the maximum altitude may be 120 meters. Requested altitude: "
							+ z + "\nAdjusted to maximum altitude. Altitude: " + this.z);
		}
		if (z < MIN_ALTITUDE) {
			this.z = MIN_ALTITUDE;
			throw new IllegalArgumentException(
					"Violation: Z coordinate (altitude) cannot be negative.\nAdjusted to minimum altitude. Altitude: "
							+ this.z);
		}

		this.z = z;
	}

}
