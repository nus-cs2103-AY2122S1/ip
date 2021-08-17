import java.util.ArrayList;
import java.util.Scanner;

public class Messages {

    private static final String GREET = "Hi there! My name's Duke, how can I help you today?";
    private static final String EXIT = "Bye! See you next time!";

    private Scanner sc;

    public Messages () {
        this.sc = new Scanner(System.in);
    }

    public String greet () {
        return GREET;
    }

    public String exit () {
        return EXIT;
    }

    public String echoCommand () {
        return sc.nextLine();
    }

    public String retrieveList() {
        ArrayList<Task> list = ToDoList.getTodoList();
        if (list.size() == 0) {
            return "You currently have no tasks!";
        } else {
            String userList = "";
            for (Task task : list) {
                userList =  userList + (list.indexOf(task) + 1) + ". " + task.toString() + "\n";
            }
            return "Here are the tasks in your list: \n" + userList;
        }
    }

    public String addedTask(Task task) {
        return String.format("Got it. I've added this task: \n %s \nNow you have %d tasks in the list.",
                task.toString(), ToDoList.getTodoList().size());
    }

    public String doneTask (Task task) {
        return String.format("Nice! I've marked this task as done: \n %s", task.toString());
    }

}
