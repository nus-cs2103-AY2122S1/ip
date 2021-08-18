import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    private static String firstWord(String input) {
        String[] strArr = input.split(" ", 2);
        return strArr[0];
    }

    private static int taskNumber(String input) {
        String[] strArr = input.split(" ", 2);
        String number = strArr[1];
        return Integer.parseInt(number);
    }

    private static void completeTask(String input, ArrayList<Task> arr) {
        System.out.println("---------------------------------------------");
        System.out.println("Nice! I've marked this task as done:");
        arr.get(taskNumber(input) - 1).markAsDone();
        System.out.println("---------------------------------------------");
    }

    private static void addTask(String task, ArrayList<Task> arr) {
        Task newTask = new Task(task);
        arr.add(newTask);
        System.out.println("---------------------------------------------\n"
                + "     added: " + newTask.description + "\n"
                + "---------------------------------------------");
    }

    private static void listItems(ArrayList<Task> arr) {
        System.out.println("---------------------------------------------");
        for (int i = 0; i < arr.size(); i++) {
            int j = i + 1;
            System.out.println("     " + j + ". ["
                    + arr.get(i).getStatusIcon() + "] "
                    + arr.get(i).description);
        }
        System.out.println("---------------------------------------------");
    }

    public static void main(String[] args) {
//        String logo = " ____        _        \n"
//                + "|  _ \\ _   _| | _____ \n"
//                + "| | | | | | | |/ / _ \\\n"
//                + "| |_| | |_| |   <  __/\n"
//                + "|____/ \\__,_|_|\\_\\___|\n";
//        System.out.println("Hello from\n" + logo);
        ArrayList<Task> todo = new ArrayList<>();

        System.out.println("---------------------------------------------");
        System.out.println("Hello! I'm Duke!\n" + "What can I do for you?");
        System.out.println("---------------------------------------------");

        while (true) {
            Scanner input = new Scanner (System.in);
            String action = input.nextLine();

            if (firstWord(action).equals("done")) {
                Duke.completeTask(action, todo);
            } else if (!action.equals("bye") && !action.equals("list")){
                Duke.addTask(action, todo);
            } else if (action.equals("list")){
                Duke.listItems(todo);
            } else {
                System.out.println("---------------------------------------------\n"
                        + "     Bye. Hope to see you again soon!" + "\n"
                        + "---------------------------------------------");
                break;
            }
        }
        
    }
}
