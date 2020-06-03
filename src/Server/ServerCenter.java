package Server;

import java.net.Socket;
import java.util.ArrayList;

import Client.ClientChat;
import DB.DAOCenter;

public class ServerCenter {
	private ArrayList<ServerChat> sList = new ArrayList<>();
	private DAOCenter dc = DAOCenter.getInstance();
	private String[] check = null;
	private ServerChat chat = null;
	private ServerCenter sc = null;
	// private static ServerCenter sc;

	public ServerCenter() {
	}

//	public static ServerCenter getInstance() {
//		if (sc == null) {
//			new ServerCenter();
//		}
//		return sc;
//	}

	public void addServerChat(ServerChat s) {
		this.sList.add(s);
	}

	public void select(Object objectMember, ServerChat chat) {
		this.chat = chat;
		dc.whichone(objectMember, this);
	}

	public void goSC(String msg) {
		System.out.println("����� ��������" + msg);
		//chat = new ServerChat(null, null, null);
		chat.sendStream(msg);
		System.out.println("�������Ϳ��� ������" + msg);
	}

}
