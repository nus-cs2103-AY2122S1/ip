class Add extends Command {

    private TaskList list;
    private Task val;

    private Add(TaskList list, Task val) {
        this.list = list;
        this.val = val;
    }

    public static Add of(TaskList list, Task val) {
        return new Add(list, val);
    }
    public void exec() {
        System.out.print("added: " + val.getDescription());
        this.list.add(this.val);
    }

}
