import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        String tab = "      ";
        String line = "------------------------------------------------------------";
        System.out.println(line);
        System.out.println("Hello. My name is Necro.");
        System.out.println("What can I do for you on this horrible day?");
        System.out.println(line);
        System.out.println("");

        Scanner sc = new Scanner(System.in);

        // Level-1
//        while (true) {
//            String input = sc.nextLine();
//
//            if (input.equals("bye")) {
//                System.out.println(tab + line);
//                System.out.println(tab + " " + "Farewell, may we never meet again.");
//                System.out.println(tab + line);
//                break;
//            } else {
//                System.out.println(tab + line);
//                System.out.println(tab + " " + input);
//                System.out.println(tab + line);
//            }
//        }

        // Level-2
        String[] arr = new String[100];
        int i = 1;
        while (true) {
            String input = sc.nextLine();

            if (input.equals("list")) {
                System.out.println(tab + line);
                for (int j = 1; j < i; j++) {
                    System.out.println(tab + " " + j + ". " + arr[j]);
                }
                System.out.println(tab + line);
            } else if (input.equals("bye")) {
                System.out.println(tab + line);
                System.out.println(tab + " " + "Farewell, may we never meet again.");
                System.out.println(tab + line);
                break;
            } else {
                System.out.println(tab + line);
                System.out.println(tab + " added: " + input);
                System.out.println(tab + line);
                arr[i] = input;
                i++;
            }
        }

    }
}
