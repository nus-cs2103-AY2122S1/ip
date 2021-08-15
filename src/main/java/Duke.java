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

    private final ArrayList<Task> taskList = new ArrayList<>();

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
            } else if (userInput.contains("done")) {
                completeTask(userInput);
            } else {
                addTaskToList(userInput, taskList);
            }
            userInput = sc.nextLine();
        }
        System.out.println(LINE + BYE + LINE);
        sc.close();
    }

    public void printList(ArrayList<Task> list) {
        boolean allTasksDone = true;
        System.out.println("Here are the tasks in your list:");
        for(int i = 0; i < list.size(); i++) {
            int ind = i + 1;
            System.out.println(ind + ". " + list.get(i).toString());
            if (!list.get(i).getStatus()) {
                allTasksDone = false;
            }
        }
        if (allTasksDone) {
            System.out.println("All tasks are complete!!");
        }
        System.out.println(LINE);
    }

    public void addTaskToList(String inp, ArrayList<Task> list) {
        Task task = new Task(inp);
        list.add(task);
        System.out.println("added: " + inp + "\n" + LINE);
    }

    public void completeTask(String input) {
        try {
            String indexOfTask = input.substring(5);
            int index = Integer.parseInt(indexOfTask);
            Task currTask = taskList.get((index - 1));
            if (currTask.getStatus()) {
                System.out.println("The task has already been completed" + "\n" + LINE);
            } else {
                currTask.markAsDone();
                System.out.println("Nice! I've marked this task as done: ");
                System.out.println(currTask.toString() + "\n" + LINE);
            }
        } catch (NumberFormatException | IndexOutOfBoundsException exception) {
            System.out.println("Please enter a valid task number" + "\n" +LINE);
        }
    }
}