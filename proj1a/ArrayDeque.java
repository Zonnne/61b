public class ArrayDeque<T> {
    private T[] a;
    private int size;
    private int nextFirst, nextLast;
    public ArrayDeque() {
        size = 0;
        a = (T []) new Object[8];
        nextFirst = 6;
        nextLast = 7;
    }
    /*public ArrayDeque(ArrayDeque other) {
        a = (T []) new Object[other.a.length];
        size = other.size;
        nextFirst = other.nextFirst;
        nextLast = other.nextLast;
        System.arraycopy(other.a, 0, a, 0, other.a.length);
    } */
    private int circularResidue(int x) {
        return (x + a.length) % a.length;
    }
    private void resize() {
        T[] b;
        if ((size * 4) < a.length) {
            b = (T []) new Object[a.length / 2];
            for (int i = 0; i < size; i += 1) {
                b[i] = a[circularResidue(i + nextFirst + 1)];
            }
            nextLast = size;
            nextFirst = b.length - 1;
        }   else {
            b = (T []) new Object[a.length * 2];
            /*System.arraycopy(a, circularResidue(nextFirst + 1), b, circularResidue(nextFirst + 1),
                    size - circularResidue(nextFirst + 1));
            System.arraycopy(a, 0, b, size, circularResidue(nextFirst + 1));

             */
            for (int i = 0; i < size; i += 1) {
                b[nextFirst + 1 + i] = a[circularResidue(i + nextFirst + 1)];
            }
            nextLast += size;
        }
        a = b;
    }
    public void addFirst(T x) {
        if (size == a.length) {
            resize();
        }
        a[nextFirst] = x;
        nextFirst = circularResidue(nextFirst - 1);
        size += 1;
    }
    public void addLast(T x) {
        if (size == a.length) {
            resize();
        }
        a[nextLast] = x;
        nextLast = circularResidue(nextLast + 1);
        size += 1;
    }
    public boolean isEmpty() {
        return (size == 0);
    }

    public int size() {
        return size;
    }

    public void printDeque() {
        for (int i = 1; i < size + 1; i++) {
            System.out.print(a[circularResidue(nextFirst + i)] + " ");
        }
        System.out.println("\n");
    }

    public T removeFirst() {
        if (size == 0) {
            return null;
        }
        T temp = a[circularResidue(nextFirst + 1)];
        nextFirst = circularResidue(nextFirst + 1);
        size -= 1;
        if (((size * 4) < a.length) && size > 16) {
            resize();
        }
        return temp;
    }
    public T removeLast() {
        if (size == 0) {
            return null;
        }
        T temp = a[circularResidue(nextLast - 1)];
        nextLast = circularResidue(nextLast - 1);
        size -= 1;
        if (((size * 4) < a.length) && size > 16) {
            resize();
        }
        return temp;
    }

    public T get(int index) {
        if (size == 0 || size <= index) {
            return null;
        }
        return a[circularResidue(nextFirst + 1 + index)];
    }

    public static void main(String[] args) {
        ArrayDeque<Integer> x = new ArrayDeque();
        x.addFirst(0);
        x.addFirst(1);
        x.addLast(2);
        x.removeFirst();
        x.addFirst(4);
        x.addFirst(5);
        x.get(3);
        x.addFirst(7);
        x.addFirst(8);
        x.addLast(9);
        x.addLast(10);
        x.removeFirst();
        x.removeLast();
        x.removeFirst();
        x.removeLast();
        x.addFirst(15);
        x.addFirst(16);
        x.get(4);
        x.addFirst(18);
        x.addFirst(19);
        x.addLast(20);
        System.out.println(x.get(5));

    }

}
