package TDAs;

import java.util.Iterator;


// estudiante Javier Vergara

public class DoubleCircularLinkedList<E> implements Iterable<E>, List<E>  {

    private NodeList<E> first;
    private NodeList<E> last;

    public DoubleCircularLinkedList() {
        this.first = null;
        this.last = null;
    }

    public NodeList<E> getFirst() {
        return first;
    }

    public void setFirst(NodeList<E> first) {
        this.first = first;
    }

    public NodeList<E> getLast() {
        return last;
    }

    public void setLast(NodeList<E> last) {
        this.last = last;
    }

    @Override
    public boolean addFirst(E e) {
        if (e == null) {
            return false;
        }
        NodeList<E> nuevo = new NodeList<>(e);
        nuevo.setNext(this.getFirst());
        nuevo.setPrevious(this.getLast());
        if (this.isEmpty()) {
            this.setLast(nuevo);
        }
        this.setFirst(nuevo);
        return true;
    }

    @Override
    public boolean addLast(E e) {
        if (e == null) {
            return false;
        }
        NodeList<E> nuevo = new NodeList<>(e);
        if (this.isEmpty()) {
            this.setFirst(nuevo);
        } else {
            this.getLast().setNext(nuevo);
            this.getFirst().setPrevious(nuevo);
        }

        this.setLast(nuevo);

        return true;
    }

    @Override
    public E removeFirst() {
        if(isEmpty()){
            return null;
        } 
        E e;
        
        if(first==last){
            e=first.getContent();
            first=null;
            last= null;
        }
        else{
            NodeList<E> n=first;
            first= first.getNext();
            this.getLast().setPrevious(first);
            e=n.getContent();
            n.setNext(null);
        }
        return e;
        }
        
    

    @Override
    public E removeLast() {
        if (isEmpty())
            return null;
        E e;
        if (first==last){ //only 1 element
            e=first.getContent();
            first=null;
            last=null;
        }
        else{
            e=last.getContent();
            NodeList <E> n=first;
            while (n.getNext()!= last)
                n=n.getNext();
            last=n;
            last.setNext(first);
        }
        return e;
    }

    @Override
    public int size() {
        int cont = 0;
        NodeList<E> t;
        for (t = this.getFirst(); t != null; t = t.getNext()) {
            cont++;
        }
        return cont;
    }

    @Override
    public boolean isEmpty() {
        return this.first == null && this.last == null;
    }

    @Override
    public void clear() {
        if(isEmpty()){
            System.out.println("Linked List vacia");
        }
        while(!isEmpty()){
            removeFirst();
        }
    }

    @Override
    public void add(int index, E element) {
        int size =size();
        if (element==null || index<0 || index>size)
            System.out.println("No se puede realizar dicha acción");
        else if (index==0){
            addFirst(element);
        }
        else if(index==size){
            addLast(element);
        }else{
        
        int i=0;
        NodeList<E> nuevo =  new NodeList<>(element);
        
        for (NodeList<E> n=first; n != null; n = n.getNext()) {
            if(i==index-1){
                NodeList<E> n1= n.getNext();  //donde añado
                nuevo.setNext(n1);        //seteo como siguiente n1 en nuevo
                nuevo.setPrevious(n);      //seteo como anterior n en nuevo
                n1.setPrevious(nuevo);         //seteo como anterior de n1 el nuevo
                n.setNext(nuevo);          //seteo como el siguiente de n el nuevo
                i++;
            }
        }}
    }

    @Override
    public E remove(int index) {
        if(size()==0) {
    		System.out.println("Lista vacia");
    		return null;
    	}
    	if(index<size()) {
    		if(index==0) {
                    return removeFirst();
    		}
    		if(index==size()-1) {
                    return removeLast();
    		}
                
                else{
                        int i =0;
                    for (NodeList<E> n=first; n != null; n = n.getNext()) {
                        if(i==index-1){
                        E e =n.getNext().getContent();    //guardo el contenido del eliminado
                        NodeList<E> eliminado = n.getNext();   //guardo el eliminado ennodo
                        n.setNext(eliminado.getNext());         //seteo como siguiente el siguiente del eliminado
                        eliminado.getNext().setPrevious(n);    //seteo como el anterior del sgt del eliminado a n

                        i++;
                        return e;
    		}
    		
    	}}
    }   return null;
}

    @Override
    public E get(int index) {
        int size=size();
        int i =0;
        
        if(isEmpty()){
            System.out.println("Lista vacia");
            return null;
        }
        else if(index>=size){
            System.out.println("Valor fuera de los limites");
            return null;
        }
        else{
        for(NodeList<E> n=first;n !=null;n=n.getNext()){
            if(i==index){
                return n.getContent();
            }
        }
        
    }return null;
    }

    @Override
    public E set(int index, E element) {
        
        if(index<0){
            System.out.println("Indice no permitido");
            return null;       
        }
        if(index>=size()){
            System.out.println("Indice no permitido");
            return null; 
        }else{
        int i =0;
        for(NodeList<E> n=first;n !=null;n=n.getNext()){
            if(i==index){
                n.setContent(element);
                return n.getContent();   // elemento reemplazado
            }
            i++;
        }
        return null;  
    }}
    
    public int getIndex(E e){
        int i=0;
        for(NodeList<E> n=first;n !=null;n=n.getNext()){
            if(n.equals(e)){
                return i;
            }
            i++;
        }
        return i;
    }

    @Override
    public String toString() {
        String s = "";
        NodeList<E> t;
        for (t = this.getFirst(); t != null; t = t.getNext()) {
            s += t.getContent() + " ";
        }
        return s;
    }

    @Override
    public  Iterator<E> iterator() {
        Iterator<E> it = new Iterator<E>(){
            NodeList<E> cursor=first;
            @Override
            public boolean hasNext() {
                return cursor!=last;
            }

            @Override
            public E next() {
                E e =cursor.getContent();
                cursor =cursor.getNext();
                return e;  //retorno el anterior
            }

        };
        return it;      
    };

}
