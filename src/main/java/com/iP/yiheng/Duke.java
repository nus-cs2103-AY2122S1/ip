package com.iP.yiheng;

import java.io.IOException;
import java.util.Locale;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.greet();

        Scanner scanner = new Scanner(System.in);
        Task task = new Task();
        task.loadArrayList();
        boolean breakWhile = false;

        while (scanner.hasNext()) {
            String firstWord = scanner.next().toLowerCase(Locale.ROOT);

            switch (firstWord) {
                case "bye":
                    duke.exit();
                    breakWhile = true;
                    break;
                case "done":
                    String stringIndex = scanner.next();
                    int index = Integer.parseInt(stringIndex) - 1;
                    if (task.markDone(index)) {
                        System.out.println("\nDuke: Nice! I've marked this task as done:\n" + Task.retrieveTask(index));
                    }
                    break;
                case "list":
                    Task.displayList();
                    break;
                case "delete":
                    String stringIndex2 = scanner.next();
                    int index2 = Integer.parseInt(stringIndex2) - 1;
                    Task deletedTask = Task.retrieveTask(index2);
                    if (deletedTask == null) break;
                    task.delete(index2);
                    System.out.println("\nDuke: Noted. I've removed this task:\n"
                    + deletedTask + "\nNow you have " + Task.listLength() + " tasks in the list.");
                    break;
                default:
                    String remaining = firstWord.concat(" " + scanner.nextLine());
                    try {
                        task.add(remaining);
                        break;
                    } catch (DukeException e) {
                        System.out.println(e);
                    }
            }
            if (breakWhile) break;
        }
    }

    private void greet() {
        System.out.println("Duke: Hello! I'm Duke\nWhat can I do for you?");
    }

    private void exit() {
        System.out.println("\nDuke: Bye. Hope to see you again soon!");
    }
}
