import java.util.*;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
//        System.out.println("Hello from\n" + logo);
        greet();
        Scanner sc = new Scanner(System.in);
        ToDoList lst = new ToDoList();

        while (sc.hasNextLine()) {
            String input = sc.nextLine();
            if (respond(input, lst)) break;
        }
    }

    public static void greet() {
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
    }

    public static boolean respond(String input, ToDoList lst) {
        if (input.equals("bye")) {
            System.out.println("Bye. Hope to see you again soon!");
            return true;
        } else if (input.equals("list")){
            lst.showList();
            return false;
        } else if (input.contains("done")) {
            int taskNum = Integer.parseInt(input.split(" ")[1]);
            lst.markAsDone(taskNum - 1);
            return false;
        } else {
            lst.addItem(input);
//            System.out.println(input); Level 1
            return false;
        }
    }
}
