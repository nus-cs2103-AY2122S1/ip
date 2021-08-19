import java.util.Scanner;

/**
 * Represents a Duke chatbot that can add tasks
 * to users' to-do list.
 *
 * @author Ne Zhijian, Didymus A0218159Y
 */
public class Duke {
    private boolean isOpen;
    private final String dukeLogo;
    private TaskList listOfTasks;

    private Duke() { // constructor for Duke chat bot object
        this.dukeLogo = "      ____        _        \n" +
                "     |  _ \\ _   _| | _____ \n" +
                "     | | | | | | | |/ / _ \\\n" +
                "     | |_| | |_| |   <  __/\n" +
                "     |____/ \\__,_|_|\\_\\___|\n";
        this.listOfTasks = TaskList.makeNewTaskList();
    }

    private void openDukeChatBot() {
        this.isOpen = true;
        System.out.println(chatBotMessage(this.dukeLogo + "\n" +
                "\tHELLO! I'm Duke\n" +
                "\tTo ease your experience, here are some commands you can type: \n" +
                    "\t\t 'list': view all tasks in your task list\n" +
                    "\t\t 'todo': add a todo task in your task list\n" +
                    "\t\t 'deadline': add a deadline task in your task list\n" +
                    "\t\t 'event': add an event task in your task list\n" +
                    "\t\t 'bye': exit chat\n" +
                "\tWhat can I do for you?\n"

                )
        );
    }

    private void closeDukeChatBot() {
        this.isOpen = false;
        System.out.println(chatBotMessage("\tBye. Hope to see you again soon!\n"));
    }

    private Task addTaskToList(String item) {
        Task task = Task.createTask(item);
        this.listOfTasks.addTaskToList(task);
        return task;
    }

    private String chatBotMessage(String reply) {
        return "\t____________________________________________________________\n" +
                reply +
                "\t____________________________________________________________\n";
    }

    private String getFirstWord(String s) {
        String[] arrString = s.split(" ", 2);
        return arrString[0];
    }

    private int getSecondNum(String s) throws DukeIncorrectInputs {
        String[] arrString = s.split(" ", 2);
        try {
            String second = arrString[1];
            Integer.parseInt(second);
        } catch (IllegalArgumentException e) {
            throw new DukeDoneIncorrectArgument();
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DukeDoneIncorrectArgument();
        }
        return Integer.parseInt(arrString[1]);
    }

    private Task deleteTask(int i) throws DukeNoSuchTask {
        return this.listOfTasks.deleteTask(i);
    }

    public static void main(String[] args) {
        Duke d = new Duke();
        d.openDukeChatBot();
        Scanner sc = new Scanner(System.in);
        while (d.isOpen) {
            try {
                String userInput = sc.nextLine();
                if (userInput.equals("bye")) {
                    d.closeDukeChatBot();
                    sc.close();
                } else if (userInput.equals("list")) {
                    System.out.println(d.chatBotMessage(d.listOfTasks.toString()));
                } else if (d.getFirstWord(userInput).equals("done")) {
                    int i = d.getSecondNum(userInput);
                    d.listOfTasks.setTaskAsDone(i);
                    System.out.println(d.chatBotMessage("\tNice! I've marked this task as done:\n" +
                            "\t\t" + d.listOfTasks.getTask(i - 1) + "\n"));
                } else if (d.getFirstWord(userInput).equals("delete")) {
                    int i = d.getSecondNum(userInput);
                    Task taskRemoved = d.deleteTask(i);
                    System.out.println(d.chatBotMessage("\tNoted. I've removed this task:\n" +
                            "\t\t" + taskRemoved + "\n"));
                } else {
                    System.out.println(d.chatBotMessage("\tGot it. I've added this task:\n" +
                            "\t\t" + d.addTaskToList(userInput) + "\n" +
                            "\tNow you have " + d.listOfTasks.getTotal() + " in your list.\n"
                            ));
                }
            } catch (DukeException e) {
                System.err.println(d.chatBotMessage(e.getMessage() + "\n"));
            }
        }
    }
}

