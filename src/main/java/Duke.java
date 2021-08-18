import java.util.Scanner;

public class Duke {
    private static Task[] taskList = new Task[100];
    private static int index = 0;
    private static void addTask(Task task) {
        taskList[index++] = task;
    }
    public static void main(String[] args) {
//        String logo = " ____        _        \n"
//                + "|  _ \\ _   _| | _____ \n"
//                + "| | | | | | | |/ / _ \\\n"
//                + "| |_| | |_| |   <  __/\n"
//                + "|____/ \\__,_|_|\\_\\___|\n";
//        System.out.println("Hello from\n" + logo);
        System.out.println("Hello! I'm Duke\n" +
                "What can I do for you?");
        Scanner sc = new Scanner(System.in);
        while(true) {
            String input = sc.nextLine();
            if(input.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            }else if (input.equals("list")) {
                for(int i = 0; i < index; i++){
                    System.out.println((i + 1) + ". " + taskList[i].toString());
                }
            }else if(input.split(" ")[0].equals("done")){
//                if(input.split(" ").length > 2) {
//                    addTask(new Task(input));
//                }else {
//                    try {
//                        int taskIndex = Integer.parseInt(input.split(" ")[1]);
//                        taskList[taskIndex - 1].markCompleted();
//                        System.out.println("Nice! I've marked this task as done:\n" + taskList[taskIndex - 1].toString());
//                    } catch (NumberFormatException e) {
//                        System.out.println("");
//                    }
//                }
                int taskIndex = Integer.parseInt(input.split(" ")[1]);
                taskList[taskIndex - 1].markCompleted();
                System.out.println("Nice! I've marked this task as done:\n" + taskList[taskIndex - 1].toString());
            } else {
                System.out.println("added: " + input);
                addTask(new Task(input));
            }
        }
    }

}
