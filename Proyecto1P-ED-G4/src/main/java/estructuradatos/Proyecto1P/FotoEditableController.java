/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package estructuradatos.Proyecto1P;

import Classes.Photo;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TreeView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author Jeremy
 */
public class FotoEditableController implements Initializable {

    @FXML
    private VBox VboxFoto;
    @FXML
    private ImageView ImgviewFoto;
    @FXML
    private HBox hboxGeneralPersonas;
    @FXML
    private VBox VboxPersonas;
    @FXML
    private TreeView<?> TreeViewPersonas;

    public Photo foto;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        Ventana_AlbumController ctrl_anterior= new Ventana_AlbumController();
        foto = ctrl_anterior.getPhoto();
        Image i= new Image("file:recursos/fotos/"+foto.getRuta());
        ImageView imgv= new ImageView(i);
        VboxFoto.getChildren().addAll(imgv);
    }    

    @FXML
    private void AgregarPersona(MouseEvent event) {
    }

    @FXML
    private void ModificarPersona(MouseEvent event) {
    }

    @FXML
    private void EliminarPersona(MouseEvent event) {
    }

    @FXML
    private void EliminarFoto(MouseEvent event) {
    }

    @FXML
    private void GuardarCambios(MouseEvent event) {
    }
    
}
