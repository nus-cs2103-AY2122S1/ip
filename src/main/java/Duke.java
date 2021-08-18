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
        int position = 0;
        Scanner sc = new Scanner(System.in);
        response.greet();
        String text = sc.nextLine();
        while (!text.equals("bye")) {
            if (text.equals("list")) {
                response.display();
            } else if (text.contains("done")) {
                String[] splitWords = text.split(" ");
                //convert the number behind "done" into an int
                int pos = Integer.parseInt(splitWords[1]);
                response.markDone(pos - 1);
            }
            else {
                response.echo(new Task(text, position));
            }
            text = sc.nextLine();
        }
        response.exit();
    }
}
