/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package estructuradatos.Proyecto1P;

import Classes.Album;
import Classes.Photo;
import static Data.AlbumesData.leerAlbumes;
import static Data.fotosData.leerFotos;
import TDAs.DoubleCircularLinkedList;
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
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TabPane;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author ERWIN AURIA
 */
public class Ventana_AlbumController implements Initializable {

    @FXML
    private ScrollPane scrollPane1;
    @FXML
    private TabPane tabPane;
    @FXML
    private Button btn_CrearAlbum;
    @FXML
    private Label lbl_Nombre;
    @FXML
    private Label lbl_Descripcion;
    @FXML
    private Button btn_Editar;
    @FXML
    private Button btn_AgregarFoto;
    @FXML
    private TitledPane list_Album;
    @FXML
    private Button btn_CrearAlbum1;
    @FXML
    private Label lbl_Nombre1;
    @FXML
    private Label lbl_Descripcion1;
    @FXML
    private Button btn_Editar1;
    @FXML
    private Button btn_AgregarFoto1;
    @FXML
    private TitledPane list_Album1;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
       VBox content = new VBox();
       
        try {
            DoubleCircularLinkedList<Photo> fotos = leerFotos("/archivos/fotosUsuario.txt");
            DoubleCircularLinkedList<Album> albumes = leerAlbumes("/archivos/albumesUsuario.txt",fotos);
            
            int cont=0;
            for (NodeList<Album> t = albumes.getFirst(); !t.getNext().equals(albumes.getFirst()); t = t.getNext()) {
                content.getChildren().add(new Label(t.getContent().getNombre()));
                list_Album.setContent(content);
                cont++; 
                
            }
            
        } catch (IOException ex) {
            Logger.getLogger(Ventana_AlbumController.class.getName()).log(Level.SEVERE, null, ex);
        }


      
    }   

    @FXML
    private void crearAlbum(ActionEvent event) {
    }

    @FXML
    private void editarAlbum(ActionEvent event) {
    }

    @FXML
    private void agregarFoto(ActionEvent event) {
    }
    
}
