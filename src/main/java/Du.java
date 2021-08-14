import java.util.Objects;
import java.util.Scanner;

public class Du {
    public static void main(String[] args) {
        Command.greet();

        Scanner sc = new Scanner(System.in);
        String command = sc.nextLine();

        while (!Objects.equals(command, "bye")) {
            Command.echo(command);
            command = sc.nextLine();
        }
        Command.close_programme();
    }

}
