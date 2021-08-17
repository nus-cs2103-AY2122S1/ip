import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        startBot();

        //level 1
        /*Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            String message = sc.nextLine();

            if (message.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            } else {
                System.out.println(message);
            }
        }
        sc.close();*/

        //level 2
        /*List<String> toDoList = new ArrayList<>();

        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            String message = sc.nextLine();

            if (message.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            } else if (message.equals("list")) {
                list(toDoList);
            } else {
                toDoList.add(message);
                System.out.println(add(message));
            }
        }
        sc.close();*/

        //level 3
        List<Task> toDoList = new ArrayList<>();

        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            String message = sc.nextLine();

            if (message.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            } else if (message.equals("list")) {
                list(toDoList);
            } else if (checkDone(message) != 0 && !(checkDone(message) > toDoList.size())) {
                //need to check for exceptions
                //like invalid number entry
                displayCheckedTask(toDoList.get(checkDone(message) - 1));
            } else {
                Task newTask = new Task(message);
                toDoList.add(newTask);
                System.out.println(add(message));
            }
        }
        sc.close();
    }

    public static void startBot() {
        String intro = "Hello! I'm Duke\n" +
                "    What can I do for you?";
        System.out.println(intro);
    }

    public static String add(String add) {
        return "added: "+ add;
    }

    public static void list(List<Task> list) {
        int order = 1;

        //level 2
        /*for(String s : list){
            System.out.println(order++ +". "+s);
        }*/
        System.out.println("Here are the tasks in your list:");
        for (Task s : list) {
            System.out.println(order++ +"." + s.displayTask());
        }
    }

    public static int checkDone(String message) {
        StringBuilder number;
        if (message.length() > 5) {
            String check = message.substring(0, 4);
            if (check.equals("done")) {
                char firstNumber = message.charAt(5);
                number = new StringBuilder(Character.toString(firstNumber));
                int counter = 6;
                while (counter < message.length()) {
                    char next = message.charAt(counter);
                    number.append(next);
                    counter++;
                }
                return Integer.parseInt(number.toString());
            }
        }
        return 0;
    }

    public static void displayCheckedTask(Task item) {
        item.checkOffTask();
        String display = "Nice! I've marked this task as done: \n  " + item.displayTask();
        System.out.println(display);
    }
}
