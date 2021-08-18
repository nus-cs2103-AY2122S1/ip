import java.util.Scanner;

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
                    "\t \t 'list': view all tasks in your task list\n" +
                    "\t \t 'bye': exit chat\n" +
                "\tWhat can I do for you?\n"

                )
        );
    }

    private void closeDukeChatBot() {
        this.isOpen = false;
        System.out.println(chatBotMessage("\tBye. Hope to see you again soon!\n"));
    }

    private void addTaskToList(String item) {
        this.listOfTasks.addTaskToList(item);
    }

    private String chatBotMessage(String reply) {
        return "\t____________________________________________________________\n" +
                reply +
                "\t____________________________________________________________\n";
    }



    public static void main(String[] args) {
        Duke d = new Duke();
        d.openDukeChatBot();
        Scanner sc = new Scanner(System.in);
        while (d.isOpen) {
            String userInput = sc.nextLine();
            if (userInput.equals("bye")) {
                d.closeDukeChatBot();
                sc.close();
            } else if (userInput.equals("list")) {
                System.out.println(d.chatBotMessage(d.listOfTasks.toString()));
            } else {
                d.addTaskToList(userInput);
                System.out.println(d.chatBotMessage("\tadded: " + userInput + "\n"));
            }
        }

    }
}

