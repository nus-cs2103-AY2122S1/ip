import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        boolean away = false;
        String bar = " -----------------------------------\n";
        System.out.println(bar + "    Hello! I'm SaDOS\n" +
                "    What can I do for you?\n" +
                "    Send \"bye\" to exit,\n" +
                "    I won't hold it against you\n" +
                bar);
        Scanner sc = new Scanner(System.in);
        do {
            String str = sc.nextLine();
            if (str.equalsIgnoreCase("bye")) {
                away = true;
                System.out.println(bar + "    Oh you've got to go?\n" +
                        "    Alright, I'll just be here...\n" +
                        "    Waiting....\n" +
                        "    You'll be back right?\n" +
                        bar);
            } else {
                System.out.println(bar + "    " + str + "\n" + bar);
            }
        }
        while (!away);
    }
}
