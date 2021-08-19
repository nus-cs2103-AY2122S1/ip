import com.sun.source.tree.DoWhileLoopTree;

import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        String line = "❤❤❤❤❤❤❤❤❤❤❤❤❤❤❤❤❤❤❤❤❤\n";
        System.out.println("Hello from\n" + logo + line + "What can I do for you?\n" + line);

        String input;
        Scanner s = new Scanner(System.in);
        input = s.nextLine();

        while(!input.equals("bye")) {
            System.out.println(line + input + "\n" + line);
            input = s.nextLine();
        }

        System.out.println(line + "Bye. Hope to see you again soon!\n" + line);
    }
}
