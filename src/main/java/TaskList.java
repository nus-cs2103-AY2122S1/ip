import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

public class TaskList {

    private static ArrayList<Task> tasks = new ArrayList<>();
    private static String indent = "    ";
    private static String div_line = "    ____________________________________________________________";

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    private enum Type {
        TODO("todo"),
        EVENT("event"),
        DEADLINE("deadline");

        private final String type;

        Type(String type) {
            this.type = type;
        }

        public String getType() {
            return type;
        }
    }

    public void addTask(String[] arr) throws DukeException {
        Task t;
        if (arr[0].equals(Type.TODO.getType())) {
            if (arr.length == 1 || arr[1].trim().length() == 0) {
                throw new DukeException("Invalid TODO entry. Description of a TODO cannot be empty.");
            } else {
                t = new ToDo(arr[1].trim());
            }
        } else if (arr.length > 1 && arr[0].equals(Type.EVENT.getType())) {
            String[] detail = arr[1].split("/at ", 2);
            if (detail.length == 1 || detail[1].trim().length() == 0 || detail[0].trim().length() == 0) {
                throw new DukeException("Invalid Event entry. Try something like: event meeting /at 19/08/2021 14:00");
            } else {
                t = addEvent(detail[0].trim(), detail[1].trim());
            }
        } else if (arr.length > 1 && arr[0].equals(Type.DEADLINE.getType())){
            String[] detail = arr[1].split("/by ", 2);
            if (detail.length == 1 || detail[1].trim().length() == 0 || detail[0].trim().length() == 0) {
                throw new DukeException("Invalid Deadline entry.Try something like: deadline HW due /by 19/8/2021 14:00");
            } else {
                t = addDeadline(detail[0].trim(), detail[1].trim());
            }
        } else {
            throw new DukeException("Invalid entry. Try again!");
        }
        
        if (t != null) {
            tasks.add(t);
            System.out.println(div_line + "\n" + indent +
                            "Got it. I've added this task:\n" + indent + t + "\n" + indent +
                            "Now you have " + size() + " tasks in the list." + "\n" + div_line);
        }
        Storage.save(tasks);
    }

    private Task addEvent(String det, String at) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        Task t = null;
        try {
            t = new Event(det, LocalDateTime.parse(at , formatter));
        } catch (DateTimeParseException e) {
            Duke.echo("Invalid entry. Valid event format: event meeting /at 19/08/2021 23:59");
        }
        return t;
    }

    private Task addDeadline(String det, String by) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        Task t = null;
        try {
            t = new Deadline(det, LocalDateTime.parse(by , formatter));
        } catch (DateTimeParseException e) {
            Duke.echo("Invalid entry. Valid deadline format: deadline do HW /by 19/08/2021 23:59");
        }
        return t;
    }

    public void markDone(String[] arr) throws DukeException {
        if (arr.length == 1) {
            throw new DukeException("Enter task no. to complete the task.");
        } else {
            try {
                int i = Integer.parseInt(arr[1]) - 1;
                tasks.get(i).markDone();
                System.out.println(div_line + "\n" + indent + "Nice! I have marked this task as done:\n" + indent +
                        getTask(i) + "\n" + div_line);
            } catch (NumberFormatException | IndexOutOfBoundsException e) {
                System.out.println(div_line + "\n" + indent + "Oops! Enter a valid task no. to complete the task." +
                        "\n" + div_line);
            }
        }
        Storage.save(tasks);
    }

    public void deleteTask(String[] arr) throws DukeException {
        if (arr.length == 1) {
            throw new DukeException("Enter task no. to delete the task.");
        } else {
            try {
                int i = Integer.parseInt(arr[1]) - 1;
                String task_desc = getTask(i).toString();
                tasks.remove(i);
                System.out.println(div_line + "\n" + indent +  "Noted. I've removed this task:\n" + indent + task_desc + "\n" + indent +
                        "Now you have " + size() + " tasks in the list." +
                        "\n" + div_line);
            } catch (NumberFormatException | IndexOutOfBoundsException e) {
                System.out.println(div_line + "\n" + indent + "Oops! Enter a valid task no. to delete the task." +
                        "\n" + div_line);
            }
        }
        Storage.save(tasks);
    }

    public void printAll() {
        System.out.println(div_line);
        System.out.println(indent + "Here are the tasks in your list:");
        for (int i = 1; i < tasks.size() + 1; i++) {
            System.out.println(indent + i + " " + tasks.get(i - 1));
        }
        System.out.println(div_line);
    }

    public Task getTask(int i) {
        return tasks.get(i);
    }

    public int size() {
        return tasks.size();
    }
}
