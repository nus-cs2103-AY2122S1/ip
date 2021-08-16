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
        while (!userInput.equalsIgnoreCase("bye")) {
            if (userInput.equalsIgnoreCase("list")) {
                printList(taskList);
            } else if (userInput.split("\\s")[0].equalsIgnoreCase("done")) {
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
        boolean valid = false;
        boolean keywordPresent = true;
        Task task;
        try {
            String[] splitTasks = inp.split("\\s", 2);
            String taskType = splitTasks[0].toLowerCase(); ;

            switch (taskType) {
                case "todo":
                    valid = true;
                    task = new ToDo(splitTasks[1]);
                    list.add(task);
                    break;
                case "deadline":
                    valid = true;
                    String desc = splitTasks[1].split("/by")[0];
                    String date = splitTasks[1].split("/by")[1];
                    task = new Deadline(desc, date);
                    list.add(task);
                    break;
                case "event":
                    valid = true;
                    String details = splitTasks[1].split("/at")[0];
                    String at = splitTasks[1].split("/at")[1];
                    task = new Event(details, at);
                    list.add(task);
                    break;
                default:
                    keywordPresent = false;
                    task = new Task("");
                    System.out.println("Invalid Keyword");
                    break;
            }
            checkValidity(task, valid, keywordPresent);
        } catch (IndexOutOfBoundsException ex) {
                System.out.println("Please enter a valid task description (and date if required)" + "\n" + LINE); // temp validation
            }
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

    public void printRemainingTasks() {
        if (taskList.size() == 1) {
            System.out.println("Now you have 1 task in the list." + "\n" + LINE);
        } else {
            System.out.println(String.format("Now you have %s tasks in the list.", taskList.size()) + "\n" + LINE);
        }
    }

    // temp elementary input validation - will be replaced by exceptions in subsequent iterations.
    public void checkValidity(Task task, boolean valid, boolean keywordPresent){
        if (valid) {
            System.out.println("Got it. I've added this task:");
            System.out.println(task.toString());
            printRemainingTasks();
        } else if (keywordPresent){
            System.out.println("Please enter a valid task description and date (if applicable)"+ "\n" + LINE);
        } else {
            System.out.println("Please use the permitted keywords (todo, deadline, event) to specify your task type" + "\n" + LINE);
        }
    }
}