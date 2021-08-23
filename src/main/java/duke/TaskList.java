package duke;

import java.util.ArrayList;

/**
 * Represents an array of tasks that the user inputs in.
 */
public class TaskList {
    protected ArrayList<Task> arrayList;
    TaskList(ArrayList<Task> loaded) {
        arrayList = loaded;
    }

    TaskList() {
        this(new ArrayList<>(100));
    }

    /**
     * Lists all of the tasks within the array.
     * Numbered from 1 onwareds.
     *
     * @return A string containing list of tasks.
     */
    public String listTasks() {
        StringBuilder sb = new StringBuilder();
        int counter = 1;
        for (Task t : arrayList) {
            sb.append(counter + ". " + t + "\n");
            counter += 1;
        }
        return sb.toString();
    }

    /**
     * Adds a task to the internal array.
     *
     * @param command Command to be parsed.
     * @param parameter Description of task.
     * @param date Provided date of task.
     */
    public void addTask(Command command, String parameter, String date) {
        StringBuilder sb = new StringBuilder();
        sb.append("Got it. I've added this task: \n");
        String parsedDate = Parser.dateParser(date).equals("") ? date : Parser.dateParser(date);
//            if (Command.EVENT.equals(command)) {
//                task = new Event(parameter, parsedDate);
//            } else if (Command.TODO.equals(command)) {
//                task = new Todo(parameter);
//            } else {//if (Command.DEADLINE.equals(command)) {
//                task = new Deadline(parameter, parsedDate);
//            }
        Task task;

        if (command.equals(Command.TODO)) {
            task = new Todo(parameter);
        } else if (command.equals(Command.DEADLINE)){
            task = new Deadline(parameter, parsedDate);
        } else if (command.equals(Command.EVENT)){
            task = new Event(parameter, parsedDate);
        } else {
            throw new DukeException("Invalid command");
        }
        arrayList.add(task);
        sb.append(task + "\n");
        sb.append("Now you have " + String.valueOf(arrayList.size()) + " tasks in the list.");
        Ui.printStatement(sb.toString());



    }

    public void addTask(Command command, String parameter) {
        addTask(command, parameter, "");
    }

    /**
     * Removes task from task list.
     * @param index Index of task to be removed.
     */
    public void removeTask(int index) {
        StringBuilder sb = new StringBuilder();
        sb.append("Noted. I've removed this task:\n");
        sb.append(arrayList.get(index).toString() + "\n");
        arrayList.remove(index);
        sb.append("Now you have " + arrayList.size() + " tasks in the list.");
        Ui.printStatement(sb.toString());
    }

    /**
     * Returns size of task list.
     * @return Integer showing size of task list.
     */
    protected int size() {
        return arrayList.size();
    }

    /**
     * Shows a task as done.
     *
     * @param i Index of task to be done.
     */
    public void done(Integer i) {
        Task task = arrayList.get(i);
        task.markAsDone();
        Ui.printStatement("Nice! I've marked this task as done:\n" + task);
    }
}