/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package estructuradatos.Proyecto1P;

import Classes.Album;
import Classes.Photo;
import TDAs.DoubleCircularLinkedList;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
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
        try {
            Photo girasol = new Photo("girasol.jpg",null,"Es un girasol");
            Photo mariposa = new Photo("mariposa.jpg",null,"Es un mariposa");

            DoubleCircularLinkedList<Photo> fotos = new DoubleCircularLinkedList<>();
            fotos.addFirst(girasol);
            fotos.addFirst(mariposa);
            for (Photo p :fotos){
               VBox vboxmenu = new VBox();
               vboxmenu.setAlignment(Pos.CENTER);
               //la imagen
               System.out.println(p.getRuta());
               System.out.println("Ruta " +App.class.getResource(""));
                InputStream inputImg= App.class.getResource("/"+p.getRuta()).openStream();
                ImageView imgv = new ImageView(new Image(inputImg));
                vboxmenu.getChildren().add(imgv);
                
                //el nombre de la pelicula
                Label lnombre = new Label(p.getInfo());
              
                vboxmenu.getChildren().add(lnombre);
                //anio
                Label lprecio = new Label(String.valueOf(p.getFecha()));
                 vboxmenu.getChildren().add(lprecio);
                  vboxmenu.setPadding(new Insets(2,3,3,4));
                  
               fpEspacio.getChildren().add(vboxmenu);
               
            }
            
            //si el archivo de pedido es diferente de null se lo carga
        } catch (IOException ex) {
            System.out.println("algo paso");
            ex.printStackTrace();
        }
    }
}
        
        
        
    

