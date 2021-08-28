public class AddCommand extends Command {

    private final boolean EXIT = false;
    private String taskType;
    private String taskInfo;

    private void checkDescription(String description, String type) throws NullDescription {
        int numOfCharacters =  type.length();
        if (description.length() <= numOfCharacters || description.split(" ").length < 2) {
            throw new NullDescription(type);
        }
    }

    private void verifyDate(String dateInfo, String type) throws InvalidDateFormat {
        switch(type) {
            case "deadline":
                if (dateInfo.split(" /by ").length != 2 ||
                        dateInfo.split(" /by ")[1].split(" ").length != 2) {
                    throw new InvalidDateFormat();
                };
                break;
            case "event":
                if (dateInfo.split(" /at ").length != 2 ||
                        dateInfo.split(" /at ")[1].split(" ").length != 2) {
                    throw new InvalidDateFormat();
                };
                break;
            default:
                break;
        }
    }

    public AddCommand(String input) throws NullDescription, InvalidDateFormat {
        this.taskType = input.split(" ")[0];

        switch (input.split(" ")[0]) {
            case "todo":
                checkDescription(input, "todo");
                this.taskInfo = input.substring(5);
                break;
            case "deadline":
                checkDescription(input, "deadline");
                this.taskInfo = input.substring(9);
                verifyDate(taskInfo, "deadline");
                break;
            case "event":
                checkDescription(input, "event");
                this.taskInfo = input.substring(6);
                verifyDate(taskInfo, "event");
                break;
            default:
                System.out.println("Something went wrong...");
                break;
        }
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) throws InvalidDateFormat {
        Task newTask = null;

        switch(taskType) {
            case "todo":
                newTask = new Todo(taskInfo, false);
                break;
            case "deadline":
                String deadlineDescription = taskInfo.split(" /by ")[0];
                String deadlineDate = taskInfo.split(" /by ")[1];
                newTask = new Deadline(deadlineDescription, deadlineDate, false);
                break;
            case "event":
                String eventDescription = taskInfo.split(" /at ")[0];
                String eventDate = taskInfo.split(" /at ")[1];
                newTask = new Event(eventDescription, eventDate, false);
                break;
            default:
                System.out.println("Something went wrong...");
        }

        tasks.addTask(newTask);
        storage.write(tasks.getTaskList());
        System.out.printf("\tGot it. I've added this task:\n" + "\t%s\n" + "\tNow you have %d tasks in the list.\n",
                newTask, tasks.size());
    }

    public boolean isExit() {
        return EXIT;
    }
}
