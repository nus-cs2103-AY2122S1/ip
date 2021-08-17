abstract class Item {
    private String name;
    private boolean isDone = false;

    public void markAsDone() {
        this.isDone = true;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return (isDone ? "[X] " : "[] ") + this.name;
    }
}
