package production;

public class ClassPartial {

	public static String staticMethod() {
		return "Hello Baeldung!";
	}

	public final String finalMethod() {
		return "Hello Baeldung!";
	}

	private String privateMethod() {
		return "Hello Baeldung!";
	}

	public String privateMethodCaller() {
		return privateMethod() + " Welcome to the Java world.";
	}

}
