package jUnit;

public class MessageUtil {

	private String message;

	// Constructor
	// @param message to be printed
	public MessageUtil(String message) {
		this.message = message;
	}

	// prints the message
	public String printMessage() {
		System.out.println(message);
		return message;
	}

	// prints the message
	public void printErrorMessage() {
		System.out.println(message);
		int a = 0;
		int b = 1 / a;
	}

}
