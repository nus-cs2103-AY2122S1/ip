import java.util.ArrayList;
import java.util.Scanner;  // Import the Scanner class

public class Duke {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Task> list = new ArrayList<>();
        String line = "-------------------------------------";
        System.out.println(line + "\n" + "Good Morning Master Wayne, Alfred here.\nWhat can I do for you today?\n" + line);

        String commandLine = scanner.nextLine();
        boolean done = false;

        while(!done) {
            String[] split = commandLine.split(" ");
            String command = split[0];
            String desc;

            switch(command) {
                case "bye" :
                    System.out.println("Have a pleasant day, Master Wayne.\n");
                    done = true;
                    break;

                case "list" :
                    System.out.println(line);
                    for (int i = 0; i < list.size(); i++) {
                        Task task = list.get(i);
                        System.out.println((i + 1) + ". " + " " + task.toString());
                    }
                    System.out.println(line);

                    commandLine = scanner.nextLine();
                    break;

                case "done" :
                    try {
                        checkLength(split.length);
                        int index = Integer.parseInt(split[1]);
                        checkIndex(index, list.size());

                        System.out.println(line);
                        list.get(index - 1).markAsDone();
                        System.out.println("Very well, Master Wayne. This task has been marked as per your request.");
                        System.out.println((index) + ". " + list.get(index - 1)); //actual index is index - 1
                        System.out.println(line);
                    } catch (DukeException e) {
                        System.out.println("***WARNING*** An error has occurred Master Wayne: " + e.getMessage());
                    }

                    commandLine = scanner.nextLine();
                    break;

                case "delete" :
                    try {
                        checkLength(split.length);
                        int index = Integer.parseInt(split[1]);
                        checkIndex(index, list.size());

                        System.out.println(line);
                        System.out.println("Very well, Master Wayne. This task has been deleted as per your request.");
                        System.out.println((index) + ". " + list.get(index - 1)); //actual index is index - 1

                        list.remove(index - 1);
                        if (list.size() == 1) {
                            System.out.println("Now you have 1 task in the list.");
                        } else {
                            System.out.println("Now you have " + list.size() + " tasks in the list.");
                        }

                        System.out.println(line);

                    } catch (DukeException e) {
                        System.out.println("***WARNING*** An error has occurred Master Wayne: " + e.getMessage());
                    }

                    commandLine = scanner.nextLine();
                    break;

                case "todo" :
                    StringBuilder todoBuilder = new StringBuilder();
                    for (int i = 1; i < split.length; i++) {
                        if (i != 1) {
                            todoBuilder.append(" ");
                        }
                        todoBuilder.append(split[i]);
                    }
                    desc = todoBuilder.toString();
                    try {
                        checkDesc(desc);
                        Todo todo = new Todo(desc);
                        list.add(todo);

                        System.out.println("Got it. I've added this task:");
                        System.out.println(todo);

                        if (list.size() == 1) {
                            System.out.println("Now you have 1 task in the list.");
                        } else {
                            System.out.println("Now you have " + list.size() + " tasks in the list.");
                        }
                    } catch (DukeException e) {
                        System.out.println("***WARNING*** An error has occurred Master Wayne: " + e.getMessage());
                    }

                    commandLine = scanner.nextLine();
                    break;

                case "deadline" :
                    StringBuilder deadlineBuilder = new StringBuilder();
                    StringBuilder byBuilder = new StringBuilder();
                    String by;
                    boolean byFound = false;

                    for (int i = 1; i < split.length; i++) {
                        if (byFound) {
                            if (!byBuilder.toString().equals("")) {
                                byBuilder.append(" ");
                            }
                            byBuilder.append(split[i]);
                        } else {
                            if (i == 1) {
                                deadlineBuilder.append(split[1]);
                            } else if (split[i].equals("/by")) {
                                byFound = true;
                            } else {
                                deadlineBuilder.append(" ");
                                deadlineBuilder.append(split[i]);
                            }
                        }
                    }
                    desc = deadlineBuilder.toString();
                    by = byBuilder.toString();
                    try {
                        checkDesc(desc);
                        Deadline deadline = new Deadline(desc, by);
                        list.add(deadline);

                        System.out.println("Got it. I've added this task:");
                        System.out.println(deadline);
                        if (list.size() == 1) {
                            System.out.println("Now you have 1 task in the list.");
                        } else {
                            System.out.println("Now you have " + list.size() + " tasks in the list.");
                        }

                    } catch (DukeException e) {
                        System.out.println("***WARNING*** An error has occurred Master Wayne: " + e.getMessage());
                    }

                    commandLine = scanner.nextLine();
                    break;

                case "event" :
                    StringBuilder eventBuilder = new StringBuilder();
                    StringBuilder atBuilder = new StringBuilder();
                    String at;
                    boolean atFound = false;

                    for (int i = 1; i < split.length; i++) {
                        if (atFound) {
                            if (!atBuilder.toString().equals("")) {
                                atBuilder.append(" ");
                            }
                            atBuilder.append(split[i]);
                        } else {
                            if (i == 1) {
                                eventBuilder.append(split[i]);
                            } else if (split[i].equals("/at")) {
                                atFound = true;
                            } else {
                                eventBuilder.append(" ");
                                eventBuilder.append(split[i]);
                            }
                        }
                    }
                    desc = eventBuilder.toString();
                    at = atBuilder.toString();
                    try {
                        checkDesc(desc);
                        Event event = new Event(desc, at);
                        list.add(event);

                        System.out.println("Got it. I've added this task:");
                        System.out.println(event);

                        if (list.size() == 1) {
                            System.out.println("Now you have 1 task in the list.");
                        } else {
                            System.out.println("Now you have " + list.size() + " tasks in the list.");
                        }
                    } catch (DukeException e) {
                        System.out.println("***WARNING*** An error has occurred Master Wayne: " + e.getMessage());
                    }

                    commandLine = scanner.nextLine();
                    break;

                default :
                    System.out.println("*** Apologies, Master Wayne. But I don't know what that means ***");
                    commandLine = scanner.nextLine();
            }
        }
    }


    public static void checkDesc(String test) throws DukeException {
        if (test.equals("")) {
            throw new DukeException("The description is empty");
        }
    }

    public static void checkLength(int l) throws DukeException {
        if (l == 1) {
            throw new DukeException("Please give an index number");
        }
    }

    public static void checkIndex(int i, int lengthOfList) throws DukeException {
        if (i <= 0) {
            throw new DukeException("Please give an index number > 0");
        } else if (i > lengthOfList) {
            throw new DukeException("Maximum index number is " + lengthOfList);
        }
    }
}