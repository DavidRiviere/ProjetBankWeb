package biz;

public class WrongIdException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8127147809989817304L;

	@Override
	public String toString() {
		return "The login and/or password don't match any registred user "+super.toString();
	}

	
}
