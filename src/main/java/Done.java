class Done extends Command {
    private TaskList list;
    private int index;

    private Done(TaskList tasklist, int index) {
        this.list = tasklist;
        this.index = index;
    }

    public static Done of(TaskList tasklist, int index) {
        return new Done(tasklist, index);
    }

    public void exec() {
        this.list.get(this.index - 1).markAsDone();
    }
}
