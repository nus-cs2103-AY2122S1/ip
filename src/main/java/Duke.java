import java.util.*;
import java.util.stream.Stream;

public class Duke {

    Task[] tasks = new Task[100];
    int count = 0;

    private void addTask(String input) {
        String type = input.split(" ")[0];
        String taskDescription = Stream.of(input.split(" "))
                .skip(1).reduce("", (x, y) -> x + " " + y);
        Task newTask = Task.makeTask(type, taskDescription);
        tasks[count] = newTask;
        count++;
        String strBreak = "    ____________________________________________________________ \n";
        String toPrint = String.format("     Got it. I've added this task: \n     %s\n     Now you have %x task%s in the list.",
                newTask.toString(), this.count, this.count > 1 ? "s" : "");
        System.out.println(strBreak + toPrint + "\n" + strBreak);
    }

    private String getTasks() {
        String strBreak = "    ____________________________________________________________ \n";
        String tasksStr = strBreak + "\n    Here are the tasks in your list: \n";
        for (int i = 0; i < count; i++) {
            tasksStr += "     " + (i + 1) + ". " + this.tasks[i].toString() + "\n";
        }
        return (tasksStr + strBreak);
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
