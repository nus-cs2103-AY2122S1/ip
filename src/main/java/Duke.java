import java.util.*;
import java.util.stream.Stream;

public class Duke {
    final String strBreak = "    ____________________________________________________________\n";
    private ArrayList<Task> tasks = new ArrayList<>();
    private int count = 0;

    private void addTask(String input) throws DukeException {
        String type = input.split(" ")[0];
        String taskDescription = Stream.of(input.split(" "))
                .skip(1).reduce("", (x, y) -> x + " " + y);
        Task newTask = Task.makeTask(type, taskDescription);
        tasks.add(newTask);
        count++;
        String toPrint = String.format("     Got it. I've added this task:\n     %s\n     Now you have %x task%s in the list.",
                newTask.toString(), this.count, this.count > 1 ? "s" : "");
        System.out.println(strBreak + toPrint + "\n" + strBreak);
    }

    private String getTasks() {
        if (count == 0) {
            return (
                    strBreak + "    You have nothing on your list\n" + strBreak
                    );
        }
        String tasksStr = strBreak + "\n    Here are the tasks in your list:\n";
        for (int i = 0; i < count; i++) {
            tasksStr += "     " + (i + 1) + ". " + this.tasks.get(i).toString() + "\n";
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
                currentDuke.tasks.get(index - 1).markDone();
            } else if (input.split(" ")[0].equals("delete")) {
                int index = Integer.parseInt(input.split(" ")[1]);
                currentDuke.removeTask(index - 1);
            } else {
                try {
                    currentDuke.addTask(input);
                } catch (DukeException e){
                    System.out.println(e);
                }
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
    private void removeTask(int index) {
        Task removedTask = this.tasks.get(index);
        this.tasks.remove(index);
        this.count--;
        System.out.println(String.format("%s\n    Noted. I've removed this task:\n    %s\n    Now you have %x tasks in the list\n%s",
                strBreak, removedTask, this.count, strBreak));
    }
}
