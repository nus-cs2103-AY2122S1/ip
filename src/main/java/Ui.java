import java.util.Scanner;

public class Ui {
    private Scanner scanner;

    Ui(){
        this.scanner = new Scanner(System.in);  // Create a Scanner object
    }

    public String readCommand() {
        if (scanner.hasNextLine()) {
            return scanner.nextLine();
        }
        return "bye";
    }

    public void showWelcomeMessage(){
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("Hello! I'm Duke\n" +   // Welcome Message
                "What can I do for you?");
    }
    public void showListMessage(){
        System.out.println("Here are the tasks in your list:");
    }

    public void showHelpMessage(){
        String logo = "These are the commands that you can use:\n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
    }

    public void showAddMessage(Task task, Tasklist tasklist){
        System.out.println("Got it. I've added this task:");
        System.out.println(" " + task);
        System.out.println(String.format("Now you have %d tasks in the list", tasklist.getTasklistSize()));
    }

    public void showLoadingError(){
        System.out.println("The file you've loaded does not work. Delete it to start a new");
    }

    public void showByeMessage(){
        System.out.println("Bye. Hope to see you again soon!");
    }
    public void showDeleteMessage(Task task, Tasklist tasklist){
        System.out.println("Noted. I've removed this task:");
        System.out.println(" " + task);
        System.out.println(String.format("Now you have %d tasks in the list", tasklist.getTasklistSize()));
    }

    public void showDoneMessage(Task task, Tasklist tasklist){
        System.out.println("Nice! I've marked this task as done");
        System.out.println(task);

    }
}
