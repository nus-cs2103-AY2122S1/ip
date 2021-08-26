package duke.command.task;

import duke.util.DukeException;
import duke.util.Message;
import duke.util.Parser;

import java.util.ArrayList;
import java.util.List;

public class TaskList {

    private final List<Task> taskArr;

    // Messages
    private static final String OUT_OF_BOUNDS_TASK = "Could not find task. Check the task number again?";
    private static final String NUMBER_OF_TASKS_MESSAGE = "Now you have %d %s in the list.";
    private static final String ADD_TASK_MESSAGE = "Got it. I've added this task:\n  %s\n" + NUMBER_OF_TASKS_MESSAGE;
    private static final String REMOVE_TASK_MESSAGE = "Noted. I've removed this task:\n %s\n" + NUMBER_OF_TASKS_MESSAGE;
    private static final String ERROR_SAVING_MESSAGE = "Error reading taskLst. Symbol not found.";
    private static final String NO_TASK_FOUND_MESSAGE = "Unfortunately no tasks with that name are found";
    private static final String MATCHING_TASKS_MESSAGE = "Here are the matching tasks in your list:\n";

    // Nouns
    private String taskWord() {
        return this.size() <= 1 ? "task" : "tasks";
    }

    public TaskList() {
        this.taskArr = new ArrayList<>();
    }

    public int size() {
        return taskArr.size();
    }

    public String addTask(Task task) {
        this.taskArr.add(task);
        return String.format(ADD_TASK_MESSAGE, task, this.size(), taskWord());
    }

    public String findTask(String input) {
        StringBuilder printedList = new StringBuilder();
        int index = 1;
        for (Task task : this.taskArr) {
            if (task.getText().contains(input)) {
                // Index from 1 onwards
                String indexStr = Integer.toString(index);
                printedList.append(String.format("%s. %s\n", indexStr, task));
                index++;
            }
        }
        if (index == 1) {
            printedList.insert(0, NO_TASK_FOUND_MESSAGE );
        } else {
            printedList.insert(0, MATCHING_TASKS_MESSAGE );
        }
        // Remove the last newline
        return printedList.toString().trim();
    }

    public void addSavedTask(String input) throws ArrayIndexOutOfBoundsException, DukeException {
        String[] inputArr = input.split("\\|");
        String symbol = inputArr[0];
        String remainingText = Parser.getRemainingText(symbol, input);
        switch (symbol.charAt(0)) {
        case ToDo.SYMBOL:
            ToDo myTodo = ToDo.newToDoFromSave(remainingText);
            this.addTask(myTodo);
            break;
        case Deadline.SYMBOL:
            Deadline myDeadline = Deadline.newDeadlineFromSave(remainingText);
            this.addTask(myDeadline);
            break;
        case Event.SYMBOL:
            Event myEvent = Event.newEventFromSave(remainingText);
            this.addTask(myEvent);
            break;
        default:
            throw new DukeException(ERROR_SAVING_MESSAGE);
        }
    }

    public String markTaskAsDone(int taskIndex) throws DukeException {
        // Task index starts from 1
        int index = taskIndex - 1;
        try {
            Task task = taskArr.get(index);
            task.markDone();
            return task.toString();
        } catch (IndexOutOfBoundsException err) {
            throw new DukeException(OUT_OF_BOUNDS_TASK);
        }
    }

    public String deleteTask(int taskIndex) throws DukeException {
        // Task index starts from 1
        int index = taskIndex - 1;
        try {
            Task task = taskArr.remove(index);
            return String.format(REMOVE_TASK_MESSAGE, task, this.size(), taskWord());
        } catch (IndexOutOfBoundsException err) {
            throw new DukeException(OUT_OF_BOUNDS_TASK);
        }
    }

    public String getSaveFormat() {
        StringBuilder printedList = new StringBuilder();
        for (Task task : taskArr) {
            printedList.append(String.format("%s\n", task.getSaveFormat()));
        }
        // Remove the last newline
        return printedList.toString().trim();
    }

    @Override
    public String toString() {
        StringBuilder printedList = new StringBuilder();
        for (int i = 0; i < taskArr.size(); i++) {
            // Index from 1 onwards
            String index = Integer.toString(i + 1);
            printedList.append(String.format("%s. %s\n", index, this.taskArr.get(i)));
        }
        // Remove the last newline
        return printedList.toString().trim();
    }
}
