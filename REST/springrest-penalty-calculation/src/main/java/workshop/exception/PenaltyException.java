package workshop.exception;

public class PenaltyException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public PenaltyException(String errorMsg) {
		super(errorMsg);
	}
}
