import java.util.ArrayList;

public class TaskList {

    private static ArrayList<Task> tasks = new ArrayList<>();
    private static String indent = "    ";
    private static String div_line = "    ____________________________________________________________";

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
            } else { t = new ToDo(arr[1]); }
        } else if (arr.length > 1 && arr[0].equals(Type.EVENT.getType())) {
            String[] detail = arr[1].split("/at ", 2);
            if (detail.length == 1 || detail[1].trim().length() == 0 || detail[0].trim().length() == 0) {
                throw new DukeException("Invalid Event entry. Try something like: Meeting /at 2pm");
            } else { t = new Event(detail[0], detail[1]); }
        } else if (arr.length > 1 && arr[0].equals(Type.DEADLINE.getType())){
            String[] detail = arr[1].split("/by ", 2);
            if (detail.length == 1 || detail[1].trim().length() == 0 || detail[0].trim().length() == 0) {
                throw new DukeException("Invalid Deadline entry.Try something like : HW due /by 2pm");
            } else { t = new Deadline(detail[0], detail[1]); }
        } else {
            throw new DukeException("Invalid entry. Try again!");
        }
        
        if (t != null) {
            tasks.add(t);
            System.out.println(div_line + "\n" + indent +
                            "Got it. I've added this task:\n" + indent + t + "\n" + indent +
                            "Now you have " + size() + " tasks in the list." + "\n" + div_line);
        }
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
