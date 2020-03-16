package appli;


public class BodyType {
	private String bType;

	// bulk
	// shredding
	// maintain
	// data field of routein is different based on costructors output

	protected BodyType(String bType) {

		if (bType.equals("bulk")) {
			this.bType = "b";
		} else if (bType.equals("shred")) {
			this.bType = "s";
		} else if (bType.equals("maintain")) {
			this.bType = "m";
		} 

	}
	
	protected BodyType() {
		//default constructor
		this.bType = "";
	}

	protected String getbType() {
		return bType;
	}

	protected void setbType(String bType) {
		this.bType = bType;
	}

	// one method to call the private ones based on bodyType
	protected void showMenu() {
		// will call bulk/shred/maintain Menu method based on char bType
		if (this.bType.equals("b")) {
			this.bulkMenu();
		} else if (this.bType.equals("s")) {
			this.shredMenu();
		} else if (this.bType.equals("m")) {
			this.maintainMenu();
		}
	}
	
	protected void scheduler() {
		// will call bulk/shred/maintain Menu method based on char bType
		if (this.bType.equals("b")) {
			this.bulkSched();
		} else if (this.bType.equals("s")) {
			this.shredSched();
		} else if (this.bType.equals("m")) {
			this.maintainSched();
		}
		
	}

	// methods for bulk
	private void bulkMenu() {

	}

	private void bulkSched() {

	}

	// methods for shred
	private void shredMenu() {

	}

	private void shredSched() {

	}

	// methods for maintain
	private void maintainMenu() {

	}

	private void maintainSched() {

	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
