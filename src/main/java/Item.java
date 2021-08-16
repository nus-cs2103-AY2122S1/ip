public class Item {
    private String name;
    private boolean isDone;

    public Item(String name) {
        this.name = name;
        this.isDone = false;
    }

    @Override
    public String toString() {
        return (isDone ? "[X] " : "[] ") + this.name;
    }
}
