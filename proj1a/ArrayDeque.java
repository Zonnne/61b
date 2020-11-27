public class ArrayDeque {
    int[] a;
    int size;
    int nextFirst, nextLast;
    public ArrayDeque() {
        int size = 0 ;
        a = new int[8];
        nextFirst = 6;
        nextLast = 0;
    }
    public ArrayDeque(ArrayDeque other) {
        int[] a = new int[other.a.length];
        int size = other.size;
        nextFirst = other.nextFirst;
        nextLast = other.nextLast;
        System.arraycopy(other.a, 0, a, 0, other.a.length);
    }
    public void resize() {
        double RF = (size/a.length) < 0.25 ? 0.5 : 2;
        int rf = (int)RF;
        int[] b = new int[size*rf];
        System.arraycopy(a, 0, b, 0, size);
        a = b;
    }
    public void addFirst(int x) {
        if (nextLast == nextFirst) {
            resize();
        }
        a[nextFirst] = x;
        nextFirst = (nextFirst - 1) % a.length;
        size += 1;
    }
    public void addLast(int x) {
        if (nextLast == nextFirst) {
            resize();
        }
        a[nextLast] = x;
        nextLast = (nextLast + 1) % a.length;
        size += 1;
    }
    public boolean isEmpty() {
        return (size == 0);
    }

    public int size() {
        return size;
    }

    public void printDeque() {
        for(int i = (nextFirst + 1) % a.length; i < size; i = (i + 1) % a.length) {
            System.out.print(a[i] + " ");
        }
        System.out.println("\n");
    }

    public Integer removeFirst() {
        if (size == 0) {
            return null;
        }
        int temp = a[0];
        nextFirst = (nextFirst + 1) % a.length;
        size -= 1;
        if (size/a.length < 0.25) {
            resize();
        }
        return temp;
    }
    public Integer removeLast() {
        if (size == 0) {
            return null;
        }
        int temp = a[size - 1];
        nextLast = (nextLast - 1) % a.length;
        size -= 1;
        if (size/a.length < 0.25) {
            resize();
        }
        return temp;
    }

    public Integer get(int index) {
        if (size == 0 || size < index) {
            return null;
        }
        return a[(nextFirst + 1 + index) % a.length];
    }

    public static void main(String[] args) {
        ArrayDeque x = new ArrayDeque();
        x.addLast(2);
        x.removeFirst();
        x.addFirst(1);
        x.addFirst(3);
        x.addLast(3);
        ArrayDeque y = new ArrayDeque(x);
        x.removeLast();
        x.printDeque();
        y.printDeque();
        //int size = x.size();
        System.out.println(x.get(0));
    }

}
