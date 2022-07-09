/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Classes;

import Classes.Photo;
import TDAs.DoubleCircularLinkedList;
import java.time.LocalDate;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 *
 * @author Javier
 */
public class Album {
    String nombre;
    DoubleCircularLinkedList<Photo> fotos;
    LocalDate fechaCreacion;

    public Album() {
        this.nombre=null;
        this.fotos=null;
        this.fechaCreacion=null;
    }
  
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
    //constructor recibiendo un LocalDate
    public Album(String nombre, DoubleCircularLinkedList<Photo> fotosAlbum, LocalDate fecha) {
        this.nombre = nombre;
        this.fotos = fotosAlbum;
        this.fechaCreacion=fecha;
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
    
    //METODOS CON MAPAS
    public DoubleCircularLinkedList<Photo> filtrarPorLugar(String lugar){
        Map<String,DoubleCircularLinkedList<Photo>> fotosPorLugar=new LinkedHashMap<>();
        for(Photo f:this.getFotos()){
            fotosPorLugar.putIfAbsent(f.getLugar(), new DoubleCircularLinkedList<Photo>(f));
            fotosPorLugar.get(f.getLugar()).addFirst(f);
        }
        
        return fotosPorLugar.get(lugar);
    }
    
    public Map<String,Integer> contadorPorLugar(String lugar){
        Map<String,Integer> contadorPorLugar=new LinkedHashMap<>();
        for(Photo f:this.getFotos()){
            contadorPorLugar.putIfAbsent(f.getLugar(), 1);
            contadorPorLugar.put(f.getLugar(),contadorPorLugar.get(f.getLugar())+1);
        }
        
        return contadorPorLugar;
    }
    
    public DoubleCircularLinkedList<Photo> filtrarPorFecha(LocalDate fecha){
        Map<LocalDate,DoubleCircularLinkedList<Photo>> fotosPorFecha=new LinkedHashMap<>();
        for(Photo f:this.getFotos()){
            fotosPorFecha.putIfAbsent(f.getFecha(), new DoubleCircularLinkedList<Photo>(f));
            fotosPorFecha.get(f.getFecha()).addFirst(f);
        }
        
        return fotosPorFecha.get(fecha);
    }
    //entrando una doublelinked
    public DoubleCircularLinkedList<Photo> filtrarPorPersonas(DoubleCircularLinkedList<String> personas){
        Map<DoubleCircularLinkedList<String>,DoubleCircularLinkedList<Photo>> fotosPorPersonas=new LinkedHashMap<>();
        for(Photo f:this.getFotos()){
            fotosPorPersonas.putIfAbsent(f.getPersonas(), new DoubleCircularLinkedList<Photo>(f));
            fotosPorPersonas.get(f.getFecha()).addFirst(f);
        }
        
        return fotosPorPersonas.get(personas);
    }
    
    //entrando un string
    public DoubleCircularLinkedList<Photo> filtrarPorPersona(String persona){
        Map<String,DoubleCircularLinkedList<Photo>> fotosPorPersona=new LinkedHashMap<>();
        for(Photo f:this.getFotos()){
            for(String p:f.getPersonas()){
                fotosPorPersona.putIfAbsent(p, new DoubleCircularLinkedList<Photo>(f));
                fotosPorPersona.get(p).addFirst(f);
            }
            
        }
        
        return fotosPorPersona.get(persona);
    }
    
    public Map<String,Integer> contadorPorPersona(String lugar){
        Map<String,Integer> contadorPorPersona=new LinkedHashMap<>();
        for(Photo f:this.getFotos()){
            for(String p:f.getPersonas()){
                contadorPorPersona.putIfAbsent(p, 1);
                contadorPorPersona.put(p,contadorPorPersona.get(p)+1);
            }
        }
        return contadorPorPersona;
    }
    
    public DoubleCircularLinkedList<Photo> filtrarPorFavorito(Boolean favorito){
        Map<Boolean,DoubleCircularLinkedList<Photo>> fotosPorFavorito=new LinkedHashMap<>();
        for(Photo f:this.getFotos()){
            fotosPorFavorito.putIfAbsent(f.getFavorita(), new DoubleCircularLinkedList<Photo>(f));
            fotosPorFavorito.get(f.getFavorita()).addFirst(f);
        }
        
        return fotosPorFavorito.get(favorito);
    }
    
    public Map<Boolean,Integer> contadorPorFavorito(String lugar){
        Map<Boolean,Integer> contadorPorFavorito=new LinkedHashMap<>();
        for(Photo f:this.getFotos()){
                contadorPorFavorito.putIfAbsent(f.getFavorita(), 1);
                contadorPorFavorito.put(f.getFavorita(),contadorPorFavorito.get(f.getFavorita())+1);
        }
        return contadorPorFavorito;
    }
    
    /*
    public DoubleCircularLinkedList<Photo> filtrarPorPalabrasClaves(DoubleCircularLinkedList<String> palabrasClaves){
        Map<DoubleCircularLinkedList<String>,DoubleCircularLinkedList<Photo>> fotosPorPalabrasClaves=new LinkedHashMap<>();
        for(Photo f:this.getFotos()){
            fotosPorPalabrasClaves.putIfAbsent(f.getPalabrasClaves(), new DoubleCircularLinkedList<Photo>(f));
            fotosPorPalabrasClaves.get(f.getPalabrasClaves()).addFirst(f);
        }
        
        return fotosPorPalabrasClaves.get(palabrasClaves);
    }
    
    public DoubleCircularLinkedList<Photo> filtrarPorPalbraClave(String palabraClave){
        Map<String,DoubleCircularLinkedList<Photo>> fotosPorPalabraClave=new LinkedHashMap<>();
        for(Photo f:this.getFotos()){
            for(String pal:f.getPalabrasClave()){
                fotosPorPersona.putIfAbsent(pal, new DoubleCircularLinkedList<Photo>(f));
                fotosPorPersona.get(pal).addFirst(f);
            }
            
        }
        
        return fotosPorPalabraClave.get(palabraClave);
    }
    
    public Map<String,Integer> contadorPorPalbraClave(String lugar){
        Map<String,Integer> contadorPorPalbraClave=new LinkedHashMap<>();
        for(Photo f:this.getFotos()){
            for(String pal:f.getPalabrasClave()){
                contadorPorPalbraClave.putIfAbsent(pal, 1);
                contadorPorPalbraClave.put(pal,contadorPorPalbraClave.get(pal)+1);
            }
        }
        return contadorPorPalbraClave;
    }
    */
    
    
    //return false;}
    
    @Override
    public String toString() {
        return this.getNombre()+"= "+fotos;
    }
}
