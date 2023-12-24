/**
 ***********************************************
 * @Author : John Brown
 * @Originally made : May 18, 2023
 * @Last Modified: December 16, 2023
 * @Description: Family doctor object containing information about the family doctor of a pharmacy patient
 ***********************************************
 */


package PatientManagement;

public class FamilyDoctor {
	private String name; // family doctor name
	private String address; // family doctor address
	private long phoneNumber; // family doctor phone number
	
	public FamilyDoctor(String name, String address, long phoneNumber) {
		this.name = name;
		this.address = address;
		this.phoneNumber = phoneNumber;
	}
	
	public FamilyDoctor() {
		
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public long getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(long phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
}
