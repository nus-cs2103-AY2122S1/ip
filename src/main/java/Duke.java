import javax.sound.midi.SysexMessage;
import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

    private Storage storage;


    public static void main(String[] args) throws NoSuchTaskException, InvalidInputException {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        String tab = "      ";
        String line = "------------------------------------------------------------";
        System.out.println(line);
        System.out.println("Hello. My name is Necro.");
        System.out.println("What can I do for you on this horrible day?");
        System.out.println(line);
        System.out.println("");

        Scanner sc = new Scanner(System.in);

        // Level-1
//        while (true) {
//            String input = sc.nextLine();
//
//            if (input.equals("bye")) {
//                System.out.println(tab + line);
//                System.out.println(tab + " " + "Farewell, may we never meet again.");
//                System.out.println(tab + line);
//                break;
//            } else {
//                System.out.println(tab + line);
//                System.out.println(tab + " " + input);
//                System.out.println(tab + line);
//            }
//        }

        // Level-2
//        String[] arr = new String[101];
//        int counter = 1;
//        while (true) {
//            String input = sc.nextLine();
//
//            if (input.equals("list")) {
//                System.out.println(tab + line);
//                for (int j = 1; j < counter; j++) {
//                    System.out.println(tab + " " + j + ". " + arr[j]);
//                }
//                System.out.println(tab + line);
//            } else if (input.equals("bye")) {
//                System.out.println(tab + line);
//                System.out.println(tab + " " + "Farewell, may we never meet again.");
//                System.out.println(tab + line);
//                break;
//            } else {
//                System.out.println(tab + line);
//                System.out.println(tab + " added: " + input);
//                System.out.println(tab + line);
//                arr[counter] = input;
//                counter++;
//            }
//        }

        // Level 3
//        Task[] tasks = new Task[101];
//        int counter = 1;
//        while (true) {
//            String input = sc.nextLine();
//
//            if (input.contains("done ")) {
//                int taskNumber = input.charAt(input.length() - 1) - 48;
//                input.split("/");
//                if (taskNumber < counter) {
//                    tasks[taskNumber].complete();
//                    System.out.println(tab + "Wow. Congratulations. You have completed the following task:");
//                    System.out.println(tab + " " + tasks[taskNumber].status);
//                    System.out.println(tab + "Are you happy now?");
//                } else {
//                    System.out.println("You have entered an invalid task number. Fool.");
//                }
//            } else if (input.equals("list")) {
//                System.out.println(tab + line);
//                System.out.println("Here are the tasks in your list:");
//                for (int j = 1; j < counter; j++) {
//                    System.out.println(tab + " " + j + ". " + tasks[j].status);
//                }
//                System.out.println(tab + line);
//            } else if (input.equals("bye")) {
//                System.out.println(tab + line);
//                System.out.println(tab + " " + "Farewell, may we never meet again.");
//                System.out.println(tab + line);
//                break;
//            } else {
//                System.out.println(tab + line);
//                System.out.println(tab + " added: " + input);
//                System.out.println(tab + line);
//                tasks[counter] = new Task(input);
//                counter++;
//            }
//        }

        // Level-4
        // TODO: MAKE NECRO TELL JOKES
        ArrayList<Task> tasks = new ArrayList<>();
        int counter = 0;

        while (true) {
            String input = sc.nextLine();

            if (input.startsWith("delete ")) {
                // Level-6
                String[] strings = input.split(" ");
                if (counter == 0) {
                    System.out.println("There are currently no tasks. You can't delete something that doesn't exist.");
                } else {
                    try {
                        if (strings.length < 2) {
                            System.out.println("You did not specify which task to delete. Please wake up.");
                        } else {
                            int taskNumber = Integer.parseInt(strings[1]) - 1;
                            if (taskNumber < counter) {
                                System.out.println(tab + line);
                                System.out.println(tab + "Since you are so lazy, I've helped you delete this task:");
                                System.out.println(tab + " " + tasks.get(taskNumber).status);
                                System.out.println(tab + "Go do something useful with your life.");
                                System.out.println(tab + line);
                                tasks.remove(taskNumber);
                                counter--;
                            } else {
                                System.out.println("You have entered an invalid task number. Fool.");
                            }
                        }
                    } catch (NumberFormatException ex) {
                        System.err.println(ex);
                    }
                }

            } else if (input.startsWith("done ")) {
                String[] strings = input.split(" ");
                if (counter == 0) {
                    System.out.println("There are currently no tasks. You can't do something that doesn't exist.");
                } else {
                    try {
                        if (strings.length < 2) {
                            System.out.println("You did not specify which task to complete.");
                        } else {
                            int taskNumber = Integer.parseInt(strings[1]) - 1;
                            if (taskNumber < counter) {
                                if (tasks.get(taskNumber).completed) {
                                    System.out.println("The task has already been completed, please be more attentive.");
                                } else {
                                    tasks.get(taskNumber).complete();
                                    System.out.println(tab + line);
                                    System.out.println(tab + "Wow. Congratulations. You have completed the following task:");
                                    System.out.println(tab + " " + tasks.get(taskNumber).status);
                                    System.out.println(tab + "Are you happy now?");
                                    System.out.println(tab + line);
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
                        System.out.println(tab + " " + (j + 1) + ". " + tasks.get(j).status);
                    }
                    System.out.println(tab + line);
                }
            } else if (input.equals("bye")) {
                System.out.println(tab + line);
                System.out.println(tab + " " + "Farewell, may we never meet again.");
                System.out.println(tab + line);
                break;
            } else {
                String output = "";
                if (input.startsWith("todo ") && input.charAt(5) != 97) {
                    String[] strings = input.split(" ", 2);
                    ToDo toDo = new ToDo(strings[1]);
                    output = toDo.status;
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
                    output = deadline.status;
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
                    output = event.status;
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
                System.out.println(tab + line);
                System.out.println(tab + "Fine. I've added this meaningless task to your list: ");
                System.out.println(tab + " --> " + output);
                System.out.println(tab + "Satisfied now? You have " + (counter + 1) + " items in your list.");
                System.out.println(tab + line);
                tasks.add(new Task(output));
                tasks.get(counter).status = tasks.get(counter).status.substring(4);
                counter++;
            }
        }

    }
}
