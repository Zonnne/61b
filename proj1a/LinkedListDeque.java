public class LinkedListDeque<T> {
    private StuffNode sentinel;
    private int size;
    public class StuffNode {
        private T item;
        private StuffNode next;
        private StuffNode previous;
        private StuffNode(T x, StuffNode n, StuffNode p) {
            item = x;
            next = n;
            previous = p;
        }
    }
    public LinkedListDeque() {
        sentinel = new StuffNode(null, null, null);
        sentinel.previous = sentinel;
        sentinel.next = sentinel;
        size = 0;
    }
    /*public LinkedListDeque(T x) {
        sentinel = new StuffNode(x, null, null);
        sentinel.next = new StuffNode(x, sentinel, sentinel);
        sentinel.previous = sentinel.next;
        size = 1;
    }*/
    public LinkedListDeque(LinkedListDeque other) {
        sentinel = new StuffNode(null, null, null);
        sentinel.previous = sentinel;
        sentinel.next = sentinel;
        StuffNode p = other.sentinel.next;
        for (int i = 0; i < other.size; i++) {
            addLast(p.item);
            p = p.next;
        }
    }
    public void addFirst(T item) {
        sentinel.next = new StuffNode(item, sentinel.next, sentinel);
        sentinel.next.next.previous = sentinel.next;
        size += 1;
    }
    public void addLast(T item) {
        sentinel.previous = new StuffNode(item, sentinel, sentinel.previous);
        sentinel.previous.previous.next = sentinel.previous;
        size += 1;
    }
    public boolean isEmpty() {
        return (size == 0);
    }
    public int size() {
        return size;
    }
    public void printDeque() {
        StuffNode p = sentinel.next;
        for (int i = 1; i < size; i++) {
            System.out.print(p.item + " ");
            p = p.next;
        }
        System.out.println(p.item + "\n");
    }
    public T removeFirst() {
        if (size == 0) {
            return null;
        }
        T front = sentinel.next.item;
        sentinel.next = sentinel.next.next;
        sentinel.next.previous = sentinel;
        size -= 1;
        return front;
    }
    public T removeLast() {
        if (size == 0) {
            return null;
        }
        T last = sentinel.previous.item;
        sentinel.previous = sentinel.previous.previous;
        sentinel.previous.next = sentinel;
        size -= 1;
        return last;
    }

    public T get(int index) {
        if (index > size - 1) {
            return null;
        }
        StuffNode p = sentinel.next;
        for (int i = 0; i < index; i++) {
            p = p.next;
        }
        return p.item;
    }
    public T getRecursive(int index) {
        if (index > size - 1) {
            return null;
        }
        LinkedListDeque<T> copy = new LinkedListDeque(this);
        if (index == 0) {
            return copy.sentinel.next.item;
        }
        copy.removeFirst();
        return copy.getRecursive(index - 1);
    }
    /* public static void main(String[] args) {
        LinkedListDeque<Integer> x = new LinkedListDeque(11);
        x.addLast(2);
        x.removeFirst();
        x.addFirst(1);
        x.addFirst(3);
        x.addLast(3);
        LinkedListDeque<Integer> y = new LinkedListDeque(x);
        x.removeLast();
        x.printDeque();
        y.printDeque();
        //int size = x.size();
        System.out.println(x.get(0));
        System.out.println(x.getRecursive(2));
    } */
}
