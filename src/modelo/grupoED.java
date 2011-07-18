/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

/**
 *
 * @author Victor
 */
public class grupoED {
    
    static private grupoED grupo=null;
    Map<String,Map<String,Producto>> productos=new HashMap();
    
    public grupoED(){
        
    }
    
    public static grupoED getGrupoED(){
        if(grupoED.grupo==null){
            grupoED.grupo=new grupoED();
        }
        return grupoED.grupo;
    }
    
    public void addProducto(String referencia, String nombre, double precio, String mayorista,int codigoSubfamilia, String fabricante, double peso,int stock,String descripcion,String url_imagen){
        if(productos.containsKey(mayorista)){
            Map<String,Producto> pr=productos.get(mayorista);
            if(!pr.containsKey(referencia)){
                Producto producto=new Producto(referencia,nombre,precio,mayorista,stock,codigoSubfamilia,fabricante,peso,descripcion,url_imagen);
                pr.put(referencia, producto);
                productos.remove(mayorista);
                productos.put(mayorista, pr);
            }
        }
        else{
            Map<String,Producto> pr=new HashMap();
            pr.put(referencia, new Producto(referencia,nombre,precio,mayorista,stock,codigoSubfamilia,fabricante,peso,descripcion,url_imagen));
            productos.put(mayorista, pr);
        }
    }
    
    public void removeProducto(String mayorista,String referencia){
        if(productos.containsKey(mayorista)){
            Map<String,Producto> pr=productos.get(mayorista);
            if(pr.containsKey(referencia)){
                pr.remove(referencia);
            }
        }
    }
    
    public Map<String,Producto> masBarato(){
        Map<String,Producto> baratos=new TreeMap();
        Map<String,Producto> megasur=productos.get("Megasur");
        Map<String,Producto> infortisa=productos.get("Infortisa");
        Iterator it=megasur.entrySet().iterator();
        while(it.hasNext()){
            Map.Entry aux=(Map.Entry) it.next();
            String ref=(String) aux.getKey();
            if(infortisa.containsKey(ref)){//Existe en Infortisa
                Producto prd=(Producto) aux.getValue();
                Producto prd2=infortisa.get(ref);
                if(prd.esmayor(prd2)){
                    baratos.put(ref, prd);
                }
                else{
                    baratos.put(ref, prd2);
                }
            }
            else{
                baratos.put(ref,(Producto)aux.getValue());
            }
        }
        it=infortisa.entrySet().iterator();
        while(it.hasNext()){
            Map.Entry aux=(Map.Entry) it.next();
            if(!megasur.containsKey(aux.getKey())){
                baratos.put((String)aux.getKey(), (Producto)aux.getValue());
            }
        }  
        return baratos;
    }
    
    
}