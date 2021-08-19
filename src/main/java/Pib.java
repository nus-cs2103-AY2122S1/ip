import java.util.ArrayList;
import java.util.Scanner;

import Tasks.Task;
import Tasks.Todo;
import Tasks.Deadline;
import Tasks.Event;

public class Pib {
    public static String DIVIDER = "____________________________________________________________\n";
    private ArrayList<Task> list;
    private Scanner sc;

    public Pib() {
        System.out.println(DIVIDER + "Hello! I'm Pib\n" + "Tell me something!\n" + DIVIDER);
        list = new ArrayList<>();
        sc = new Scanner(System.in);
        readInput();
    }

    private enum TaskType {
        TODO, DEADLINE, EVENT
    }

    private void readInput() {
        while (sc.hasNextLine()) {
            try {
                System.out.println(DIVIDER);
                String next = sc.nextLine();

                if (next.contains(" ")) {
                    int spaceDividerIndex = next.indexOf(" ");
                    String taskType = next.substring(0, spaceDividerIndex).toLowerCase();
                    String taskDetails = next.substring(1 + spaceDividerIndex);
                    switch (taskType) {
                        case "todo":
                            addToList(TaskType.TODO, taskDetails);
                            break;
                        case "deadline": {
                            addToList(TaskType.DEADLINE, taskDetails);
                            break;
                        }
                        case "event": {
                            addToList(TaskType.EVENT, taskDetails);
                            break;
                        }
                        case "done": {
                            try {
                                markAsDone(taskDetails);
                            } catch (IndexOutOfBoundsException e) {
                                System.out.println("Uh oh :( Please enter a valid task number!\n");
                            }
                            break;
                        }
                        case "delete": {
                            try {
                                delete(taskDetails);
                            } catch (IndexOutOfBoundsException e) {
                                System.out.println("Uh oh :( Please enter a valid task number!\n");
                            }
                            break;
                        }
                        default: {
                            throw new PibException("Uh oh :( I don't know that command :(\n");
                        }
                    }
                } else {
                    String action = next.toLowerCase();
                    if (action.equals("list")) {
                        displayList();
                    } else if (action.equals("bye")) {
                        endPib();
                        break;
                    } else {
                        throw new PibException("Uh oh :( I don't know that command :(\n");
                    }
                }
                System.out.println(DIVIDER);
            } catch (PibException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private void endPib() {
        System.out.println("Bye! See you next time!\n");
        System.out.println(DIVIDER);
        sc.close();
    }

    private void addToList(TaskType t, String taskDetails) {
        try {
            if (t.equals(TaskType.TODO)) {
                if (taskDetails.trim().isBlank()) {
                    throw new PibException("Task description cannot be empty!\n");
                }
                list.add(new Todo(taskDetails));
            } else {
                if (t.equals(TaskType.DEADLINE)) {
                    int dateDividerIndex = taskDetails.indexOf("/by ");
                    if (dateDividerIndex == -1) {
                        throw new PibException("Include /by <date>\n");
                    }
                    String description = taskDetails.substring(0, dateDividerIndex);
                    String date = taskDetails.substring(dateDividerIndex + 4);
                    if (description.trim().isBlank()) {
                        throw new PibException("Task description cannot be empty!\n");
                    }
                    if (date.trim().isBlank()) {
                        throw new PibException("Date cannot be empty!\n");
                    }
                    list.add(new Deadline(taskDetails.substring(0, dateDividerIndex), taskDetails.substring(dateDividerIndex + 4)));
                } else if (t.equals(TaskType.EVENT)) {
                    int dateDividerIndex = taskDetails.indexOf("/at ");
                    if (dateDividerIndex == -1) {
                        throw new PibException("Include /at <date>\n");
                    }
                    String description = taskDetails.substring(0, dateDividerIndex);
                    String date = taskDetails.substring(dateDividerIndex + 4);
                    if (description.trim().isBlank()) {
                        throw new PibException("Task description cannot be empty!\n");
                    }
                    if (date.trim().isBlank()) {
                        throw new PibException("Date cannot be empty!\n");
                    }
                    list.add(new Event(taskDetails.substring(0, dateDividerIndex), taskDetails.substring(dateDividerIndex + 4)));
                } else {
                    return;
                }
            }
            System.out.println("Now you have " + list.size() + " task(s) in your list.\n");
        } catch (PibException e) {
            System.out.println(e.getMessage());
        }
    }

    private void displayList() {
        if (list.size() == 0) {
            System.out.println("Nothing added yet\n");
        } else {
            for (int i = 0; i < list.size(); i++) {
                System.out.println((i + 1) + "." + list.get(i).toString());
            }
            System.out.println("");
        }
    }

    private void markAsDone(String s) {
        if (s.isBlank()) {
            System.out.println("Tell me which item to mark as complete!\n ");
        } else {
            list.get(Integer.parseInt(s) - 1).markAsDone();
        }
    }

    private void delete(String s) {
        if (s.isBlank()) {
            System.out.println("Tell me which item to delete!\n ");
        } else {
            list.remove(Integer.parseInt(s) - 1);
            System.out.println("Successfully deleted task!\n");
            System.out.println("Now you have " + list.size() + " task(s) in your list.\n");
        }
    }


    public static void main(String[] args) {
        Pib p = new Pib();
    }
}
