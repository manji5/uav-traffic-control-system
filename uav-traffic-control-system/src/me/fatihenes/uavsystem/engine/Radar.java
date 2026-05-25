package me.fatihenes.uavsystem.engine;

import java.util.List;

import me.fatihenes.uavsystem.model.Drone;

public class Radar {
	private final double CRITICAL_DISTANCE = 10;

	public void scanAirspace(List<Drone> drones) {
		System.out.println("Radar airspace scan started...");

		for (int i = 0; i < drones.size() - 1; i++) {
			Drone droneA = drones.get(i);

			for (int j = i + 1; j < drones.size(); j++) {
				Drone droneB = drones.get(j);

				double distance = droneA.getCurrentPosition().distanceTo(droneB.getCurrentPosition());

				if (distance < CRITICAL_DISTANCE) {
					System.out.println("DANGER: Collision course detected! " + droneA.getId() + " and " + droneB.getId()
							+ " are " + String.format("%.2f", distance) + "m apart!");
				}
			}
		}
	}

}
