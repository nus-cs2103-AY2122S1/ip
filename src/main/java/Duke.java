import java.util.*;

public class Duke {

    String[] tasks = new String[100];
    int count = 0;

    private void addTask(String task) {
        tasks[count] = task;
        count++;
        System.out.println("added: " + task);
    }

    private String getTasks() {
        String tasksStr = "";
        for (int i = 0; i < count; i++) {
            tasksStr += (i + 1) + "." + this.tasks[i] + "\n";
        }
        return (tasksStr);
    }

    public static void main(String[] args) {
        Duke currentDuke = new Duke();
        System.out.println("Urgh I hate having to wake up. Why did you do that");
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        while (!input.equals("bye")) {
            if (input.equals("list")) {
                System.out.println(currentDuke.getTasks());
            } else {
                currentDuke.addTask(input);
            }
            input = sc.nextLine();
        }
        Duke.sayBye();
    }

    private static void sayBye() {
        System.out.println("Don't wake me up again");
    }

//    private static void echo(String input) {
//        System.out.println(input);
//    }
}
