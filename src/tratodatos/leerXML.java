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
        return valor;
    }
    
    private String cortar(String valor){
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
        if(subfamilia.equalsIgnoreCase("NETBOOKS") || subfamilia.equalsIgnoreCase("MINI PORTATILES (NETBOOK)")){
            return Integer.toString(1);
        }
        else if(subfamilia.equalsIgnoreCase("PC PORTATILES") || subfamilia.equalsIgnoreCase("BAREBONES DE PORTATIL") || subfamilia.equalsIgnoreCase("PORTATILES (NOTEBOOK)")){
            return Integer.toString(2);
        }
        else if(subfamilia.equalsIgnoreCase("PDA") || subfamilia.equalsIgnoreCase("TABLET PC")){
            return Integer.toString(3);
        }
        else if(subfamilia.equalsIgnoreCase("TABLETS") || subfamilia.equalsIgnoreCase("TABLET")){
            return Integer.toString(4);
        }
        else if(subfamilia.equalsIgnoreCase("ACCESORIOS TABLETAS")){
            return Integer.toString(6);
        }
        else if(subfamilia.equalsIgnoreCase("BATERIAS, CARGADORES, CABLES")){
            return Integer.toString(7);
        }
        else if(subfamilia.equalsIgnoreCase("MALETINES Y FUNDAS") || subfamilia.equalsIgnoreCase("BOLSAS TRANSPORTE")){
            return Integer.toString(8);
        }
        else if(subfamilia.equalsIgnoreCase("TECLADOS INALÁMBRICOS") || subfamilia.equalsIgnoreCase("RATONES CON CABLE") || subfamilia.equalsIgnoreCase("TECLADOS CON CABLE") || subfamilia.equalsIgnoreCase("TECLADO + RATÓN") || subfamilia.equalsIgnoreCase("ACCESORIOS TECLADOS") || subfamilia.equalsIgnoreCase("RATONES INALAMBRICOS") || subfamilia.equalsIgnoreCase("TECLADO USB") || subfamilia.equalsIgnoreCase("COMBOS (TECLADO+RATON)")){
            return Integer.toString(9);
        }
        else if(subfamilia.equalsIgnoreCase("ACCESORIOS DE PORTATILES") || subfamilia.equalsIgnoreCase("PROLONGADORES TECLADO-RATON-MONITOR") || subfamilia.equalsIgnoreCase("CABLES Y ACCESORIOS PORTATIL")){
            return Integer.toString(10);
        }
        else if(subfamilia.equalsIgnoreCase("CARGADOR PARA PORTATIL")){
            return Integer.toString(11);
        }
        else if(subfamilia.equalsIgnoreCase("CONECTORES PARA CARGADOR")){
            return Integer.toString(12);
        }
        else if(subfamilia.equalsIgnoreCase("DISPOSITIVOS USB PARA PORTATIL")){
            return Integer.toString(13);
        }
        else if(subfamilia.equalsIgnoreCase("EXTENSION GARANTIA PORTATIL")){
            return Integer.toString(14);
        }
        else if(subfamilia.equalsIgnoreCase("REFRIGERACION PARA PORTATIL")){
            return Integer.toString(15);
        }
        else if(subfamilia.equalsIgnoreCase("TABLETAS DIGITALIZADORAS") || subfamilia.equalsIgnoreCase("LAPICES DIGITALES") || subfamilia.equalsIgnoreCase("TABLETAS")){
            return Integer.toString(16);
        }
        else if(subfamilia.equalsIgnoreCase("LECTOR DE MEMORIAS") || subfamilia.equalsIgnoreCase("LECTORES TARJETAS FLASH") || subfamilia.equalsIgnoreCase("PANEL LECTOR TARJETAS") || subfamilia.equalsIgnoreCase("LECTORES TARJETAS MAGNETICAS")){
            return Integer.toString(17);
        }
        else if(subfamilia.equalsIgnoreCase("SAI") || subfamilia.equalsIgnoreCase("SAI ONLINE") || subfamilia.equalsIgnoreCase("SAI OFFLINE")){
            return Integer.toString(18);
        }
        else if(subfamilia.equalsIgnoreCase("ACCESORIOS SAI") ){
            return Integer.toString(19);
        }
        else if(subfamilia.equalsIgnoreCase("CAJAS EXTERNAS MULTIMEDIA") || subfamilia.equalsIgnoreCase("CAJAS EXTERNAS/EXTRAIBLES HD") || subfamilia.equalsIgnoreCase("CAJAS EXTERNAS")){
            return Integer.toString(20);
        }
        else if(subfamilia.equalsIgnoreCase("DISCOS DUROS EXTERNOS")  || subfamilia.equalsIgnoreCase("DISCOS DUROS MULTIMEDIA")){
            return Integer.toString(21);
        }
        else if(subfamilia.equalsIgnoreCase("LAPIZ USB/PENDRIVE") || subfamilia.equalsIgnoreCase("MEMORIAS USB")){
            return Integer.toString(22);
        }
        else if(subfamilia.equalsIgnoreCase("MEMORIAS COMPACT FLASH") || subfamilia.equalsIgnoreCase("COMPACT FLASH")){
            return Integer.toString(23);
        }
        else if(subfamilia.equalsIgnoreCase("SD MICRO SD MINI SD")  || subfamilia.equalsIgnoreCase("MEMORIAS SECURE DIGITAL (SD)")){
            return Integer.toString(24);
        }
        else if(subfamilia.equalsIgnoreCase("MULTIMEDIA CARD (MMC)/RSMMC") || subfamilia.equalsIgnoreCase("STICK PRO DUO STICK MICRO M2/3") || subfamilia.equalsIgnoreCase("MEMORIAS XD PICTURE") || subfamilia.equalsIgnoreCase("OTRAS TARJETAS") || subfamilia.equalsIgnoreCase("MEMORIAS MEMORY STICK")){
            return Integer.toString(25);
        }
        else if(subfamilia.equalsIgnoreCase("REGRABADORAS DVD EXTERNAS")){
            return Integer.toString(26);
        }
        else if(subfamilia.equalsIgnoreCase("DISCOS DUROS") || subfamilia.equalsIgnoreCase("NAS") || subfamilia.equalsIgnoreCase("DISCOS DUROS MAGNETICOS") || subfamilia.equalsIgnoreCase("ALMACENAMIENTO RED SOBREMESA")){
            return Integer.toString(27);
        }
        else if(subfamilia.equalsIgnoreCase("DISCOS DUROS SOLIDOS")){
            return Integer.toString(28);
        }
        else if(subfamilia.equalsIgnoreCase("TARJETAS DE SONIDO")){
           return Integer.toString(29);
        }
        else if(subfamilia.equalsIgnoreCase("CAJAS MINI Y MICRO TORRE") || subfamilia.equalsIgnoreCase("CAJAS SEMITORRE Y MIDITORRE") || subfamilia.equalsIgnoreCase("CAJAS SOBREMESA") || subfamilia.equalsIgnoreCase("CAJAS SERVIDORES") || subfamilia.equalsIgnoreCase("CAJAS SEMITORRE")  || subfamilia.equalsIgnoreCase("CAJAS TPV")){
            return Integer.toString(30);
        }
        else if(subfamilia.equalsIgnoreCase("FUENTES ALIMENTACION >600W") || subfamilia.equalsIgnoreCase("FUENTES ALIMENTACION <=600W") || subfamilia.equalsIgnoreCase("FUENTES DE ALIMENTACION")){
            return Integer.toString(31);
        }
        else if(subfamilia.equalsIgnoreCase("SINTONIZADORAS EDITORES Y CAPTURADORAS")){
            return Integer.toString(32);
        }
        else if(subfamilia.equalsIgnoreCase("MEMORIAS DDR2") || subfamilia.equalsIgnoreCase("MEMORIAS DDR3") || subfamilia.equalsIgnoreCase("MEMORIAS SDRAM") || subfamilia.equalsIgnoreCase("MEMORIAS DDR") || subfamilia.equalsIgnoreCase("MEMORIAS PARA PORTÁTILES") || subfamilia.equalsIgnoreCase("MEMORIAS PARA PC")){
            return Integer.toString(33);
        }
        else if(subfamilia.equalsIgnoreCase("CONTROLADORAS IDE USB FIREWIRDE") || subfamilia.equalsIgnoreCase("TARJETAS CAPTURADORAS")){
            return Integer.toString(32);
        }
        else if(subfamilia.equalsIgnoreCase("MICROS INTEL SOCKET 1156") || subfamilia.equalsIgnoreCase("VENTILADOR/REFRIGERADOR/COOLER") || subfamilia.equalsIgnoreCase("VENTILADORES") || subfamilia.equalsIgnoreCase("MICROPROCESADORES") || subfamilia.equalsIgnoreCase("MICROS INTEL SOCKET 1155") || subfamilia.equalsIgnoreCase("MICROS INTEL SOCKET 775") || subfamilia.equalsIgnoreCase("MICROS AMD AM3")){
            return Integer.toString(34);
        }
        else if(subfamilia.equalsIgnoreCase("PC DE SOBREMESA") || subfamilia.equalsIgnoreCase("MINI ORDENADORES") || subfamilia.equalsIgnoreCase("BAREBONES") || subfamilia.equalsIgnoreCase("PC SOBREMESA")){
            return Integer.toString(35);
        }
        else if(subfamilia.equalsIgnoreCase("MONITORES TPV Y TÁCTILES") || subfamilia.equalsIgnoreCase("ORDENADORES TPV") || subfamilia.equalsIgnoreCase("IMPRESORAS DE TICKET")){
            return Integer.toString(36);
        }
        else if(subfamilia.equalsIgnoreCase("PLACAS BASE") || subfamilia.equalsIgnoreCase("PB INTEL SOCKET 1366") || subfamilia.equalsIgnoreCase("PB AMD SOCKET 939") || subfamilia.equalsIgnoreCase("PB INTEL SOCKET 775") || subfamilia.equalsIgnoreCase("PB INTEL SOCKET 478") || subfamilia.equalsIgnoreCase("PB INTEL SOCKET 1156") || subfamilia.equalsIgnoreCase("PB INTEL SOCKET 1155") || subfamilia.equalsIgnoreCase("PB AMD SOCKET A/462") || subfamilia.equalsIgnoreCase("PB AMD SOCKET AM3") || subfamilia.equalsIgnoreCase("PB CON CPU INTEGRADA")){
            return Integer.toString(38);
        }
        else if(subfamilia.equalsIgnoreCase("SERVIDORES RACK")){
            return Integer.toString(39);
        }
        else if(subfamilia.equalsIgnoreCase("ARMARIOS RACK")){
            return Integer.toString(40);
        }
        else if(subfamilia.equalsIgnoreCase("SERVIDORES TORRE")){
            return Integer.toString(41);
        }
        else if(subfamilia.equalsIgnoreCase("PCI EXPRESS DDR5") || subfamilia.equalsIgnoreCase("PCI EXPRESS DDR3") || subfamilia.equalsIgnoreCase("PCI EXPRESS DDR2") || subfamilia.equalsIgnoreCase("AGP")){
            return Integer.toString(43);
        }
        else if(subfamilia.equalsIgnoreCase("LECTORES Y REGRABADORES DE DVD") || subfamilia.equalsIgnoreCase("LECTORES Y REGRABADORES DE BLU RAY") || subfamilia.equalsIgnoreCase("REGRABADORES DE DVD") || subfamilia.equalsIgnoreCase("LECTORES DE DVD")){
            return Integer.toString(44);
        }
        else if(subfamilia.equalsIgnoreCase("CABLES Y ACCESORIOS PC") || subfamilia.equalsIgnoreCase("CABLE DE DATOS") || subfamilia.equalsIgnoreCase("ACCESORIOS") || subfamilia.equalsIgnoreCase("CABLE DE RED") || subfamilia.equalsIgnoreCase("ACCESORIOS MEMORIAS") || subfamilia.equalsIgnoreCase("ACCESORIOS TPV") || subfamilia.equalsIgnoreCase("CABLES USB/FIREWIRE") || subfamilia.equalsIgnoreCase("CABLES DE ALIMENTACIÓN") || subfamilia.equalsIgnoreCase("ACCESORIOS COMPONENTES") || subfamilia.equalsIgnoreCase("ACCESORIOS ORDENADORES")){
            return Integer.toString(45);
        }
        else if(subfamilia.equalsIgnoreCase("AMPLIACION DE GARANTIA") ){
            return Integer.toString(46);
        }
        else if(subfamilia.equalsIgnoreCase("CAJONES PORTAMONEDAS")){
            return Integer.toString(48);
        }
        else if(subfamilia.equalsIgnoreCase("LECTOR CÓDIGO DE BARRAS") || subfamilia.equalsIgnoreCase("LECTORES CODIGO BARRAS")){
            return Integer.toString(50);
        }
        else if(subfamilia.equalsIgnoreCase("CONSUMIBLES VIDEO") || subfamilia.equalsIgnoreCase("CD/DVD/DISQUETES VIRGENES") || subfamilia.equalsIgnoreCase("CD-R/RW") || subfamilia.equalsIgnoreCase("DVD-R/RW") || subfamilia.equalsIgnoreCase("BLU RAY")){
            return Integer.toString(51);
        }
        else if(subfamilia.equalsIgnoreCase("CARTUCHOS TINTA COMPATIBLE") || subfamilia.equalsIgnoreCase("CARTUCHOS TINTA ORIGINAL") || subfamilia.equalsIgnoreCase("CARTUCHOS TINTA RECICLADO") || subfamilia.equalsIgnoreCase("CONSUMIBLES IMPRESIÓN DE TINTA")){
            return Integer.toString(52);
        }
        else if(subfamilia.equalsIgnoreCase("TONER COMPATIBLE") || subfamilia.equalsIgnoreCase("TONER ORIGINAL") || subfamilia.equalsIgnoreCase("TONER RECICLADO") || subfamilia.equalsIgnoreCase("CONSUMIBLES IMPRESIÓN LÁSER")){
            return Integer.toString(53);
        }
        else if(subfamilia.equalsIgnoreCase("BOBINA") || subfamilia.equalsIgnoreCase("CINTAS") || subfamilia.equalsIgnoreCase("PAPEL") || subfamilia.equalsIgnoreCase("TAMBOR/FOTOCONDUCTOR") || subfamilia.equalsIgnoreCase("CONSUMIBLES IMPRESIÓN MATRICIAL") || subfamilia.equalsIgnoreCase("ACCESORIOS CONSUMIBLES")){
            return Integer.toString(54);
        }
        else if(subfamilia.equalsIgnoreCase("BACKUP") || subfamilia.equalsIgnoreCase("UNIDAD BACKUP") || subfamilia.equalsIgnoreCase("ACCESORIOS ALMACENAMIENTO")){
            return Integer.toString(55);
        }        
        else if(subfamilia.equalsIgnoreCase("ACCESORIOS CD/DVD")){
            return Integer.toString(56);
        }
        else if(subfamilia.equalsIgnoreCase("CARGADORES")){
            return Integer.toString(57);
        }        
        else if(subfamilia.equalsIgnoreCase("CONVERTIDORES")){
            return Integer.toString(58);
        }
        else if(subfamilia.equalsIgnoreCase("REGLETAS")){
            return Integer.toString(59);
        }        
        else if(subfamilia.equalsIgnoreCase("PILAS") || subfamilia.equalsIgnoreCase("PILAS Y CARGADORES")){
            return Integer.toString(60);
        }
        else if(subfamilia.equalsIgnoreCase("LIMPIEZA ORDENADOR")){
            return Integer.toString(62);
        }        
        else if(subfamilia.equalsIgnoreCase("CONSUMIBLES IMPRESIÓN LASER") || subfamilia.equalsIgnoreCase("RECAMBIOS")){
            return Integer.toString(63);
        }
        else if(subfamilia.equalsIgnoreCase("POWERLINE")){
            return Integer.toString(64);
        }
        else if(subfamilia.equalsIgnoreCase("ADAPTADORES CON CABLE") || subfamilia.equalsIgnoreCase("TARJETAS DE RED")){
            return Integer.toString(65);
        }        
        else if(subfamilia.equalsIgnoreCase("ADAPTADORES INALAMBRICOS") || subfamilia.equalsIgnoreCase("TARJETAS DE RED WIFI") || subfamilia.equalsIgnoreCase("BLUETOOTH")){
            return Integer.toString(66);
        }
        else if(subfamilia.equalsIgnoreCase("MODEM") || subfamilia.equalsIgnoreCase("MODEMS ANALOGICOS Y RDSI")){
            return Integer.toString(68);
        }
        else if(subfamilia.equalsIgnoreCase("ROUTERS INALAMBRICOS") || subfamilia.equalsIgnoreCase("ROUTERS WIFI")){
            return Integer.toString(70);
        }
        else if(subfamilia.equalsIgnoreCase("ROUTERS CON CABLE") || subfamilia.equalsIgnoreCase("ROUTERS ADSL") || subfamilia.equalsIgnoreCase("ROUTERS")){
            return Integer.toString(69);
        }
        else if(subfamilia.equalsIgnoreCase("SERVIDORES IMPRESION CON CABLE") || subfamilia.equalsIgnoreCase("SERVIDORES IMPRESION INALAMBRI") || subfamilia.equalsIgnoreCase("SERVIDORES DE IMPRESIÓN")){
            return Integer.toString(71);
        }        
        else if(subfamilia.equalsIgnoreCase("PUNTOS DE ACCESO")){
            return Integer.toString(72);
        }
        else if(subfamilia.equalsIgnoreCase("SWITCH") || subfamilia.equalsIgnoreCase("SWITCHES ARMARIO") || subfamilia.equalsIgnoreCase("SWITCHES SOBREMESA")){
            return Integer.toString(73);
        }
        else if(subfamilia.equalsIgnoreCase("ACCESORIOS REDES") || subfamilia.equalsIgnoreCase("ACCESORIOS REDES WIFI") || subfamilia.equalsIgnoreCase("HERRAMIENTAS CONECTORES REDES")){
            return Integer.toString(74);
        }
        else if(subfamilia.equalsIgnoreCase("ANTISPAM HOGAR")){
            return Integer.toString(75);
        }
        else if(subfamilia.equalsIgnoreCase("ANTIVIRUS HOGAR") || subfamilia.equalsIgnoreCase("ANTIVIRUS")){
            return Integer.toString(76);
        }
        else if(subfamilia.equalsIgnoreCase("SOFTWARE GESTION") || subfamilia.equalsIgnoreCase("GESTIÓN")){
            return Integer.toString(78);
        }
        else if(subfamilia.equalsIgnoreCase("SISTEMAS DE 32 BITS") || subfamilia.equalsIgnoreCase("SISTEMAS DE 64 BITS") || subfamilia.equalsIgnoreCase("SISTEMAS OPERATIVOS DE PC") || subfamilia.equalsIgnoreCase("SISTEMAS OPERATIVOS DE SERVIDORES")){
            return Integer.toString(80);
        }
        else if(subfamilia.equalsIgnoreCase("PAQUETES INTEGRADOS") || subfamilia.equalsIgnoreCase("MULTIMEDIA") || subfamilia.equalsIgnoreCase("EDICION")){
            return Integer.toString(79);
        }
        else if(subfamilia.equalsIgnoreCase("LICENCIAS S.O. SERVIDORES")){
            return Integer.toString(81);
        }        
        else if(subfamilia.equalsIgnoreCase("UNIDAD CENTRAL")){
            return Integer.toString(82);
        }
        else if(subfamilia.equalsIgnoreCase("CAMARAS BNC EXTERIOR") || subfamilia.equalsIgnoreCase("CAMARAS BNC INTERIOR") || subfamilia.equalsIgnoreCase("CAMARAS IP") || subfamilia.equalsIgnoreCase("CAMARAS CCTV") || subfamilia.equalsIgnoreCase("CAMARA IP WIFI")){
            return Integer.toString(83);
        }        
        else if(subfamilia.equalsIgnoreCase("GRABADORAS DIGITALES") || subfamilia.equalsIgnoreCase("GRABADORES DE VIDEO")){
            return Integer.toString(84);
        }
        else if(subfamilia.equalsIgnoreCase("FOCOS INFRARROJOS")){
            return Integer.toString(85);
        }
        else if(subfamilia.equalsIgnoreCase("ACCESORIOS VIGILANCIA Y SEGURIDAD") || subfamilia.equalsIgnoreCase("OTROS ACCESORIOS VIDEOVIGILANCIA") || subfamilia.equalsIgnoreCase("SOPORTE Y CARCASA PARA CAMARAS")){
            return Integer.toString(86);
        }
        else if(subfamilia.equalsIgnoreCase("ALTAVOCES 2.0") || subfamilia.equalsIgnoreCase("ALTAVOCES 2.1") || subfamilia.equalsIgnoreCase("ALTAVOCES 5.1") || subfamilia.equalsIgnoreCase("ALTAVOCES DOCK/PORTATILES") || subfamilia.equalsIgnoreCase("HOME CINEMA") || subfamilia.equalsIgnoreCase("ALTAVOCES") || subfamilia.equalsIgnoreCase("ACCESORIOS ALTAVOCES") || subfamilia.equalsIgnoreCase("AMPLIFICADORES Y ALTAVOCES")){
            return Integer.toString(87);
        }
        else if(subfamilia.equalsIgnoreCase("AURICULARES") || subfamilia.equalsIgnoreCase("AURICULARES + MICROFONOS") || subfamilia.equalsIgnoreCase("MICROFONOS") || subfamilia.equalsIgnoreCase("MICRÓFONOS")){
            return Integer.toString(88);
        }        
        else if(subfamilia.equalsIgnoreCase("CONSOLAS/MEZCLADORES DJ")){
            return Integer.toString(89);
        }
        else if(subfamilia.equalsIgnoreCase("SINTONIZADORES RADIO")){
            return Integer.toString(90);
        }
        else if(subfamilia.equalsIgnoreCase("MP3 MP4 MP5") || subfamilia.equalsIgnoreCase("REPRODUCTORES MP3/MP4/MP5")){
            return Integer.toString(91);
        }
        else if(subfamilia.equalsIgnoreCase("ACCESORIOS DE MP3/MP4/MP5") || subfamilia.equalsIgnoreCase("FUNDAS PARA MP3/MP4/MP5")){
            return Integer.toString(92);
        }
        else if(subfamilia.equalsIgnoreCase("LCD") || subfamilia.equalsIgnoreCase("TV LCD") || subfamilia.equalsIgnoreCase("TV PLASMA")){
            return Integer.toString(93);
        }
        else if(subfamilia.equalsIgnoreCase("LED") || subfamilia.equalsIgnoreCase("TV LED")){
            return Integer.toString(94);
        }        
        else if(subfamilia.equalsIgnoreCase("TV 3D")){
            return Integer.toString(95);
        }
        else if(subfamilia.equalsIgnoreCase("MONITORES TFT 17\"-19\"") || subfamilia.equalsIgnoreCase("MONITORES TFT >=20\"") || subfamilia.equalsIgnoreCase("MONITORES LCD 3D") || subfamilia.equalsIgnoreCase("MONITORES LCD") || subfamilia.equalsIgnoreCase("MONITORES CON TV")){
            return Integer.toString(96);
        }        
        else if(subfamilia.equalsIgnoreCase("MONITORES LED")){
            return Integer.toString(97);
        }
        else if(subfamilia.equalsIgnoreCase("PROYECTORES") || subfamilia.equalsIgnoreCase("VIDEO PROYECTORES 3D") || subfamilia.equalsIgnoreCase("VIDEO PROYECTORES")){
            return Integer.toString(98);
        }
        else if(subfamilia.equalsIgnoreCase("PANTALLAS DE PROYECCIÓN") || subfamilia.equalsIgnoreCase("PANTALLAS PROYECCIÓN")){
            return Integer.toString(99);
        }
        else if(subfamilia.equalsIgnoreCase("PIZARRAS ELECTRONICAS")){
            return Integer.toString(101);
        }
        else if(subfamilia.equalsIgnoreCase("ACCESORIOS 3D") || subfamilia.equalsIgnoreCase("SOPORTES MONITORES / TV") || subfamilia.equalsIgnoreCase("ACCESORIOS MONITORES / TV") || subfamilia.equalsIgnoreCase("ACCESORIOS TELEVISION/MONITOR")){
            return Integer.toString(102);
        }
        else if(subfamilia.equalsIgnoreCase("SOPORTES VIDEO PROYECTOR") || subfamilia.equalsIgnoreCase("ACCESORIOS VIDEOPROYECTORES") || subfamilia.equalsIgnoreCase("ACCESORIOS PROYECTOR/PANTALLA")){
            return Integer.toString(100);
        }
        else if(subfamilia.equalsIgnoreCase("VIDEO EXTERNO PARA PC")){
            return Integer.toString(103);
        }        
        else if(subfamilia.equalsIgnoreCase("REPRODUCTORES Y GRABADORES DVD") || subfamilia.equalsIgnoreCase("REPRODUCTORES Y GRABADORES BLU RAY") || subfamilia.equalsIgnoreCase("GRABADOR DVD SOBREMESA") || subfamilia.equalsIgnoreCase("REPRODUCTOR PORTATIL") || subfamilia.equalsIgnoreCase("REPRODUCTOR SOBREMESA")){
            return Integer.toString(104);
        }
        else if(subfamilia.equalsIgnoreCase("RECEPTORES TDT") || subfamilia.equalsIgnoreCase("TDT TDT+GRABACION") || subfamilia.equalsIgnoreCase("SINTONIZADORES TV PARA PC") || subfamilia.equalsIgnoreCase("SATELITE")){
            return Integer.toString(105);
        }
        else if(subfamilia.equalsIgnoreCase("CONMUTADOR VIDEO Y CABLES") || subfamilia.equalsIgnoreCase("CALBE/CONECTOR IMAGEN/SONIDO") || subfamilia.equalsIgnoreCase("CONMUTADORES Y COMPARTIDORES") || subfamilia.equalsIgnoreCase("CABLES AUDIO/VIDEO")){
            return Integer.toString(106);
        }
        else if(subfamilia.equalsIgnoreCase("CAMARAS DE FOTOS ANALOGICAS") || subfamilia.equalsIgnoreCase("CAMARAS DE FOTOS 3D") || subfamilia.equalsIgnoreCase("CAMARA DE FOTOS COMPACTAS") || subfamilia.equalsIgnoreCase("CAMARAS COMPACTAS")){
            return Integer.toString(107);
        }
        else if(subfamilia.equalsIgnoreCase("CAMARAS DE FOTOS REFLEX")){
            return Integer.toString(108);
        }        
        else if(subfamilia.equalsIgnoreCase("OBJETIVOS Y FLASH")){
            return Integer.toString(109);
        }
        else if(subfamilia.equalsIgnoreCase("CAMARAS DE VIDEO")){
            return Integer.toString(110);
        }
        else if(subfamilia.equalsIgnoreCase("FUNDAS DE CAMARAS DE FOTOS") || subfamilia.equalsIgnoreCase("ACCESORIOS FOTOGRAFIA") || subfamilia.equalsIgnoreCase("BOLSAS Y FUNDAS PARA CAMARAS") || subfamilia.equalsIgnoreCase("FUNDAS PARA VIDEOCAMARAS") || subfamilia.equalsIgnoreCase("ACCESORIOS CAMARAS DE VIDEO") || subfamilia.equalsIgnoreCase("BATERIAS Y CARGADORES")){
            return Integer.toString(111);
        }
        else if(subfamilia.equalsIgnoreCase("IMPRESORAS GRAN FORMATO")){
            return Integer.toString(112);
        }        
        else if(subfamilia.equalsIgnoreCase("IMPRESORAS FOTOGRÁFICAS") || subfamilia.equalsIgnoreCase("IMPRESORAS DE INYECCIÓN") || subfamilia.equalsIgnoreCase("IMPRESORAS INYECCION")){
            return Integer.toString(113);
        }
        else if(subfamilia.equalsIgnoreCase("IMPRESORAS LASER MONOCROMO") || subfamilia.equalsIgnoreCase("IMPRESORAS LÁSER MONOCROMO")){
            return Integer.toString(114);
        }
        else if(subfamilia.equalsIgnoreCase("IMPRESORAS LÁSER COLOR") || subfamilia.equalsIgnoreCase("IMPRESORAS LASER COLOR")){
            return Integer.toString(115);
        }        
        else if(subfamilia.equalsIgnoreCase("IMPRESORAS MATRICIALES")){
            return Integer.toString(116);
        }
        else if(subfamilia.equalsIgnoreCase("IMPRESORAS TERMICAS")){
            return Integer.toString(118);
        }
        else if(subfamilia.equalsIgnoreCase("")){
            return Integer.toString(117);
        }
        else if(subfamilia.equalsIgnoreCase("MULTIFUNCÓNES FOTOGRÁFICAS") || subfamilia.equalsIgnoreCase("MULTIFUNCIÓN DE INYECCIÓN") || subfamilia.equalsIgnoreCase("FOTOCOPIADORAS") || subfamilia.equalsIgnoreCase("MULTIFUNCION INYECCION SIN FAX") || subfamilia.equalsIgnoreCase("MULTIFUNCION INYECCION CON FAX")){
            return Integer.toString(119);
        }
        else if(subfamilia.equalsIgnoreCase("MULTIFUNCION LASER CON FAX") || subfamilia.equalsIgnoreCase("MULTIFUNCION LASER SIN FAX") || subfamilia.equalsIgnoreCase("MULTIFUNCIÓN LÁSER COLOR") || subfamilia.equalsIgnoreCase("MULTIFUNCIÓN LÁSER MONOCROMO")){
            return Integer.toString(120);
        }
        else if(subfamilia.equalsIgnoreCase("ESCANER") || subfamilia.equalsIgnoreCase("ESCANERS SOBREMESA")){
            return Integer.toString(121);
        }
        else if(subfamilia.equalsIgnoreCase("CABLES IMPRESORAS/ESCANERS") || subfamilia.equalsIgnoreCase("CABLES DE IMPRESORA/SERIE")){
            return Integer.toString(122);
        }
        else if(subfamilia.equalsIgnoreCase("CONSOLAS NINTENDO DS")  || subfamilia.equalsIgnoreCase("CONSOLAS PSP")){
            return Integer.toString(123);
        }
        else if(subfamilia.equalsIgnoreCase("CONSOLAS XBOX") || subfamilia.equalsIgnoreCase("CONSOLAS WII") || subfamilia.equalsIgnoreCase("CONSOLAS PS3")){
            return Integer.toString(124);
        }
        else if(subfamilia.equalsIgnoreCase("JUEGOS NINTENDO DS") || subfamilia.equalsIgnoreCase("VIDEOJUEGOS DS/DS LITE")){
            return Integer.toString(125);
        }
        else if(subfamilia.equalsIgnoreCase("JUEGOS PSP") || subfamilia.equalsIgnoreCase("VIDEOJUEGOS PSP")){
            return Integer.toString(126);
        }
        else if(subfamilia.equalsIgnoreCase("JUEGOS WII")  || subfamilia.equalsIgnoreCase("VIDEOJUEGOS WII")){
            return Integer.toString(127);
        }
        else if(subfamilia.equalsIgnoreCase("VIDEOJUEGOS PS2") || subfamilia.equalsIgnoreCase("JUEGOS PS2")){
            return Integer.toString(128);
        }
        else if(subfamilia.equalsIgnoreCase("JUEGOS PS3") || subfamilia.equalsIgnoreCase("VIDEOJUEGOS PS3")){
            return Integer.toString(129);
        }        
        else if(subfamilia.equalsIgnoreCase("JUEGOS XBOX360") || subfamilia.equalsIgnoreCase("VIDEOJUEGOS XBOX")){
            return Integer.toString(130);
        }
        else if(subfamilia.equalsIgnoreCase("JUEGOS PC") || subfamilia.equalsIgnoreCase("VIDEOJUEGOS PC")){
            return Integer.toString(131);
        }
        else if(subfamilia.equalsIgnoreCase("JOYSTICK Y VOLANTES") || subfamilia.equalsIgnoreCase("GAMEPAD") || subfamilia.equalsIgnoreCase("BATERIAS/CARGADORES CONSOLAS") || subfamilia.equalsIgnoreCase("BOLSAS Y FUNDAS PARA CONSOLAS") || subfamilia.equalsIgnoreCase("CABLES Y ALIMENTADORES CONSOLA") || subfamilia.equalsIgnoreCase("MANDOS JUEGOS MUSICA CONSOLA") || subfamilia.equalsIgnoreCase("OTROS MANDOS/PACK PARA CONSOLA") || subfamilia.equalsIgnoreCase("MANDOS JUEGOS ACCION CONSOLA") || subfamilia.equalsIgnoreCase("MANDOS Y JUEGOS PARA PC") || subfamilia.equalsIgnoreCase("MANDOS Y PUNTEROS PARA CONSOLA") || subfamilia.equalsIgnoreCase("MEMORIAS CONSOLAS") || subfamilia.equalsIgnoreCase("ACCESORIOS DE CONSOLA DS") || subfamilia.equalsIgnoreCase("ACCESORIOS DE CONSOLA PSP") || subfamilia.equalsIgnoreCase("ACCESORIOS DE CONSOLA PS2") || subfamilia.equalsIgnoreCase("ACCESORIOS DE CONSOLA PS3")){
            return Integer.toString(132);
        }
        else if(subfamilia.equalsIgnoreCase("TELÉFONOS MÓVILES")){
            return Integer.toString(133);
        }
        else if(subfamilia.equalsIgnoreCase("TELÉFONOS FIJOS CON CABLE") || subfamilia.equalsIgnoreCase("TELÉFONOS FIJOS SIN CABLE") || subfamilia.equalsIgnoreCase("CENTRALES TELEFONICAS")){
            return Integer.toString(134);
        }
        else if(subfamilia.equalsIgnoreCase("SMARTPHONES")){
            return Integer.toString(135);
        }
        else if(subfamilia.equalsIgnoreCase("BATERIAS PARA TELEFONO") || subfamilia.equalsIgnoreCase("CARGADOR PARA TELEFONO") || subfamilia.equalsIgnoreCase("MANOS LIBRES ADAPTADORES") || subfamilia.equalsIgnoreCase("ACCESORIOS DE TELEFONÍA") || subfamilia.equalsIgnoreCase("ACCESORIOS CENTRALES TELEFONICAS") || subfamilia.equalsIgnoreCase("TELÉFONOS FIJOS SIN CABLE") || subfamilia.equalsIgnoreCase("")){
            return Integer.toString(136);
        }
        else if(subfamilia.equalsIgnoreCase("GPS") || subfamilia.equalsIgnoreCase("ACCESORIOS DE GPS") || subfamilia.equalsIgnoreCase("GPS")){
            return Integer.toString(137);
        }
        else if(subfamilia.equalsIgnoreCase("LECTOR LIBROS ELECTRONICOS") || subfamilia.equalsIgnoreCase("EBOOKS")){
            return Integer.toString(138);
        }
        else if(subfamilia.equalsIgnoreCase("MARCO DIGITAL") || subfamilia.equalsIgnoreCase("MARCOS DIGITALES")){
            return Integer.toString(139);
        }
        else if(subfamilia.equalsIgnoreCase("CAMARAS WEB")){
            return Integer.toString(142);
        }
        else if(subfamilia.equalsIgnoreCase("TELEFONOS SKYPE/IP") || subfamilia.equalsIgnoreCase("TELEFONÍA VOIP")){
            return Integer.toString(143);
        }
        else if(subfamilia.equalsIgnoreCase("FAX") || subfamilia.equalsIgnoreCase("MAQUINAS DE FAX")){
            return Integer.toString(144);
        }
        return Integer.toString(141);
    }
    
    private Map obtenerDatosElemento(Element tag,Element raiz,Map<String,String> datos){
        if(raiz.getName().equals("datos")){
            datos.put("mayorista","Infortisa");
        }
        else if(raiz.getName().equalsIgnoreCase("catalogo")){
            datos.put("mayorista", "Megasur");
        }
        if(tag.getName().equalsIgnoreCase("referencia") || tag.getName().equalsIgnoreCase("part_number")){
            datos.put("referencia", this.cortar(tag.getText()));
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
