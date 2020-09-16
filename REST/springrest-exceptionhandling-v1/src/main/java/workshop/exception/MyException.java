package workshop.exception;

public class MyException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	private String errorMsg;

	public MyException(String errorMsg) {
		super(errorMsg);
	}

	public String getErrorMsg() {
		return errorMsg;
	}
}
