import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String[] list = new String[100];
        int cnt = 1;
        System.out.println("Hello! I'm Duke created by Tianyue.\n" +
                "What can I do for you?");


        String text = scanner.nextLine();



        while(!text.isEmpty()) {
            if (text.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            }

            if (text.equals("list")) {
                for (int i = 0; i < cnt - 1; i++) {
                    System.out.println(i + 1 + ". " + list[i]);
                }
                text = scanner.nextLine();
            }

            else {
                System.out.println("added: " + text);
                list[cnt - 1] = text;
                cnt++;
                text = scanner.nextLine();
            }


        }
    }
}
