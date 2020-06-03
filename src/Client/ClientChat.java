package Client;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;

public class ClientChat {
	private Socket withServer = null; // ��Ʈ����
	private Socket withServer2 = null; // ��ü��
	private InputStream reMsg = null;
	private OutputStream sendMsg = null;
	private String[] chk = null;
	private Scanner in = new Scanner(System.in);
	private static MsCenter mc = MsCenter.getInstance();
	private String msg = null;

	ClientChat(Socket withServer, Socket withServer2) {
		this.withServer = withServer;
		this.withServer2 = withServer2;
		start();
		sendObject(chk);
		receiveStream();
	}

	private void start() {
		mc.start(this);

	}

	public void receiveStream() {
		new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					System.out.println("receive start~~");
					while (true) {
						reMsg = withServer2.getInputStream();
						byte[] reBuffer = new byte[1024];
						reMsg.read(reBuffer);
						msg = new String(reBuffer);
						if (msg != null) {
							msg = msg.trim();
							System.out.println("Ŭ���̾�Ʈ���� �޼����� �޾Ҿ��." + msg);
							gotoCenter(msg);
						}
					}
				} catch (Exception e) {
					System.out.println("client receive end !!!");
					return;
				}
			}
		}).start();
	}

	public void sendStream(String msg) {
		new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					// while (true) {
					if (msg != null) {
						sendMsg = withServer2.getOutputStream();
						sendMsg.write(msg.getBytes());
					}
				} catch (Exception e) {
				}
			}
		}).start();

	}

	private void gotoCenter(String msg) {
		mc.checkMsg(msg);
		System.out.println("�޼����� �޼������ͷ� ���´�");
	}

	public void sendObject(String[] check) {
		new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					if (check != null) {
						ByteArrayOutputStream baos = new ByteArrayOutputStream();
						ObjectOutputStream oos = new ObjectOutputStream(baos);
						oos.writeObject(check);

						byte[] bowl = baos.toByteArray();

						sendMsg = withServer.getOutputStream();

						sendMsg.write(bowl);
						System.out.println("������ �Ϸ�");
					}
				} catch (Exception e) {
					System.out.println("client send end !!!");
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
						reMsg = withServer.getInputStream();
						byte[] reBuffer = new byte[1024];
						reMsg.read(reBuffer);

						ByteArrayInputStream bais = new ByteArrayInputStream(reBuffer);

						ObjectInputStream ois = new ObjectInputStream(bais);

						Object o = ois.readObject();

						if (o != null) {
							System.out.println("��ü ����!!!");
						}
					}

				} catch (Exception e) {
				}
			}
		}).start();
	}
}
