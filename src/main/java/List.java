class List extends Command {
    private TaskList list;

    private List(TaskList list) {
        this.list = list;
    }
    public static List of(TaskList list) {
        return new List(list);
    }

    public void exec() {
        this.list.display();
    }
}
