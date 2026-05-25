package me.fatihenes.uavsystem.engine;

import me.fatihenes.uavsystem.model.Coordinate;
import me.fatihenes.uavsystem.model.Drone;
import me.fatihenes.uavsystem.model.FlightPlan;

public class RouteValidator {
	public boolean validate(FlightPlan flightPlan, Drone drone) {
		if (flightPlan.getWaypoints().isEmpty()) {
			return false;
		}

		double totalDistance = 0;
		Coordinate previousPoint = drone.getCurrentPosition();

		for (Coordinate waypoint : flightPlan.getWaypoints()) {
			totalDistance += previousPoint.distanceTo(waypoint);
			previousPoint = waypoint;
		}

		double neededBattery = drone.calculateRequiredBattery(totalDistance);
		return neededBattery <= drone.getBatteryLevel();
	}
}
