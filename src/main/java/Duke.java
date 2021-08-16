import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        // Starting Message
        String[] startMessage = {" ____        _        ", 
                    "|  _ \\ _   _| | _____ ",
                    "| | | | | | | |/ / _ \\",
                    "| |_| | |_| |   <  __/",
                    "|____/ \\__,_|_|\\_\\___|",
                    "Hello! I'm Duke",
                    "What can I do for you?\n"};
        System.out.println(StringFormat.formatString(startMessage));

        // Storage for tasks
        TaskStorage storage = new TaskStorage();

        // Taking in user input
        Scanner sc = new Scanner(System.in);
        
        // While there's user input
        while(sc.hasNext()) {
            String input = sc.nextLine();

            String[] inputs = input.split(" ", 2);
            

            // if it is "bye", we exit the loop
            if (inputs[0].equals("bye")) {
                System.out.println(StringFormat.formatString("Bye. Hope to see you again soon!\n"));
                break;
            // if it is "list", we list the stored inputs
            } else if (inputs[0].equals("list")) {
                System.out.println(
                    StringFormat.formatString(
                        StringFormat.tabAllNewline(
                            storage.toString()
                        )
                    )
                );
            // if we are marking a task as done
            } else if (inputs[0].equals("done")) {
                String[] result = input.split(" ");
                int ind = Integer.valueOf(result[1]) - 1;

                System.out.println(
                    StringFormat.formatString(
                        StringFormat.tabAllNewline(
                            storage.markDone(ind)
                        )
                    )
                );
            // if we are making a task
            } else {
                taskHandler(storage, inputs, input);
            }
        }
        sc.close();
    }

    public static void taskHandler(TaskStorage storage, String[] inputs, String input) {
        // if we are creating a todo task
        if (inputs[0].equals("todo")) {
            System.out.println(
                StringFormat.formatString(
                    StringFormat.tabAllNewline(
                        storage.add(
                            new Todo(inputs[1])
                        )
                    )
                )
            );   
        // if we are creating a deadline task 
        } else if (inputs[0].equals("deadline")) {
            String[] result = inputs[1].split(" /by ");
            System.out.println(
                StringFormat.formatString(
                    StringFormat.tabAllNewline(
                        storage.add(
                            new Deadline(result[0], result[1])
                        )
                    )
                )
            );
        // if we are creating an event task
        } else if (inputs[0].equals("event")) {
            String[] result = inputs[1].split(" /at ");
            System.out.println(
                StringFormat.formatString(
                    StringFormat.tabAllNewline(
                        storage.add(
                            new Deadline(result[0], result[1])
                        )
                    )
                )
            );
        // if we are creating a generic task
        } else {
            System.out.println(
                StringFormat.formatString(
                    storage.add(new Task(input)
                    )
                ) 
            );
        }
    }
}
