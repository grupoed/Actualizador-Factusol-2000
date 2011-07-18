/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tratodatos;


import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;

/**
 *
 * @author Victor
 */
public class leerXML {
    SAXBuilder builder=new SAXBuilder(false);
    Document doc;
    
    public leerXML(String path) throws FileNotFoundException, JDOMException, IOException{
        doc=builder.build(path);
    }
    
    public List obtenerDatos() throws IOException{
        List<Map<String,String>> productos=new ArrayList();
        Element raiz=doc.getRootElement();
        List subhijos=raiz.getChildren();
        Iterator it=subhijos.iterator();
        while(it.hasNext()){
            Map<String,String> datos=new HashMap();
            Element aux=(Element)it.next();
            List etiquetas=aux.getChildren();
            //System.out.println(etiquetas.size());
            Iterator it2=etiquetas.iterator();
            while(it2.hasNext()){
                Element etiqueta=(Element)it2.next();
                datos=this.obtenerDatosElemento(etiqueta, raiz,datos);
            }
            productos.add(datos);
            
        }
        return productos;
    }
    
    private String parseXML(String valor){
        if(valor.indexOf("[CDATA: ")!=-1){
            valor=valor.substring(valor.indexOf("[CDATA: ")+8,valor.length()-1);
        }
        else if(valor.indexOf("[Text: ")!=-1){
            valor=valor.substring(valor.indexOf("[Text: ")+7,valor.length()-1);
        }
        if(valor.length()>=13)
            valor=valor.substring(0,13);
        return valor;
    }

    private String convertirPrecio(String precio, String canon) {
        DecimalFormat df = new DecimalFormat("0.00"); 
        Double can=this.convertirStringFloat(canon);
        Double price=this.convertirStringFloat(precio);
        price=can+price;
        price*=1.18;
        String ret=String.format("%.2f",price) ;
        //String ret2=this.quitarComas(ret);
        return ret;
    }
    
    private Double convertirStringFloat(String num){
        if(num.indexOf(',')!=-1){
            num=num.replace(',', '.');
        }
        return Double.parseDouble(num);
    }
    
    private String quitarComas(String num){
        if(num.indexOf(',')!=-1){
            num=num.replace(',', '.');
        }
        return num;
    }

    private String parseSubFamilia(String subfamilia) {
        if(subfamilia.equalsIgnoreCase("netbooks")){
            return Integer.toString(1);
        }
        else if(subfamilia.equalsIgnoreCase("PC PORTATILES") || subfamilia.equalsIgnoreCase("BAREBONES DE PORTATIL")){
            return Integer.toString(2);
        }
        else if(subfamilia.equalsIgnoreCase("PDA")){
            return Integer.toString(3);
        }
        else if(subfamilia.equalsIgnoreCase("TABLETS")){
            return Integer.toString(4);
        }
        else if(subfamilia.equalsIgnoreCase("ACCESORIOS TABLETAS")){
            return Integer.toString(6);
        }
        else if(subfamilia.equalsIgnoreCase("BATERIAS, CARGADORES, CABLES")){
            return Integer.toString(7);
        }
        else if(subfamilia.equalsIgnoreCase("MALETINES Y FUNDAS")){
            return Integer.toString(8);
        }
        else if(subfamilia.equalsIgnoreCase("TECLADOS INALÁMBRICOS") || subfamilia.equalsIgnoreCase("RATONES CON CABLE") || subfamilia.equalsIgnoreCase("TECLADOS CON CABLE") || subfamilia.equalsIgnoreCase("TECLADO + RATÓN") || subfamilia.equalsIgnoreCase("ACCESORIOS TECLADOS") || subfamilia.equalsIgnoreCase("RATONES INALÁMBRICOS")){
            return Integer.toString(9);
        }
        else if(subfamilia.equalsIgnoreCase("ACCESORIOS DE PORTATILES") || subfamilia.equalsIgnoreCase("PROLONGADORES TECLADO-RATON-MONITOR")){
            return Integer.toString(10);
        }
        else if(subfamilia.equalsIgnoreCase("TABLETAS DIGITALIZADORAS")){
            return Integer.toString(16);
        }
        else if(subfamilia.equalsIgnoreCase("LECTOR DE MEMORIAS")){
            return Integer.toString(17);
        }
        else if(subfamilia.equalsIgnoreCase("SAI") || subfamilia.equalsIgnoreCase("ACCESORIOS SAI")){
            return Integer.toString(18);
        }
        else if(subfamilia.equalsIgnoreCase("CAJAS EXTERNAS MULTIMEDIA")  || subfamilia.equalsIgnoreCase("CAJAS EXTERNAS")){
            return Integer.toString(21);
        }
        else if(subfamilia.equalsIgnoreCase("DISCOS DUROS EXTERNOS")){
            return Integer.toString(22);
        }
        else if(subfamilia.equalsIgnoreCase("MEMORIAS USB")){
            return Integer.toString(24);
        }
        else if(subfamilia.equalsIgnoreCase("MEMORIAS COMPACT FLASH")){
            return Integer.toString(25);
        }
        else if(subfamilia.equalsIgnoreCase("MEMORIAS SECURE DIGITAL (SD)")){
            return Integer.toString(26);
        }
        else if(subfamilia.equalsIgnoreCase("DISCOS DUROS") || subfamilia.equalsIgnoreCase("NAS")){
            return Integer.toString(28);
        }
        else if(subfamilia.equalsIgnoreCase("TARJETAS DE SONIDO")){
            return Integer.toString(30);
        }
        else if(subfamilia.equalsIgnoreCase("CAJAS SOBREMESA") || subfamilia.equalsIgnoreCase("CAJAS SERVIDORES") || subfamilia.equalsIgnoreCase("CAJAS SEMITORRE")){
            return Integer.toString(31);
        }
        else if(subfamilia.equalsIgnoreCase("FUENTES DE ALIMENTACION")){
            return Integer.toString(32);
        }
        else if(subfamilia.equalsIgnoreCase("MEMORIAS PARA PORTÁTILES") || subfamilia.equalsIgnoreCase("MEMORIAS PARA PC")){
            return Integer.toString(34);
        }
        else if(subfamilia.equalsIgnoreCase("VENTILADORES") || subfamilia.equalsIgnoreCase("MICROPROCESADORES")){
            return Integer.toString(35);
        }
        else if(subfamilia.equalsIgnoreCase("MINI ORDENADORES") || subfamilia.equalsIgnoreCase("BAREBONES") || subfamilia.equalsIgnoreCase("PC SOBREMESA")){
            return Integer.toString(36);
        }
        else if(subfamilia.equalsIgnoreCase("ORDENADORES TPV") || subfamilia.equalsIgnoreCase("MONITORES TPV Y TÁCTILES")){
            return Integer.toString(37);
        }
        else if(subfamilia.equalsIgnoreCase("PLACAS BASE")){
            return Integer.toString(39);
        }
        else if(subfamilia.equalsIgnoreCase("SERVIDORES RACK")){
            return Integer.toString(40);
        }
        else if(subfamilia.equalsIgnoreCase("ARMARIOS RACK")){
            return Integer.toString(41);
        }
        else if(subfamilia.equalsIgnoreCase("SERVIDORES TORRE")){
            return Integer.toString(42);
        }
        else if(subfamilia.equalsIgnoreCase("TARJETAS GRAFICAS")){
            return Integer.toString(44);
        }
        else if(subfamilia.equalsIgnoreCase("LECTORES Y REGRABADORES DE DVD") || subfamilia.equalsIgnoreCase("LECTORES Y REGRABADORES DE BLU RAY")){
            return Integer.toString(45);
        }
        else if(subfamilia.equalsIgnoreCase("CABLE DE DATOS") || subfamilia.equalsIgnoreCase("ACCESORIOS") || subfamilia.equalsIgnoreCase("CABLES DE RED") || subfamilia.equalsIgnoreCase("ACCESORIOS MEMORIAS") || subfamilia.equalsIgnoreCase("ACCESORIOS TPV") || subfamilia.equalsIgnoreCase("CABLES USB/FIREWIRE") || subfamilia.equalsIgnoreCase("CABLES DE ALIMENTACIÓN") || subfamilia.equalsIgnoreCase("ACCESORIOS COMPONENTES") || subfamilia.equalsIgnoreCase("ACCESORIOS ORDENADORES")){
            return Integer.toString(46);
        }
        else if(subfamilia.equalsIgnoreCase("AMPLIACION DE GARANTIA")){
            return Integer.toString(47);
        }
        else if(subfamilia.equalsIgnoreCase("CAJAS TPV") || subfamilia.equalsIgnoreCase("CAJONES PORTAMONEDAS")){
            return Integer.toString(48);
        }
        else if(subfamilia.equalsIgnoreCase("LECTOR CÓDIGO DE BARRAS")){
            return Integer.toString(50);
        }
        else if(subfamilia.equalsIgnoreCase("CONSUMIBLES DE VIDEO") || subfamilia.equalsIgnoreCase("CD/DVD/DISQUETTES VIRGENES")){
            return Integer.toString(52);
        }
        else if(subfamilia.equalsIgnoreCase("CONSUMIBLES IMPRESIÓN DE TINTA")){
            return Integer.toString(56);
        }
        else if(subfamilia.equalsIgnoreCase("CONSUMIBLES IMPRESIÓN MATRICIAL")){
            return Integer.toString(58);
        }
        else if(subfamilia.equalsIgnoreCase("PAPEL")){
            return Integer.toString(60);
        }
        else if(subfamilia.equalsIgnoreCase("CONSUMIBLES IMPRESIÓN LASER")){
            return Integer.toString(63);
        }
        else if(subfamilia.equalsIgnoreCase("BACKUP") || subfamilia.equalsIgnoreCase("UNIDAD BACKUP")){
            return Integer.toString(66);
        }
        else if(subfamilia.equalsIgnoreCase("CARGADORES")){
            return Integer.toString(67);
        }
        else if(subfamilia.equalsIgnoreCase("CONVERTIDORES")){
            return Integer.toString(68);
        }
        else if(subfamilia.equalsIgnoreCase("REGLETAS")){
            return Integer.toString(69);
        }
        else if(subfamilia.equalsIgnoreCase("PILAS")){
            return Integer.toString(70);
        }
        else if(subfamilia.equalsIgnoreCase("ACCESORIOS ALMACENAMIENTO") || subfamilia.equalsIgnoreCase("ACCESORIOS CONSUMIBLES") || subfamilia.equalsIgnoreCase("ACCESORIOS IMPRESORA")){
            return Integer.toString(72);
        }
        else if(subfamilia.equalsIgnoreCase("TARJETAS DE RED")){
            return Integer.toString(78);
        }
        else if(subfamilia.equalsIgnoreCase("TARJETAS DE RED WIFI") || subfamilia.equalsIgnoreCase("BLUETOOTH")){
            return Integer.toString(79);
        }
        else if(subfamilia.equalsIgnoreCase("MODEM")){
            return Integer.toString(82);
        }
        else if(subfamilia.equalsIgnoreCase("ROUTERS WIFI")){
            return Integer.toString(84);
        }
        else if(subfamilia.equalsIgnoreCase("ROUTERS")){
            return Integer.toString(85);
        }
        else if(subfamilia.equalsIgnoreCase("SERVIDORES DE IMPRESIÓN")){
            return Integer.toString(86);
        }
        else if(subfamilia.equalsIgnoreCase("PUNTOS DE ACCESO")){
            return Integer.toString(87);
        }
        else if(subfamilia.equalsIgnoreCase("SWITCH")){
            return Integer.toString(89);
        }
        else if(subfamilia.equalsIgnoreCase("ACCESORIOS REDES")){
            return Integer.toString(90);
        }
        else if(subfamilia.equalsIgnoreCase("ANTIVIRUS")){
            return Integer.toString(95);
        }
        else if(subfamilia.equalsIgnoreCase("GESTIÓN") || subfamilia.equalsIgnoreCase("PAQUETES INTEGRADOS")){
            return Integer.toString(97);
        }
        else if(subfamilia.equalsIgnoreCase("MULTIMEDIA") || subfamilia.equalsIgnoreCase("EDICION")){
            return Integer.toString(98);
        }
        else if(subfamilia.equalsIgnoreCase("SISTEMAS OPERATIVOS DE PC") || subfamilia.equalsIgnoreCase("SISTEMAS OPERATIVOS DE SERVIDORES")){
            return Integer.toString(99);
        }
        else if(subfamilia.equalsIgnoreCase("LICENCIAS S.O. SERVIDOREs")){
            return Integer.toString(100);
        }
        else if(subfamilia.equalsIgnoreCase("CAMARAS CCTV")){
            return Integer.toString(104);
        }
        else if(subfamilia.equalsIgnoreCase("CAMARAS IP") || subfamilia.equalsIgnoreCase("CAMARA IP WIFI")){
            return Integer.toString(105);
        }
        else if(subfamilia.equalsIgnoreCase("GRABADORAS DIGITALEs")){
            return Integer.toString(106);
        }
        else if(subfamilia.equalsIgnoreCase("SINTONIZADORAS EDITORAS y CAPTURADORAS")){
            return Integer.toString(107);
        }
        else if(subfamilia.equalsIgnoreCase("ACCESORIOS VIGILANCIA Y SEGURIDAD")){
            return Integer.toString(109);
        }
        else if(subfamilia.equalsIgnoreCase("HOME CINMEA") || subfamilia.equalsIgnoreCase("ALTAVOCES") || subfamilia.equalsIgnoreCase("ACCESORIOS ALTAVOCES") || subfamilia.equalsIgnoreCase("AMPLIFICADORES Y ALTAVOCES")){
            return Integer.toString(111);
        }
        else if(subfamilia.equalsIgnoreCase("AURICULARES")){
            return Integer.toString(113);
        }
        else if(subfamilia.equalsIgnoreCase("REPRODUCTORES MP3/MP4/MP5")){
            return Integer.toString(116);
        }
        else if(subfamilia.equalsIgnoreCase("ACCESORIOS DE MP3/MP4/MP5")){
            return Integer.toString(117);
        }
        else if(subfamilia.equalsIgnoreCase("TV LCD") || subfamilia.equalsIgnoreCase("TV PLASMA")){
            return Integer.toString(118);
        }
        else if(subfamilia.equalsIgnoreCase("TV LED")){
            return Integer.toString(119);
        }
        else if(subfamilia.equalsIgnoreCase("MONITORES LCD 3D") || subfamilia.equalsIgnoreCase("MONITORES LCD")){
            return Integer.toString(120);
        }
        else if(subfamilia.equalsIgnoreCase("VIDEO PROYECTORES 3D") || subfamilia.equalsIgnoreCase("VIDEO PROYECTORES")){
            return Integer.toString(121);
        }
        else if(subfamilia.equalsIgnoreCase("PANTALLAS DE PROYECCION")){
            return Integer.toString(122);
        }
        else if(subfamilia.equalsIgnoreCase("SOPORTES VIDEOPROYECTOR")  || subfamilia.equalsIgnoreCase("ACCESORIOS VIDEOPROYECTORES")){
            return Integer.toString(123);
        }
        else if(subfamilia.equalsIgnoreCase("MICRÓFONOS")){
            return Integer.toString(124);
        }
        else if(subfamilia.equalsIgnoreCase("PIZARRAS ELECTRONICAS")){
            return Integer.toString(125);
        }
        else if(subfamilia.equalsIgnoreCase("ACCESORIOS TV 3D") || subfamilia.equalsIgnoreCase("SOPORTES MONITORES / TV") || subfamilia.equalsIgnoreCase("ACCESORIOS MONITORES / TV")){
            return Integer.toString(126);
        }
        else if(subfamilia.equalsIgnoreCase("REPRODUCTORES Y GRABADORES DE DVD") || subfamilia.equalsIgnoreCase("REPRODUCTORES Y GRABADORES DE BLU RAY")){
            return Integer.toString(130);
        }
        else if(subfamilia.equalsIgnoreCase("RECEPTORES TDT")){
            return Integer.toString(131);
        }
        else if(subfamilia.equalsIgnoreCase("CONMUTADORES Y COMPARTIDORES")){
            return Integer.toString(133);
        }
        else if(subfamilia.equalsIgnoreCase("CABLES AUDIO / VIDEO")){
            return Integer.toString(134);
        }
        else if(subfamilia.equalsIgnoreCase("CAMARAS DE FOTOS ANALOGICAS") || subfamilia.equalsIgnoreCase("CAMARAS DE FOTOS 3D") || subfamilia.equalsIgnoreCase("CAMARAS DE FOTOS COMPACTAS") || subfamilia.equalsIgnoreCase("FUNDAS DE CAMARAS DE FOTOS") || subfamilia.equalsIgnoreCase("ACCESORIOS FOTOGRAFIA")){
            return Integer.toString(135);
        }
        else if(subfamilia.equalsIgnoreCase("CAMARAS DE FOTOS REFLEX")){
            return Integer.toString(136);
        }
        else if(subfamilia.equalsIgnoreCase("FUNDAS DE VIDEOCAMARAS") || subfamilia.equalsIgnoreCase("OBJETIVOS Y FLASH")){
            return Integer.toString(137);
        }
        else if(subfamilia.equalsIgnoreCase("ACCESORIOS CAMARAS DE VIDEO") || subfamilia.equalsIgnoreCase("CAMARAS DE VIDEO")){
            return Integer.toString(138);
        }
        else if(subfamilia.equalsIgnoreCase("BATERIAS Y CARGADORES")){
            return Integer.toString(139);
        }
        else if(subfamilia.equalsIgnoreCase("IMPRESORAS DE GRAN FORMATO")){
           return Integer.toString(141);
        }
        else if(subfamilia.equalsIgnoreCase("IMPRESORAS FOTOGRÁFICAS") || subfamilia.equalsIgnoreCase("IMPRESORAS DE INYECCIÓN")){
            return Integer.toString(142);
        }
        else if(subfamilia.equalsIgnoreCase("IMPRESORAS LASER COLOR")){
            return Integer.toString(143);
        }
        else if(subfamilia.equalsIgnoreCase("IMPRESORAS DE TICKET") || subfamilia.equalsIgnoreCase("IMPRESORAS DE LASER MONOCROMO")){
            return Integer.toString(144);
        }
        else if(subfamilia.equalsIgnoreCase("IMPRESORAS MATRICIALES")){
            return Integer.toString(145);
        }
        else if(subfamilia.equalsIgnoreCase("MULTIFUNCIÓNES FOTOGRÁFICAS") || subfamilia.equalsIgnoreCase("MULTIFUNCIÓN DE INYECCIÓN") || subfamilia.equalsIgnoreCase("FOTOCOPIADORAS")){
            return Integer.toString(149);
        }
        else if(subfamilia.equalsIgnoreCase("MULTIFUNCIÓN LÁSER COLOR") || subfamilia.equalsIgnoreCase("MULTIFUNCIÓN LASER MONOCROMO")){
            return Integer.toString(151);
        }
        else if(subfamilia.equalsIgnoreCase("ESCÁNER")){
            return Integer.toString(152);
        }
        else if(subfamilia.equalsIgnoreCase("CABLES DE IMPRESORA/SERIE")){
            return Integer.toString(153);
        }
        else if(subfamilia.equalsIgnoreCase("CONSOLAS NINTENDO DS") || subfamilia.equalsIgnoreCase("CONSOLAS PSP")){
            return Integer.toString(154);
        }
        else if(subfamilia.equalsIgnoreCase("CONSOLAS XBOX") || subfamilia.equalsIgnoreCase("CONSOLAS WII") || subfamilia.equalsIgnoreCase("CONSOLAS PS3")){
            return Integer.toString(155);
        }
        else if(subfamilia.equalsIgnoreCase("JUEGOS NINTENDO DS")){
            return Integer.toString(156);
        }
        else if(subfamilia.equalsIgnoreCase("JUEGOS PC")){
            return Integer.toString(157);
        }
        else if(subfamilia.equalsIgnoreCase("JUEGOS PS2")){
            return Integer.toString(158);
        }
        else if(subfamilia.equalsIgnoreCase("JUEGOS PS3")){
            return Integer.toString(159);
        }
        else if(subfamilia.equalsIgnoreCase("JUEGOS PSP")){
            return Integer.toString(160);
        }
        else if(subfamilia.equalsIgnoreCase("JUEGOS WII")){
            return Integer.toString(161);
        }
        else if(subfamilia.equalsIgnoreCase("JUEGOS XBOX360")){
            return Integer.toString(162);
        }
        else if(subfamilia.equalsIgnoreCase("JOYSTICK Y VOLANTE") || subfamilia.equalsIgnoreCase("GAMEPAD")){
            return Integer.toString(166);
        }
        else if(subfamilia.equalsIgnoreCase("ACCESORIOS DE GPS") || subfamilia.equalsIgnoreCase("GPS")){
            return Integer.toString(167);
        }
        else if(subfamilia.equalsIgnoreCase("EBOOKS")){
            return Integer.toString(168);
        }
        else if(subfamilia.equalsIgnoreCase("MARCO DIGITAL")){
            return Integer.toString(169);
        }
        else if(subfamilia.equalsIgnoreCase("MICRO CADENAS") || subfamilia.equalsIgnoreCase("CALCULADORAS") || subfamilia.equalsIgnoreCase("RADIO - RADIO DESPERTADOR") || subfamilia.equalsIgnoreCase("FRIGORIFICOS") || subfamilia.equalsIgnoreCase("VITROCERÁMICAS") || subfamilia.equalsIgnoreCase("HORNOS") || subfamilia.equalsIgnoreCase("REPOSAPIES") || subfamilia.equalsIgnoreCase("AIRE ACONDICIONADO") || subfamilia.equalsIgnoreCase("DESTRUCTORAS") || subfamilia.equalsIgnoreCase("LAVADORAS")){
            return Integer.toString(171);
        }
        else if(subfamilia.equalsIgnoreCase("MONITORES LED")){
            return Integer.toString(172);
        }
        else if(subfamilia.equalsIgnoreCase("TV 3D")){
            return Integer.toString(173);
        }
        else if(subfamilia.equalsIgnoreCase("ACCESORIOS DE CONSOLA DS") || subfamilia.equalsIgnoreCase("ACCESORIOS DE CONSOLA PSP") || subfamilia.equalsIgnoreCase("ACCESORIOS DE CONSOLA PS2") || subfamilia.equalsIgnoreCase("ACCESORIOS DE CONSOLA WII") || subfamilia.equalsIgnoreCase("ACCESORIOS DE CONSOLA PS3") || subfamilia.equalsIgnoreCase("ACCESORIOS DE CONSOLA XBOX360")){
            return Integer.toString(174);
        }
        else if(subfamilia.equalsIgnoreCase("MEMORIAS XD PICTURE") || subfamilia.equalsIgnoreCase("MEMORIAS MEMORY STICK") || subfamilia.equalsIgnoreCase("OTRAS TARJETAS")){
            return Integer.toString(175);
        }
        else if(subfamilia.equalsIgnoreCase("CAMARAS WEB")){
            return Integer.toString(177);
        }
        else if(subfamilia.equalsIgnoreCase("DISCOS DUROS MULTIMEDIA")){
            return Integer.toString(178);
        }
        else if(subfamilia.equalsIgnoreCase("TELEFONÍA VOIP")){
            return Integer.toString(180);
        }
        else if(subfamilia.equalsIgnoreCase("FAX")){
            return Integer.toString(181);
        }
        else if(subfamilia.equalsIgnoreCase("SMARTPHONES") || subfamilia.equalsIgnoreCase("TELÉFONOS FIJOS CON CABLE") || subfamilia.equalsIgnoreCase("ACCESORIOS DE TELEFONÍA") || subfamilia.equalsIgnoreCase("ACCESORIOS CENTRALES TELEFONICAS") || subfamilia.equalsIgnoreCase("TELÉFONOS FIJOS SIN CABLE") || subfamilia.equalsIgnoreCase("TELÉFONOS MÓVILES") || subfamilia.equalsIgnoreCase("CENTRALES TELEFONICAS")){
            return Integer.toString(182);
        }
        
        return Integer.toString(0);
    }
    
    private Map obtenerDatosElemento(Element tag,Element raiz,Map<String,String> datos){
        if(raiz.getName().equals("datos")){
            datos.put("mayorista","Infortisa");
        }
        else if(raiz.getName().equalsIgnoreCase("catalogo")){
            datos.put("mayorista", "Megasur");
        }
        if(tag.getName().equalsIgnoreCase("referencia") || tag.getName().equalsIgnoreCase("part_number")){
            datos.put("referencia", this.parseXML(tag.getText()));
        }
        else if(tag.getName().equalsIgnoreCase("titulo") || tag.getName().equalsIgnoreCase("nombre")){
            datos.put("nombre", this.parseXML(tag.getText()));
        }
        else if(tag.getName().equalsIgnoreCase("stock")){
            datos.put("stock", tag.getText());
        }
        else if(tag.getName().equalsIgnoreCase("pvd") || tag.getName().equalsIgnoreCase("precio")){
            datos.put("precio", convertirPrecio(tag.getText(),tag.getParentElement().getChild("canon").getText()));
        }
        else if(tag.getName().equalsIgnoreCase("stock")){
            datos.put("stock", tag.getText());
        }
        else if(tag.getName().equalsIgnoreCase("subfamilia") || tag.getName().equalsIgnoreCase("categoria_2")){
            datos.put("subfamilia", parseSubFamilia(tag.getText()));
        }
        else if(tag.getName().equalsIgnoreCase("marca")){
            datos.put("marca", tag.getText());
        }
        else if(tag.getName().equalsIgnoreCase("peso")){
            datos.put("peso", Double.toString(this.convertirStringFloat(tag.getText())));
        }
        else if(tag.getName().equalsIgnoreCase("descripcion")){
            datos.put("descripcion", eliminarHTML.convert(tag.getText()));
        }
        else if(tag.getName().equalsIgnoreCase("url_imagen")){
            datos.put("imagen",tag.getText());
        }
        else if(tag.getName().equalsIgnoreCase("imagen")){
            datos.put("imagen", "http://www.infortisa.com/spa/item/extractimg.cgi?action=normal&code="+nombreImagen(tag.getText()));
        }
        return datos;
    }
    private static String nombreImagen(String url_imagen) {
            String[] datos=url_imagen.split("/");
            datos=datos[datos.length-1].split("[?]");
            datos=datos[datos.length-1].split("&");
            datos=datos[datos.length-1].split("=");
            return datos[datos.length-1];
    }
}
