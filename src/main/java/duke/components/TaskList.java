package duke.components;
import duke.task.Task;
import duke.task.Todo;
import duke.task.Deadline;
import duke.task.Event;
import java.util.ArrayList;
import java.time.LocalDateTime;

public class TaskList {
    private ArrayList<Task> inputs;
    private Storage storage;
    private Ui ui;
    private Parser parser;

    public TaskList(Storage storage, Ui ui, Parser parser) {
        inputs = new ArrayList<>();
        this.storage = storage;
        this.ui = ui;
        this.parser = parser;
    }

    public int getSize() {
        return this.inputs.size();
    }

    public void addTaskFromDataFile(Task task) {
        inputs.add(task);
    }

    // normal input
    public void addTaskFromInput(String input) {

        String[] group = input.split(" ");

        // Case Todo
        if (parser.isTodo(group[0]) && group.length > 1) {
            String todoToAdd = input.substring(5); // name of the task is after "todo" and space
            Task newTodo = new Todo(todoToAdd);
            inputs.add(newTodo);

            storage.writeTaskToFile(inputs.get(0).toString() + System.lineSeparator());

            for (int i = 1; i < inputs.size(); i++) {
                storage.appendTaskToFile(inputs.get(i).toString() + System.lineSeparator());
            }

            ui.displayAddTaskMessage(newTodo);
            ui.displayTaskNumber(this);
            ui.displayLinebreak();
        }
        if (parser.isTodo(group[0]) && group.length == 1) {
            ui.displayTodoEmptyMessage();
            ui.displayLinebreak();
        }

        // Case Deadline
        if (parser.isDeadline(group[0]) && input.contains(" /by ")) {
            String[] ddlGroup = input.split(" /by ");
            String ddlToAdd = ddlGroup[0].substring(9); // name of the task is after "deadline" and space
            String ddlByTime = ddlGroup[1];
            LocalDateTime dateTime = LocalDateTime.parse(ddlByTime, parser.getFormatter());
            Task newDeadline = new Deadline(ddlToAdd, dateTime);
            inputs.add(newDeadline);

            storage.writeTaskToFile(inputs.get(0).toString() + System.lineSeparator());

            for (int i = 1; i < inputs.size(); i++) {
                storage.appendTaskToFile(inputs.get(i).toString() + System.lineSeparator());
            }

            ui.displayAddTaskMessage(newDeadline);
            ui.displayTaskNumber(this);
            ui.displayLinebreak();
        }

        // Case Event
        if (parser.isEvent(group[0]) && input.contains(" /at ")) {
            String[] eveGroup = input.split(" /at ");
            String eveToAdd = eveGroup[0].substring(6); // name of the task is after "event" and space
            String eveAtTime = eveGroup[1];
            LocalDateTime dateTime = LocalDateTime.parse(eveAtTime, parser.getFormatter());
            Task newEvent = new Event(eveToAdd, dateTime);
            inputs.add(newEvent);

            storage.writeTaskToFile(inputs.get(0).toString() + System.lineSeparator());

            for (int i = 1; i < inputs.size(); i++) {
                storage.appendTaskToFile(inputs.get(i).toString() + System.lineSeparator());
            }

            ui.displayAddTaskMessage(newEvent);
            ui.displayTaskNumber(this);
            ui.displayLinebreak();
        }
    }

    public void deleteTask(String input) {
        String[] parts = input.split(" ");
        int index = Integer.parseInt(parts[1]) - 1;
        if (parser.isDelete(parts[0]) && index < inputs.size()) {
            System.out.println("Noted. I've removed this task: ");
            System.out.println(inputs.get(index).toString());
            inputs.remove(index);

            if (inputs.size() == 0) {
                storage.eraseFileContent();
            } else {
                storage.writeTaskToFile(inputs.get(0).toString() + System.lineSeparator());
                for (int i = 1; i < inputs.size(); i++) {
                    storage.appendTaskToFile(inputs.get(i).toString() + System.lineSeparator());
                }
            }

            ui.displayLinebreak();
        }
    }

    public void markDone(String input) {
        String[] parts = input.split(" ");
        int index = Integer.parseInt(parts[1]) - 1;
        if (parser.isDone(parts[0]) && index < inputs.size()) {
            inputs.get(index).setDone();

            storage.writeTaskToFile(inputs.get(0).toString() + System.lineSeparator());
            for (int i = 1; i < inputs.size(); i++) {
                storage.appendTaskToFile(inputs.get(i).toString() + System.lineSeparator());
            }

            ui.displayMarkDoneMessage(inputs.get(index).toString());
            ui.displayLinebreak();
        }
    }

    public void displayAllTasks() {
        System.out.println("Here are the tasks in your list: ");
        for (int i = 0; i < inputs.size(); i++) {
            int index = i + 1;
            System.out.println(index + ". " + inputs.get(i).toString());
        }
        ui.displayLinebreak();
    }

}
