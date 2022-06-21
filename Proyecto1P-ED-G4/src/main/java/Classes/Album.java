/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Classes;

import Classes.Photo;
import TDAs.DoubleCircularLinkedList;
import java.time.LocalDate;

/**
 *
 * @author Javier
 */
public class Album {
    String nombre;
    DoubleCircularLinkedList<Photo> fotos;
    LocalDate fechaCreacion;
    
    //getters and setters

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public DoubleCircularLinkedList<Photo> getFotos() {
        return fotos;
    }

    public void setFotos(DoubleCircularLinkedList<Photo> fotos) {
        this.fotos = fotos;
    }

    public LocalDate getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDate fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }
    
    
    //constructor

    public Album(String nombre, DoubleCircularLinkedList<Photo> fotos) {
        this.nombre = nombre;
        this.fotos = fotos;
        this.fechaCreacion=LocalDate.now();
    }
    
    //metodos
    public void agregarImagen(Photo foto){
        fotos.addLast(foto);
    }
    
    public boolean eliminarImagen(Photo foto){
       for(Photo f:fotos){   //revisar el iterable
            if(f.equals(foto)){
                int i=fotos.getIndex(f);
                fotos.remove(i);
                return true;
            }
        }
        return false;
    }
    //return false;}
}
