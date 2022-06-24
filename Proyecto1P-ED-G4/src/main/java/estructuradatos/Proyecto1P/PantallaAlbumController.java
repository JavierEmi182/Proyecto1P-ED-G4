/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package estructuradatos.Proyecto1P;

import Classes.Album;
import Classes.Photo;
import TDAs.DoubleCircularLinkedList;
import TDAs.NodeList;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author Javier
 */
public class PantallaAlbumController implements Initializable {

    @FXML
    private FlowPane fpEspacio;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        Photo girasol = new Photo("girasol.jpg",null,"Es un girasol");
        Photo mariposa = new Photo("mariposa.jpg",null,"Es un mariposa");
        Photo foco = new Photo("foco.jpg",null,"Es un foco");
        //System.out.println("cargo escena");
        DoubleCircularLinkedList<Photo> fotos = new DoubleCircularLinkedList<>();
        //System.out.println("1");
        fotos.addFirst(girasol);
        //System.out.println(fotos);
        fotos.addFirst(mariposa);
        //System.out.println(fotos);
        fotos.addFirst(foco);
        /*System.out.println(fotos);
        System.out.println("2");
        System.out.println("El tamaño es: "+fotos.size());*/
        VBox vboxmenu = new VBox();
        /*System.out.println();
        System.out.println("primero:");
        System.out.println(fotos.getFirst().getContent());
        System.out.println(fotos.getFirst().getPrevious().getContent());
        System.out.println();
        System.out.println("segundo:");
        System.out.println(fotos.getFirst().getNext().getContent());
        System.out.println(fotos.getFirst().getNext().getNext().getContent());
        System.out.println(fotos.getFirst().getNext().getPrevious().getContent());
        System.out.println();
        System.out.println("tercero:");
        System.out.println(fotos.getLast().getContent());
        System.out.println(fotos.getLast().getNext().getContent());
        System.out.println(fotos.getLast().getPrevious().getContent());*/
        //int i= 0;
        //for(NodeList<Photo> p= fotos.getFirst();p !=fotos.getLast();p.getNext()){
        
        
        /*  ESTE FOR RECORRE TODO
        NodeList<Photo> p = fotos.getFirst();
        for(int i=0; i<fotos.size();i++){
        
        System.out.println("*");
        System.out.println(p.getContent());
        p=p.getNext();
        }
        */
        /*  for (Photo p1 :fotos){  ESTE FOR EACH DE PRUEBA
        System.out.println("Entre for each");
        System.out.println(p1);
        }*/
        try{
        for (Photo p :fotos){
            // for(NodeList<Photo> p= fotos.getFirst();f ==fotos.getLast();f.next()){
            System.out.println("*");
            vboxmenu.setAlignment(Pos.CENTER);
            //la imagen
        /*    System.out.println(p.getRuta());
            System.out.println("Ruta " +App.class.getClassLoader().getResourceAsStream("recursos/fotos"));
            System.out.println("Ruta " +App.class.getClassLoader().getResourceAsStream("mariposa.jpg"));*/
            InputStream inputImg= App.class.getResource("/fotoss/"+p.getRuta()).openStream();
           // InputStream inputImg=App.class.getClassLoader().getResourceAsStream(p.getRuta());
            ImageView imgv = new ImageView(new Image(inputImg));
            //   ImageView imgv = new ImageView(new Image(getClass().getResourceAsStream("/fotoss/"+p.getRuta())));
            vboxmenu.getChildren().add(imgv);
            
            //el nombre de la pelicula
            Label lnombre = new Label(p.getInfo());
            
            vboxmenu.getChildren().add(lnombre);
            //anio
            Label lprecio = new Label(String.valueOf(p.getFecha()));
            vboxmenu.getChildren().add(lprecio);
            vboxmenu.setPadding(new Insets(2,3,3,4));
            System.out.println("***");
        }
        fpEspacio.getChildren().add(vboxmenu);
        //si el archivo de pedido es diferente de null se lo carga
    }catch (IOException ex) {
            //si llegamos a este punto es porque no se pudo cargar del archivo
            //reporte.fxml el scenegraph
            //creamos con programacion un nuevo roort y lo fijamos a la scena
          /*  VBox v = new VBox(new Label("No se cargo el archivo FileChooser.fxml"));
            scene = new Scene(v);*/
            System.out.println(ex);
            //System.out.println(root);
        }
}}
        
        
        
    

