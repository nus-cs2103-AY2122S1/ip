public class Item {
    private String name;
    private boolean isDone = false;

    public Item(String[] strings) {
        this.name = strings[0];
    }

    public void markAsDone() {
        this.isDone = true;
    }

    @Override
    public String toString() {
        return (isDone ? "[X] " : "[] ") + this.name;
    }
}
