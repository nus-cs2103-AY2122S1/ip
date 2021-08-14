import java.util.Scanner;

public class Duke {
    //level 2
    public static String[] taskList = new String[100];
    public static int counter = 0;

    public static String getUserInput(Scanner scanner){
        return scanner.nextLine();
    }

    public static void dukeAction(Scanner scanner){
        String inp = getUserInput(scanner);
        while(!inp.equals("bye")) {
            switch (inp) {
                case "list":
                    for (int i = 0; i < taskList.length; i++) {
                        if (taskList[i] == null) break;
                        System.out.printf("%s" + "." + taskList[i] + "%n", i + 1);
                    }
                    inp = getUserInput(scanner);
                    break;
                default:
                    taskList[counter++] = inp;
                    System.out.println("added: " + inp);
                    inp = getUserInput(scanner);
            }
        }
        System.out.println("Bye. Hope to see you again soon!");
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Hello! I'm Duke\nWhat can I do for you?\n");
        dukeAction(scanner);
    }
}
