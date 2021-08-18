import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> taskList = new ArrayList<>();

    private void addTask(Task task) {
        taskList.add(task);

        Duke.printMessage("Got it. I've added this task:\n  "
                + task.toString()
                + "\nNow you have " + taskList.size()
                + (taskList.size() != 1 ? " tasks" : " task") + " in the list.");
    }

    public void addToDo(String input) throws DukeException {
        if (input.length() <= 5) {
            throw new DukeException("Please input the todo's name!");
        }

        String name = input.substring(5);
        Task task = new ToDo(name);
        addTask(task);
    }

    public void addDeadline(String input) throws DukeException {
        if (input.length() <= 9) {
            throw new DukeException("Please input the deadline's name!");
        }

        String[] inputs = input.substring(9).split(" /by ");

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
        if (input.length() <= 6) {
            throw new DukeException("Please input the event's name!");
        }

        String[] inputs = input.substring(6).split(" /at ");

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
        int idx;

        try {
            idx = Integer.parseInt(taskNum);
        } catch (NumberFormatException numberFormatException) {
            throw new DukeException("Please input a number.");
        }

        if (idx <= 0 || idx > taskList.size()) {
            throw new DukeException("Please input the ID of a task!");
        }

        Task task = getTask(idx);
        task.doTask();
        Duke.printMessage("Nice! I've marked this task as done:\n  " + task.toString());
    }

    // Returns a nicely formatted string representation of all tasks in the list
    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        int size = taskList.size();

        if (size == 0) {
            return "No tasks yet!";
        }

        for (int i = 1; i <= size; i++) {
            stringBuilder.append(i);
            stringBuilder.append(". ");
            stringBuilder.append(getTask(i).toString());
            stringBuilder.append("\n");
        }

        // delete the last new line character
        stringBuilder.deleteCharAt(stringBuilder.length() - 1);

        return stringBuilder.toString();
    }

    // taskList represents its tasks 1-indexed
    private Task getTask(int idx) {
        return taskList.get(idx - 1);
    }
}
