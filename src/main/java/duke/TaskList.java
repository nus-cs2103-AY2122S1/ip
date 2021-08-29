package duke;

import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasks;
    private Storage storage;
    private Ui ui;

    public TaskList(Storage storage, Ui ui) {
        this.tasks = new ArrayList<>(100);
        this.storage = storage;
        this.ui = ui;

        this.storage.initialiseTasks(tasks);
    }

    public int sizeOf() {
        return tasks.size();
    }

    public void displayList() {
        String line = "____________________________________________________________";
        String indent = "    ";

        if (tasks.size() == 0) {
            ui.toScreen("You currently have no tasks.");
            return;
        }

        System.out.println(indent + line + "\n" +
                indent + "Here are the tasks in your list: ");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println(indent + indent + (i + 1) + "." + tasks.get(i).toString());
        }

        System.out.println(indent + line);
    }

    public void doneTask(int index) {
        try {
            Task finishedTask = tasks.get(index);
            finishedTask.markAsDone();
            storage.updateFile(tasks);
            ui.toScreen("Nice! I've marked the following task as done: ",
                    "    " + finishedTask.toString());
        } catch (IndexOutOfBoundsException e) {
            ui.toScreen("Duke.Task could not be marked as done.", "Please input valid task index.");
        }
    }

    public void deleteTask(int index) {
        try {
            Task deletedTask = tasks.get(index);
            tasks.remove(index);
            storage.updateFile(tasks);
            ui.toScreen("Ok, I've deleted the following task: ",
                    "    " + deletedTask.toString());
        } catch (IndexOutOfBoundsException e) {
            ui.toScreen("Duke.Task could not be deleted.", "Please input valid task index.");
        }
    }

    public void addTodo(String desc) {
        try {
            if (desc.isEmpty()) {
                throw new DukeException();
            }
            Task temp = new Todo(desc);
            tasks.add(temp);
            storage.writeTask(temp);
            ui.toScreen("Ok! A new task has been added:",
                    "    " + temp.toString(),
                    "You now have " + tasks.size() + " task(s) in total.");
        } catch (DukeException de) {
            ui.toScreen("Sorry, the Duke.Todo task could not be added.",
                    "Please include the description for this task.", desc);
        }
    }

    public void addDeadline(String[] req) {
        try {
            if (req.length != 2) {
                throw new DukeException();
            }
            String desc = req[0];
            String deadline = req[1];
            Task temp = new Deadline(desc, deadline);
            tasks.add(temp);
            storage.writeTask(temp);
            ui.toScreen("Ok! A new task has been added:",
                    "    " + temp.toString(),
                    "You now have " + tasks.size() + " task(s) in total.");
        } catch (DukeException de) {
            ui.toScreen("Sorry, the Duke.Deadline task could not be added.",
                    "Please include the description and deadline for this task with /by.",
                    "(Date and time format: dd/MM/yyyy HHmm)");
        }
    }

    public void addEvent(String[] req) {
        try {
            if (req.length != 2) {
                throw new DukeException("oops");
            }
            String desc = req[0];
            String time = req[1];
            Task temp = new Event(desc, time);
            tasks.add(temp);
            storage.writeTask(temp);
            ui.toScreen("Ok! A new task has been added:",
                    "    " + temp.toString(),
                    "You now have " + tasks.size() + " task(s) in total.");
        } catch (DukeException de) {
            ui.toScreen("Sorry, the Duke.Event task could not be added.",
                    "Please include the description and time of this task with /at.");
        }
    }

    public void findTask(String key) {
        if (key.isEmpty()) {
            ui.toScreen("Please include a search word.");
            return;
        }

        String line = "____________________________________________________________";
        String indent = "    ";
        ArrayList<Task> temp = new ArrayList<>();
        for (Task t : tasks) {
            if (t.toString().contains(key)) {
                temp.add(t);
            }
        }

        if (temp.size() == 0) {
            ui.toScreen("No tasks match this search, please try again.");
            return;
        }

        System.out.println(indent + line + "\n" +
                indent + "Here are the tasks that match your search: ");
        for (int i = 0; i < temp.size(); i++) {
            System.out.println(indent + indent + (i + 1) + "." + temp.get(i).toString());
        }

        System.out.println(indent + line);

        
    }
}
