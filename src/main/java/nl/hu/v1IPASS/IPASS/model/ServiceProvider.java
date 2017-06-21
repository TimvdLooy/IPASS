package nl.hu.v1IPASS.IPASS.model;

public class ServiceProvider {
	//doorgeven van de IPASSService aan de rescources.
	private static IPASSService IPASSService = new IPASSService();

	public static IPASSService getIPASSService() {
		return IPASSService;
	}
}
