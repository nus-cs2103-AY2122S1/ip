package main.java;

import java.io.IOException;
import java.time.DateTimeException;


public class Done extends Command {
    Done(String description) {
        super(description);
    }
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            int index = Integer.valueOf(description);
            if (index > tasks.count()) {
                throw new IllegalArgumentException();
            }
            tasks.setDone(index - 1);
            storage.setDone(index - 1);
        } catch (DateTimeException e) {
            throw new DukeException("Your date (YYYY-MM-DD) / date & time (YYYY-MM-DD HHMM) (24h) is given in the wrong format!");
        } catch (IllegalArgumentException e) {
            throw new DukeException("There is no such task in existence.");
        }  catch (IOException e) {
            throw new DukeException("There is an error reflecting the task as done in the saved data.");
        }
    }
}
