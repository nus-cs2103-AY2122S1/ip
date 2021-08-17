import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String bearPicStart = "┏ʕ •ᴥ•ʔ┛";
        String bearPicEnd = " ＼ʕ •ᴥ•ʔ／";
        System.out.println("Hi, I'm Duke the Bear! \n");
        System.out.println(bearPicStart + "\n");
        System.out.println("How can I help you?");
        while (true) {
            String input = scanner.nextLine();
            if (input.equals("bye")) {
                System.out.println("Bye bye! Thank u Beary Much!\n");
                System.out.println(bearPicEnd);
                break;
            }
            System.out.println(input);
        }
    }
}
