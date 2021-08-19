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
            try {
                input = sc.nextLine();

                if (input.equals("bye")) {
                    System.out.println("Bye. Hope to see you again soon!");
                    break;
                }
                else if (input.startsWith("done ") || input.startsWith("done")) {
                    if (input.length() > 5 && Character.isDigit(input.charAt(5))) {
                        try {
                            int pos = Integer.parseInt(input.substring(5)) - 1;
                            if (pos < 1) {
                                throw new DukeException("Task list index starts from 1!");
                            }

                            else if (pos < inputs.size()) {
                                inputs.get(pos).markAsDone();
                                System.out.println("Nice! I've marked this task as done: ");
                                System.out.println(inputs.get(pos).toString());
                            }
                            else {
                                throw new DukeException("There are only " + pos + " tasks!");
                            }
                        } catch (NumberFormatException nfe) {
                            throw new DukeException("A task index should only contain numbers!");
                        }

                    } else {
                        throw new DukeException("Please specify the task index to be marked as done!");
                    }


                }
                else if (input.equals("list")) {
                    if (inputs.size() == 0) {
                        System.out.println("There is no task for now :)");
                    }
                    else {
                        System.out.println("Here are the tasks in your list:");
                        for(int i = 1; i < inputs.size() + 1; i++) {
                            System.out.println(i + "." + inputs.get(i - 1).toString());
                        }

                    }
                }
                else if (input.startsWith("delete ") || input.startsWith("delete")) {
                    if (input.length() > 7 && Character.isDigit(input.charAt(7))) {
                        try {
                            int pos = Integer.parseInt(input.substring(7)) - 1;

                            if (pos < 1) {
                                throw new DukeException("Task list index starts from 1!");
                            }

                            else if (pos < inputs.size()) {
                                System.out.println("Noted. I've removed this task: ");
                                System.out.println(inputs.get(pos).toString());
                                inputs.remove(pos);
                                System.out.println("Now you have " + inputs.size() + " task(s) in the list.");
                            }
                            else {
                                throw new DukeException("There are only " + pos + " tasks!");
                            }
                        } catch (NumberFormatException nfe) {
                            throw new DukeException("A task index should only contain numbers!");
                        }

                    }
                    else {
                        throw new DukeException("Please specify the task index to be deleted!");
                    }

                }
                else {
                    if (input.startsWith("todo ") || input.equals("todo")) {
                        if (input.length() > 5 ) {
                            Todo task = new Todo(input.substring(5));
                            inputs.add(task);

                            System.out.println("Got it. I've added this task:");
                            System.out.println(task.toString());
                            System.out.println("Now you have " + inputs.size() + " task(s) in the list.");
                        }
                        else {
                            throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
                        }

                    }
                    else if (input.startsWith("deadline ") || input.equals("deadline")) {
                        if (!input.contains(" /by ")) {
                            throw new DukeException("OOPS!! To add a Deadline, type -> deadline <Description> /by <deadline>!");
                        }
                        if (input.length() > 12 && input.contains("/by")) {
                            String[] spl = input.substring(9).split("/");
                            if (spl[0].length() < 2 || spl[1].length() < 4) {
                                System.out.println(spl[0].length());
                                System.out.println(spl[1].length());
                                throw new DukeException("☹ OOPS!!! The descriptions of a deadline cannot be empty.");
                            }
                            else {
                                Deadline task = new Deadline(spl[0], spl[1].substring(3));
                                inputs.add(task);

                                System.out.println("Got it. I've added this task:");
                                System.out.println(task.toString());
                                System.out.println("Now you have " + inputs.size() + " task(s) in the list.");
                            }
                        }
                        else {
                            throw new DukeException("☹ OOPS!!! The descriptions of a deadline cannot be empty.");
                        }

                    }
                    else if (input.startsWith("event ") || input.equals("event") ) {
                        if (!input.contains(" /at ")) {
                            throw new DukeException("OOPS!! To add an Event, type -> event <Description> /at <details>!");
                        }
                        if (input.length() > 9 && input.contains("/at")) {
                            String[] spl = input.substring(6).split("/");
                            if (spl[0].length() < 2 || spl[1].length() < 4) {
                                throw new DukeException("☹ OOPS!!! The descriptions of an event cannot be empty.");
                            }
                            else {
                                Event task = new Event(spl[0], spl[1].substring(3));
                                inputs.add(task);

                                System.out.println("Got it. I've added this task:");
                                System.out.println(task.toString());
                                System.out.println("Now you have " + inputs.size() + " task(s) in the list.");
                            }
                        }
                        else {
                            throw new DukeException("☹ OOPS!!! The descriptions of an event cannot be empty.");
                        }

                    }
                    else {
                        throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                    }
                }
            } catch (DukeException ex) {
                System.out.println(ex.toString());
            }

        }
        sc.close();
    }



}
