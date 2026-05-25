package me.fatihenes.uavsystem.exception;

public class InsufficientBatteryException extends RuntimeException {

	public InsufficientBatteryException(String message) {
		super(message);
	}
}
