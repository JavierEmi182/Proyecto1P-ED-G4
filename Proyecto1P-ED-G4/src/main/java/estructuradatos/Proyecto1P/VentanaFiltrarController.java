/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package estructuradatos.Proyecto1P;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Javier
 */
public class VentanaFiltrarController implements Initializable {

    @FXML
    private TextField tfLugar;
    @FXML
    private TextField tfPersonas;
    @FXML
    private Button btnFiltrar;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void filtrarAlbum(ActionEvent event) {
        /*
        Ventana_a...albumfiltrado=Ventana....albumfiltrado.filtrarPorLugar(tfLugar.getContent());
        String[] personas=tfPersonas.split(",");
        for(String s:personas){
        Ventana.....albumfiltrado=Venta.....albumfiltrado.filtrarPorPersona(s);
        }
        cargarFotos(ventana....albumFiltrado); 
        */
        if(tfLugar.getText()!=null){
            System.out.println(Ventana_AlbumController.albumfiltrado.filtrarPorLugar(tfLugar.getText()));
        Ventana_AlbumController.albumfiltrado.setFotos(Ventana_AlbumController.albumfiltrado.filtrarPorLugar(tfLugar.getText()));
        }
        if(tfPersonas.getText()!=null){
            String[] personas=tfPersonas.getText().split(",");
            for(String p:personas){
                Ventana_AlbumController.albumfiltrado.setFotos(Ventana_AlbumController.albumfiltrado.filtrarPorPersona(p));
            }
            //cargarFotos
        }
        System.out.println(Ventana_AlbumController.albumfiltrado);
    }

    @FXML
    private void VolverVentanaAnterior(ActionEvent event) {
        Node node = (Node)event.getSource();
        Stage stage = (Stage)node.getScene().getWindow();
        stage.close();
        FXMLLoader f = new FXMLLoader(App.class.getResource("/fxml/Ventana_Album.fxml"));
            Parent root;
            try {
                root = f.load();
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
                root.autosize();
            } catch (IOException ex) {
                Logger.getLogger(Ventana_AlbumController.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
    
}
