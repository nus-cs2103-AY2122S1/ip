import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

    private static final String FILEPATH = "./data/tasks.json";
    private Storage storage = new Storage(FILEPATH);
    private static final String tab = "      ";
    private static final String line = "------------------------------------------------------------";

    public static void textBox(String... messages) {

        System.out.println(tab + line);
        for (int i = 0; i < messages.length; i++) {
            System.out.println(tab + " " + messages[i]);
        }
        System.out.println(tab + line);
    }

    public static void main(String[] args) throws NoSuchTaskException, InvalidInputException {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        String tab = "      ";
        String line = "------------------------------------------------------------";
        Duke.textBox("Hello. My name is Necro.",
                "What can I do for you on this horrible day?");

        Scanner sc = new Scanner(System.in);

        TaskList tasks = new TaskList();
        int counter = 0;

        while (true) {
            String input = sc.nextLine();

            // POLYMORPHISM! make a general method in the Task class and have each
            // different task handle the input differently!

            if (input.startsWith("delete ")) {
                String[] strings = input.split(" ");
                if (counter == 0) {
                    Duke.textBox("There are currently no tasks.",
                            "You can't delete something that doesn't exist.");
                } else {
                    try {
                        if (strings.length < 2) {
                            Duke.textBox("You did not specify which task to delete. Please wake up.");
                        } else {
                            int taskNumber = Integer.parseInt(strings[1]) - 1;
                            if (taskNumber < counter) {
                                Duke.textBox("Since you are so lazy, I've helped you delete this task:",
                                        tasks.get(taskNumber).toString(),
                                        "Go do something useful with your life.");
                                tasks.delete(taskNumber);
                                counter--;
                            } else {
                                Duke.textBox("You have entered an invalid task number. Fool.");
                            }
                        }
                    } catch (NumberFormatException ex) {
                        System.err.println(ex);
                    }
                }

            } else if (input.startsWith("done ")) {
                String[] strings = input.split(" ");
                if (counter == 0) {
                    Duke.textBox("There are currently no tasks. ",
                            "You can't do something that doesn't exist.");
                } else {
                    try {
                        if (strings.length < 2) {
                            Duke.textBox("You did not specify which task to complete.");
                        } else {
                            int taskNumber = Integer.parseInt(strings[1]) - 1;
                            if (taskNumber < counter) {
                                if (tasks.get(taskNumber).checkCompletion()) {
                                    Duke.textBox("The task has already been completed, please be more attentive.");
                                } else {
                                    tasks.get(taskNumber).complete();
                                    Duke.textBox("Wow. Congratulations. You have completed the following task:",
                                            tasks.get(taskNumber).toString(),
                                            "Are you happy now?");
                                }
                            } else {
                                System.out.println("You have entered an invalid task number. Fool.");
                            }
                        }
                    } catch (NumberFormatException ex) {
                        System.err.println(ex);
                    }
                }
            } else if (input.equals("list")) {
                if (counter == 0) {
                    System.out.println("You currently do not have any tasks. Fool.");
                } else {
                    System.out.println(tab + line);
                    System.out.println(tab + "Here are the tasks in your list:");
                    for (int j = 0; j < counter; j++) {
                        System.out.println(tab + " " + (j + 1) + ". " + tasks.get(j).toString());
                    }
                    System.out.println(tab + line);
                }
            } else if (input.equals("bye")) {
                Duke.textBox("Farewell, may we never meet again.");
                break;
            } else {
                String output;
                if (input.startsWith("todo ") && input.charAt(5) != 97) {
                    String[] strings = input.split(" ", 2);
                    ToDo toDo = new ToDo(strings[1]);
                    output = toDo.toString();
                } else if (input.startsWith("deadline ") && input.charAt(9) != 97) {
                    String[] strings = input.split("/", 2);
                    try {
                        if (!input.contains("/") || strings[1].contains("/") || strings[1].length() < 1) {
                            throw new InvalidInputException("You fool. You cannot create a task without a description, " +
                                    "or you have formatted your description wrongly. Check your spaces and try again.");
                        }
                    } catch (InvalidInputException ex) {
                        System.err.println(ex);
                        continue;
                    }
                    String[] strings1 = strings[0].split(" ", 2);
                    String[] strings2 = strings[1].split(" ", 2);
                    Deadline deadline = new Deadline(strings1[1], strings2[1]);
                    output = deadline.toString();
                } else if (input.startsWith("event ") && input.charAt(6) != 97) {
                    String[] strings = input.split("/", 2);
                    try {
                        if (!input.contains("/") || strings[1].contains("/") || strings[1].length() < 1) {
                            throw new InvalidInputException("You fool. You cannot create a task without a description, " +
                                    "or you have formatted your description wrongly. Check your spaces and try again.");
                        }
                    } catch (InvalidInputException ex) {
                        System.err.println(ex);
                        continue;
                    }
                    String[] strings1 = strings[0].split(" ", 2);
                    String[] strings2 = strings[1].split(" ", 2);
                    Event event = new Event(strings1[1], strings2[1]);
                    output = event.toString();
                } else {
                    // Check if there are any spaces first. if there are no spaces, means that it is either an invalid input or no such task

                    // Level-5
                    try {
                        if ((input.startsWith("todo") || input.startsWith("deadline") || input.startsWith("event")) && !input.contains(" ")) {
                            throw new InvalidInputException("You fool. You cannot create a task without a description, " +
                                    "or you have formatted your description wrongly. Check your spaces and try again.");
                        } else {
                            try {
                                throw new NoSuchTaskException("You have entered an invalid message. Please do not commit this idiocy again.");
                            } catch (NoSuchTaskException ex) {
                                System.err.println(ex);
                                continue;
                            }
                        }
                    } catch (InvalidInputException ex) {
                        System.err.println(ex);
                        continue;
                    }

                }
                Duke.textBox("Fine. I've added this meaningless task to your list: ",
                        " --> " + output,
                        "Satisfied now? You have " + (counter + 1) + " items in your list. ");
                tasks.add(new Task(output));
                //tasks.get(counter).status = tasks.get(counter).status.substring(4);
                counter++;
                
            }
        }

    }
}
