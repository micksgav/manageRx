package patientUI;

import PatientManagement.*;

public class TempMain {

	public static void main(String[] args) {
		FamilyDoctor doc = new FamilyDoctor("Joe Mama", "123 Address Street", 5191234566L);
		Patient patient = new Patient("John", 17, "413 ABC Street", "March", 3, 2006, null, null,
				1234567890, "jbbbb@gmail.com", 1234567890123456L, 1224, null, null, null, doc, null);
		ManagePatientInfoUI oui = new ManagePatientInfoUI("ManageRx", patient);
		
		oui.setVisible(true);
	}
}
