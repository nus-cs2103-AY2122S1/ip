import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static ArrayList<Task> list = new ArrayList<>();

    private static Scanner sc;

    public static final String friendGreeting = "(*^_^*) Friend says: \n";

    public static void main(String[] args) {
        String logo
                = " _____       _                    _        \n"
                + "|  ___|  __  _  ___   _,____     | |       \n"
                + "| |_  |/  _|| |/ _ \\ |  __  |____| |       \n"
                + "| __| | /   | |  __/ | / \\  |  __  |       \n"
                + "|_|   |_|   |_|\\____ |_|  |_|______|       \n";

        System.out.println("Hi there! Start chatting with your new \n" + logo);
        System.out.println("What would you like to do today?");

        sc = new Scanner(System.in);  // Create a Scanner object

        handleInput();
    }

    private static String printList() {
        String listString = "";
        for (int i = 0; i < list.size(); i++) {
            int index = i + 1;
            listString = listString + index + "." + list.get(i).toString() + "\n";
        }
        return listString;
    }

    private static void handleInput() {

        while (sc.hasNextLine()) {

            String message = sc.nextLine();

            if (message.trim().equals("bye")) {
                System.out.println(Duke.friendGreeting + "See you again, my friend!");
                break;
            }

            try {
                // List tasks
                if (message.trim().equals("list")) {
                    System.out.println(Duke.friendGreeting + "Your to-do list has the following tasks: \n");
                    System.out.println(printList());
                }
                // Delete tasks
                else if (message.startsWith("delete")) {
                    if (message.length() > 7 && message.substring(6, 7).equals(" ")
                            && message.substring(7).trim().chars().allMatch(Character::isDigit)) {
                        int taskIndex = Integer.parseInt(message.substring(7).trim()) - 1;
                        if (0 <= taskIndex && taskIndex < list.size()) {
                            String removed = list.get(taskIndex).toString();
                            list.remove(taskIndex);

                            System.out.println(friendGreeting + "removed the following task from your to-do list: \n" + removed);
                            System.out.println("Now you have " + list.size() + " tasks in the list.");
                        } else {
                            throw new DukeException.DukeTaskNotFoundException();
                        }
                    } else {
                        throw new DukeException.DukeTaskFailException();
                    }
                }
                // Mark tasks as done
                else if (message.startsWith("done")) {
                    if (message.length() > 5 && message.substring(4, 5).equals(" ")
                            && message.substring(5).trim().chars().allMatch(Character::isDigit)) {
                        int taskIndex = Integer.parseInt(message.substring(5).trim()) - 1;
                        if (0 <= taskIndex && taskIndex < list.size()) {
                            Task task = list.get(taskIndex);
                            String description = task.description;
                            if (!task.isDone) {
                                task.markAsDone();
                                System.out.println(friendGreeting + "Hooray! You've completed task \n[X] " + description);
                            } else {
                                System.out.println(description + " has already been done! :)");
                            }
                        } else {
                            throw new DukeException.DukeTaskNotFoundException();
                        }
                    } else {
                        throw new DukeException.DukeTaskFailException();
                    }
                }
                // To Do
                else if (message.startsWith("todo ") || message.equals("todo")) {
                    if (message.length() > 5 && !message.substring(5).isBlank()) {
                        String description = message.substring(5).trim();
                        Task.createTask(description, "", Task.Category.TODO);
                    } else {
                        throw new DukeException.DukeNoDescriptionException();
                    }
                }
                // deadline
                else if (message.startsWith("deadline ") || message.equals("deadline")) {
                    if (message.contains(" /by ")) {
                        String description = message.substring(9, message.indexOf("/by")).trim();
                        if (message.length() > message.indexOf("/by") + 3) {
                            String by = message.substring(message.indexOf("/by") + 4).trim();
                            if (description.isBlank()) {
                                // blank description
                                throw new DukeException.DukeNoDescriptionException();
                            } else if (by.isBlank()) {
                                // proper description, blank /by
                                throw new DukeException.DukeNoTimeGivenException();
                            } else {
                                // proper description and by
                                Task.createTask(description, by, Task.Category.DEADLINE);
                            }
                        } else {
                            throw new DukeException.DukeNoTimeGivenException();
                        }
                    } else {
                        if (message.contains(" /by") || message.equals("deadline") || message.substring(9).isBlank()) {
                            throw new DukeException.DukeNoDescriptionException();
                        } else if (message.contains("by")) {
                            throw new DukeException.DukeInvalidInputException();
                        } else {
                            throw new DukeException.DukeNoTimeGivenException();
                        }
                    }
                }
                // event
                else if (message.startsWith("event ") || message.equals("event")) {
                    if (message.contains(" /at ")) {
                        String description = message.substring(6, message.indexOf("/at")).trim();
                        if (message.length() > message.indexOf("/at") + 3) {
                            String at = message.substring(message.indexOf("/at") + 4).trim();
                            if (description.isBlank()) {
                                // blank description
                                throw new DukeException.DukeNoDescriptionException();
                            } else if (at.isBlank()) {
                                // proper description, blank /at
                                throw new DukeException.DukeNoTimeGivenException();
                            } else {
                                // proper description and at
                                Task.createTask(description, at, Task.Category.EVENT);
                            }
                        } else {
                            throw new DukeException.DukeNoTimeGivenException();
                        }
                    } else {
                        if (message.contains(" /at") || message.equals("event") || message.substring(6).isBlank()) {
                            throw new DukeException.DukeNoDescriptionException();
                        } else if (message.contains("at")) {
                            throw new DukeException.DukeInvalidInputException();
                        } else {
                            throw new DukeException.DukeNoTimeGivenException();
                        }
                    }
                }
                // invalid input
                else {
                    throw new DukeException.DukeInvalidInputException();
                }
            } catch (DukeException e) {
                System.out.println(e);
            } finally {
                handleInput();
            }
        }
    }
}
