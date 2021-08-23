import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;
import java.time.format.DateTimeParseException;
import java.time.LocalDate;

public class TaskList {
    private final static File saveFile = new File("save.csv");
    private ArrayList<Task> taskList = new ArrayList<>();

    private void addTask(Task task) throws DukeException {
        taskList.add(task);

        save();
        Duke.printMessage("Got it. I've added this task:\n  "
                + task.toString() + "\n" + taskLengthReport());
    }

    public void addToDo(String input) throws DukeException {
        // length of the command + the trailing whitespace
        int commandLength = Commands.TODO.toString().length() + 1;

        if (input.length() <= commandLength) {
            throw new DukeException("Please input the todo's name!");
        }

        String name = input.substring(commandLength).strip();
        if (name.equals("")) {
            throw new DukeException("Please input the deadline's name!");
        }

        Task task = new ToDo(name);
        addTask(task);
    }

    public void addDeadline(String input) throws DukeException {
        // length of the command + the trailing whitespace
        int commandLength = Commands.DEADLINE.toString().length() + 1;

        if (input.length() <= commandLength) {
            throw new DukeException("Please input the deadline's name and date!");
        }

        String[] inputs = input.substring(commandLength).split("/by");

        if (inputs.length < 2) {
            // /by not specified
            throw new DukeException("Please input when the deadline is to be done by!");
        } else if (inputs.length > 2) {
            // more than one /by
            throw new DukeException("Please input only one deadline!");
        }

        String name = inputs[0].strip();
        if (name.equals("")) {
            throw new DukeException("Please input the deadline's name!");
        }

        LocalDate date;
        try {
            date = LocalDate.parse(inputs[1].strip());
        } catch (DateTimeParseException e) {
            throw new DukeException("Please input your date in the format YYYY-MM-DD");
        }

        Task task = new Deadline(name, date);
        addTask(task);
    }

    public void addEvent(String input) throws DukeException {
        // length of the command + the trailing whitespace
        int commandLength = Commands.EVENT.toString().length() + 1;

        if (input.length() <= commandLength) {
            throw new DukeException("Please input the event's name and date!");
        }

        String[] inputs = input.substring(commandLength).split("/at");

        if (inputs.length < 2) {
            // /by not specified
            throw new DukeException("Please input when the event is at!");
        } else if (inputs.length > 2) {
            // more than one /by
            throw new DukeException("Please input only one timing for the event!");
        }

        String name = inputs[0].strip();

        if (name.equals("")) {
            throw new DukeException("Please input the event's name!");
        }

        LocalDate date;
        try {
            date = LocalDate.parse(inputs[1].strip());
        } catch (DateTimeParseException e) {
            throw new DukeException("Please input your date in the format YYYY-MM-DD");
        }

        Task task = new Event(name, date);
        addTask(task);
    }

    public void doTask(String taskNum) throws DukeException {
        int idx = getTaskIndexFromString(taskNum);

        Task task = taskList.get(idx);
        task.doTask();
        save();

        Duke.printMessage("Nice! I've marked this task as done:\n  " + task.toString());
    }

    public void deleteTask(String taskNum) throws DukeException {
        if (taskNum.equals("done")) {
            deleteDone();
            return;
        }

        if (taskNum.equals("expired")) {
            deleteExpired();
            return;
        }

        int idx = getTaskIndexFromString(taskNum);

        Task task = taskList.get(idx);
        taskList.remove(idx);
        save();

        Duke.printMessage("Noted! I've removed this task:\n  " +
                task.toString() + "\n" + taskLengthReport());
    }

    private void deleteDone() {
        taskList.removeIf(Task::isDone);
        Duke.printMessage("Noted! I've removed all completed tasks.\n" +
                taskLengthReport());
    }

    private void deleteExpired() {
        taskList.removeIf(Task::isExpired);

        Duke.printMessage("Noted! I've removed all expired tasks.\n" +
                taskLengthReport());
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

    private void save() throws DukeException {
        try {
            // create the file if it does not exist
            saveFile.createNewFile();
        } catch (IOException e) {
            throw new DukeException(e.getMessage());
        }

        try (FileWriter fw = new FileWriter(saveFile)){
            fw.write(convertToSaveString());
        } catch (IOException e) {
            throw new DukeException(e.getMessage());
        }
    }

    private String convertToSaveString() {
        StringBuilder sb = new StringBuilder();

        if (taskList.size() == 0) {
            return "";
        }

        for (Task task : taskList) {
            sb.append(task.getSaveString());
            sb.append('\n');
        }

        // remove the last newline character
        sb.deleteCharAt(sb.length() - 1);

        return sb.toString();
    }

    // returns whether the saveFile exists, indicating whether the user is a new user
    public boolean loadFile() {
        try {
            Scanner sc = new Scanner(saveFile);
            while (sc.hasNextLine()) {
                String[] data = sc.nextLine().split(",");
                switch (data[0]) {
                case "t":
                    taskList.add(ToDo.load(data));
                    break;
                case "d":
                    taskList.add(Deadline.load(data));
                    break;
                case "e":
                    taskList.add(Event.load(data));
                    break;
                default:
                    break;
                }
            }
            return true;
        } catch (FileNotFoundException e) {
            return false;
        }
    }
}
