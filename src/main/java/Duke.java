import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String divider = " ------------------------------------------- \n";
        String indent = "       ";

        System.out.println(indent + "Hello! I'm Viper :) \n");
        System.out.println(indent + "How can I help you today?");
        System.out.println(divider);

        String str = sc.nextLine();

        while (!str.equalsIgnoreCase("bye")) {
            System.out.println(indent + str);
            System.out.println(divider);
            str = sc.nextLine();
        }

        System.out.println(indent + "Bye! See you again~");
        System.out.println(divider);

    }
}
