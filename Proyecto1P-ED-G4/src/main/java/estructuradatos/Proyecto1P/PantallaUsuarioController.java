/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package estructuradatos.Proyecto1P;

import Classes.Album;
import static Data.AlbumesData.leerAlbumes;
import static Data.fotosData.leerFotos;
import TDAs.DoubleCircularLinkedList;
import TDAs.List;
import TDAs.NodeList;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;

/**
 * FXML Controller class
 *
 * @author Javier
 */
public class PantallaUsuarioController implements Initializable {

    @FXML
    private Label lbCantidadAlbumes;
    @FXML
    private Button BtnTodasImagenes;
    @FXML
    private Button BtnAlbum2;
    @FXML
    private Button BtnAlbum3;
    @FXML
    private ComboBox<String> cbAlbumes;
    @FXML
    private Button btnModificar;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            DoubleCircularLinkedList<Album> albumes = leerAlbumes("/archivos/albumesUsuario.txt",leerFotos("/archivos/fotosUsuario.txt"));
            lbCantidadAlbumes.setText(String.valueOf(albumes.size()));
            System.out.println(albumes);
            //List<String> nombresAlbumes = null;
            if(albumes.isEmpty()){
                System.out.println("Albumes esta vacia");
            }else if(albumes.size()==1){
                //System.out.println("Entre a un album");
            //System.out.println("Entre al to string con un elemento");
                System.out.println(albumes.getFirst().getContent());
                System.out.println(albumes.getFirst().getContent().getNombre());
                cbAlbumes.getItems().add(albumes.getFirst().getContent().getNombre());
            }else if (albumes.size()==2){
            //System.out.println("Entre al to string con dos elemento");
                cbAlbumes.getItems().add(albumes.getFirst().getContent().getNombre());
                cbAlbumes.getItems().add(albumes.getLast().getContent().getNombre());
            }else{
                for (NodeList<Album> t = albumes.getFirst(); !t.getNext().equals(albumes.getFirst()); t = t.getNext()) {
                //System.out.println("Entre al to string con"+t.getContent());
                    cbAlbumes.getItems().add(t.getContent().getNombre());
                }
            //return "["+s+this.getLast().getContent()+"]";
            }
            //cbComida.getItems().addAll(tipos); 
            
        } catch (IOException ex) {
            System.out.println(ex);
        }
    }    

    @FXML
    private void moveToAlbumTodas(ActionEvent event) throws IOException {
        App.setRoot("/fxml/PantallaAlbum");
        //App.setRoot(fxml);
    }

    @FXML
    private void modificarAlbum(ActionEvent event) {
        //aqui deberia abrir ventana segun el album seleccionado
        //cbAlbumes.getTypeSelector()
    }
    
}
