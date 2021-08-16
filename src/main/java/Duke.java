import java.util.*;

public class Duke {

    Task[] tasks = new Task[100];
    int count = 0;

    private void addTask(String taskDescription) {
        Task newTask = new Task(taskDescription, count);
        tasks[count] = newTask;
        count++;
        System.out.println("added: " + taskDescription);
    }

    private String getTasks() {
        String tasksStr = "Here are the tasks in your list: \n";
        for (int i = 0; i < count; i++) {
            tasksStr += this.tasks[i].toString() + "\n";
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
            } else if (input.split(" ")[0].equals("done")) {
                int index = Integer.parseInt(input.split(" ")[1]);
                currentDuke.tasks[index - 1].markDone();
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
