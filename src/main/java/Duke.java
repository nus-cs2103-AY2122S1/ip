import java.util.Scanner;

public class Duke {
    private static final TaskList list = new TaskList();
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println(logo);
        System.out.println("Hello! I'm Duke\nWhat can I do for you?");
        Scanner sc = new Scanner(System.in);
        String input = "";
        while (!input.equals("bye")) {
            if (input.equals("list")) {
                System.out.println(list.toString());
            } else {
                list.add(input);
            }
            input = sc.nextLine();
        }
        sc.close();
        System.out.println("Byebye");
    }
}
