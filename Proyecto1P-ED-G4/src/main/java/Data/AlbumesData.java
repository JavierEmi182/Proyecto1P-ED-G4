/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data;

import Classes.Album;
import Classes.Photo;
import TDAs.DoubleCircularLinkedList;
import estructuradatos.Proyecto1P.App;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.util.Scanner;
import java.util.regex.Pattern;


/**
 *
 * @author Javier
 */
public class AlbumesData {
    
    public static DoubleCircularLinkedList<Album> leerAlbumes(String ruta,DoubleCircularLinkedList<Photo> fotosCompletas) throws IOException{
        DoubleCircularLinkedList<Album> albumes = new DoubleCircularLinkedList<Album>();
        try(InputStream input = App.class.getResource(ruta).openStream();
                BufferedReader bf = new BufferedReader(
                                    new InputStreamReader(input,"UTF-8"))){
            //GENERA EL URL RELATIVO AL ARCHIVO QUE VAMOS A LEER
           
            /*URL u = App.class.getResource(ruta);
            File file = new File(u.toURI());
            try(BufferedReader bf = new BufferedReader(new FileReader(file))){*/
                String linea;
                //leemos linea a linea hasta llegar la final del archivo
                while( (linea=bf.readLine())!=null ){
                    //System.out.println("tets");
                    //System.out.println(linea);
                    //dividir la en partes 
                    String[] partes = linea.split(";");
                    //System.out.println("nombre album: "+partes[0]);
             /*       for(String s:partes){
                        for(Photo p:fotosCompletas){
                            if(p.getRuta().equals(s)){
                                
                            }
                        }
                    }*/
                    
                    DoubleCircularLinkedList<Photo> fotosAlbum= new DoubleCircularLinkedList<>();
                    if(partes[1].equals("null")){
                        System.out.println("Album vacio");
                        //personas=null;
                    }else{
                        String[] partesFotos=partes[1].split("-");
                        for(String s:partesFotos){
                            for(Photo f:fotosCompletas){
                                if(s.equals(f.getRuta())){
                                    //System.out.println(s+" coincidio con "+f.getRuta());
                                    fotosAlbum.addFirst(f);
                            }
                        }
                        //fotos.addFirst(s);
                    }
                    }
                   // System.out.println("Album: "+fotosAlbum);
                    if(partes[2].equals("null")){
                        //System.out.println("Deberia entrar aqui");
                        Album albumAñadir= new Album(partes[0],fotosAlbum,LocalDate.now());
                        //System.out.println("album añadir es: "+albumAñadir);
                        albumes.addFirst(albumAñadir);
                        //albumes.addFirst(new Album(partes[0],fotosAlbum,LocalDate.now()));
                        //System.out.println("tuvo que añadirse a albumes");
                        //System.out.println(partes[0]+fotosAlbum+LocalDate.now());
                    }else{
                        albumes.addFirst(new Album(partes[0],fotosAlbum,LocalDate.parse(partes[2])));
                    }

                }
            } catch (FileNotFoundException ex) {
                System.out.println(ex.getMessage());
                throw ex;
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
                throw ex;
            } 
        //System.out.println("Llegue antes del return");
        //System.out.println("Retorne: "+albumes);
        return albumes;
}
    
    public static void escribirAlbumes(Album album) {
        
        String ruta = "/archivos/albumesUsuario.txt";
        //List<Comida> comidas = new ArrayList<>();
        File file = new File(App.class.getResource(ruta).getFile());
        try(BufferedWriter bw = new BufferedWriter(new FileWriter(file,true))){
            bw.newLine();
            //String linea = comida.getNombre()+";"+ comida.getPrecio()+";"+comida.getRutaImagen()+";"+comida.getTipoComida();
            String fotos ="";
            for(Photo f:album.getFotos()){
                fotos+=f.getRuta()+"-";
            }
            int i=0;
            /*if(foto.getFavorita()==true){
                i=1;
            }*/
            String linea =album.getNombre()+fotos+";"+album.getFechaCreacion();
            bw.write(linea);
            bw.close();
            
        }  catch (IOException ex) {
            System.out.println(ex.getMessage());
            ex.printStackTrace();
            
        }
        //return comidas;
    }
    
    //LEE ARCHIVO COMPLETO Y RETORNO EL STRING DE TODO
    public static String leerArchivoCompleto(String ruta) throws FileNotFoundException, IOException{
        /*String cadena = "";
        FileReader entrada = null;
            entrada = new FileReader(ruta);
            int c;
            while((c = entrada.read()) != -1){
             cadena += (char)c;
            }
    
        return cadena;*/
        
        InputStream input = App.class.getResource(ruta).openStream();
        //input.
        //System.out.println(input);
        
        Scanner s = new Scanner(input).useDelimiter("\\A");
        String result = s.hasNext() ? s.next() : "";
        
        return result;
        
    }
    
    //SOBREESCRIBIR FAVORITOS
    public static void ingresarNuevoImagen(Photo nuevaImagen,int a) throws IOException{
        // a =0 ->Todas
        //a =1  ->Favoritos
        // si se conoce el numero del album tambien funcionaria con el resto
        //String completo=leerArchivoCompleto("/archivos/albumesUsuario.txt");
        InputStream input = App.class.getResource("/archivos/albumesUsuario.txt").openStream();
        
        //System.out.println(input);
        
       /* Scanner s = new Scanner(input).useDelimiter("\\A");
        String result = s.hasNext() ? s.next() : "";
        
        System.out.println(result);*/
        
        String completo=leerArchivoCompleto("/archivos/albumesUsuario.txt");
        System.out.println(completo);
        
        //String result = IOUtils.toString(input, StandardCharsets.UTF_8);
        
        //String lineas[]=completo.split("\n");
        String lineas[]=completo.split("\r?\n|\r");
        String[] editarLinea=lineas[a].split(";");
        String nuevaLinea=editarLinea[0]+";"+editarLinea[1]+"-"+nuevaImagen.getRuta()+";"+editarLinea[2];
        /*System.out.println("lineas a :"+lineas[a]);
        System.out.println(nuevaLinea);*/
        //String fotosFavoritas[]=lineas[1].split("-");
        String ruta = App.class.getResource("/archivos/albumesUsuario.txt").getFile();
        int posicion = 1;
        FileWriter fichero = null;
        PrintWriter escritor = null;
        try {
        fichero = new FileWriter(ruta);
        escritor = new PrintWriter(fichero);
        escritor.flush();
        
        //String split[] = leerArchivoCompleto("/archivos/albumesUsuario.txt").split("\r?\n|\r");
           // System.out.println(split[0]);
           // System.out.println(split.length);
        lineas[posicion] = nuevaLinea;
        
        for(int x = 0; x < lineas.length; x++){
            escritor.write(lineas[x]);
            escritor.println();
         }
            escritor.close();
        } catch (IOException e) {
            System.out.println(e);
           // JOptionPane.showMessageDialog(null, "Error al escribir en el archivo de texto: "+e.getMessage());
        } finally {
            if(fichero != null){
                try {
                    fichero.close();
                } catch (IOException e) {
                    System.out.println(e);
                   // JOptionPane.showMessageDialog(null, "Error al cerrar archivo de texto: "+e.getMessage());
                }
               }
        }
    }
    
    
    //DEBERIA SOBREESCRIBIR EN LA LINEA DESEADA LA NUEVA LINEA
    public static void ingresar(String ruta,String nuevaLinea, int posicion){
        FileWriter fichero = null;
        PrintWriter escritor = null;
        try {
        fichero = new FileWriter(ruta);
        escritor = new PrintWriter(fichero);
        escritor.flush();
        String split[] = leerArchivoCompleto(ruta).split("\n");
        split[posicion] = nuevaLinea;
        for(int x = 0; x < split.length; x++){
            escritor.write(split[x]);
            escritor.println();
         }
            escritor.close();
        } catch (IOException e) {
            System.out.println(e);
           // JOptionPane.showMessageDialog(null, "Error al escribir en el archivo de texto: "+e.getMessage());
        } finally {
            if(fichero != null){
                try {
                    fichero.close();
                } catch (IOException e) {
                    System.out.println(e);
                   // JOptionPane.showMessageDialog(null, "Error al cerrar archivo de texto: "+e.getMessage());
                }
               }
        }
    }
    
    //NO PROBADO
    public static int getLineadeNombreAlbum(String ruta, String elementoBuscado) throws IOException{
        int l=0;
        String split[] = leerArchivoCompleto(ruta).split("\n");
        for(String s:split){
            String lineaSeparada[]=s.split(";");
            if(lineaSeparada[0].equals(elementoBuscado)){
                return l;
            }
            l++;
        }
        return l;
    }
    
    public static void sobreescribirAlbum(String ruta,Album album) throws IOException{
        int numLinea=getLineadeNombreAlbum(ruta,album.getNombre());
        String nuevaLinea=album.getNombre()+";"+album.getFotos().toString().substring(1, album.getFotos().toString().length()-1).replaceAll(",", "-")+";"+album.getFechaCreacion().toString();
        ingresar(ruta,nuevaLinea,numLinea);
    }

}
