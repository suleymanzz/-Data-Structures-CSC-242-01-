import java.util.Comparator;
public class DoubleLinkedList<T> implements ListInterface<T> {

    private DoubleNode<T> head;
    private int itemCount;
    private Finger<T>[] fingers;

    public DoubleLinkedList() {
        head = null;
        itemCount = 0;
        fingers = (Finger<T>[]) new Finger[2];

        for (int i = 0; i < fingers.length; i++) {
            fingers[i] = new Finger<T>();
        }
    }

    @Override
    public boolean isEmpty() {
        return itemCount == 0;
    }

    @Override
    public int getLength() {
        return itemCount;
    }

    @Override
    public boolean insert(int position, T item) {
        if (position < 1 || position > itemCount + 1) {
            return false;
        }

        DoubleNode<T> newNode = new DoubleNode<T>(item);

        if (position == 1) {
            newNode.setNext(head);
            newNode.setPrev(null);

            if (head != null) {
                head.setPrev(newNode);
            }

            head = newNode;
        } else {
            DoubleNode<T> before = getNodeAt(position - 1);
            DoubleNode<T> after = before.getNext();

            newNode.setNext(after);
            newNode.setPrev(before);
            before.setNext(newNode);

            if (after != null) {
                after.setPrev(newNode);
            }
        }

        itemCount++;
        updateFinger();
        return true;
    }

    @Override
    public T remove(int position) {
        if (position < 1 || position > itemCount || head == null) {
            return null;
        }

        T removedValue;

        if (position == 1) {
            removedValue = head.getItem();
            head = head.getNext();

            if (head != null) {
                head.setPrev(null);
            }
        } else {
            DoubleNode<T> before = getNodeAt(position - 1);
            DoubleNode<T> removeNode = before.getNext();
            DoubleNode<T> after = removeNode.getNext();

            removedValue = removeNode.getItem();
            before.setNext(after);

            if (after != null) {
                after.setPrev(before);
            }
        }

        itemCount--;
        updateFinger();
        return removedValue;
    }

    @Override
    public void clear() {
        head = null;
        itemCount = 0;
        updateFinger();
    }

    @Override
    public T getEntry(int position) throws IndexOutOfBoundsException {
        if (position < 1 || position > itemCount) {
            throw new IndexOutOfBoundsException();
        }

        return getNodeAt(position).getItem();
    }

    @Override
    public T replace(int position, T entry) throws IndexOutOfBoundsException {
        if (position < 1 || position > itemCount) {
            throw new IndexOutOfBoundsException();
        }

        DoubleNode<T> current = getNodeAt(position);
        T oldValue = current.getItem();
        current.setItem(entry);
        return oldValue;
    }

    public int getIndexOf(T entry) {
        DoubleNode<T> current = head;
        int pos = 1;

        while (current != null) {
            if (current.getItem().equals(entry)) {
                return pos;
            }
            current = current.getNext();
            pos++;
        }

        return -1;
    }

    @Override
    public Object[] toArray() {
        if (itemCount == 0) {
            return null;
        }

        Object[] arr = new Object[itemCount];
        DoubleNode<T> current = head;
        int i = 0;

        while (current != null) {
            arr[i] = current.getItem();
            current = current.getNext();
            i++;
        }

        return arr;
    }

    private void updateFinger() {
        if (fingers == null) {
            return;
        }

        for (int i = 0; i < fingers.length; i++) {
            fingers[i] = new Finger<T>();
        }

        if (head == null || itemCount == 0) {
            return;
        }

        for (int i = 0; i < fingers.length; i++) {
            int index = ((i + 1) * itemCount) / (fingers.length + 1);

            if (index < 1) {
                index = 1;
            }

            DoubleNode<T> node = head;
            for (int j = 1; j < index; j++) {
                node = node.getNext();
            }

            fingers[i].setIndex(index);
            fingers[i].setNode(node);
        }
    }

    private Finger<T> getClosest(int idx) {
        Finger<T> closest = new Finger<T>(head, 1);
        int minDistance = Math.abs(idx - 1);

        for (int i = 0; i < fingers.length; i++) {
            if (fingers[i] != null && fingers[i].getNode() != null) {
                int distance = Math.abs(idx - fingers[i].getIndex());

                if (distance < minDistance) {
                    minDistance = distance;
                    closest = fingers[i];
                }
            }
        }

        return new Finger<T>(closest.getNode(), closest.getIndex());
    }

    private DoubleNode<T> getNodeAt(int position) {
        if (position < 1 || position > itemCount || head == null) {
            return null;
        }

        Finger<T> start = getClosest(position);
        DoubleNode<T> current = start.getNode();
        int currentIndex = start.getIndex();

        while (currentIndex < position) {
            current = current.getNext();
            currentIndex++;
        }

        while (currentIndex > position) {
            current = current.getPrev();
            currentIndex--;
        }

        return current;
    }

public void sort(Comparator<T> comp) {
    if (head == null || head.getNext() == null) {
        return;
    }

    DoubleNode<T> current = head;

    while (current != null) {
        DoubleNode<T> minNode = current;
        DoubleNode<T> nextNode = current.getNext();

        while (nextNode != null) {
            if (comp.compare(nextNode.getItem(), minNode.getItem()) < 0) {
                minNode = nextNode;
            }
            nextNode = nextNode.getNext();
        }

        T temp = current.getItem();
        current.setItem(minNode.getItem());
        minNode.setItem(temp);

        current = current.getNext();
    }

    updateFinger();
}






















    @Override
    public String toString() {
        if (isEmpty()) {
            return "Empty List.";
        }

        String result = "";
        DoubleNode<T> current = head;
        int pos = 1;

        while (current != null) {
            result += "[" + pos + "] " + current.getItem();

            if (current.getNext() != null) {
                result += "\n";
            }

            current = current.getNext();
            pos++;
        }

        return result;
    }
}