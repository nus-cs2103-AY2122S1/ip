import java.util.Scanner;
import java.util.ArrayList;

public class Duke {

    public static int doneCheck(String response, int currentCapacity) {
        String strippedResponse = response.strip();
        if (strippedResponse.startsWith("done")) {
            if (strippedResponse.length() < 6) {
                return 0;
            } else {
                String cutString = strippedResponse.substring(5);
                char[] chars = cutString.toCharArray();
                StringBuilder sb = new StringBuilder();
                for (char c : chars) {
                    if (Character.isDigit(c)) {
                        sb.append(c);
                    } else {
                        return 0;
                    }
                }
                Integer intToCheck = Integer.parseInt(sb.toString());
                if (intToCheck <= currentCapacity) {
                    return intToCheck;
                } else {
                    return -1;
                }
            }
        } else {
            return 0;
        }
    }

    public static boolean eventCheck(String response) {
        if (response.startsWith("event ") && response.split("/at").length == 2) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean deadlineCheck(String response) {
        if (response.startsWith("deadline ") && response.split("/by").length == 2) {
            return true;
        } else {
            return false;
        }
    }
    public static boolean todoCheck(String response) {
        if (response.startsWith("todo")) {
            return true;
        } else {
            return false;
        }
    }

    public static int undoCheck(String response, int currentCapacity) {
        String strippedResponse = response.strip();
        if (strippedResponse.startsWith("undo")) {
            if (strippedResponse.length() < 6) {
                return 0;
            } else {
                String cutString = strippedResponse.substring(5);
                char[] chars = cutString.toCharArray();
                StringBuilder sb = new StringBuilder();
                for (char c : chars) {
                    if (Character.isDigit(c)) {
                        sb.append(c);
                    } else {
                        return 0;
                    }
                }
                Integer intToCheck = Integer.parseInt(sb.toString());
                if (intToCheck <= currentCapacity) {
                    return intToCheck;
                } else {
                    return -1;
                }
            }
        } else {
            return 0;
        }
    }

    public static int deleteCheck(String response, int currentCapacity) {
        String strippedResponse = response.strip();
        if (strippedResponse.startsWith("delete")) {
            if (strippedResponse.length() < 8) {
                return 0;
            } else {
                String cutString = strippedResponse.substring(7);
                char[] chars = cutString.toCharArray();
                StringBuilder sb = new StringBuilder();
                for (char c : chars) {
                    if (Character.isDigit(c)) {
                        sb.append(c);
                    } else {
                        return 0;
                    }
                }
                Integer intToCheck = Integer.parseInt(sb.toString());
                if (intToCheck <= currentCapacity) {
                    return intToCheck;
                } else {
                    return -1;
                }
            }
        } else {
            return 0;
        }
    }

    public static String responseToTodo(String response) {
        return response.substring(5);
    }
    public static String pluralOrNo(int cap) {
        return cap <= 1 ? "" : "s";
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String greetingMessage = "Henlo, this is Duke.\nHow may I help?";
        System.out.println(greetingMessage);
        String listCommand = "list";
        String byeString = "bye";
        String listFullMessage = "Your current list is full! Unable to add any more items!";
        String taskAddedMessage  = "Got it. I've added this task:\n  %s\nNow you have %d task%s in the list\n";
        ArrayList<Task> userTaskList = new ArrayList<Task>(100);
        int currentCapacity = 0;

        while (true) {
            String response = sc.nextLine();

            if (response.equals(byeString)) {
                System.out.println("Byebye!! Hehe..");
                break;
            } else if (response.startsWith("done ")) {
                if (doneCheck(response, currentCapacity) > 0) {
                    int positionToMarkDone = doneCheck(response, currentCapacity);
                    userTaskList.get(positionToMarkDone - 1).markDone();
                    String replyString = "Nice! I've marked this task as done:\n";
                    replyString += String.format("  %s", userTaskList.get(positionToMarkDone - 1));
                    System.out.println(replyString);
                    continue;
                } else if (doneCheck(response, currentCapacity) == -1) {
                    System.out.println("No such task exists! Please try again!");
                    continue;
                } else {
                    System.out.println("Please indicate the task you'd like to mark as done!");
                }
            } else if (response.startsWith("undo ")) {
                if (undoCheck(response, currentCapacity) > 0) {
                    int positionToMarkUndone = undoCheck(response, currentCapacity);
                    userTaskList.get(positionToMarkUndone - 1).markUndone();
                    String replyString = "Oh no! I've marked this task as undone:\n";
                    replyString += String.format("  %s", userTaskList.get(positionToMarkUndone - 1));
                    System.out.println(replyString);
                    continue;
                } else if (undoCheck(response, currentCapacity) == -1) {
                    System.out.println("No such task exists! Please try again!");
                    continue;
                } else {
                    System.out.println("Please indicate the task you'd like to undo!");
                }
            } else if (response.startsWith("delete ")){
                if (deleteCheck(response, currentCapacity) > 0) {
                    int positionToRemove = deleteCheck(response, currentCapacity);
                    Task removedTask = userTaskList.remove(positionToRemove - 1);
                    currentCapacity -= 1;
                    System.out.printf("Noted. I've removed this task:\n  %s\nNow you have %d task%s in the list.\n", removedTask, currentCapacity, pluralOrNo(currentCapacity));
                } else if (deleteCheck(response, currentCapacity) == -1) {
                    System.out.println("No such task exists! Please try again!");
                    continue;
                } else {
                    System.out.println("Please indicate the task you'd like to delete!");
                }

            } else if (response.equals(listCommand)) {
                if (currentCapacity > 0) {
                    String toPrint = "Here are the tasks in your list:\n";
                    for (int i = 0; i < currentCapacity; i++) {
                        Task task = userTaskList.get(i);
                        toPrint += String.format("%d. %s\n", i + 1, task);
                    }
                    System.out.print(toPrint);
                } else {
                    System.out.println("You have no tasks!");
                }
            } else if (response.startsWith("todo")) {
                if (response.strip().length() == 4) {
                    System.out.println("The description of a todo cannot be empty.");
                    continue;
                }
                if (!response.startsWith("todo ")) {
                    System.out.println("Did you mean to use the 'todo' command?");
                    continue;
                }
                if (currentCapacity < 100) {
                    userTaskList.add(new ToDo(responseToTodo(response)));
                    System.out.printf(taskAddedMessage, userTaskList.get(currentCapacity), currentCapacity + 1, pluralOrNo(currentCapacity + 1));
                    currentCapacity += 1;
                } else {
                    System.out.println(listFullMessage);
                }
            } else if (response.startsWith("event")) {
                if (response.split("/at")[0].strip().length() == 5) {
                    System.out.println("The description of an event cannot be empty.");
                    continue;
                }
                if (!response.startsWith("event ")) {
                    System.out.println("Did you mean to use the 'event' command?");
                    continue;
                }

                if (!response.contains("/at")) {
                    System.out.println("Please specify the date of the event with '/at'.");
                    continue;
                }
                if (response.split("/at").length < 2 || response.split("/at")[1].strip().length() < 1) {
                    System.out.println("The date of an event cannot be empty.");
                    continue;
                }
                if (currentCapacity < 100) {
                    String[] eventSplit = response.split("/at");
                    String description = eventSplit[0].strip().substring(6);
                    String date = eventSplit[1].strip();
                    userTaskList.add(new Event(description, date));
                    System.out.printf(taskAddedMessage, userTaskList.get(currentCapacity), currentCapacity + 1, pluralOrNo(currentCapacity + 1));
                    currentCapacity += 1;
                } else {
                    System.out.println(listFullMessage);
                }
            } else if (response.startsWith("deadline")) {
                if (response.split("/by")[0].strip().length() == 8) {
                    System.out.println("The description of a deadline cannot be empty.");
                    continue;
                }
                if (!response.startsWith("deadline ")) {
                    System.out.println("Did you mean to use the 'deadline' command?");
                    continue;
                }

                if (!response.contains("/by")) {
                    System.out.println("Please specify the date of the deadline with '/by'.");
                    continue;
                }
                if (response.split("/by").length < 2 || response.split("/by")[1].strip().length() < 1) {
                    System.out.println("The date of a deadline cannot be empty.");
                    continue;
                }
                if (currentCapacity < 100) {
                    String[] deadlineSplit = response.split("/by");
                    String description = deadlineSplit[0].strip().substring(9);
                    if (description.length() < 1) {
                        System.out.println("The description of a deadline cannot be empty.");
                        continue;
                    }
                    String date = deadlineSplit[1].strip();
                    userTaskList.add(new Deadline(description, date));
                    System.out.printf(taskAddedMessage, userTaskList.get(currentCapacity), currentCapacity + 1, pluralOrNo(currentCapacity + 1));
                    currentCapacity += 1;
                } else {
                    System.out.println(listFullMessage);
                }
            } else {
                System.out.printf("I don't quite understand the command '%s'. Please try again!\n", response);
            }
        }
    }
}
