public class Parser {
    private TaskList taskList;

    public static Parser initialize(TaskList taskList) {
        return new Parser(taskList);
    }

    private Parser(TaskList taskList) {
        this.taskList = taskList;
    }

    /**
     * Returns reply message
     */
    public String parseCommand(String userInput) {
        if (userInput.equals("list")) {
            return String.format(
                    "Here are the tasks in your list:\n%s",
                    taskList.toString()
            );
        } else if (userInput.matches("done\s[0-9]{1,2}")) {
            //eg. done 12
            //limiting tasks from 0-99
            String inputBody = userInput.split(" ", 2)[1];
            int idxFrom0 = Integer.parseInt(inputBody) - 1;
            if (TaskList.isValidIndex(idxFrom0, taskList.length())) { //valid argument indexes
                taskList.toggleDone(idxFrom0);
                return String.format(
                        "Nice! I've marked this task as done:\n    %s",
                        taskList.get(idxFrom0).toString()
                );
            }
        } else if (userInput.matches("delete\s[0-9]{1,2}")) {
            //eg. delete 3
            String inputBody = userInput.split(" ", 2)[1];
            int idxFrom0 = Integer.parseInt(inputBody) - 1;
            if (TaskList.isValidIndex(idxFrom0, taskList.length())) { //valid argument indexes
                String reply = String.format(
                        "Noted. I've removed this task:\n    %s\nNow you have %d tasks in the list.",
                        taskList.get(idxFrom0).toString(),
                        taskList.length() - 1
                );
                taskList.removeTask(idxFrom0);
                return reply;
            }
        } else if (userInput.matches(ToDo.COMMAND_REGEX)) {
            //eg. todo read book
            String inputBody = userInput.split(" ", 2)[1];
            Task newTask = ToDo.of(inputBody);
            String reply = String.format(
                    "Got it! I've added this task:\n %s\nNow you have %d tasks in the list.",
                    newTask.toString(),
                    taskList.length() + 1
            );
            taskList.addTask(newTask);
            return reply;
        } else if (userInput.matches(Deadline.COMMAND_REGEX)) {
            //eg. deadline xxx /by dd-MM-uuuu HHmm
            String inputBody = userInput.split(" ", 2)[1];
            String[] deadlineDetails = inputBody.split("\s/by\s", 2);
            String deadlineTask = deadlineDetails[0];
            String deadlineByDate = deadlineDetails[1];

            Task newTask = Deadline.of(deadlineTask, deadlineByDate);
            String reply = String.format(
                    "Got it! I've added this task:\n %s\nNow you have %d tasks in the list.",
                    newTask.toString(),
                    taskList.length()  + 1
            );
            taskList.addTask(newTask);
            return reply;
        } else if (userInput.matches(Event.COMMAND_REGEX)) {
            //eg. event xxx /by xxx
            String inputBody = userInput.split(" ", 2)[1];
            String[] eventDetails = inputBody.split("\s/at\s", 2);
            String eventTask = eventDetails[0];
            String eventTime = eventDetails[1];

            Task newTask = Event.of(eventTask, eventTime);
            String reply = String.format(
                    "Got it! I've added this task:\n %s\nNow you have %d tasks in the list.",
                    newTask.toString(),
                    taskList.length()  + 1
            );
            taskList.addTask(newTask);
            return reply;
        }
        throw DukeException.of(userInput);
    }
}
