import java.net.Inet4Address;
import java.net.UnknownHostException;


public class Test {

	public Test() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param args
	 * @throws UnknownHostException 
	 */
	public static void main(String[] args) throws UnknownHostException {
		System.out.println(Inet4Address.getLocalHost().getHostAddress());

	}

}
