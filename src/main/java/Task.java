class Task {
    protected String content;
    protected boolean isDone;
    Task(String content) {
        this.content = content;
        this.isDone = false;
    }

    Task(String content, boolean isDone) {
        this.content = content;
        this.isDone = isDone;
    }

    public void setDone() {
        this.isDone = true;
    }

    public String encoding() {
        return (isDone ? "Done" : "InProgress") + "&&" + content;
    }

    static public Task decoding(String description) {
        String[] strings = description.split("&&");

        switch (strings[0]) {
        case "T":
            return new Todo(strings[2], strings[1].equals("Done"));
        case "D":
            return new Deadline(strings[2], strings[3], strings[1].equals("Done"));
        case "E":
            return new Event(strings[2], strings[3], strings[1].equals("Done"));
        default:
            return null;
        }
    }

    @Override
    public String toString() {
        return String.format("[%s] %s", isDone ? "X":" ", this.content);
    }
}
