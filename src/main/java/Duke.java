import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        boolean away = false;
        int listLen = 0;
        String[] list = new String[100];
        String bar = " -----------------------------------";
        System.out.println(bar + "\n    Hello! I'm SaDOS\n" +
                "    What can I do for you?\n" +
                "    Send \"bye\" to exit,\n" +
                "    I won't hold it against you\n" +
                bar);
        Scanner sc = new Scanner(System.in);
        do {
            String str = sc.nextLine();
            if (str.equalsIgnoreCase("bye")) {
                away = true;
                System.out.println(bar + "\n    Oh you've got to go?\n" +
                        "    Alright, I'll just be here...\n" +
                        "    Waiting....\n" +
                        "    You'll be back right?\n" +
                        bar);
            } else if (str.equalsIgnoreCase("list")){
                System.out.println(bar);
                for (int i = 1; i <= listLen; i++) {
                    System.out.println("    " + i + ". " + list[i - 1]);
                }
                System.out.println(bar);
            } else {
                list[listLen] = str;
                listLen++;
                System.out.println(bar + "\n    added: " + str + "\n" +
                        bar);
            }
        }
        while (!away);
    }
}
