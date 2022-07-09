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
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

/**
 * FXML Controller class
 *
 * @author ERWIN AURIA
 */
public class VistaAlbumController implements Initializable {

    @FXML
    private GridPane hBoxImgeneral;
    @FXML
    private Label nombre_AlbumBiblio;
    @FXML
    private Label descrip_AlbumBiblio;
    @FXML
    private AnchorPane scrollpane;
    @FXML
    private TreeView<String> treeView;
    @FXML
    private GridPane hBoxImgeneral1;
    @FXML
    private Label nombre_AlbumEdi;
    @FXML
    private Label descrip_AlbumEdi;
    @FXML
    private AnchorPane scrollpane1;
    @FXML
    private TreeView<String> treeView1;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            //"file:recursos/fotos/"+f.getRuta();
            DoubleCircularLinkedList<Photo> fotos = leerFotos("recursos/textos/fotosUsuario.txt");
            DoubleCircularLinkedList<Album> albumes = leerAlbumes("recursos/textos/albumesUsuario.txt",fotos);
            
            //Cantidad de albumes existentes
            int c = albumes.size();
            
            TreeItem rootItem = new TreeItem("Albumes "+" ("+c+")");
            
            for (NodeList<Album> t = albumes.getFirst(); !t.getNext().equals(albumes.getFirst()); t = t.getNext()) {    
                TreeItem item = new TreeItem(t.getContent().getNombre());
                rootItem.getChildren().add(item);       
            }
            
            treeView.setRoot(rootItem); 
            
            
        } catch (IOException ex) {
            Logger.getLogger(Ventana_AlbumController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    

    @FXML
    private void crearAlbum(MouseEvent event) {
    }

    @FXML
    private void editarNombreAlbum(MouseEvent event) {
    }

    @FXML
    private void editarDescripcionAlbum(MouseEvent event) {
    }

    @FXML
    private void importarFoto(MouseEvent event) {
    }

    @FXML
    private void eliminarAlbum(MouseEvent event) {
    }

    @FXML
    private void listaAlbumesEdicion(MouseEvent event) {
    }
    
}
