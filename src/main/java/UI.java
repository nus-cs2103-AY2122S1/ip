import javax.lang.model.type.ArrayType;
import java.util.ArrayList;
import java.util.Scanner;

public class UI {

    private static final String GREET = "Hi there! My name's Duke, how can I help you today?";
    private static final String EXIT = "Bye! See you next time!";

    private Scanner sc;

    public UI () {
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
        ArrayList<String> list = ToDoList.getTodoList();
        if (list.size() == 0) {
            return "You currently have no tasks!";
        } else {
            String userList = "";
            for (String task : list) {
                userList =  userList + (list.indexOf(task) + 1) + ". " + task + "\n";
            }
            return userList;
        }
    }

    public String addedTask(String task) {
        return String.format("added: %s", task);
    }

    /**
     public String formattedEchoCommand () {
     return String.format("...............\n %s \n...............", echoCommand());
     }
     */


}
