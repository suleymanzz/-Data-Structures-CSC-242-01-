
public class DoubleNode<T> {

    private T item;
    private DoubleNode<T> next;
    private DoubleNode<T> prev;

    
     
    public DoubleNode() {
        this(null, null, null);
    }

   
    public DoubleNode(T item) {
        this(item, null, null);
    }


    public DoubleNode(T item, DoubleNode<T> next, DoubleNode<T> prev) {
        this.item = item;
        this.next = next;
        this.prev = prev;
    }


    public T getItem() {
        return item;
    }

    public void setItem(T item) {
        this.item = item;
    }

    public DoubleNode<T> getNext() {
        return next;
    }

    public void setNext(DoubleNode<T> next) {
        this.next = next;
    }

    public DoubleNode<T> getPrev() {
        return prev;
    }

    public void setPrev(DoubleNode<T> prev) {
        this.prev = prev;
    }

    @Override
    public String toString() {
        return item.toString();
    }
}