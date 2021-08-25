public class Parser {
    private TaskList tasks;

    public Parser(TaskList tasks) {
        this.tasks = tasks;
    }

    public int parse(String command) throws DukeException {
        String[] commandSplit = command.split(" ", 2);
        String commandWord = commandSplit[0].toLowerCase();
        String commandDesc = "";
        if (commandSplit.length == 2) {
            commandDesc = commandSplit[1];
        }

        if (commandWord.equals("done")) {
            if (commandDesc.equals("")) {
                throw new DukeException("\t☹ OOPS!!! Please specify the task number for the task " +
                        "you want to complete.");
            }
            try {
                int taskNumber = Integer.parseInt(commandDesc);
                this.tasks.markDone(taskNumber);
            } catch (NumberFormatException e) {
                System.out.println("\t☹ OOPS!!! Please input a task number instead.");
            }

        } else if (commandWord.equals("list")) {
            this.tasks.listTasks();

        } else if (commandWord.equals("todo")) {
            if (commandDesc.equals("")) {
                throw new DukeException("\t☹ OOPS!!! The description of a todo cannot be empty.");
            }
            this.tasks.addTask(new ToDo(commandDesc));

        } else if (commandWord.equals("deadline")) {
            if (commandDesc.equals("")) {
                throw new DukeException("\t☹ OOPS!!! The description of a deadline cannot be empty.");
            }
            String[] commandDescSplit = commandDesc.split("/by");
            this.tasks.addTask(new Deadline(commandDescSplit[0], commandDescSplit[1]));

        } else if (commandWord.equals("event")) {
            if (commandDesc.equals("")) {
                throw new DukeException("\t☹ OOPS!!! The description of a event cannot be empty.");
            }
            String[] commandDescSplit = commandDesc.split("/at");
            this.tasks.addTask(new Event(commandDescSplit[0], commandDescSplit[1]));

        } else if (commandWord.equals("delete")) {
            if (commandDesc.equals("")) {
                throw new DukeException("\t☹ OOPS!!! Please specify the task number for the task " +
                        "you want to delete.");
            }
            try {
                int taskNumber = Integer.parseInt(commandDesc);
                this.tasks.deleteTask(taskNumber);
            } catch (NumberFormatException e) {
                System.out.println("\t☹ OOPS!!! Please input a task number instead.");
            }
        } else if (commandWord.equals("bye")) {
            return 1;
        } else {
            throw new DukeException("\t☹ OOPS!!! You have entered an invalid command, please try again!");
        }
        return 0;
    }
}
