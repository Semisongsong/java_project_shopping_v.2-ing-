package Server;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;

public class ServerChat extends Thread {
	private Socket withClient = null;
	private Socket withClient2 = null;
	private InputStream reMsg = null;
	private OutputStream sendMsg = null;
	private String id = null;
	private String[] check = null;
	private ServerCenter sc = null;
	private ServerChat chat = null;
	String msg = null;

	ServerChat(Socket withClient, Socket withClient2, ServerCenter sc) {
		this.withClient = withClient;
		this.withClient2 = withClient2;
		this.sc = sc;
		chat = this;
		// streamSet();
		// receive(msg);
		receiveObject();
		sendStream(msg);
	}

//	@Override
//	public void run() {
//		streamSet();
//		send(msg);
//		// send();
//	}

	public void sendStream(String msg) {
		new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					// while (true) {
					if (msg != null) {
						System.out.println("여기는 서버챗인데 받은 메세지를 확인해볼것이야 : " + msg);
						sendMsg = withClient2.getOutputStream();
						sendMsg.write(msg.getBytes());
						System.out.println("서버에서 메세지를 보냈어요");
					}
				} catch (Exception e) {
				}
			}
		}).start();

	}

	public void receiveObject() {

		new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					while (true) {
						reMsg = withClient.getInputStream();
						byte[] reBuffer = new byte[1024];
						reMsg.read(reBuffer);

						ByteArrayInputStream bais = new ByteArrayInputStream(reBuffer);

						ObjectInputStream ois = new ObjectInputStream(bais);

						Object o = ois.readObject();

						if (o != null) {
							check = (String[]) o;
							for (int i = 0; i < check.length; i++) {
								System.out.println(check[i]);
								id = check[0];
							}
							sc.select(check,chat);
						}
					}

				} catch (Exception e) {
				}
			}
		}).start();
	}
}
