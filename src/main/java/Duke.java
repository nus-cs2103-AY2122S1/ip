import java.util.Scanner;
public class Duke {
    int numOfTasks = 0;
    String[] tasks = new String[100];

    Duke() {
        numOfTasks= 0;
        tasks = new String[100];
    }

    void toDo() {
        for (int i = 0; i < this.numOfTasks; i++) {
            System.out.println(i+1+"."+ this.tasks[i]);
        }
        if (this.numOfTasks == 0) {
            System.out.println("List is empty!");
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
            System.out.println(echo);
            duke.tasks[duke.numOfTasks] = echo;
            duke.numOfTasks = duke.numOfTasks + 1;
            echo = scanner.nextLine();
        }

    }
}
