class Loader {
    private final int id;
    private final boolean recycled;

    Loader(int id) {
        this.id = id;
        this.recycled = false;
    }

    Loader(int id, boolean recycled) {
        this.id = id;
        this.recycled = recycled;
    }

    @Override
    public String toString() {
        return (recycled == true ? String.format("Recycled Loader #%d", this.id)
                : String.format("Loader #%d", this.id));
    }

    public int getID() {
        return this.id;
    }
}
