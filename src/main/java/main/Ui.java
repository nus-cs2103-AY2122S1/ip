package main;

import exception.DukeException;
import exception.EmptyDescription;
import exception.RandomDescription;

import java.util.Scanner;

/**
 * Handle errors and scanning for interaction with user.
 */
public class Ui {
    private Scanner scan;

    public Ui() {
        this.scan = new Scanner(System.in);
    }

    public String scanNextLine() {
        String nextLine = this.scan.nextLine();
        return nextLine;
    }


    /**
     * Check whether the user input is a empty description.
     * if yes, print out error statement.
     *
     * @param nextLine String input to be checked.
     */
    public String emptyDescriptionException(String nextLine) {
        String returned = "";

        try {
            this.catchError(nextLine);
        } catch (DukeException e) {
            returned = e.output_error() + "\n";
        } finally {
        }
        return returned;
    }

    /**
     * Catch empty description error and throw correct type of task error.
     *
     * @param nextLine String input without task description.
     * @throws DukeException  If the description is empty.
     */
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
     * Check whether the user input is a random unrelated description.
     * if yes, print out error statement.
     *
     * @param nextLine String input to be checked.
     */
    public String randomDescriptionException(String nextLine) {
        String returned = "";

        while (true) {

            try {
                this.randomError(nextLine);
            } catch (DukeException e) {
                returned = e.output_error() + "\n";
            } finally {
                break;
            }

        }
        return returned;
    }

    /**
     * Catch random invalid description error.
     *
     * @param nextLine String input without task description.
     * @throws DukeException  If the input is gibberish.
     */
    public void randomError(String nextLine) throws DukeException {
        DukeException e = new RandomDescription(nextLine);
        throw e;
    }







}
