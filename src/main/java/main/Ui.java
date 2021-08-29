package main;

import exception.DukeException;
import exception.EmptyDescription;
import exception.RandomDescription;

import java.util.Scanner;

/**
 * Handle errors and scanning for interaction with user
 */
public class Ui {
    private Scanner scan;

    public Ui() {
        this.scan = new Scanner(System.in);
    }

    public void greetings() {
        String greetings = "Hello! I'm Grace" + "\n" + "How can I help you?";
        System.out.println(greetings);
    }

    public String scanNextLine() {
        String nextLine = this.scan.nextLine();
        return nextLine;
    }


    /**
     * Check whether the user input is a empty description
     * if yes, print out error statement
     *
     * @param nextLine String input to be checked
     */
    public void emptyDescriptionException(String nextLine) {
        int caught = 0;

        while (true) {
            try {
                this.catchError(nextLine);
            } catch (DukeException e) {
                System.out.println(e.output_error());
                caught = 1;
            } finally {
                if (caught == 1) {
                    nextLine = scan.nextLine();
                    caught = 0;
                } else {
                    break;
                }
            }
        }
    }

    public void catchError(String nextLine) throws DukeException {
        if (nextLine.equals("todo")) {
            DukeException e = new EmptyDescription(nextLine);
            throw e;
        } else if (nextLine.equals("deadline")) {
            DukeException e = new EmptyDescription(nextLine);
            throw e;
        } else if (nextLine.equals("event")) {
            DukeException e = new EmptyDescription(nextLine);
            throw e;
        }
    }


    /**
     * Check whether the user input is a random unrelated description
     * if yes, print out error statement
     *
     * @param nextLine String input to be checked
     */
    public void randomDescriptionException(String nextLine) {
        while (true) {
            try {
                this.randomError(nextLine);
            } catch (DukeException e) {
                System.out.println(e.output_error());
            } finally {
                break;
            }
        }
    }

    public void randomError(String nextLine) throws DukeException {
        DukeException e = new RandomDescription(nextLine);
        throw e;
    }







}
