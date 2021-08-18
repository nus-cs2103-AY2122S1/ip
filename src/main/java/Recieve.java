import java.util.ArrayList;
import java.util.Scanner;

public class Recieve {
    private String input;
    private static ArrayList<Task> inputs = new ArrayList<>();


    public Recieve() {
    }

    public void run() {
        Scanner sc = new Scanner(System.in);

        while (true) {
            input = sc.nextLine();

            if (input.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            }
            else if (input.startsWith("done ") && input.length() >= 6 && Character.isDigit(input.charAt(5))) {
                //check 1x ? done x? done 2x? done 0
                int pos = Integer.parseInt(input.substring(5)) - 1;

                if (pos < inputs.size()) {
                    inputs.get(pos).markAsDone();
                    System.out.println("Nice! I've marked this task as done: ");
                    System.out.println(inputs.get(pos).toString());
                }
                else {
                    System.out.println("There are only " + pos + " tasks!");
                }
            }
            else if (input.equals("list")) {
                System.out.println("Here are the tasks in your list:");
                for(int i = 1; i < inputs.size() + 1; i++) {
                    System.out.println(i + "." + inputs.get(i - 1).toString());
                }
            }
            else {
                if (input.startsWith("todo ") || input.equals("todo")) {
                    if (input.length() > 5 ) {
                        Todo task = new Todo(input.substring(5));
                        inputs.add(task);

                        System.out.println("Got it. I've added this task:");
                        System.out.println(task.toString());
                        System.out.println("Now you have " + inputs.size() + " tasks in the list.");
                    }
                    else {
                        try {
                            throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
                        }
                        catch(DukeException ex) {
                            System.out.println(ex.toString());
                        }
                    }

                }
                else if (input.startsWith("deadline ") || input.equals("deadline")) {
                    if (input.length() > 12 && input.contains("/by")) {
                        String[] spl = input.substring(9).split("/");
                        Deadline task = new Deadline(spl[0], spl[1].substring(3));
                        inputs.add(task);

                        System.out.println("Got it. I've added this task:");
                        System.out.println(task.toString());
                        System.out.println("Now you have " + inputs.size() + " tasks in the list.");
                    }
                    else {
                        try {
                            throw new DukeException("☹ OOPS!!! The description of a deadline cannot be empty.");
                        }
                        catch(DukeException ex) {
                            System.out.println(ex.toString());
                        }
                    }

                }
                else if (input.startsWith("event ") || input.equals("event") ) {
                    if (input.length() > 9 && input.contains("/at")) {
                        String[] spl = input.substring(6).split("/");
                        Event task = new Event(spl[0], spl[1].substring(3));
                        inputs.add(task);

                        System.out.println("Got it. I've added this task:");
                        System.out.println(task.toString());
                        System.out.println("Now you have " + inputs.size() + " tasks in the list.");
                    }
                    else {
                        try {
                            throw new DukeException("☹ OOPS!!! The description of an event cannot be empty.");
                        }
                        catch(DukeException ex) {
                            System.out.println(ex.toString());
                        }
                    }

                }
                else {
                    try {
                        throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                    }
                    catch(DukeException ex) {
                        System.out.println(ex.toString());
                    }
                }
            }
        }
        sc.close();
    }



}
