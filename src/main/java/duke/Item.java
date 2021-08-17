package duke;

abstract class Item {
    private String name;
    private boolean isDone = false;

    public Item(String[] strings) {
        this.checkInput(strings);
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void checkInput(String[] strings) {
        if (strings.length == 1) {
            throw new DukeException("I need more than just the command");
        }
    }

    @Override
    public String toString() {
        return (isDone ? "[X] " : "[] ") + this.name;
    }
}
