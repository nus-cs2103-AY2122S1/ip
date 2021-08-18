import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> taskList = new ArrayList<>();

    private void addTask(Task task) {
        taskList.add(task);

        Duke.printMessage("Got it. I've added this task:\n  "
                + task.toString() + "\n" + taskLengthReport());
    }

    public void addToDo(String input) throws DukeException {
        // length of the command + the trailing whitespace
        int commandLength = Commands.TODO.toString().length() + 1;

        if (input.length() <= commandLength) {
            throw new DukeException("Please input the todo's name!");
        }

        String name = input.substring(commandLength);
        Task task = new ToDo(name);
        addTask(task);
    }

    public void addDeadline(String input) throws DukeException {
        // length of the command + the trailing whitespace
        int commandLength = Commands.DEADLINE.toString().length() + 1;

        if (input.length() <= commandLength) {
            throw new DukeException("Please input the deadline's name!");
        }

        String[] inputs = input.substring(commandLength).split(" /by ");

        if (inputs.length < 2) {
            // /by not specified
            throw new DukeException("Please input when the deadline is to be done by!");
        } else if (inputs.length > 2) {
            // more than one /by
            throw new DukeException("Please input only one deadline!");
        }

        String name = inputs[0];
        String time = inputs[1];
        Task task = new Deadline(name, time);
        addTask(task);
    }

    public void addEvent(String input) throws DukeException {
        // length of the command + the trailing whitespace
        int commandLength = Commands.EVENT.toString().length() + 1;

        if (input.length() <= commandLength) {
            throw new DukeException("Please input the event's name!");
        }

        String[] inputs = input.substring(commandLength).split(" /at ");

        if (inputs.length < 2) {
            // /by not specified
            throw new DukeException("Please input when the event is at!");
        } else if (inputs.length > 2) {
            // more than one /by
            throw new DukeException("Please input only one timing for the event!");
        }

        String name = inputs[0];
        String time = inputs[1];
        Task task = new Event(name, time);
        addTask(task);
    }

    public void doTask(String taskNum) throws DukeException {
        int idx = getTaskIndexFromString(taskNum);

        Task task = taskList.get(idx);
        task.doTask();
        Duke.printMessage("Nice! I've marked this task as done:\n  " + task.toString());
    }

    public void deleteTask(String taskNum) throws DukeException {
        int idx = getTaskIndexFromString(taskNum);

        Task task = taskList.get(idx);
        taskList.remove(idx);

        Duke.printMessage("Noted! I've removed this task:\n  " +
                task.toString() + "\n" + taskLengthReport());
    }

    // Returns a nicely formatted string representation of all tasks in the list
    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        int size = taskList.size();

        if (size == 0) {
            return "No tasks yet!";
        }

        for (int i = 0; i < size; i++) {
            stringBuilder.append(i + 1);
            stringBuilder.append(". ");
            stringBuilder.append(taskList.get(i).toString());
            stringBuilder.append("\n");
        }

        // delete the last new line character
        stringBuilder.deleteCharAt(stringBuilder.length() - 1);

        return stringBuilder.toString();
    }

    // parses the user's input and returns the index of the task in question
    private int getTaskIndexFromString(String taskNum) throws DukeException {
        int idx;
        try {
            idx = Integer.parseInt(taskNum);
        } catch (NumberFormatException numberFormatException) {
            throw new DukeException("Please input a number.");
        }

        if (idx <= 0 || idx > taskList.size()) {
            throw new DukeException("Please input the ID of a task!");
        }

        // tasks are 1-indexed to the user, but 0-indexed by implementation
        return idx - 1;
    }

    // returns a string telling the user how many tasks are in the list
    private String taskLengthReport() {
        return "Now you have " + taskList.size()
                + (taskList.size() != 1 ? " tasks" : " task") + " in the list.";
    }
}
