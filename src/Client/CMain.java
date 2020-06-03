package Client;

import java.net.Socket;

public class CMain {

	public static void main(String[] args) throws Exception {
		Socket withServer = null;
		withServer = new Socket("10.0.0.127", 7777);
		System.out.println("아이피랑 포트넘버 생성");

		Socket withServer2 = null;
		withServer2 = new Socket("10.0.0.127", 8888);
		System.out.println("아이피랑 포트넘버 생성2");
		new ClientChat(withServer, withServer2);
	}

}
