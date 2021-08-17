class Add extends Command {

    private TaskList list;
    private Task task;

    private Add(TaskList list, Task task) {
        this.list = list;
        this.task = task;
    }

    public static Add of(TaskList list, String input) {
        if (input.startsWith("todo")) {
            return new Add(list, new Todo(input.substring(5)));
        } else if (input.startsWith("deadline")) {
            String[] arr = input.split(" /by");
            return new Add(list, new Deadline(arr[0].substring(9), arr[1]));
        } else if (input.startsWith("event")) {
            String[] arr = input.split("/at");
            return new Add(list, new Event(arr[0].substring(6), arr[1]));
        } else {
            throw new IllegalArgumentException();
        }
    }
    public void exec() {
        this.list.add(this.task);
        System.out.print("Got it. I've added this task:\n"
                + this.task.toString() + "\n"
                + "Now you have " + this.list.size() + " tasks in the list.");
    }

}
