package duke;

public abstract class Item {
    private String name;
    private boolean isDone = false;

    public Item(String name) {
        this.name = name;
    }

    public void markAsDone() {
        this.isDone = true;
    }

    @Override
    public String toString() {
        return (isDone ? "[X] " : "[] ") + this.name;
    }
}
