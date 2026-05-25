package me.fatihenes.uavsystem.engine;

import java.util.ArrayList;
import java.util.List;

import me.fatihenes.uavsystem.model.Drone;
import me.fatihenes.uavsystem.model.FlightPlan;

public class Airspace {
	public final List<Drone> activeDrones = new ArrayList<>();
	public final Radar radar = new Radar();
	public final RouteValidator validator = new RouteValidator();

	public void register(Drone drone, FlightPlan plan) {
		if (validator.validate(plan, drone)) {
			drone.setFlightPlan(plan);
			activeDrones.add(drone);
			drone.moveTo(plan.getWaypoints().get(0));
			System.out.println(drone.getId() + " was admitted to the airspace.");
		} else {
			System.err.println("Register denied: " + drone.getId() + " - not enough battery...");
		}
	}

	// multithread
	public void startSimulation() {
		System.out.println("-- SIMULATION STARTED ---");
		boolean isRunning = true;

		while (isRunning) {
			System.out.println("-- TICK (1 Second Passed) --");
			boolean anyDroneFlying = false;

			for (Drone drone : activeDrones) {
				drone.updatePosition();

				if (drone.isFlying()) {
					anyDroneFlying = true;
				}
			}

			radar.scanAirspace(activeDrones);

			if (!anyDroneFlying) {
				System.out.println("All drones reached their targets. The simulation is ending.");
				isRunning = false;
				continue;
			}

			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				System.err.println("Simulation interrupted!");
				isRunning = false;
			}

		}
	}
}
