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
import java.io.InputStream;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.CustomMenuItem;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TabPane;
import javafx.scene.control.TitledPane;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.ContextMenuEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author ERWIN AURIA
 */
public class Ventana_AlbumController implements Initializable {

    @FXML
    private TreeView<String> list_Album;
    @FXML
    private Label nombre_Album;
    @FXML
    private Label descripcion_Album;
    //private ScrollPane tablero_Fotos;
    @FXML
    private AnchorPane tablero;
    @FXML
    private VBox tabliew;
    @FXML
    private TreeView<?> list_Album1;
    @FXML
    private Label lbl_NombreAlbum;
    @FXML
    private Label lbl_DescripcionAlbum;
    @FXML
    private Button btn_EditNombreA;
    @FXML
    private Button btn_EditDescripcionA;
    @FXML
    private AnchorPane tablero1;
    @FXML
    private VBox tabliew1;
    @FXML
    private Button btn_CrearAlbum;
    @FXML
    private Button btn_ImportarFoto;
    @FXML
    private MenuButton comboBox_Filtro;


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        try {
            int c=0;
            TreeItem rootItem = new TreeItem("Albumes   "+ "(" + c + ")");
            DoubleCircularLinkedList<Photo> fotos = leerFotos("/archivos/fotosUsuario.txt");
            DoubleCircularLinkedList<Album> albumes = leerAlbumes("/archivos/albumesUsuario.txt",fotos);
           
            for (NodeList<Album> t = albumes.getFirst(); !t.getNext().equals(albumes.getFirst()); t = t.getNext()) {
                
                TreeItem item = new TreeItem(t.getContent().getNombre());
                rootItem.getChildren().add(item);
                c++;     
            }
            list_Album.setRoot(rootItem); 
            
        } catch (IOException ex) {
            Logger.getLogger(Ventana_AlbumController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        CheckBox cb0 = new CheckBox("Personas");  
        CustomMenuItem item0 = new CustomMenuItem(cb0);  
        CheckBox cb1 = new CheckBox("Lugar");  
        CustomMenuItem item1 = new CustomMenuItem(cb1);  
        item0.setHideOnClick(false);  
        item1.setHideOnClick(false);  
        comboBox_Filtro.getItems().setAll(item0,item1);


      
    }   

    @FXML
    private void mouseClickSelected(MouseEvent event) throws IOException {
        TreeItem item_Seleccionado = list_Album.getSelectionModel().getSelectedItem();
        TreeItem item_Root= list_Album.getTreeItem(0);
        
        if(item_Seleccionado != item_Root){
            tabliew.getChildren().removeAll();   
            DoubleCircularLinkedList<Photo> fotos = leerFotos("/archivos/fotosUsuario.txt");
            DoubleCircularLinkedList<Album> albumes = leerAlbumes("/archivos/albumesUsuario.txt",fotos);
            //VBox vboxPhoto = new VBox();
            DoubleCircularLinkedList<Photo> fotosAlbum = null;
           
            for (NodeList<Album> t = albumes.getFirst(); !t.getNext().equals(albumes.getFirst()); t = t.getNext()) {
                if(t.getContent().getNombre().equals(item_Seleccionado.getValue().toString()) ){
                    nombre_Album.setText(t.getContent().getNombre()); 
                    fotosAlbum = t.getContent().getFotos();
                    
                    for (Photo p :fotos){
                        Label lnombre = new Label(p.getInfo());
                        tabliew.getChildren().add(lnombre);
                    }  
                    
                } 
              
            }
            
        }   
    }

    @FXML
    private void mouseClickSelected(ContextMenuEvent event) {
    }



    
}


/*//descripcion_Album.setText(t.getContent());
                    fotosAlbum  = t.getContent().getFotos();  
                    for (Photo p :fotosAlbum){  
                        VBox vboxPhoto = new VBox();
                        InputStream inputImg= App.class.getResource("/fotoss/"+p.getRuta()).openStream();
                        ImageView imgv = new ImageView(new Image(inputImg));
                        imgv.setFitHeight(185);
                        imgv.setFitWidth(275);
                        vboxPhoto.getChildren().add(imgv);
                        Label lprecio = new Label(String.valueOf(p.getFecha()));
            vboxPhoto.getChildren().add(lprecio);
                        tabliew.getChildren().add(vboxPhoto);
                        //tablero_Fotos.getChildren().add(vboxPhoto);
                        
                    }
                }
            } 
            
            
            
           // tablero_Fotos.setContent(vboxPhoto);
        }
         
        
       // nombre_Album.setText(item_Seleccionado.getValue());    tablero_Fotos.setContent(vboxPhoto);   */