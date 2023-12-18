package PatientManagement;

public class Insurance {
	private String company;
	private int insuranceNumber;
	
	public Insurance(String company, int insuranceNumber) {
		this.company = company;
		this.insuranceNumber = insuranceNumber;
	}
	
	public String getCompany() {
		return company;
	}
	
	public void setCompany(String company) {
		this.company = company;
	}
	
	public int getNumber() {
		return insuranceNumber;
	}
	
	public void setNumber(int insuranceNumber) {
		this.insuranceNumber = insuranceNumber;
	}
	
}
