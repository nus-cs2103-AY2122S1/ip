import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        //System.out.println("Hello from\n" + logo);
        Response response = new Response();
        Scanner sc = new Scanner(System.in);
        response.greet();
        String text = sc.nextLine();
        while (!text.equals("bye")) {
            if (text.equals("list")) {
                response.display();
            } else {
                response.echo(text);
            }
            text = sc.nextLine();
        }
        response.exit();

    }
}
