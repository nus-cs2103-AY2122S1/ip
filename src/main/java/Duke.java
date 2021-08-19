import java.util.Scanner;


public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("Hello! I'm Dude");
        System.out.println("What can I do for you?");

        Scanner sc =  new Scanner(System.in);
        boolean isRunning = true;
        ToDoList toDoList = new ToDoList();

        while (isRunning) {
            String input = getPrompt(sc);
            isRunning = processInput(input, toDoList);
        }
    }

    static String getPrompt(Scanner sc) {
        return sc.nextLine();
    }

    static boolean processInput(String str, ToDoList toDoList){
        if (str.equals("bye")) {
            System.out.println("Bye. Hope to see you again soon!");
            return false;
        } else if (str.equals("list")) {
            System.out.println(toDoList.list());
        } else {
            System.out.println(toDoList.add(str));
        }
        return true;
    }
}
