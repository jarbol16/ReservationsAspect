package org.thanos.modelo.entities;



public class User {
 	private String username;
    private boolean autorizado;
    public  boolean Activo;
    public String Permissions;
    public int Type;
    
    public enum UserType{
    	ADMIN,
    	STUDENT,
    	TEACHER
    }
    
    public boolean IsAdmin() {
    	if (this.Type == 1)
    		return true;
    	return false;
    }
}
