import java.util.ArrayList;

public class DukeHandler {
    private final ArrayList<Task> tasks;

    DukeHandler(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public boolean isExit(String input) {
        return input.equals("bye");
    }

    public String[] parseInput(String input) throws DukeException {
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

    private String[] addTask(String input) throws DukeException {
        String[] splitString = input.split(" ", 2);
        String type = splitString[0];
        switch (type) {
        case "todo":
            if (splitString.length == 1) {
                throw new DukeException("OOPS!!! The description of a todo cannot be empty.\n");
            }
            tasks.add(new Todo(splitString[1]));
            break;
        case "deadline":
            if (splitString.length == 1) {
                throw new DukeException("OOPS!!! The description of a deadline cannot be empty.\n");
            }
            String[] splitDeadline = splitString[1].split(" /by", 2);
            if (splitDeadline.length == 1) {
                throw new DukeException("OOPS!!! The description or deadline for a deadline cannot be empty or it must be after a '/'");
            }
            System.out.println(splitDeadline[1]);
            tasks.add(new Deadline(splitDeadline[0], splitDeadline[1]));
            break;
        case "event":
            if (splitString.length == 1) {
                throw new DukeException("OOPS!!! The description of an event cannot be empty.\n");
            }
            String[] splitEvent = splitString[1].split(" /at", 2);
            if (splitEvent.length == 1) {
                throw new DukeException("\tOOPS!!! The description or duration for an event cannot be empty or it must be after a '/'");
            }
            tasks.add(new Event(splitEvent[0], splitEvent[1]));
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
