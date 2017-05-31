package mvc.model;

public class AccountAlreadyExistException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public String toString() {
		return "Account already exist \n"+super.toString();
	}
	
}
