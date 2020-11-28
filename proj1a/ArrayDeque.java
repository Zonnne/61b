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
        while (x < 0) {
            x += a.length;
        }
        return x % a.length;
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
            System.arraycopy(a, circularResidue(nextFirst + 1), b, circularResidue(nextFirst + 1),
                    size - circularResidue(nextFirst + 1));
            System.arraycopy(a, 0, b, size, circularResidue(nextFirst + 1));
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

    private static void main(String[] args) {
        ArrayDeque<Integer> x = new ArrayDeque();
        for (int i = 1; i < 34; i += 1) {
            x.addLast(i);
        }
        x.printDeque();
        System.out.println(x.removeFirst());
        System.out.println(x.removeLast());
    }

}
