import java.util.Scanner;
public class Duke {
    int numOfTasks;
    Task[] tasks;

    Duke() {
        this.numOfTasks= 0;
        this.tasks = new Task[100];
    }

    void list() {
        for (int i = 0; i < this.numOfTasks; i++) {
            Task temp = this.tasks[i];
            System.out.printf("%s. %s\n", i+1, temp);
        }
        if (this.numOfTasks == 0) {
            System.out.println("List is empty!");
        }
    }

    void markAsDone(String taskNumber) {
        try{
            int index = Integer.parseInt(taskNumber) - 1;
            if (index > numOfTasks - 1) {
                System.out.printf("Cannot find task %s. There are only %s tasks.\n", taskNumber, numOfTasks);
                return;
            }
            this.tasks[index].markAsDone();
        }
        catch (NumberFormatException ex){
            System.out.println("Task must be an integer!");
        }
    }

    void addTask(String task) {
        if (task.startsWith("todo ")){
            this.tasks[this.numOfTasks] = new ToDo(task.substring(5));
        } else if (task.startsWith("event ")) {
            int index = task.indexOf("/at ");
            if (index == -1) {
                System.out.println("You must specify the the time for an event.");
                return;
            }
            String description = task.substring(6, index);
            String time = task.substring(index + 4);
            this.tasks[this.numOfTasks] = new Event(description, time);
        } else if (task.startsWith("deadline ")) {
            int index = task.indexOf("/by ");
            if (index == -1) {
                System.out.println("You must specify the the time for a deadline.");
                return;
            }
            String description = task.substring(9, index);
            String time = task.substring(index + 4);
            this.tasks[this.numOfTasks] = new Deadline(description, time);
        } else {
            this.tasks[this.numOfTasks] = new Task(task);
        }
        System.out.println("Got it! I have added this task:");
        System.out.println(this.tasks[this.numOfTasks]);
        this.numOfTasks = this.numOfTasks + 1;
        System.out.printf("Now you have %s tasks in the list.\n", this.numOfTasks);
    }

    public static void main(String[] args) {
        Duke duke = new Duke();
        System.out.println("Hello I am Duke.\nWhat can I do for you?");
        Scanner scanner = new Scanner(System.in);
        String echo = scanner.nextLine();
        while(true) {
            if (echo.equals("bye")) {
                System.out.println("Bye! See you next time!");
                break;
            }
            if (echo.equals("List")) {
                duke.list();
                echo = scanner.nextLine();
                continue;
            }
            if (echo.startsWith("done")) {
                String[] parsed = echo.split(" ");
                if (parsed.length > 1) {
                    duke.markAsDone(parsed[1]);
                } else {
                    duke.addTask(echo);
                }
                echo = scanner.nextLine();
                continue;
            }
            if (echo.equals("")) {
                System.out.println("Please enter a new task or action.");
                echo = scanner.nextLine();
                continue;
            }
            duke.addTask(echo);
            echo = scanner.nextLine();
        }

    }
}
