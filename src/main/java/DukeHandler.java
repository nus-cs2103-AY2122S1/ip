import java.util.ArrayList;
import java.io.IOException;

public class DukeHandler {
    private final ArrayList<Task> tasks;
    private final FileHandler fileHandler;
    DukeHandler(ArrayList<Task> tasks, FileHandler fileHandler) {
        this.tasks = tasks;
        this.fileHandler = fileHandler;
    }

    public boolean isExit(String input) {
        return input.equals("bye");
    }

    public String[] parseInput(String input) throws DukeException, IOException {
        if (input.startsWith("done")) {
            return new String[]{setTaskAsDone(input)};
        } else if (input.startsWith("delete")) {
            return new String[]{deleteTask(input)};
        } else if (input.startsWith("list")) {
            return list();
        } else {
            return addTask(input);
        }
    }

    private String[] addTask(String input) throws DukeException, IOException {
        String[] splitString = input.split(" ", 2);
        String type = splitString[0];
        switch (type) {
        case "todo":
            if (splitString.length == 1) {
                throw new DukeException("OOPS!!! The description of a todo cannot be empty.\n");
            }
            Todo todo = new Todo(splitString[1]);
            tasks.add(todo);
            fileHandler.write(todo.save());
            break;
        case "deadline":
            if (splitString.length == 1) {
                throw new DukeException("OOPS!!! The description of a deadline cannot be empty.\n");
            }
            String[] splitDeadline = splitString[1].split(" /by ", 2);
            if (splitDeadline.length == 1) {
                throw new DukeException("OOPS!!! The description or deadline for a deadline cannot be empty or it must be after a '/'");
            }
            Deadline deadline = new Deadline(splitDeadline[0], splitDeadline[1]);
            tasks.add(deadline);
            System.out.println(deadline.save());
            fileHandler.write(deadline.save());
            break;
        case "event":
            if (splitString.length == 1) {
                throw new DukeException("OOPS!!! The description of an event cannot be empty.\n");
            }
            String[] splitEvent = splitString[1].split(" /at ", 2);
            if (splitEvent.length == 1) {
                throw new DukeException("\tOOPS!!! The description or duration for an event cannot be empty or it must be after a '/'");
            }
            Event event = new Event(splitEvent[0], splitEvent[1]);
            tasks.add(event);
            fileHandler.write(event.save());
            break;
        default:
                throw new DukeException("OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
        ArrayList<String> results = new ArrayList<>();
        results.add("\tGot it. I've added this task:");
        results.add("\t\t" + tasks.get(tasks.size()-1).toString());
        results.add("\tNow you have " + tasks.size() + " tasks in the list.");
        return results.toArray(new String[0]);
    }

    private String deleteTask(String input) {
        String[] splitString = input.split(" ", 2);
        int i = Integer.parseInt(splitString[1])-1;
        return "\tNoted. I've removed this task:\n" + "\t\t" + tasks.remove(i).toString() + "\n" + "\tNow you have " + tasks.size() + " tasks in the list.";
    }

    private String setTaskAsDone(String input) {
        String[] splitString = input.split(" ", 2);
        int i = Integer.parseInt(splitString[1])-1;
        tasks.get(i).markAsDone();
        return "\tNice! I've marked this task as done:\n\t\t" +  tasks.get(i).toString();
    }

    private String[] list() {
        ArrayList<String> list = new ArrayList<>();
        list.add("\tHere are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            list.add("\t" + (i + 1) + ". " + tasks.get(i).toString());
        }
        return list.toArray(new String[0]);
    }
}
