package nl.hu.v1IPASS.IPASS.model;

public class ServiceProvider {
	private static IPASSService IPASSService = new IPASSService();

	public static IPASSService getIPASSService() {
		return IPASSService;
	}
}
