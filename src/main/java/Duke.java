import java.util.*;

public class Duke {
    private static final String HELLO = "\nHello! I'm Duke \nWhat can I do for you?\n";
    private static final String LINE = "===============================================";
    private static final String logo =
            " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";
    private static final String BYE = "\nBye. Hope to see you again soon!\n";

    private final ArrayList<String> taskList = new ArrayList<>();

    public static void main(String[] args) {
        Duke chatbot = new Duke();
        chatbot.start();
    }

    public void start() {
        System.out.println(logo + HELLO + LINE);
        Scanner sc = new Scanner(System.in);
        String userInput = sc.nextLine();
        while (!userInput.equals("bye")) {
            if (userInput.equals("list")) {
                printList(taskList);
            } else {
                addTaskToList(userInput, taskList);
            }
            userInput = sc.nextLine();
        }
        System.out.println(LINE + BYE + LINE);
        sc.close();
    }

    public void printList(ArrayList<String> list) {
        for(int i = 0; i < list.size(); i++) {
            int ind = i + 1;
            System.out.println(ind + ". " + list.get(i));
        }
        System.out.println(LINE);
    }

    public void addTaskToList(String task, ArrayList<String> list) {
        list.add(task);
        System.out.println("added: " + task + "\n" + LINE);
    }
}
