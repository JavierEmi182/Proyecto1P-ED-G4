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
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.time.LocalDate;

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
        
        String ruta = "albumesUsuario.txt";
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
}
