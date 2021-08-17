import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        boolean check = true;
        String[] mem = new String[100];
        int counter = 0;

        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

        System.out.println("Hello! I'm Duke\n" + "What can I do for you?");
        while (check) {
            String s = sc.nextLine();
            if (s.equals("bye")) {
                check = false;
                System.out.println("Bye. Hope to see you again soon!");
            } else if (s.equals("list")){
                for (int i = 0; i < counter; i++) {
                    System.out.println((i + 1) + ". " + mem[i]);
                }
            } else {
                mem[counter] = s;
                counter++;
                System.out.println("added: " + s);
            }
        }
    }
}
