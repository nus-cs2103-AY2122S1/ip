import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
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
            input = sc.nextLine();
            System.out.println(input);
        }
        sc.close();
        System.out.println("Byebye");
    }
}
