package duke;

import java.time.LocalDate;

/**
 * Deals with making sense of the user command
 */
public class Parser {
    /**
     * Deals with parsing the command and do some reaction
     *
     * @param input user command input
     * @param taskList a list of tasks
     * @return if the command required to exit
     * @throws DukeException exceptions that are possible to occur in Duke
     */
    public static String parser(String input, TaskList taskList) throws DukeException{
        String[] pieces = input.split(" ", 2);
        String command = pieces[0];

        if (command.equals("list")) {
            return handleList(taskList);
        } else if (command.equals("bye")) {
            return handleBye();
        } else if (command.equals("done")) {
            return handleDone(pieces, taskList);
        } else if (command.equals("todo")) {
            return handleTodo(pieces, taskList);
        } else if (command.equals("event")) {
            return handleEvent(pieces, taskList);
        } else if (command.equals("deadline")) {
            return handleDeadline(pieces, taskList);
        } else if (command.equals("delete")){
            return handleDelete(pieces, taskList);
        } else if (command.equals("find")) {
            return handleFind(pieces, taskList);
        } else {
            throw new DukeException("OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }

    /**
     * Handle the input started with "find"
     *
     * @param pieces split command
     * @param taskList a list of tasks
     * @return the response of Duke
     */
    private static String handleFind(String[] pieces, TaskList taskList) {
        TaskList tempList = new TaskList();
        for(int i = 0; i < taskList.size(); i++) {
            Task checkTask = taskList.get(i);
            if (checkTask.getName().contains(pieces[1])) {
                tempList.add(checkTask);
            }
        }
        System.out.println("    Here are the matching tasks in your list:");
        return Ui.list(tempList);
    }

    /**
     * Handle the input started with "delete"
     *
     * @param pieces split command
     * @param taskList a list of tasks
     * @return the response of Duke
     */
    private static String handleDelete(String[] pieces,TaskList taskList) throws DukeException{
        try {
            int index = Integer.parseInt(pieces[1]);
            Task task = taskList.get(index - 1);
            taskList.remove(task);
            return Ui.delete(taskList, task);
        } catch (IndexOutOfBoundsException e){
            throw new DukeException("OOPS!!! Invalid task number");
        }
    }

    /**
     * Handle the input started with "deadline"
     *
     * @param pieces split command
     * @param taskList a list of tasks
     * @return the response of Duke
     */
    private static String handleDeadline(String[] pieces, TaskList taskList) throws DukeException{
        try {
            String[] eventPieces = pieces[1].split("/", 2);
            String name = eventPieces[0];
            String[] timePieces = eventPieces[1].split("by ", 2);
            LocalDate time = LocalDate.parse(timePieces[1]);
            Task newTask = new Deadline(name, time);
            taskList.add(newTask);
            return Ui.addTask(taskList, newTask);
        }
        catch (ArrayIndexOutOfBoundsException e){
            throw new DukeException("Please enter the event command in 'deadline [task description]/by [end time]' format");
        }
    }

    /**
     * Handle the input started with "list"
     *
     * @param taskList a list of tasks
     * @return the response of Duke
     */
    public static String handleList(TaskList taskList) {
        if(taskList.size() == 0) {
            return "Your list is empty!";
        }
        return Ui.list(taskList);
    }

    /**
     * Handle the input "bye"
     *
     * @return the response of Duke
     */
    public static String handleBye() {
        return Ui.bye();
    }

    /**
     * Handle the input started with "done"
     *
     * @param pieces split command
     * @param taskList a list of tasks
     * @return the response of Duke
     */
    public static String handleDone(String[] pieces, TaskList taskList) throws DukeException{
        try {
            int index = Integer.parseInt(pieces[1]);
            Task task = taskList.get(index - 1);
            task.markAsDone();
            return Ui.done(task);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("OOPS!!! Invalid task number");
        }
    }

    /**
     * Handle the input started with "todo"
     *
     * @param pieces split command
     * @param taskList a list of tasks
     * @return the response of Duke
     */
    public static String handleTodo(String[] pieces, TaskList taskList) throws DukeException {
        try {
            Task newTask = new Todo(pieces[1]);
            taskList.add(newTask);
            return Ui.addTask(taskList, newTask);
        } catch (ArrayIndexOutOfBoundsException e){
            throw new DukeException("OOPS!!! The description of a todo cannot be empty.");
        }
    }

    /**
     * Handle the input started with "event"
     *
     * @param pieces split command
     * @param taskList a list of tasks
     * @return the response of Duke
     */
    public static String handleEvent(String[] pieces, TaskList taskList) throws DukeException{
        try {
            String[] eventPieces = pieces[1].split("/", 2);
            String name = eventPieces[0];
            String[] timePieces = eventPieces[1].split("at ", 2);
            LocalDate time = LocalDate.parse(timePieces[1]);
            Task newTask = new Event(name, time);
            taskList.add(newTask);
            return Ui.addTask(taskList, newTask);
        } catch (ArrayIndexOutOfBoundsException e){
            throw new DukeException("Please enter the event command in 'event [task description]/at [start time]' format");
        }
    }
}
