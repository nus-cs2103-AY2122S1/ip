public class Parser {
    private static String getMetadata(String command) throws IrisException {
        String[] splitted = command.split(" ", 2);
        if (splitted.length == 1 || splitted[1].equals("")) {
            // TODO: make this error message more specific?
            throw new IrisException("The description cannot be empty");
        } else {
            return splitted[1];
        }
    }

    private static int parseInt(String text) throws IrisException {
        try {
            return Integer.parseInt(text);
        } catch (NumberFormatException exception) {
            throw new IrisException("Please enter a valid integer");
        }
    }

    public static void handleCommand(String command, TaskList taskList) throws IrisException {
        handleCommand(command, taskList, false);
    }

    public static void handleCommand(String command, TaskList taskList, boolean silent) throws IrisException {
        if (command.equals("list")) {
            Ui.listTasks(taskList);
        } else if (command.startsWith("done")) {
            Task task = taskList.done(parseInt(getMetadata(command)));
            if (!silent) Ui.say(String.format("Good job! I've marked this task as done: %s", task));
        } else if (command.startsWith("delete")) {
            Task task = taskList.delete(parseInt(getMetadata(command)));
            if (!silent) {
                Ui.say("Noted. I've removed this task:");
                Ui.say(task.toString(), false);
                int count = taskList.getCount();
                Ui.say(String.format("Now you have %d %s in the list.",
                        count, count == 1 ? "task" : "tasks"), false);
            }
        } else if (command.startsWith("todo")) {
            taskList.addTodo(getMetadata(command));
            if (!silent) Ui.sayTaskAdded(taskList);
        } else if (command.startsWith("deadline")) {
            String[] splitted = getMetadata(command).split(" /by ");
            if (splitted.length != 2) throw new IrisException("deadline should have 2 arguments: a name and a time");
            taskList.addDeadline(splitted[0], splitted[1]);
            if (!silent) Ui.sayTaskAdded(taskList);
        } else if (command.startsWith("event")) {
            String[] splitted = getMetadata(command).split(" /at ");
            if (splitted.length != 2) throw new IrisException("event should have 2 arguments: a name and a time");
            taskList.addEvent(splitted[0], splitted[1]);
            if (!silent) Ui.sayTaskAdded(taskList);
        } else {
            throw new IrisException("I'm sorry, but I don't know what that means.");
        }
    }
}
