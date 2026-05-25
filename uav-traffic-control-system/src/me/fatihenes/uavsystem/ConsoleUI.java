package me.fatihenes.uavsystem;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

import me.fatihenes.uavsystem.engine.Airspace;
import me.fatihenes.uavsystem.model.Coordinate;
import me.fatihenes.uavsystem.model.Drone;
import me.fatihenes.uavsystem.model.FixedWing;
import me.fatihenes.uavsystem.model.FlightPlan;
import me.fatihenes.uavsystem.model.QuadCopter;

public class ConsoleUI {

	public void startConsole() {
		boolean isRunning = true;
		ArrayList<Drone> drones = new ArrayList<Drone>();
		Scanner scanner = new Scanner(System.in);
		Airspace airspace = new Airspace();
		FlightPlan plan = new FlightPlan();

		System.out.println("==========================================");
		System.out.println("-- Welcome to UAV Trafic Control System --");
		System.out.println("==========================================");

		while (isRunning) {

			System.out.println("------------------------------------------");
			System.out.println("1 - Add a drone");
			System.out.println("2 - Select a plan");
			System.out.println("3 - Add the drone to the fleet");
			System.out.println("4 - List drones");
			System.out.println("5 - Start simulation");
			System.out.println("0 - Exit");

			boolean isValidAction = false;
			int action1 = 0;
			while (!isValidAction) {
				try {
					System.out.print("Please select an action => ");
					action1 = scanner.nextInt();

					isValidAction = true;
				} catch (InputMismatchException e) {
					System.err.println("CRITICAL ERROR: Please enter a numeric value!!!");
					scanner.nextLine();
					System.out.println("Retrying action selection...\n");
				}
			}

			switch (action1) {
			case 1:
				System.out.println("------------------------------------------");
				System.out.println("1 - Fixed Wing");
				System.out.println("2 - Quad Copter");

				int type1 = 0;
				boolean isValidType = false;
				while (!isValidType) {
					try {
						System.out.print("Select the type of drone => ");
						type1 = scanner.nextInt();

						isValidType = true;
					} catch (InputMismatchException e) {
						System.err.println("CRITICAL ERROR: Please enter a numeric value!!!");
						scanner.nextLine();
						System.out.println("Retrying drone selection...\n");
					}
				}

				if (type1 == 1) {
					System.out.println("------------------------------------------");
					System.out.print("Drone ID: ");
					String getID = scanner.next();

					double getBatteryLevel = 0;
					int getX = 0, getY = 0, getZ = 0;
					boolean isValidInput = false;

					while (!isValidInput) {
						try {
							System.out.print("Drone Battery Level: ");
							getBatteryLevel = scanner.nextDouble();

							System.out.println("--Enter coordinates--");
							System.out.print("X: ");
							getX = scanner.nextInt();
							System.out.print("Y: ");
							getY = scanner.nextInt();
							System.out.print("Z: ");
							getZ = scanner.nextInt();

							isValidInput = true;
						} catch (InputMismatchException e) {
							System.err.println("CRITICAL ERROR: Please enter a numeric value!!!");
							scanner.nextLine();
							System.out.println("Retrying drone creation...\n");
						}
					}

					FixedWing fWing = new FixedWing(getID, getBatteryLevel, new Coordinate(getX, getY, getZ));
					drones.add(fWing);

				} else if (type1 == 2) {
					System.out.println("------------------------------------------");
					System.out.print("Drone ID: ");
					String getID = scanner.next();

					double getBatteryLevel = 0;
					int getX = 0, getY = 0, getZ = 0;
					double payload = 0;
					boolean isValidInput = false;

					while (!isValidInput) {
						try {
							System.out.print("Drone Battery Level: ");
							getBatteryLevel = scanner.nextDouble();

							System.out.print("Payload (MAX 50KG): ");
							payload = scanner.nextDouble();

							System.out.println("--Enter coordinates--");
							System.out.print("X: ");
							getX = scanner.nextInt();
							System.out.print("Y: ");
							getY = scanner.nextInt();
							System.out.print("Z: ");
							getZ = scanner.nextInt();

							isValidInput = true;
						} catch (InputMismatchException e) {
							System.err.println("CRITICAL ERROR: Please enter a numeric value!!!");
							scanner.nextLine();
							System.out.println("Retrying drone creation...\n");
						}
					}

					QuadCopter qCopter = new QuadCopter(getID, getBatteryLevel, new Coordinate(getX, getY, getZ),
							payload);
					drones.add(qCopter);
				}
				break;

			case 2:
				System.out.println("------------------------------------------");
				int planX = 0, planY = 0, planZ = 0;
				boolean isValidInput = false;

				while (!isValidInput) {
					try {
						System.out.println("Please enter a flight plan");
						System.out.print("X: ");
						planX = scanner.nextInt();
						System.out.print("Y: ");
						planY = scanner.nextInt();
						System.out.print("Z: ");
						planZ = scanner.nextInt();

						isValidInput = true;
					} catch (InputMismatchException e) {
						System.err.println("CRITICAL ERROR: Please enter a numeric value!!!");
						scanner.nextLine();
						System.out.println("Retrying flight plan creation...\n");
					}
				}

				plan.addWaypoint(new Coordinate(planX, planY, planZ));
				break;

			case 3:
				for (Drone drone : drones) {
					airspace.register(drone, plan);
				}

				drones.clear();

				break;

			case 4:
				System.out.println("------------------------------------------");
				System.out.println("--------------- Drone List ---------------");

				for (Drone drone : drones) {
					System.out.println("ID: " + drone.getId() + " | Battery Level: " + drone.getBatteryLevel());
				}
				break;

			case 5:
				System.out.println("------------------------------------------");
				airspace.startSimulation();
				break;

			case 0:
				System.out.println("Exiting...");
				isRunning = false;
				break;

			default:
				break;
			}

		}

	}
}
