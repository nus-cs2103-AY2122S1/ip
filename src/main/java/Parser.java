import java.time.LocalDate;
import java.util.ArrayList;

public class Parser {

    private String input;

    public Parser(String input) {
        this.input = input;
    }

    public static void displayLabel(String information) {
        String label = "    ____________________________________________________________\n" +
                "     " +
                information + "\n    " +
                "____________________________________________________________\n";
        System.out.println(label);
    }

    public void useInput(TaskList tasks) throws DukeException {
        exceptionCommand(input, tasks);
        if (input.equals("list"))  {
            listCommand(tasks);
        } else if (input.contains("done")) {
            doneCommand(tasks);
        } else if (input.contains("delete")) {
            deleteCommand(tasks);
        } else if (input.contains("find"))     {
            findCommand(tasks);
        } else if (input.contains("deadline") || input.contains("event")) {
            timingCommand(input, tasks);
        } else {
            addTaskCommand(input, tasks);
        }
    }

    public void listCommand(TaskList tasks) {
        String itemCollection = getItems(tasks);
        displayLabel(("Here are the tasks in your list: \n"
                + "     " + itemCollection));
    }

    public void doneCommand(TaskList tasks) {
        int index = Integer.parseInt(input.substring(5, 6)) - 1;
        tasks.getTask(index).setIsDone();
        displayLabel("Nice! I've marked this task as done: \n" +
                "       " + tasks.getTask(index).toString());
    }

    public void addTaskCommand(String input, TaskList tasks) {
        if (input.contains("todo")) {
            String info = input.substring(5);
            tasks.addTask(new ToDo(info));
        } else {
            tasks.addTask(new Task(input));
        }
        displayLabel("Got it. I've added this task:  \n" +
                "       " + tasks.getTask(tasks.size() - 1).toString() + "\n     " +
                "Now you have " + Integer.toString(tasks.size()) + " tasks in the list.");
    }

    public void timingCommand(String input, TaskList tasks) {
        if (input.contains("deadline")) {
            String[] info = input.substring(9).split(" /by ");
            tasks.addTask(getDateAndTime(info, input, "deadline"));
        } else {
            String[] info = input.substring(6).split(" /at ");
            tasks.addTask(getDateAndTime(info, input, "event"));
        }
        displayLabel("Got it. I've added this task:  \n" +
                "       " + tasks.getTask(tasks.size() - 1).toString() + "\n     Now you have "
                + Integer.toString(tasks.size()) + " tasks in the list.");
    }

    public void exceptionCommand(String input, TaskList tasks) throws DukeException {
        if (input.equals("todo") || input.equals("event") || input.equals("deadline")) {
            throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
        } else if (input.equals("done")) {
            throw new DukeException("☹ OOPS!!! The completed task number must be given.");
        } else if (input.equals("delete")) {
            throw new DukeException("☹ OOPS!!! You need to specify which task you want to delete.");
        } else if (input.equals("blah")) {
            throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }

    public void deleteCommand(TaskList tasks) {
        int index = Integer.parseInt(input.substring(7, 8)) - 1;
        Task removedTask = tasks.getTask(index);
        tasks.removeTask(removedTask);
        displayLabel("Noted. I've removed this task:  \n" +
                "       " + removedTask.toString() + "\n     Now you have "
                + Integer.toString(tasks.size()) + " tasks in the list.");
    }

    public void findCommand(TaskList tasks) {
        TaskList newTasks = new TaskList(new ArrayList<>());
        if (input.contains("find")) {
            String item = input.split(" ")[1];
            for (int i = 0; i < tasks.size(); i++) {
                if (tasks.getTask(i).newTask.contains(item)) {
                    newTasks.addTask(tasks.getTask(i));
                }
            }
            displayLabel(
                    "Here are the matching tasks in your list: \n" +
                            "     " + getItems(newTasks));
        }
    }


    public static String getItems(TaskList items) {
        String collection = "";
        for (int index = 0; index < items.size(); index++) {
            if (index != 0) { collection += "     "; }
            String info = items.getTask(index).toString();
            collection += Integer.toString(index + 1) + "." + info;
            if (index != items.size() - 1) {
                collection += "\n";
            }
        }
        return collection;
    }

    public static Task getDateAndTime(String[] info, String input, String type) {
        String[] potentialDate = info[1].split(" ");
        LocalDate date = null;
        if (!getTime(potentialDate[0]).equals("")) {
            info[1] = getTime(potentialDate[0]);
        } else if (potentialDate.length > 1 &&
                !getTime(potentialDate[1]).equals("")) {
            potentialDate[1] = getTime(potentialDate[1]);
            info[1] = potentialDate[0] + " " + potentialDate[1];
        }
        if (potentialDate.length > 1 && potentialDate[0].contains("/")) {
            date = LocalDate.parse(parseDates(potentialDate[0]));
        }
        if (type.equals("deadline")) {
            if (date != null) {
                return new Deadline(info[0], date, potentialDate[1]);
            } else {
                return new Deadline(info[0], info[1]);
            }
        } else {
            if (date != null) {
                return new Event(info[0], date, potentialDate[1]);
            } else {
                return new Event(info[0], info[1]);
            }
        }
    }

    public static String parseDates(String date) {
        String[] sep = date.split("/");
        if (Integer.parseInt(sep[0]) < 10) {
            sep[0] = "0" + sep[0];
        }
        return sep[2] + "-" + sep[1] + "-" + sep[0];
    }

    public static String getTime(String time) {
        try {
            int timeVal = Integer.parseInt(time) / 100;
            if (timeVal > 12) { timeVal -= 12; }
            return Integer.toString(timeVal) + "pm";
        } catch (NumberFormatException e) {
            return "";
        }
    }
}
