package me.fatihenes.uavsystem.model;

import java.util.ArrayList;
import java.util.List;

public class FlightPlan {
	private final List<Coordinate> waypoints = new ArrayList<Coordinate>();

	public void addWaypoint(Coordinate c) {
		waypoints.add(c);
	}

	public List<Coordinate> getWaypoints() {
		return waypoints;
	}
}
