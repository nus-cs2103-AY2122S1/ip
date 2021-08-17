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
        while (true) {
            String input = sc.nextLine();
            String[] split = input.split(" ");

            if (input.equals("bye")) {
                break;
            } else if (input.equals("list")) {
                System.out.println(list.toString());
            } else if (split[0].equals("done")) {
                list.done(split[1]);
            } else {
                list.add(input);
            }
        }
        sc.close();
        System.out.println("Byebye");
    }
}
