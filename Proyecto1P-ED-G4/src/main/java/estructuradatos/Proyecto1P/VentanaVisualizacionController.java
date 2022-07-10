/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package estructuradatos.Proyecto1P;

import Classes.Album;
import Classes.Photo;
import static Data.AlbumesData.getLineadeNombreAlbum;
import static Data.AlbumesData.leerAlbumes;
import static Data.AlbumesData.leerArchivoCompleto;
import static Data.fotosData.leerFotos;
import TDAs.DoubleCircularLinkedList;
import TDAs.NodeList;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Javier
 */
public class VentanaVisualizacionController implements Initializable {

    @FXML
    private Label lbNumFoto;
    @FXML
    private Button btnAnterior;
    @FXML
    private Label lbNombreFoto;
    @FXML
    private Label lbFechaFoto;
    @FXML
    private ImageView ivFotoCentral;
    @FXML
    private Button btnSiguiente;
    
    NodeList<Photo> nodoClickeado;
    Album albumDelNodo;
    @FXML
    private AnchorPane raiz;
    @FXML
    private Button btnVolver;
    @FXML
    private Button btnCargarFoto;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Image i = new Image("file:recursos/fotos/"+Ventana_AlbumController.foto.getRuta());
        ivFotoCentral.setImage(i);
        // TODO
        //NECESITO VER COMO CONSEGUIR EL NODO Y EL ALBUM (TAL VEZ UN STRING CON: NOMBREALBUM;FOTO
        //HAGO UN SPLIT Y BUSCO EN EL TXT LOS VALORES
        
        //SETEO LA RUTA SIN EL .JPG
       /* lbNombreFoto.setText(nodoClickeado.getContent().getRuta().substring(0,nodoClickeado.getContent().getRuta().length()-4));
        lbFechaFoto.setText(nodoClickeado.getContent().getFecha().toString());
        int numeroFoto=albumDelNodo.getFotos().getIndex(nodoClickeado.getContent())+1;  //LE SUMO UNO PORQUE ES INDICE
        int totalFotos=albumDelNodo.getFotos().size();
        lbNumFoto.setText(String.valueOf(numeroFoto)+"/"+String.valueOf(totalFotos));*/
        
    }    

    @FXML
    private void anteriorFoto(ActionEvent event) {
        actualizarDatos(nodoClickeado.getPrevious());
        
    }

    @FXML
    private void siguienteFoto(ActionEvent event) {
        actualizarDatos(nodoClickeado.getNext());
    }
    
     public void recuperarDatos() throws IOException{
        System.out.println("Entre a recuperar datos");
        Stage stage = (Stage)this.raiz.getScene().getWindow();
        String s =(String)stage.getUserData();
        String[] partes=s.split(";");
        String nombreAlbum=partes[0];
        Photo fotoSeleccionada = null;
        String nombreFoto=partes[1];
        DoubleCircularLinkedList<Photo> fotosCompletas= leerFotos("recursos/textos/fotosUsuario.txt");
        DoubleCircularLinkedList<Album> albumes=leerAlbumes("recursos/textos/albumesUsuario.txt",fotosCompletas);
        
        int numLinea=getLineadeNombreAlbum("recursos/textos/albumesUsuario.txt", nombreAlbum);
        Album albumSeleccionado=albumes.get(numLinea);
        DoubleCircularLinkedList<Photo> fotosAlbum =albumSeleccionado.getFotos();
        
        //CASO ALBUM DE UNA FOTO
        if(fotosAlbum.size()==1){
            nodoClickeado=fotosAlbum.getFirst();
        }else if(fotosAlbum.size()==2){
            if(fotosAlbum.getFirst().getContent().getRuta().equals(nombreFoto)){
                nodoClickeado=fotosAlbum.getFirst();
            }else if(fotosAlbum.getLast().getContent().getRuta().equals(nombreFoto)){
                nodoClickeado=fotosAlbum.getLast();
            }else{
                //NO DEBERIA ENTRAR AQUI
                System.out.println("No se encontro el nodo en el album");
            }
        }else{
            for(NodeList<Photo> t = fotosAlbum.getFirst(); !t.getNext().equals(fotosAlbum.getFirst()); t = t.getNext()){
                if(t.getContent().getRuta().equals(nombreFoto)){
                    nodoClickeado=t;
                }
            }
        }
        //CON METODO APARTE
        actualizarDatos(nodoClickeado);
        //FUNCIONA
        /*fotoSeleccionada=nodoClickeado.getContent();
        //FUNCIONA PERO NO ME DA EL NODO
        /*for(Photo f:albumSeleccionado.getFotos()){
            if(f.getRuta().equals(nombreFoto)){
                fotoSeleccionada=f;
                
            }
        }
        lbNombreFoto.setText(nodoClickeado.getContent().getRuta().substring(0,nodoClickeado.getContent().getRuta().length()-4));
        lbFechaFoto.setText(nodoClickeado.getContent().getFecha().toString());
        int numeroFoto=albumDelNodo.getFotos().getIndex(nodoClickeado.getContent())+1;  //LE SUMO UNO PORQUE ES INDICE
        int totalFotos=albumDelNodo.getFotos().size();
        lbNumFoto.setText(String.valueOf(numeroFoto)+"/"+String.valueOf(totalFotos));
        
        Image i= new Image("file:recursos/fotos/"+fotoSeleccionada.getRuta());
        ivFotoCentral.setImage(i);*/
    }
     
    public void actualizarDatos(NodeList<Photo> n1){
        nodoClickeado=n1;
        Photo fotoSeleccionada=n1.getContent();
        lbNombreFoto.setText(nodoClickeado.getContent().getRuta().substring(0,nodoClickeado.getContent().getRuta().length()-4));
        lbFechaFoto.setText(nodoClickeado.getContent().getFecha().toString());
        int numeroFoto=albumDelNodo.getFotos().getIndex(nodoClickeado.getContent())+1;  //LE SUMO UNO PORQUE ES INDICE
        int totalFotos=albumDelNodo.getFotos().size();
        lbNumFoto.setText(String.valueOf(numeroFoto)+"/"+String.valueOf(totalFotos));
        
        Image i= new Image("file:recursos/fotos/"+fotoSeleccionada.getRuta());
        ivFotoCentral.setImage(i);
    }

    @FXML
    private void volverVentana(ActionEvent event) throws IOException {
        /*FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("/fxml/Ventana_Album.fxml"));
        Parent root = fxmlLoader.load();
        stage.setScene(scene);
        stage.show();*/
        App.setRoot("/fxml/Ventana_Album.fxml");
    }

    @FXML
    private void cargarFoto(ActionEvent event) {
        
    }
    
}
