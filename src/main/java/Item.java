public class Item {
    private String name;
    private boolean done;

    public Item(String name) {
        this.name = name;
        this.done = false;
    }

    public void markAsDone() {
        this.done = true;
    }

    @Override
    public String toString() {
        return "[" + (this.done ? "X" : " ") + "] " + this.name;
    }
}
