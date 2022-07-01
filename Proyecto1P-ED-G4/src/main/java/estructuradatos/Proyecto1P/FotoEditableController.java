/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package estructuradatos.Proyecto1P;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TreeView;
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

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
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
