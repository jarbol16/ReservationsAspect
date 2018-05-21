package org.thanos.modelo.entities;



public class User {
 	private String username;
    private boolean autorizado;
    public  boolean Activo;
    public String Permissions;
    public int Type;
    public int PersonId;
    
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
    
    public boolean IsStudent() {
    	if (this.Type == 2)
    		return true;
    	return false;
    }
}
