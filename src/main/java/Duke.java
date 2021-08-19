import java.util.Scanner;
public class Duke {
    public static String echo(Scanner scanner) {
        String echo = scanner.nextLine();
        return echo;
    }
    public static void main(String[] args) {
        System.out.println("Hello I am Duke. \nWhat can I do for you?");
        Scanner scanner = new Scanner(System.in);
        String answer = echo(scanner);
        System.out.println(answer);
    }
}
