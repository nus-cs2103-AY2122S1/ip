import java.util.Scanner;

public class Duke {
    private static final TaskList list = new TaskList();

    private static String[] processInput(String[] arr) {
        String desc = "";
        String time = "";
        boolean slash = false;
        for (int i = 1; i < arr.length; i++) {
            if (arr[i].charAt(0) == '/') {
                slash = true;
                continue;
            }
            if (slash) {
                time += arr[i] + " ";
            } else {
                desc += arr[i] + " ";
            }
        }
        if (slash) {
            return new String[] {desc, time};
        }
        return new String[] {desc};
    }

    private static boolean isAddingNewTask(String str) {
        return str.equals("todo") || str.equals("deadline") || str.equals("event");
    }

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
            String first = split[0];

            if (input.equals("bye")) {
                break;
            } else if (input.equals("list")) {
                System.out.println(list.toString());
            } else if (first.equals("done")) {
                list.done(split[1]);
            } else if (isAddingNewTask(first)) {
                String[] processed = processInput(split);
                String desc = processed[0];

                if (first.equals("todo")) {
                    list.add(new Todo(desc));
                } else if (first.equals("deadline")) {
                    String time = processed[1];
                    list.add(new Deadline(desc, time));
                } else if (first.equals("event")) {
                    String time = processed[1];
                    list.add(new Event(desc, time));
                }
            } else {
                System.out.println("Invalid command");
            }
        }
        sc.close();
        System.out.println("Byebye");
    }
}
