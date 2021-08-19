import java.util.*;

public class Duke {

    //To do list storage DS
    private static ArrayList<Task> list = new ArrayList<>();

    //Constants
    // Some String format
    private static String start = "____________________________________________________________\n";
    private static String end = "____________________________________________________________";
    private static String logo = " ____        _        \n"
                                + "|  _ \\ _   _| | _____ \n"
                                + "| | | | | | | |/ / _ \\\n"
                                + "| |_| | |_| |   <  __/\n"
                                + "|____/ \\__,_|_|\\_\\___|\n";


    public static void welcomeMessage() {
        String welcome = "Hello! I'm Duke. A friendly chatbot!! :)\n" +
                            "What can I do for you?\n";
        System.out.println(Duke.start + Duke.logo + "\n" +  welcome + Duke.end);
    }

    public static void getPrompt() {
        String prompt_message = "Add to-do list ({input})/ View list (list) / Complete task (done {input}) / End (bye) :";
        System.out.println(prompt_message);
    }

    public static void addTask(Task t) {
        Duke.list.add(t);
        System.out.println(Duke.start + "added: " + t.toString() + "\n" + Duke.end);
    }

    public static void getList() {
        System.out.println(Duke.end);
                System.out.println("Your to-do list:");
                for (int i = 0; i < Duke.list.size(); i++) {
                    String res = (i + 1) + ". " + Duke.list.get(i).toString();
                    System.out.println(res);
                }
                System.out.println(Duke.end);

    }

    public static void markDone(int i) {
        System.out.println(Duke.start + "Nice! I've marked this task as done: ");
        Duke.list.get((int) i - 1).markAsDone();
        String res = Duke.list.get(i-1).toString();
        System.out.println("  " + res + "\n" +  Duke.end);
    }

    public static void main(String[] args) {

        String end_message = "Bye. I hope to talk to you again soon! :)";

        //Print welcome message to the user
        Duke.welcomeMessage();

        //Init new scanner to take in inputs
        Scanner s = new Scanner(System.in);
        Duke.getPrompt();

        String input = s.nextLine();


        //A loop to check for bye. Else will echo the users input
        while (!input.equals("bye")) {
            if (input.contains("list")) {
                Duke.getList();
            } else if (input.contains("done")) {
                int i = Integer.valueOf(input.substring(5));
                Duke.markDone(i);
            } else {
                Task t = new Task(input);
                Duke.addTask(t);
            }

            //Get next command for the loop
            Duke.getPrompt();
            input = s.nextLine();
        }
        System.out.println(Duke.start + end_message + "\n" + Duke.end);
        s.close();
    }
}

