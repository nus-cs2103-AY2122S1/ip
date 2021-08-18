import java.util.Scanner;

public class Bern {
    public static boolean isANumber(String s) {
        try {
            Integer.parseInt(s);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
    public static void main(String[] args) {
        Scanner myObj = new Scanner(System.in);

        Task[] listTask = new Task[100];
        int available = 0;

        System.out.println("Hi! I'm Bern, your trustworthy chatbot.\nWhat can I do for you?");

        while (true) {
            String input = myObj.nextLine();
            try {
                if (input.length() >= 4 && input.substring(0, 4).equals("done")){
                    if (input.length() == 4 || (input.length() == 5 && input.substring(4, 5).equals(" "))) {
                        throw new EmptyDescriptionException("done");
                    } else if (!isANumber(input.substring(5))) {
                        throw new InvalidCommandException(input);
                    } else if (Integer.parseInt(input.substring(5)) > available) {
                        throw new IndexException(input);
                    }
                    int index = Integer.parseInt(input.substring(5)) - 1;
                    listTask[index].markAsDone();
                    System.out.println("Good job! I've marked this task as done:\n"
                            + listTask[index].toString());
                } else if (input.length() >= 8 && input.substring(0, 8).equals("deadline")) {
                    if (input.length() == 8 || (input.length() == 9 && input.substring(8, 9).equals(" "))) {
                        throw new EmptyDescriptionException("deadline");
                    }
                    // TODO Haven't handled if there is no /by
                    String task = input.substring(9, input.indexOf('/') - 1);
                    String by = input.substring(input.indexOf('/') + 4);
                    listTask[available] = new Deadline(task, by);
                    System.out.println("Got it. I've added this task:\n"
                            + listTask[available].toString() + "\n"
                            + "Now you have " + String.valueOf(available + 1)
                            + (available == 0 ? "task in the list" : " tasks in the list")
                    );
                    available += 1;
                } else if (input.length() >= 5 && input.substring(0, 5).equals("event")) {
                    if (input.length() == 5 || (input.length() == 6 && input.substring(5, 6).equals(" "))) {
                        throw new EmptyDescriptionException("event");
                    }
                    // TODO Haven't handled if there is no /at
                    String task = input.substring(6, input.indexOf('/') - 1);
                    String at = input.substring(input.indexOf('/') + 4);
                    listTask[available] = new Event(task, at);
                    System.out.println("Got it. I've added this task:\n"
                            + listTask[available].toString() + "\n"
                            + "Now you have " + String.valueOf(available + 1)
                            + (available == 0 ? "task in the list" : " tasks in the list")
                    );
                    available += 1;
                } else if (input.length() >= 4 && input.substring(0, 4).equals("todo")) {
                    if (input.length() == 4 || (input.length() == 5 && input.substring(4, 5).equals(" "))) {
                        throw new EmptyDescriptionException("todo");
                    }
                    listTask[available] = new ToDo(input.substring(5));
                    System.out.println("Got it. I've added this task:\n"
                            + listTask[available].toString() + "\n"
                            + "Now you have " + String.valueOf(available + 1)
                            + (available == 0 ? " task in the list" : " tasks in the list")
                    );
                    available += 1;
                } else if (input.equals("bye")){
                    System.out.println("Bye. Hope to see you soon and hope you found my service useful!");
                    break;
                } else if (input.equals("list")){
                    for (int i = 0; i < available; i++) {
                        System.out.println(String.valueOf(i + 1)
                                + ". "
                                + listTask[i].toString());
                    }
                    if (available == 0) {
                        System.out.println("There is no task.");
                    }
                } else {
                    throw new InvalidCommandException(input);
                }
            } catch (BernException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
