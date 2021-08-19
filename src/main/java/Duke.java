import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    public static void main(String[] args) {
        //Greet
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("    ____________________________________________________________");
        System.out.println("    Hello! I'm Duke.");
        System.out.println("    What can I do for you?");
        System.out.println("    ____________________________________________________________");

        Scanner scanner = new Scanner(System.in);
        boolean exit = false;
        ArrayList<Task> taskList = new ArrayList<>();

        //Echo
        while(!exit){
            String input = scanner.nextLine();

            System.out.println("    ____________________________________________________________");

            if(input.equals("list")){
                for (int i = 0; i < taskList.size(); i++) {
                    Task task = taskList.get(i);
                    System.out.println((i+1) + ". " + task.getTaskName());
                }
            }else if(input.equals("bye")){
                System.out.println("    Bye. Hope to see you again soon!");
                exit = true;
            }else {
                Task newTask = new Task(input);
                taskList.add(newTask);
                System.out.println("    added: " + input);
            }

            System.out.println("    ____________________________________________________________");
        }
    }
}
