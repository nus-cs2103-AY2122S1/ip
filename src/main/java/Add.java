class Add extends Command {

    private TaskList list;
    private String val;

    private Add(TaskList list, String val) {
        this.list = list;
        this.val = val;
    }

    public static Add of(TaskList list, String val) {
        return new Add(list, val);
    }
    public void exec() {
        System.out.print("added: " + val);
        this.list.add(this.val);
    }

}
