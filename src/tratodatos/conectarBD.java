/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tratodatos;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.util.Properties;

/**
 *
 * @author pcoficina1
 */
public class conectarBD {
    
     public static Connection getConnection(String dsn) throws Exception {
        Driver d = (Driver)Class.forName("sun.jdbc.odbc.JdbcOdbcDriver").newInstance();
        Properties props = new Properties();
        props.put ("charSet", "iso-8859-1");
        Connection c = DriverManager.getConnection("jdbc:odbc:"+dsn,props);
        return c;
     }
}
