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
import java.util.Comparator;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.CustomMenuItem;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.ScrollBar;
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
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author ERWIN AURIA
 */
public class Ventana_AlbumController implements Initializable {

    @FXML
    private TreeView<String> list_Album;
    @FXML
    private TreeView<String> list_Album1;
    @FXML
    private HBox hBoxImgeneral;
    @FXML
    private Label nombreAlbum;
    @FXML
    private Label descricpcionAlbum;
    @FXML
    private HBox hBoxImgeneral1;
    @FXML
    private Label nombreAlbum1;
    @FXML
    private Label descricpcionAlbum1;

    public Photo foto;
    @FXML
    private ScrollPane scrollpaneBiblio;
    @FXML
    private ScrollPane scrollpaneEdi;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        try {
            //"file:recursos/fotos/"+f.getRuta();
            DoubleCircularLinkedList<Photo> fotos = leerFotos("recursos/textos/fotosUsuario.txt");
            DoubleCircularLinkedList<Album> albumes = leerAlbumes("recursos/textos/albumesUsuario.txt", fotos);

            int c = albumes.size();

            TreeItem rootItem = new TreeItem("Albumes " + " (" + c + ")");
            if(c == 1){
                TreeItem item = new TreeItem(albumes.getFirst().getContent().getNombre());
                rootItem.getChildren().add(item);
            }
            
            for (Album a: albumes) {
                
                TreeItem item = new TreeItem(a.getNombre());
                rootItem.getChildren().add(item);
            }

            list_Album.setRoot(rootItem);
            list_Album1.setRoot(rootItem);

        } catch (IOException ex) {
            Logger.getLogger(Ventana_AlbumController.class.getName()).log(Level.SEVERE, null, ex);
        }

        /*CheckBox cb0 = new CheckBox("Personas");  
        CustomMenuItem item0 = new CustomMenuItem(cb0);  
        CheckBox cb1 = new CheckBox("Lugar");  
        CustomMenuItem item1 = new CustomMenuItem(cb1);  
        item0.setHideOnClick(false);  
        item1.setHideOnClick(false);  
        comboBox_Filtro.getItems().setAll(item0,item1);*/
    }

    @FXML
    private void listaAlbumes(MouseEvent event) throws IOException {
        TreeItem<String> item_Seleccionado = list_Album.getSelectionModel().getSelectedItem();
        TreeItem<String> item_Root = list_Album.getTreeItem(0);
        if (item_Seleccionado != item_Root) {
            nombreAlbum.setText(item_Seleccionado.getValue());
            mostrarImagenesXAlbum(item_Seleccionado.getValue());

        }
    }

    @FXML
    private void crearAlbum(MouseEvent event) {
        
    }

    public void mostrarImagenesXAlbum(String itemSeleccionado) throws IOException {

        DoubleCircularLinkedList<Photo> fotos = leerFotos("recursos/textos/fotosUsuario.txt");
        DoubleCircularLinkedList<Album> albumes = leerAlbumes("recursos/textos/albumesUsuario.txt", fotos);

        ScrollBar s = new ScrollBar();
        s.setMin(0);
        s.setMax(200);
        s.setValue(110);
        s.setOrientation(Orientation.VERTICAL);
        s.setUnitIncrement(12);
        s.setBlockIncrement(10);
        VBox vboxmenu = new VBox();

        /*Comparator<Album> cmpAlbum = new Comparator<>() {
            @Override
            public int compare(Album a1, Album a2) {
                return a1.getNombre().compareTo(a2.getNombre());

            }
        };*/
        
        int c = albumes.size();
        
        if(c == 1){
            if (itemSeleccionado.equals(albumes.getFirst().getContent().getNombre())) {
                albumes.getFirst().getContent().getFotos();
            }
            
        }

        for (Album a: albumes) {
            
            //System.out.println(a);
            
            if (itemSeleccionado.equals(a.getNombre())) {
                //t.getContent().getFotos()
                for (Photo p : a.getFotos()) {
                    System.out.println(p);

                    //if (p != null) {
                        //VBox vboxmenu = new VBox();   
                        Image i = new Image("file:recursos/fotos/" + p.getRuta());
                        
                        ImageView imgv = new ImageView(i);
                        imgv.setFitHeight(185);
                        imgv.setFitWidth(275);
                        vboxmenu.getChildren().add(imgv);
                        
                        //nombre
                        Label lnombre = new Label(p.getInfo());
                        vboxmenu.getChildren().add(lnombre);
                        
                        //anio
                        Label lprecio = new Label(String.valueOf(p.getFecha()));
                        vboxmenu.getChildren().add(lprecio);
                        vboxmenu.setPadding(new Insets(2, 3, 3, 4));
                        System.out.println("***");

                    //}
                }

            }

        }
        scrollpaneBiblio.setContent(vboxmenu);
        scrollpaneEdi.setContent(vboxmenu);

    }

    

    @FXML
    private void editarNombre_Album(MouseEvent event) {
    }

    @FXML
    private void editarDescrip_Album(MouseEvent event) {
    }


    @FXML
    private void eliminarAlbum(MouseEvent event) {
    }
    
    
    public void setPhoto(Photo foto) {
        this.foto = foto;
    }

    public Photo getPhoto() {
        return this.foto;
    }

    @FXML
    private void importarFoto(ActionEvent event) throws IOException {
        Node node = (Node)event.getSource();
        Stage stage = (Stage)node.getScene().getWindow();
        stage.close();
        FXMLLoader f = new FXMLLoader(App.class.getResource("/fxml/FileChooser.fxml"));
        //sendData();
        Parent root = f.load();
        //stage.setUserData(albumSeleccionado);
        Scene scene = new Scene(root);
        //Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
        root.autosize();    
    }

    @FXML
    private void listaAlbumesEdicion(MouseEvent event) throws IOException {
        TreeItem<String> item_Seleccionado = list_Album1.getSelectionModel().getSelectedItem();
        TreeItem<String> item_Root = list_Album1.getTreeItem(0);
        if (item_Seleccionado != item_Root) {
            nombreAlbum1.setText(item_Seleccionado.getValue());
            mostrarImagenesXAlbum(item_Seleccionado.getValue());

        }
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
