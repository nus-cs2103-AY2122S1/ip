import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

    public ArrayList<String> tasks = new ArrayList<>();

    public String byeString() {
        return "Bye. Hope to see you again soon!";
    }

    public void addTask(String task) {
        tasks.add(task);
        System.out.println("added: " + task);
    }

    public void taskListString() {
        StringBuilder ans = new StringBuilder();
        for(int i = 0; i < tasks.size(); i++) {
            String task = tasks.get(i);
            if(i != tasks.size()-1) {
                ans.append(String.format("%d. %s\n", i + 1, task));
            } else {
                ans.append(String.format("%d. %s", i + 1, task));
            }
        }
        System.out.println(ans);
    }

    public void run() {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        if(input.equals("bye"))  {
            System.out.println(byeString());
        } else {
            interpretInput(input);
            run();
        }
    }

    public void interpretInput(String input) {
        if(input.equals("list")) {
            taskListString();
        } else {
            addTask(input);
        }
    }

    public static void main(String[] args) {
        Duke duke = new Duke();
        String hello = "Hello! I'm Duke\n" +
                "What can I do for you?";
        System.out.println(hello);
        duke.run();
    }
}
