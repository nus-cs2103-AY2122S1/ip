package duke.util;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

import java.util.ArrayList;

/**
 * Represents a dealer to process a full command.
 */
public class Parser {
    private TaskList taskList;
    private Storage storage;
    private Ui ui;

    public Parser(TaskList taskList, Ui ui, Storage storage) {
        this.taskList = taskList;
        this.ui = ui;
        this.storage = storage;
    }

    /**
     * Parses an input and process it,
     * example of the input command should be like: deadline return book /by 2/12/2019 1800 .
     *
     * @param input a full command
     */
    public void parse(String input) {
        String lowerCase = input.toLowerCase();
        if(lowerCase.equals("bye")) {
            return;
        } else if (lowerCase.equals("list")) {
            listCommand();
        } else if (lowerCase.startsWith("deadline")) {
            int indexOfTime = input.indexOf("/by");
            if (indexOfTime == -1) {
                throw new DukeException("OOPS!!! The timeline of a deadline cannot be empty.");
            }
            String item = input.substring(9, indexOfTime);
            String by = input.substring(indexOfTime+4);
            Task deadline = new Deadline(item, by);
            taskList.add(deadline);
            storage.add(deadline);
            ui.showNewTask(deadline);
        } else if (lowerCase.startsWith("event")) {
            int indexOfTime = input.indexOf("/at");
            if (indexOfTime == -1) {
                throw new DukeException("OOPS!!! The timeline of a event cannot be empty.");
            }
            String item = input.substring(6, indexOfTime);
            String at = input.substring(indexOfTime+4);
            Task event = new Event(item, at);
            taskList.add(event);
            storage.add(event);
            ui.showNewTask(event);
        } else if (lowerCase.startsWith("todo")) {
            try {
                String item = input.substring(5);
                Task todo = new Todo(item);
                taskList.add(todo);
                storage.add(todo);
                ui.showNewTask(todo);
            } catch(StringIndexOutOfBoundsException e) {
                throw new DukeException("OOPS!!! The description of a event cannot be empty.");
            }
        } else if (lowerCase.startsWith("done")) {
            try {
                char temp = input.charAt(5);
                if (Character.isDigit(temp)) {
                    int item = Integer.parseInt(input.substring(5, 6));
                    taskList.done(item - 1);
                    storage.done(item - 1);
                    ui.showDone(taskList.get(item - 1));
                    return;
                }
            } catch (StringIndexOutOfBoundsException e) {
                throw new DukeException("OOPS!!! The target of finished duke.task cannot be empty.");
            }
        } else if (lowerCase.startsWith("delete")) {
            try {
                char temp = input.charAt(7);
                if (Character.isDigit(temp)) {
                    int item = Integer.parseInt(input.substring(7, 8));
                    Task task = taskList.get(item - 1);
                    taskList.delete(item - 1);
                    storage.delete(item - 1);
                    ui.showDelete(task, taskList.length());
                    return;
                }
            } catch (StringIndexOutOfBoundsException e) {
                throw new DukeException("OOPS!!! The target of deleting duke.task cannot be empty.");
            }
        } else if (lowerCase.startsWith("find")) {
            try{
                String keyword = lowerCase.substring(5).trim();
                findKeyword(keyword);
            } catch (StringIndexOutOfBoundsException e) {
                throw new DukeException("OOPS!!! Keyword cannot be empty.");
            }
        } else {
            throw new DukeException("OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }

    /**
     * Fetches Task item from TaskList and print through Ui
     */
    public void listCommand() {
        String items = "";
        if (taskList.length() > 0) {
            items = items + "1. " + taskList.get(0).toString();
        }
        for (int i = 2 ; i <= taskList.length(); i++) {
            items = items + "\n    " + i + ". " + taskList.get(i-1).toString();
        }
        ui.showList(items);
    }

    /**
     * Determines if the command is to exit the program.
     *
     * @param command the input
     * @return true or false
     */
    public boolean isExit(String command) {
        return command.toLowerCase().equals("bye");
    }

    public void findKeyword(String keyword) {
        ArrayList<Task> results = new ArrayList<>();
        for (int i = 0; i < taskList.length(); i++) {
            if (taskList.get(i).getDescription().toLowerCase().contains(keyword)) {
                results.add(taskList.get(i));
            }
        }
        ui.showFind(results);
    }
}
