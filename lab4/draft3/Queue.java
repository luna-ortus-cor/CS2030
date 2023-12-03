class Queue<E> {
    private final ImList<E> q;
    private final int maxCap;

    Queue(int maxCap) {
        this.maxCap = maxCap;
        this.q = new ImList<E>();
    }

    Queue(int maxCap, ImList<E> q) {
        this.maxCap = maxCap;
        this.q = q;
    }

    int getMaxCap() {
        return this.maxCap;
    }

    int lenQ() {
        return this.q.size() <= this.maxCap ? this.q.size() : Integer.MAX_VALUE;
    }

    boolean isFull() {
        return this.q.size() >= this.maxCap;
    }

    boolean isEmpty() {
        return this.q.size() == 0;
    }

    Queue<E> push(E item) {
        return new Queue<E>(this.maxCap, this.q.add(item));
    }

    Queue<E> pop() {
        return new Queue<E>(this.maxCap, this.q.remove(0));
    }

    E getFront() {
        // should have asserted non empty queue
        return this.q.get(0);
    }

    @Override
    public String toString() {
        String out = "";
        for (int i = 0; i < this.q.size(); i++) {
            out += this.q.get(i).toString();
            out += ", ";
        }
        return out;
    }
}
