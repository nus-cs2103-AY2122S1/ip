import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

// A-Enums: Set Enums for Task type
enum TASK_TYPE {
    T,D,E
}

public class Duke {

    // shows if the Duke chat bot has been activated
    private boolean activated;
    // Line Separator
    private final String SEP_LINE = "____________________________________________________________";
    // Standard boot response
    private final String bootMessage =
            SEP_LINE
            .concat(
            " \n____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n"
            + "\nMade by Dr-Octavius\n")
            .concat(SEP_LINE)
            .concat(
            "\nHello! I'm Duke\n"
            + "What can I do for you?\n")
            .concat(SEP_LINE);
    // Level-6 -> A-Collections: Task List
    private final List<Task> taskList = new ArrayList<>();
    // Level-7: File Storage associated to an instance of Duke
    private final Storage fileStorage;

    // Duke Constructor
    public Duke(String storagePath, String fileName) {
        activated = true;
        fileStorage = new Storage(storagePath, fileName);
    }

    // Checks if Duke chat bot has been activated
    public boolean isActive() {
        return activated;
    }

    // Level-1: Greets user when Duke is initialised
    public void greet() {
        System.out.println(bootMessage);
    }

    // Level-1: echos user message back to user
    // Level-5: returns the error message for unknown inputs
    public String echo() {
        return "☹ OOPS!!! I'm sorry, but I don't know what that means :-(";
    }

    // Level-1: Exit Message triggered by "bye"
    public String exit() {
        activated = false;
        return " Bye. Hope to see you again soon!";
    }

    // Level-2: adds new task to user
    public String add(String text) {
        Task nextTask = new Task(text);
        taskList.add(nextTask);
        fileStorage.saveEntry(nextTask);
        return "added: ".concat(text);
    }

    // Level-4: setting of task urgency to "to-do"
    public String todo(String description) {
        Task task = new Todo(description,TASK_TYPE.T);
        taskList.add(task);
        fileStorage.saveEntry(task);
        return "Got it. I've added task:\n  "
                .concat(task.toString())
                .concat("\nNow you have ".concat(taskList.size() + "").concat(" tasks in the list."));
    }

    // Level-4: setting of task urgency to "deadline"
    // Level-4: setting of task urgency to "deadline"
    public String deadline(String description, String by) {
        Task task = new Deadline(description,by,TASK_TYPE.D);
        taskList.add(task);
        fileStorage.saveEntry(task);
        return "Got it. I've added task:\n  "
                .concat(task.toString())
                .concat("\nNow you have ".concat(taskList.size() + "").concat(" tasks in the list."));
    }

    // Level-4: setting of task urgency to "event"
    public String event(String description, String at) {
        Task task = new Event(description,at,TASK_TYPE.E);
        taskList.add(task);
        fileStorage.saveEntry(task);
        return "Got it. I've added task:\n  "
                .concat(task.toString())
                .concat("\nNow you have ".concat(taskList.size() + "").concat(" tasks in the list."));
    }

    // Level-6: delete items from list
    public String delete(int index) {
        Task task = taskList.get(index);
        taskList.remove(index);
        fileStorage.deleteEntry(task);
        return "Noted. I've removed task:\n  "
                .concat(task.toString())
                .concat("\nNow you have ".concat(taskList.size() + "").concat(" tasks in the list."));
    }

    // Level-3: Mark items as done
    public String done(int index) {
        taskList.get(index).setDone();
        Task task = taskList.get(index);
        return "Nice! I've marked task as done: \n  ".concat(task.toString());
    }

    // Level-2: Lists all items in Task List
    public String list() {
        String out = "Here are the tasks in your list:\n";
        System.out.println(taskList);
        for (int i = 0; i < taskList.size(); i++) {
            if (i != 0) out = out.concat("\n");
            out = out.concat((i+1) + ".").concat(taskList.get(i).toString());
        }
        return out;
    }

    // wraps all messages between line separators
    public String messageWrapper(String text) {
        return SEP_LINE.concat("\n").concat(text).concat("\n").concat(SEP_LINE);
    }

    // decodes input from user and passes that argument to a response builder
    public int decoder(String userInput) {
        int res;
        switch (userInput) {
        case "bye":
            res = 0;
            break;
        case "list":
            res = 1;
            break;
        case "done":
            res = 2;
            break;
        case "todo":
            res = 3;
            break;
        case "deadline":
            res = 4;
            break;
        case "event":
            res = 5;
            break;
        case "delete":
            res = 6;
            break;
        default:
            res = 7;
            break;
        }
        return res;
    }

    // Level-5 -> A-Exceptions: Added try and except to handle errors
    private void run() {
        Scanner scannerObj = new Scanner(System.in);
        greet();
        while (isActive()) {
            String nextIn;
            try {
                nextIn = scannerObj.next();
            } catch (NoSuchElementException e) {
                continue;
            }
            int selector = decoder(nextIn);
            String output;
            switch (selector) {
            case 0:
                output = exit();
                break;
            case 1:
                output = list();
                break;
            case 2:
                output = done(scannerObj.nextInt() - 1);
                break;
            case 3:
                try {
                    output = todo(scannerObj.nextLine());
                    break;
                } catch (NoSuchElementException e) {
                    output = "☹ OOPS!!! The description of a todo cannot be empty.";
                    break;
                }
            case 4:
            case 5:
                String desc = "";
                String var;
                int x = 0;
                while (true) {
                    nextIn = scannerObj.next();
                    if (nextIn.equals("/by") || nextIn.equals("/at")) {
                        x = 1;
                        continue;
                    }
                    if (x == 1) {
                        var = nextIn;
                        var = var.concat(scannerObj.nextLine());
                        break;
                    } else {
                        desc = (desc.equals("") ? nextIn : desc.concat(" ").concat(nextIn));
                    }
                }
                output = (selector == 4 ? deadline(desc, var) : event(desc, var));
                break;
            case 6:
                output = delete(scannerObj.nextInt() - 1);
                break;
            case 7:
                output = add(nextIn.concat(scannerObj.nextLine()));
                break;
            default:
                output = echo();
            }
            output = messageWrapper(output);
            System.out.println(output);
        }
    }

    public static void main(String[] args) {
        Duke chatBotObj = new Duke("data", "data.txt");
        chatBotObj.run();
    }
}
