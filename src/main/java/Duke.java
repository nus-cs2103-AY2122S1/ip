import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    private static ArrayList<Task> list = new ArrayList<>();

    private static Scanner sc;

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
            String friendGreeting = "(*^_^*) Friend says: \n";

            if (message.trim().equals("bye")) {
                System.out.println(friendGreeting + "See you again, my friend!");
                return;
            }

            try {
                if (message.trim().equals("list")) {
                    System.out.println(friendGreeting + "Your to-do list has the following tasks: \n");
                    System.out.println(printList());
                }
                else if (message.startsWith("done")) {
                    if (message.length() > 5 && message.substring(5).chars().allMatch(Character::isDigit)) {
                        int taskIndex = Integer.parseInt(message.substring(5)) - 1;
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
                        String description = message.substring(5);
                        list.add(new ToDo(description));

                        System.out.println(friendGreeting + "added: " + list.get(list.size() - 1).toString() + " to your to-do list!");
                        System.out.println("Now you have " + list.size() + " tasks in the list.");
                    } else {
                        throw new DukeException.DukeNoDescriptionException();
                    }
                }
                // deadline
                else if (message.startsWith("deadline ") || message.equals("deadline")) {
                    if (message.contains(" /by ")) {
                        String description = message.substring(9, message.indexOf("/by"));
                        if (message.length() > message.indexOf("/by") + 3) {
                            String by = message.substring(message.indexOf("/by") + 4);
                            if (description.isBlank()) {
                                // blank description
                                throw new DukeException.DukeNoDescriptionException();
                            } else if (by.isBlank()) {
                                // proper description, blank /by
                                throw new DukeException.DukeNoTimeGivenException();
                            } else {
                                // proper description and by
                                list.add(new Deadline(description, by));

                                System.out.println(friendGreeting + "added: " + list.get(list.size() - 1).toString()
                                        + " to your to-do list!");
                                System.out.println("Now you have " + list.size() + " tasks in the list.");
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
                        String description = message.substring(6, message.indexOf("/at"));
                        if (message.length() > message.indexOf("/at") + 3) {
                            String at = message.substring(message.indexOf("/at") + 4);
                            if (description.isBlank()) {
                                // blank description
                                throw new DukeException.DukeNoDescriptionException();
                            } else if (at.isBlank()) {
                                // proper description, blank /at
                                throw new DukeException.DukeNoTimeGivenException();
                            } else {
                                // proper description and at
                                list.add(new Event(description, at));

                                System.out.println(friendGreeting + "added: " + list.get(list.size() - 1).toString()
                                        + " to your to-do list!");
                                System.out.println("Now you have " + list.size() + " tasks in the list.");
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
