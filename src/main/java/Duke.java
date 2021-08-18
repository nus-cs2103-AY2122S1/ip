import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        List<String> sList = new ArrayList<>();

        String divider = " ------------------------------------------- \n";
        String indent = "       ";

        System.out.println(indent + "Hello! I'm Viper :) \n");
        System.out.println(indent + "How can I help you today?");
        System.out.println(divider);

        String str = sc.nextLine();

        while (!str.equalsIgnoreCase("bye")) {
            if (str.equalsIgnoreCase("list")) {
                for (int i = 0; i < sList.size(); i++) {
                    int curr = i + 1;
                    System.out.println(indent + curr + ". " + sList.get(i));
                }
            }
            else {
                sList.add(str);
                System.out.println(indent + "added: " + str);
                System.out.println(divider);
            }

            str = sc.nextLine();
        }

        System.out.println(indent + "Bye! See you again~");
        System.out.println(divider);

    }
}
