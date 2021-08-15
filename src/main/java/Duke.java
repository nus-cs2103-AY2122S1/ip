import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        ChatBot bot = new ChatBot();
        Printer.prettyPrint(bot.greet());
        while(bot.isRunning()) {
            Scanner sc = new Scanner(System.in);
            Printer.prettyPrint(bot.listen(sc.nextLine()));
        }
    }
}
