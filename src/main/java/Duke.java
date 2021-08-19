import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Duke {
    public static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    public static String LOGO = " ____        _        \n"
                              + "|  _ \\ _   _| | _____ \n"
                              + "| | | | | | | |/ / _ \\\n"
                              + "| |_| | |_| |   <  __/\n"
                              + "|____/ \\__,_|_|\\_\\___|\n";

    public static Response welcomeResponse = new Response(new String[]{ "Hello! I'm Duke", "What can I do for you?" });
    public static Response goodbyeResponse = new Response(new String[]{ "Bye. Hope to see you again soon!" });

    private static final TaskCollection tasks = new TaskCollection();

    public static void main(String[] args) throws IOException {
        Duke.printResponse(Duke.welcomeResponse);

        while (true) {
            String input = Duke.getUserInput();
            String[] inputSubsections = input.split("\\s", 2);
            String command = inputSubsections[0];
            
            if (command.equals("bye")) {
                Duke.printResponse(Duke.goodbyeResponse);
                break;
            }

            if (command.equals("list")) {
                Response listResponse = new Response();
                listResponse.add("Here are the tasks in your list:");
                listResponse.add(Duke.tasks.toString());
                Duke.printResponse(listResponse);
                continue;
            }

            if (command.equals("done")) {
                int itemNumber = Integer.parseInt(inputSubsections[1]);
                Task task = Duke.tasks.get(itemNumber);
                task.isDone = true;
                Response doneResponse = new Response();
                doneResponse.add("Nice! I've marked this task as done: ");
                doneResponse.add(String.format("  %s", task.toString()));
                Duke.printResponse(doneResponse);
                continue;
            }

            Task newTask = new Task(input);
            Duke.tasks.add(newTask);
            Response defaultResponse = new Response();
            defaultResponse.add(String.format("added: %s", newTask.description));
            Duke.printResponse(defaultResponse);
        }
    }

    /**
     * Prints the Response to the console.
     * @param response The Response to be printed.
     */
    public static void printResponse(Response response) {
        System.out.println(response.toString());
    }

    /**
     * Prompts the user for an input.
     * @return The input string
     * @throws IOException If an I/O error occurs
     */
    public static String getUserInput() throws IOException {
        System.out.println();
        return reader.readLine();
    }
}
