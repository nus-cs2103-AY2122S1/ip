import java.sql.Array;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Scanner;

public class MyJournal {
    public static void main(String[] args) {
        ArrayList<Task> items = new ArrayList<>();
        String input;
        Scanner reader = new Scanner(System.in);
        System.out.println("Hello!\n"
                + "1. Type a task (todo/event/deadline) to be added into your task list.\n"
                + "2. Type 'list' if you want to generate your task list.\n"
                + "3. Type 'bye' to exit");
        while (true) {
            input = reader.nextLine();
            String thisLine = input;
            Scanner line = new Scanner(thisLine);
            String firstWord = line.next();
            if (input.equals("bye")) {
                break;
            } else if (firstWord.equals("done")) {
                if (line.hasNextInt()) {
                    int index = line.nextInt() - 1;
                    if (index >= items.size() || index < 0 || items.get(index) == null) {
                        System.out.println("That task does not exist");
                    } else {
                        items.get(index).setState(true);
                        System.out.println("Okay!! I have marked this task as done:\n"
                                    + items.get(index));
                    }
                } else {
                    System.out.println("Input insufficient. Please specify the task that needs to be marked as done.");
                }
            } else if (firstWord.equals("list")) {
                for (int i = 0; i < items.size(); i++) {
                    System.out.println((i + 1) + ". " + items.get(i));
                }
            } else {
                Scanner currLine = new Scanner(input);
                String taskName = "";
                String time = "";
                try {
                    if (firstWord.equals("todo")) {
                        currLine.next();
                        if (currLine.hasNext()) {
                            while (currLine.hasNext()) {
                                String currWord = currLine.next();
                                taskName = taskName + currWord + " ";
                            }
                            items.add(new Todo(taskName));
                            System.out.println("Okay!! I've added the following task:\n"
                                    + items.get(items.size() - 1) + "\n"
                                    + "Now you have " + items.size() + " in the list");
                        } else {
                            throw new EmptyDescriptionException("OOPS!!! Please specify the todo!!");
                        }
                    } else if (firstWord.equals("event")) {
                        currLine.next();
                        if (currLine.hasNext()) {
                            while (currLine.hasNext()) {
                                String currWord = currLine.next();
                                if (currWord.equals("/at")) {
                                    break;
                                }
                                taskName = taskName + currWord + " ";
                            }
                            while (currLine.hasNext()) {
                                time = time + " " + currLine.next();
                            }
                            items.add(new Event(taskName, time));
                            System.out.println("Okay!! I've added the following task:\n"
                                    + items.get(items.size() - 1) + "\n"
                                    + "Now you have " + items.size() + " in the list");
                        } else {
                            throw new EmptyDescriptionException("OOPS!!! Please specify the event!!");
                        }
                    } else if (firstWord.equals("deadline")) {
                        currLine.next();
                        if (currLine.hasNext()) {
                            while (currLine.hasNext()) {
                                String currWord = currLine.next();
                                if (currWord.equals("/by")) {
                                    break;
                                }
                                taskName = taskName + currWord + " ";
                            }
                            while (currLine.hasNext()) {
                                time = time + " " + currLine.next();
                            }
                            items.add(new Deadline(taskName, time));
                            System.out.println("Okay!! I've added the following task:\n"
                                    + items.get(items.size() - 1) + "\n"
                                    + "Now you have " + items.size() + " in the list");
                        } else {
                            throw new EmptyDescriptionException("OOPS!!! Please specify the deadline!!");
                        }
                    } else {
                        throw new InvalidTypeException("OOPS!!! Please put either todo/event/deadline!");
                    }
                } catch (EmptyDescriptionException exception) {
                    System.out.println(exception.getMessage());
                } catch (InvalidTypeException exception) {
                    System.out.println(exception.getMessage());
                }
            }
        }
        System.out.println("Bye. Hope to see you again soon!:)");
    }
}
