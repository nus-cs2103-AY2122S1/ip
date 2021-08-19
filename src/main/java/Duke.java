import java.util.Scanner;
public class Duke {
    int numOfTasks;
    Task[] tasks;

    Duke() {
        numOfTasks= 0;
        tasks = new Task[100];
    }

    void toDo() {
        for (int i = 0; i < this.numOfTasks; i++) {
            Task temp = this.tasks[i];
            System.out.println(i+1+".["+temp.getStatusIcon() +"] "+temp.getDescription());
        }
        if (this.numOfTasks == 0) {
            System.out.println("List is empty!");
        }
    }

    void markAsDone(String taskNumber) {
        try{
            int index = Integer.parseInt(taskNumber) - 1;
            if (index > numOfTasks - 1) {
                System.out.printf("Cannot find task %s. There are only %s tasks.", taskNumber, numOfTasks);
                return;
            }
            this.tasks[index].markAsDone();
        }
        catch (NumberFormatException ex){
            System.out.println("Task must be an integer!");
        }
    }
    public static void main(String[] args) {
        Duke duke = new Duke();
        System.out.println("Hello I am Duke. \nWhat can I do for you?");
        Scanner scanner = new Scanner(System.in);
        String echo = scanner.nextLine();
        while(true) {
            if (echo.equals("bye")) {
                System.out.println("Bye! See you next time!");
                break;
            }
            if (echo.equals("List")) {
                duke.toDo();
                echo = scanner.nextLine();
                continue;
            }
            if (echo.startsWith("done")) {
                String[] parsed = echo.split(" ");
                duke.markAsDone(parsed[1]);
                echo = scanner.nextLine();
                continue;
            }
            System.out.println(echo);
            duke.tasks[duke.numOfTasks] = new Task(echo);
            duke.numOfTasks = duke.numOfTasks + 1;
            echo = scanner.nextLine();
        }

    }
}
