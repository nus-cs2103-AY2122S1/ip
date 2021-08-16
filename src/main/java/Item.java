public class Item {
    private String name;
    public int id;

    public Item(String name, int id) {
        this.name = name;
        this.id = id;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
