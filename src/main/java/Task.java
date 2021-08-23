class Task {
    protected String content;
    protected boolean isDone;
    Task(String content) {
        this.content = content;
        this.isDone = false;
    }

    public void setDone() {
        this.isDone = true;
    }

    public String encoding() {
        return (isDone ? "Done" : "InProgress") + "|" + content;
    }

/*    static public Task decoding(String description) {
        String
    }*/

    @Override
    public String toString() {
        return String.format("[%s] %s", isDone ? "X":" ", this.content);
    }
}
