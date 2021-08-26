package main;

import exception.DukeException;
import exception.EmptyDescription;
import exception.RandomDescription;

import java.util.Scanner;

public class Ui {
    Scanner scan;

    public Ui() {
        this.scan = new Scanner(System.in);
    }

    public void greetings() {
        String greetings = "Hello! I'm Grace" + "\n" + "How can I help you?";
        System.out.println(greetings);
    }

    public String scan_nextLine() {
        String next_line = this.scan.nextLine();
        return next_line;
    }

    public void Empty_Description_exception(String next_line) {
        int caught = 0;

        while (true) {
            try {
                this.catch_error(next_line);
            } catch (DukeException e) {
                System.out.println(e.output_error());
                caught = 1;
            } finally {
                if (caught == 1) {
                    next_line = scan.nextLine();
                    caught = 0;
                } else {
                    break;
                }
            }
        }
    }

    public void catch_error(String next_line) throws DukeException {
        if (next_line.equals("todo")) {
            DukeException e = new EmptyDescription(next_line);
            throw e;
        } else if (next_line.equals("deadline")) {
            DukeException e = new EmptyDescription(next_line);
            throw e;
        } else if (next_line.equals("event")) {
            DukeException e = new EmptyDescription(next_line);
            throw e;
        }
    }

    public void Random_Description_exception(String next_line) {
        while (true) {
            try {
                this.random_error(next_line);
            } catch (DukeException e) {
                System.out.println(e.output_error());
            } finally {
                break;
            }
        }
    }

    public void random_error(String next_line) throws DukeException {
        DukeException e = new RandomDescription(next_line);
        throw e;
    }







}
