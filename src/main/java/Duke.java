import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    private static TodoList todolist = new TodoList();

    public static void main(String[] args) {
//        String logo = " ____        _        \n"
//                + "|  _ \\ _   _| | _____ \n"
//                + "| | | | | | | |/ / _ \\\n"
//                + "| |_| | |_| |   <  __/\n"
//                + "|____/ \\__,_|_|\\_\\___|\n";
//        System.out.println("Hello from\n" + logo);
        introduceDuke();
    }

    public static void formatMessages(String message) {
        String output = "     --------------------------------------\n"
                + "      " + message + "\n"
                + "\n     --------------------------------------";
        System.out.println(output);
    }

    public static void introduceDuke() {
        Scanner sc = new Scanner(System.in);
        String introduction = "Hello, I am Ah Seng, the foodcourt uncle. Come chitchat with me.";
        formatMessages(introduction);
        respondTo(sc);
    }

    public static void terminateProgramme() {
        String endingMessage = "Ah ok bye. Next time treat uncle kopi ok?";
        formatMessages(endingMessage);
    }

    public static void respondTo(Scanner sc) {
        String input = sc.nextLine();
        if(input.equals("bye")) {
            terminateProgramme();
        } else if (input.equals("list")) {
            printTasks();
            respondTo(sc);
        } else if (containsDone(input)) {
            completeTask(input);
            respondTo(sc);
        } else if (isTask(input)) {
            insertTask(input);
            respondTo(sc);
        } else {
//            insertTask(input);
            formatMessages("Aiyo, please enter a task hor!");
            respondTo(sc);
        }
    }

    public static void insertTask(String input) {
        formatMessages(todolist.insertTask(input));
    }

    public static void printTasks() {
        formatMessages(todolist.getList());
    }

    public static void completeTask(String input) {
        String first = input.split(" ")[0];
        String second = input.split(" ")[1];

        try {
            int value = Integer.parseInt(second);
            formatMessages(todolist.completeTask(value-1));

        } catch (NumberFormatException e) {
            System.out.println("Aiyo, you say done a task but you never tell me which one leh.");
        }
    }

    public static boolean containsDone(String input) {
        String first = input.split(" ")[0];
        return first.equalsIgnoreCase("done");
    }

    public static boolean isTask(String input) {
        String first = input.split(" ")[0];
        return first.equalsIgnoreCase("todo")
                || first.equalsIgnoreCase("event") || first.equalsIgnoreCase("deadline");
    }
}
