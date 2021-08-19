import com.sun.source.tree.DoWhileLoopTree;

import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        String line = "❤❤❤❤❤❤❤❤❤❤❤❤❤❤❤❤❤❤❤❤❤";
        ArrayList<String> task = new ArrayList<>();
        System.out.println("Hello from\n" + logo + line + "\nWhat can I do for you?\n" + line);

        String input;
        Scanner s = new Scanner(System.in);
        input = s.nextLine();

        while(!input.equals("bye")) {
            if(input.equals("list")) {
                int i = 1;
                System.out.println(line);
                for(String str: task) {
                    System.out.println(i + ". " + str);
                    i++;
                }
                System.out.println(line);
            } else {
                System.out.println(line + "\n" + "added: " + input + "\n" + line);
                task.add(input);
            }
            input = s.nextLine();
        }

        System.out.println(line + "\nBye. Hope to see you again soon!\n" + line);
    }
}
