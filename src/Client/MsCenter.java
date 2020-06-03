package Client;

public class MsCenter {
	// private String msg = null;
	private static ClientChat ch = null;
	private static MsCenter msgCenter = null;
	private Login login = null;
	private Signup join = null;

	public static MsCenter getInstance() {
		if (msgCenter == null) {
			msgCenter = new MsCenter(ch);
		}
		return msgCenter;
	}

	private MsCenter(ClientChat ch) {
		this.ch = ch;
	}

	public void start(ClientChat ch) {
		this.ch = ch;
		login = new Login();
	}

	public void checkMsg(String msg) {
		if (msg.contains("member")) {
			join.membercheck(msg);
		} else if (msg.contains("login")) {
			login.loginresult(msg);
		} else if (msg.contains("check")) {
			join.dup(msg);

		}

	}

	public void setObject(String[] check) {
		System.out.println("∞¨≥™???-------login");
		ch.sendObject(check);

	}

	public void setObjectforjoin(String[] check, Signup join) {
		this.join = join;
		System.out.println("∞¨≥™???»Ò»Ò»Ò»Ò»Ò»Ò»Ò»Ò»Ò");
		ch.sendObject(check);
	}

}
