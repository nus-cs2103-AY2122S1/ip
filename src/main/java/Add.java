class Add extends Command {

    private TaskList list;
    private Task task;

    private Add(TaskList list, Task task) {
        this.list = list;
        this.task = task;
    }

    public static Add of(TaskList list, String input) throws IllegalCommandException, EmptyDescriptionException {
        if (input.startsWith("todo")) {
            String temp = input.substring(4);
            if (temp.length() == 0) {
                String message = "OOPS!!! The description of a todo cannot be empty.";
                throw new EmptyDescriptionException(message);
            }
            return new Add(list, new Todo(temp.substring(1)));
        } else if (input.startsWith("deadline")) {
            String[] arr = input.split(" /by");
            return new Add(list, new Deadline(arr[0].substring(9), arr[1]));
        } else if (input.startsWith("event")) {
            String[] arr = input.split("/at");
            return new Add(list, new Event(arr[0].substring(6), arr[1]));
        } else {
            throw new IllegalCommandException("OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }
    public void exec() {
        this.list.add(this.task);
        System.out.println("Got it. I've added this task:\n"
                + this.task.toString() + "\n"
                + "Now you have " + this.list.size() + " tasks in the list.");
    }

}
