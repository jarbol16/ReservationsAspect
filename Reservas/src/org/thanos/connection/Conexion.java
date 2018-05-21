package org.thanos.connection;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import java.sql.Connection;
import java.sql.SQLException;
import org.thanos.server.config.Settings.DB_Local;
/**
 *
 * @author juandaniel
 */


public class Conexion {

    private static final String SERVIDOR = DB_Local.SERVIDOR;
    private static final int PUERTO = DB_Local.PUERTO;
    private static final String BD = DB_Local.BD;
    private static final String NOMBRE_USUARIO = DB_Local.NOMBRE_USUARIO;
    private static final String CONTRASENA_USUARIO
            = DB_Local.CONTRASENA_USUARIO;

    public static Connection getConexion() throws SQLException {
        MysqlDataSource ds = new MysqlDataSource();
        ds.setServerName(SERVIDOR);
        ds.setPortNumber(PUERTO);
        ds.setDatabaseName(BD);
        ds.setUser(NOMBRE_USUARIO);
        ds.setPassword(CONTRASENA_USUARIO);

        return ds.getConnection();
    }
}