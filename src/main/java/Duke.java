import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class Duke {

    // shows if the Duke chatbot has been activated
    private boolean activated;
    // Line Separator
    private static String SEP_LINE = "____________________________________________________________\n";
    // Standard boot response
    private final String bootMessage =
            SEP_LINE
            .concat(
            " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n"
            + "\nMade by Dr-Octavius\n")
            .concat(SEP_LINE)
            .concat(
            "Hello! I'm Duke\n"
            + "What can I do for you?\n")
            .concat(SEP_LINE);
    // Text Storage
    private final List<Task> storage = new ArrayList<Task>();

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
            out = out.concat((i+1) + ".[")
                    .concat(this.storage.get(i).getStatusIcon())
                    .concat("] ")
                    .concat(this.storage.get(i).getDescription());
        }
        return out;
    }

    // Level-3: Mark items as done
    public String done(int index) {
        this.storage.get(index).setDone();
        Task t = this.storage.get(index);
        return "Nice! I've marked this task as done: \n  ["
                .concat(t.getStatusIcon())
                .concat("] ")
                .concat(t.getDescription());
    }

    // Level-4: to-do command
    public String todo() {
        return "";
    }

    // wraps all messages between line seperators
    public String messageWrapper(String text) {
        return this.SEP_LINE.concat(text).concat("\n").concat(SEP_LINE);
    }

    // Task class that represents all Tasks
    public class Task {
        protected String description;
        protected boolean isDone;

        public Task(String description) {
            this.description = description;
            this.isDone = false;
        }

        public String getStatusIcon() {
            return (isDone ? "X" : " ");
        }

        public String getDescription() {
            return this.description;
        }

        public void setDone() {
            this.isDone = true;
        }
    }

    // decodes input from user and passes that argument to a response builder
    public int decoder(String userInput) {
        int res;
        if (userInput.equals("bye")) res = 0;
        else if (userInput.equals("list")) res = 1;
        else if (userInput.equals("done")) res = 2;
        else if (userInput.equals("todo")) res = 3;
        else res = 4;
        return res;
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
                case 3: output = chatBotObj.todo();
                        break;
                case 4: output = chatBotObj.add(nextIn.concat(scannerObj.nextLine()));
                        break;
                default: output = "";
            }
            output = chatBotObj.messageWrapper(output);
            System.out.println(output);
        }
    }
}
