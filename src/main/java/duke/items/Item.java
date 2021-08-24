package duke.items;

public abstract class Item {
    private String name;
    private boolean isDone = false;

    public Item(String name) {
        this.name = name;
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public String getDone() {
        return (isDone ? "X" : "O");
    }

    public String getPickle() {
        return this.name;
    }

    @Override
    public String toString() {
        return (isDone ? "[X] " : "[] ") + this.name;
    }
}
