package DB;

import Server.ServerChat;

public interface DAOInterface {
	
	Boolean Insert(Object DTO, String notice, ServerChat ss);
	int Select(String id, String pwd,String notice, ServerChat ss);
	Boolean Edit(Object DTO);
	Boolean Delete(Object DTO);
	

}

