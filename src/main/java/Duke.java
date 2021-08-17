import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        String dash = "__________________________________";

        System.out.println("Hello from\n" + logo);
        System.out.println(dash);
        System.out.println("Howdy! I'm Duke" + '\n'+ "How may I assist you?");
        System.out.println(dash);

        Scanner scanner = new Scanner(System.in);
        String userInput;

        Task tasks[] = new Task[100];
        int counter = 0;

        while (true) {
            userInput = scanner.nextLine();

            if (userInput.matches("bye")) {
                System.out.println(dash);
                System.out.println("Bye. Hope to see you again soon!");
                System.out.println(dash);
                break;

            } else if (userInput.matches("list")){
                System.out.println(dash);
                System.out.println("Here are the tasks in your list:");
                for (int i = 0; i < counter; i++) {
                    Task currTask = tasks[i];
                    System.out.println(i+1 + ".[" + currTask.getStatusIcon() + "] " + currTask.getDescription());
                }
                System.out.println(dash);

            } else if (userInput.contains("done")){
                Integer index = Integer.parseInt(userInput.substring(5));
                Task currTask = tasks[index - 1];
                currTask.completeTask();

                System.out.println(dash);
                System.out.println("Nice! I've marked this task as done:");
                System.out.println(" [" + currTask.getStatusIcon()+ "] " + currTask.getDescription());
                System.out.println(dash);

            } else  {
                tasks[counter] = new Task(userInput);
                counter += 1;
                System.out.println(dash);
                System.out.println("added: " + userInput);
                System.out.println(dash);

            }
        }
    }
}
