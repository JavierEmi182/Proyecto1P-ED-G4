package TDAs;


// estudiante Javier Vergara

public class DoubleCircularLinkedList<E> implements List<E> {

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
        if (index==0){
            addFirst(element);
        }
        if(index==size){
            addLast(element);
        }
        
        int i=0;
        NodeList<E> nuevo =  new NodeList<>(element);
        
        for (NodeList<E> n=first; n != null; n = n.getNext()) {
            if(i==index-1){
                nuevo.setNext(n.getNext());
                n.setNext(nuevo);
                size++;
            }
        }
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
                        E e =n.getNext().getContent();
                        n.setNext(n.getNext().getNext());;
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
        if(index>=size){
            System.out.println("Valor fuera de los limites");
            return null;
        }
        else{
        for(NodeList<E> n=first;n !=null;n=n.getNext()){
            if(i==index){
                return n.getContent();
            }
        }
        return null;
    }}

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

    @Override
    public String toString() {
        String s = "";
        NodeList<E> t;
        for (t = this.getFirst(); t != null; t = t.getNext()) {
            s += t.getContent() + " ";
        }
        return s;
    }

}
