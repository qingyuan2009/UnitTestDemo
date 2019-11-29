package production;

public class ClassA {
	
	private final String test;

    public ClassA(String test) {
        this.test = test;
    }

    public String check() {
        return "checked " + this.test;
    }

}
