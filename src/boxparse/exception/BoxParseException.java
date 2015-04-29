package boxparse.exception;

public abstract class BoxParseException extends Exception {

	private static final long serialVersionUID = 54792305581261651L;

	public BoxParseException() {
	}

	public BoxParseException(String message) {
		super(message);
	}

}
