package Server;

import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class SMain {

	public static void main(String[] args) throws Exception {

		ServerSocket serverS, serverS2 = null;
		Socket withClient, withClient2 = null;

		serverS = new ServerSocket();
		serverS2 = new ServerSocket();
		serverS.bind(new InetSocketAddress("10.0.0.127", 7777));
		serverS2.bind(new InetSocketAddress("10.0.0.127", 8888));

		ArrayList<Socket> cList = new ArrayList<>();
		ServerCenter sc = new ServerCenter();
		while (true) {
			System.out.println("���� �����");
			withClient = serverS.accept();
			withClient2 = serverS2.accept();
			cList.add(withClient);
			cList.add(withClient2);
			System.out.println(cList);
			System.out.println(withClient.getInetAddress() + "���� ������.");
			System.out.println(withClient2.getInetAddress() + "���� ������.");
			ServerChat s = new ServerChat(withClient, withClient2, sc);
			s.start();

		}
	}

}
