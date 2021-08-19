import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class Duke {

    // shows if the Duke chatbot has been activated
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
    // Level-6 -> A-Collections: Text Storage
    private final List<Task> storage = new ArrayList<>();

    // Duke Constructor
    public Duke() {
        this.activated = true;
    }

    // Checks if Duke Chatbot has been activated
    public boolean isActive() {
        return this.activated;
    }

    // Level-1: Greets user when Duke is initialised
    public void greet() {
        System.out.println(this.bootMessage);
    }

    // Level-1: echos user message back to user
    public String echo(String text) {
        return text;
    }

    // Level-1: Exit Message triggered by "bye"
    public String exit() {
        this.activated = false;
        return " Bye. Hope to see you again soon!";
    }


    // Level-2: adds new task to user
    public String add(String text) {
        Task nextTask = new Task(text);
        this.storage.add(nextTask);
        return "added: ".concat(text);
    }
    // Level-2: Lists all items in storage
    public String list() {
        String out = "Here are the tasks in your list:\n";
        for (int i = 0; i < this.storage.size(); i++) {
            if (i != 0) out = out.concat("\n");
            out = out.concat((i+1) + ".").concat(this.storage.get(i).toString());
        }
        return out;
    }

    // Level-3: Mark items as done
    public String done(int index) {
        this.storage.get(index).setDone();
        Task task = this.storage.get(index);
        return "Nice! I've marked this task as done: \n  ".concat(task.toString());
    }

    // Level-4: setting of task urgency to "to-do"
    public String todo(String description) {
        Task task = new Todo(description,state.T);
        this.storage.add(task);
        return "Got it. I've added this task:\n  "
                .concat(task.toString())
                .concat("\nNow you have ".concat(this.storage.size() + "").concat(" tasks in the list."));
    }

    // Level-4: setting of task urgency to "deadline"
    public String deadline(String description, String by) {
        Task task = new Deadline(description,by,state.D);
        this.storage.add(task);
        return "Got it. I've added this task:\n  "
                .concat(task.toString())
                .concat("\nNow you have ".concat(this.storage.size() + "").concat(" tasks in the list."));
    }

    // Level-4: setting of task urgency to "event"
    public String event(String description, String at) {
        Task task = new Event(description,at,state.E);
        this.storage.add(task);
        return "Got it. I've added this task:\n  "
                .concat(task.toString())
                .concat("\nNow you have ".concat(this.storage.size() + "").concat(" tasks in the list."));
    }

    // Level-6: delete items from list
    public String delete(int index) {
        Task task = this.storage.get(index);
        this.storage.remove(index);
        return "Noted. I've removed this task:\n  "
                .concat(task.toString())
                .concat("\nNow you have ".concat(this.storage.size() + "").concat(" tasks in the list."));
    }

    // wraps all messages between line seperators
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

    // A-Enums: Set Enums for Task type
    protected enum state {
        T,D,E
    }

    // Task class that represents all Tasks
    private static class Task {

        protected String description;
        protected String by;
        protected String at;
        protected boolean isDone;
        protected state STATE;

        // overloaded constructors for seperate task types
        public Task(String description, String var, state STATE) {

            if (STATE.equals(state.D)) this.by = var;
            else this.at = var;

            this.description = description;
            this.isDone = false;
            this.STATE = STATE;
        }

        public Task(String description, state STATE) {
            this.description = description;
            this.isDone = false;
            this.STATE = STATE;
        }

        public Task(String description) {
            this.description = description;
            this.isDone = false;
        }

        public String getStatusIcon() {
            return (isDone ? "X" : " ");
        }

        public String getEventIcon() {
            String out;
            switch (this.STATE) {
                case T: out = "T";
                        break;
                case D: out = "D";
                        break;
                case E: out = "E";
                        break;
                default: out = " ";
                        break;
            }
            return out;
        }

        public String getDescription() {
            return this.description;
        }

        public void setDone() {
            this.isDone = true;
        }

        @Override
        public String toString() {
            return "["
                    .concat(this.getEventIcon())
                    .concat("][")
                    .concat(this.getStatusIcon())
                    .concat("] ")
                    .concat(this.getDescription());
        }
    }

    //Level-4 -> A-Inheritance: To-do Task Class
    private static class Todo extends Task {

        public Todo(String description,state STATE) {
            super(description, STATE);
        }

    }

    //Level-4 -> A-Inheritance: Deadline Task Class
    private static class Deadline extends Task {

        public Deadline(String description, String at, state STATE) {
            super(description, at, STATE);
        }

        @Override
        public String toString() {
            return super.toString().concat(" (by: ".concat(this.by).concat(")"));
        }
    }

    //Level-4 -> A-Inheritance: Event Task Class
    private static class Event extends Task {

        public Event(String description, String at, state STATE) {
            super(description, at, STATE);
        }

        @Override
        public String toString() {
            return super.toString().concat(" (at: ".concat(this.at).concat(")"));
        }
    }

    public static void main(String[] args) {
        Scanner scannerObj = new Scanner(System.in);
        Duke chatBotObj = new Duke();
        chatBotObj.greet();
        while (chatBotObj.isActive()) {
            String nextIn = scannerObj.next();
            int selector = chatBotObj.decoder(nextIn);
            String output;
            switch (selector) {
                case 0: output = chatBotObj.exit();
                        break;
                case 1: output = chatBotObj.list();
                        break;
                case 2: output = chatBotObj.done(scannerObj.nextInt()-1);
                        break;
                case 3: output = chatBotObj.todo(scannerObj.nextLine());
                        break;
                case 4:
                case 5:
                    String desc = "";
                    String var;
                    int x = 0;
                    while(true) {
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
                    output = (selector == 4 ? chatBotObj.deadline(desc,var) : chatBotObj.event(desc,var));
                    break;
                case 6: output = chatBotObj.delete(scannerObj.nextInt()-1);
                        break;
                case 7: output = chatBotObj.add(nextIn.concat(scannerObj.nextLine()));
                        break;
                default: output = chatBotObj.echo(nextIn.concat(scannerObj.nextLine()));
            }
            output = chatBotObj.messageWrapper(output);
            System.out.println(output);
        }
    }
}
