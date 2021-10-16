package ailurus.task;

import java.io.FileWriter;
import java.io.IOException;

import ailurus.AilurusException;


public class Todo extends Task {
    /**
     * Constructor for Todo
     *
     * @param description description of task
     */
    public Todo(String description) throws AilurusException {
        super(description.trim());
        handleException();
    }

    @Override
    public void handleException(String... arr) {
        if (description.length() == 0) {
            throw new AilurusException(AilurusException.Error.EMPTYTODO);
        }
    }

    /**
     * Write data to log task
     *
     * @param writer file writer for writing data to file
     */
    @Override
    public void log(FileWriter writer) {
        try {
            writer.write(String.format("T☺%d☺%s\n", this.isDone ? 1 : 0, this.description.trim()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
