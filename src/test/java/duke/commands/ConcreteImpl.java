package duke.commands;

import duke.Storage;
import duke.Tasklist;
import duke.Ui;
import duke.exceptions.NoSuchTaskException;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ConcreteImpl extends Command {
    ConcreteImpl(){
        super();
    }

    @Override
    public String execute(Tasklist tasklist, Ui ui, Storage storage) {
    }

    @Test
    public void isExitTest(){
        assertEquals(false, isExit());
    }



}
