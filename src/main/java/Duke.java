import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Task[] store = new Task[100];
        int index = 0;

        System.out.println(
                "░▄░█░░░▄▀▀▀▀▀▄░░░█░▄░\n" +
                "▄▄▀▄░░░█─▀─▀─█░░░▄▀▄▄\n" +
                "░░░░▀▄▒▒▒▒▒▒▒▒▒▄▀░░░░\n" +
                "░░░░░█────▀────█░░░░░\n" +
                "░░░░░█────▀────█░░░░░\n");
        System.out.println("I'm Frosty! How can I help?");

        Scanner sc = new Scanner(System.in);
        String in = sc.nextLine();
        while(!in.equals("bye")) {
            if (in.equals("list")) {
                System.out.println("Here are the tasks in your list:");
                for (int i = 0; i < index; i++) {
                    System.out.println((i + 1) + ". " + store[i]);
                }
            } else if (in.startsWith("done")) {
                String[] temp = in.split(" ");
                store[Integer.parseInt(temp[1]) - 1].setFlag(true);
                System.out.println("Nice! I've marked this task as done:");
                System.out.println("  " + store[Integer.parseInt(temp[1]) - 1]);
            } else {
                System.out.println("added: " + in);
                store[index] = new Task(in);
                index++;
            }
            in = sc.nextLine();
        }

        System.out.println("Have a Merry Christmas and a Happy New Year!");
    }
}
