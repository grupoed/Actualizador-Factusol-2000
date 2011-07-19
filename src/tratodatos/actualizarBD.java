/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tratodatos;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelo.Producto;

/**
 *
 * @author Dell
 */
public class actualizarBD {
    
    private static Connection theConn;
    private static Statement stmt;
    
    static private void conectar(String dsn) throws Exception{
        try {
            theConn=conectarBD.getConnection(dsn);
            stmt=theConn.createStatement();
            
        } catch (Exception ex) {
            Logger.getLogger(actualizarBD.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void actualizarBD(String dsn,Map<String,Producto> datos) throws Exception{
        int actualizar=0,nuevo=0,fallos=0;
        conectar(dsn);
        ResultSet rs;
        System.out.println("Comenzado actualización BD");
        String sql;
        sql="select codart from F_ART";
        rs=stmt.executeQuery(sql);
        Set<String> claves = new TreeSet();
        while(rs.next()){
               claves.add(rs.getString("codart"));             
        }
        Set<String> fallados=new TreeSet();
        Iterator it=datos.entrySet().iterator();
        while(it.hasNext()){
            Map.Entry e=(Map.Entry) it.next();
            Producto p=(Producto) e.getValue();
            String path=System.getProperty("user.home") + "\\Mis Documentos"+"\\Imagenes de Productos\\";
            if(existe(claves,(String)e.getKey())){
                try{
                    sql="update F_ART set PCOART="+p.getPrecio()+",PHAART="+getMayorista(p.getMayorista())+",PESART="+p.getPeso()+" where CODART="+getReferencia((String)e.getKey());
                    stmt.executeUpdate(sql);
                    sql="update F_STO set ACTSTO="+getStock(p.getStock())+",DISSTO="+getStock(p.getStock())+" where ARTSTO="+getReferencia((String)e.getKey());
                    stmt.executeUpdate(sql);
                    //Actualizar F_LTA
                    actualizar++;
                }catch(SQLException ex){
                    System.out.println(sql);
                    fallos++;
                }
            }
            else{
                try{
                   // descargarFichero.descargarFichero(p.getUrl_imagen(), path+actualizarBD.nombreImagen(p.getUrl_imagen(), p.getMayorista()));
                    Map<String,String> data=p.getData();
                    sql="insert into F_ART(CODART,DESART,PCOART,PHAART,PESART,DEWART,IMGART,REFART,FAMART,FTEART,FALART,FUMART,CSTART,SUWART) VALUES("+getReferencia(data.get("CODART"))+",'"+quitarComillas(cortarNombre(data.get("DESART")))+"',"+data.get("PCOART")+","+getMayorista(data.get("SUWART"))+","+data.get("PESART")+",'"+quitarComillas(data.get("DEWART"))+" ','"+path+"\\"+quitarComillas(nombreImagen(p.getUrl_imagen(),p.getMayorista()))+"',"+getReferencia(data.get("CODART"))+","+data.get("FAMART")+","+actualizarBD.getFabricante(data.get("FTEART")) +",Now(),Now(),3,1)";
                    stmt.execute(sql);
                    sql="insert into F_STO(ARTSTO,ALMSTO,MINSTO,MAXSTO,ACTSTO,DISSTO) VALUES ("+getReferencia(data.get("CODART"))+",'GEN',1,10,"+getStock(p.getStock())+","+getStock(p.getStock())+")";
                    stmt.execute(sql);
                    float precio=(float) (Float.parseFloat(data.get("PCOART"))+Float.parseFloat(data.get("PCOART"))*0.05);
                    sql="insert into F_LTA(TARLTA,ARTLTA,MARLTA,PRELTA) values (1,"+getReferencia(data.get("CODART"))+",5,"+precio+")";
                    stmt.execute(sql);
                    sql="insert into F_LTA(TARLTA,ARTLTA,MARLTA,PRELTA) values (2,"+getReferencia(data.get("CODART"))+",15,0)";
                    stmt.execute(sql);
                    nuevo++;
                }
                catch(SQLException ex){
                    System.out.println(sql);
                    fallos++;
                }
            }
        }
        theConn.commit();
        theConn.close();
        escribirFichero.escribirFichero("C:/fallados.csv", fallados);
        System.out.println("Se ha actualizado:"+actualizar+"\nSe han insertado:"+nuevo+"\n han fallado:"+fallos);
    }
    
    static public int getMayorista(String mayorista) throws SQLException{
        String sql="select codpro from F_PRO where NOCPRO='"+mayorista+"'";
       // System.out.println(sql);
        ResultSet rs=stmt.executeQuery(sql);
        while(rs.next()){
            return Integer.parseInt(rs.getString("codpro"));
        }
        return -1;
    }
    
    static public String getPrecio(double precio){
     String s=Double.toString(precio);
     s=s.replace('.', ',');
     return s;
    }
    
    static public String cortarNombre(String nombre){
        if(nombre.length()>50){
            nombre=nombre.substring(0,50);
        }
        return nombre;
    }
    
    static public String quitarComillas(String cadena){
        cadena=cadena.replace('\'',' ');
        cadena=cadena.replace('[', ' ');
        cadena=cadena.replace(']', ' ');
        cadena=cadena.replace('\'',' ');
        return cadena;
    }
    
    static public String getReferencia(String cadena){
        cadena=quitarComillas(cadena);
        cadena="'"+cadena+"'";
        return cadena;
    }
    
    static private boolean existe(Set<String> datos,String ref){
        Iterator it=datos.iterator();
        while(it.hasNext()){
            String aux=(String) it.next();
            if(aux.contains(ref)){
                return true;
            }
        }
        return false;
    }

    private static String nombreImagen(String url_imagen,String mayorista) {
        if(mayorista.equalsIgnoreCase("Megasur")){
            String[] datos=url_imagen.split("/");
            return datos[datos.length-1];
        }
        else{
            String[] datos=url_imagen.split("/");
            datos=datos[datos.length-1].split("[?]");
            datos=datos[datos.length-1].split("&");
            datos=datos[datos.length-1].split("=");
            return datos[datos.length-1]+".jpg";
        }
    }

    private static int getStock(int stock) {
        int sto=0;
        switch(stock){
            case 1:
                sto=5;
                break;
            case 2:
                sto=50;
                break;
        }
        return sto;
    }
    
    private static int getFabricante(String fabricante) throws SQLException{
        String sql="select codfte from F_FTE where NOMFTE='"+fabricante+"'";
        //System.out.println(sql);
        ResultSet rs=stmt.executeQuery(sql);
        if(rs.next()){
            return rs.getInt("codfte");
        }
        else{
            sql="select max(codfte) as expr from F_FTE";
            ResultSet rs2=stmt.executeQuery(sql);
            int max=0;
            if(rs2.next())
                max=rs2.getInt("expr");
            max++;
            sql="insert into F_FTE(codfte,nomfte) values("+max+",'"+fabricante+"')";
            stmt.execute(sql);
            return max;
        }
    }
}
