class Delete extends Command {
    private TaskList tasklist;
    private int index;

    private Delete(TaskList tasklist, int index) {
        this.tasklist = tasklist;
        this.index = index;
    }

    public static Delete of(TaskList tasklist, int index) {
        return new Delete(tasklist, index);
    }

    public void exec() {
        Task temp = this.tasklist.get(index - 1);
        this.tasklist.delete(index);
        System.out.println("Noted. I've removed this task:\n"
                            + temp.toString() + "\n"
                            + "Now you have " + this.tasklist.size() + " tasks in the list.");
    }
}