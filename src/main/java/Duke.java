import java.lang.reflect.Array;
import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        boolean loop = true;
        Scanner textInput = new Scanner(System.in);
        ArrayList<String> list = new ArrayList<>();

        while (loop) {
            String input = textInput.nextLine();

            switch(input) {
                case "bye":
                    loop = false;
                    break;
                case "list":
                    for (int i = 1; i <= list.size(); i++) {
                        System.out.println(i + ". " + list.get(i - 1));
                    }
                    break;
                default:
                    list.add(input);
                    System.out.println("added: " + input);
            }
        }
        System.out.println("Bye bye. Duke going to sleep now.");
    }
}
