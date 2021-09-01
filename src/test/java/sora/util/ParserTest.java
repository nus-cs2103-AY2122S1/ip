package sora.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import sora.exception.IllegalFormatException;
import sora.exception.SoraException;
import sora.exception.UnknownCommandException;
import sora.task.TaskList;

class ParserTest {
    @Test
    void interpretCommand_unknownCommand_exceptionThrown() {
        String[] commands = {
                "blah",
                "ls", "lst", "lista",
                "don", "dona", "donef",
                "tod", "todi", "todo.",
                "dead", "deedline", "dead line",
                "eve", "evant", "event3",
                "del", "deleta", "deleteh",
                "fin", "fine", "finda",
        };

        for (String command : commands) {
            UnknownCommandException exception = assertThrows(UnknownCommandException.class,
                    () -> Parser.interpretCommand(command));

            assertEquals("Sorry but my database does not have such command.\n"
                            + "Try typing 'help' for more information regarding this app!",
                    exception.getMessage());
        }
    }

    @Test
    void interpretCommand_partialCommand_exceptionThrown() {
        String[] commands = {
                "done",
                "todo",
                "deadline",
                "event",
                "delete",
                "find",
        };

        String[] formats = {
                "done [task number]",
                "todo [description]",
                "deadline [description] /by [dd/MM/yy] [HHmm]",
                "event [description] /at [dd/MM/yy] /from [HHmm] /to [HHmm]",
                "delete [task number]",
                "find [keyword]",
        };

        TaskList taskList = new TaskList();
        int i = 0;

        for (String command : commands) {
            IllegalFormatException exception = assertThrows(IllegalFormatException.class,
                    () -> Parser.interpretCommand(command).execute(taskList));

            assertEquals("Please follow this format:\n  " + formats[i++], exception.getMessage());
        }
    }

    @Test
    void interpretCommand_success() throws SoraException {
        TaskList taskListExpected = new TaskList();
        TaskList taskListActual = new TaskList();

        String[] commands = {
                "todo test 1",
                "deadline test 2 /by 06/06/21 1712",
                "event test 3 /at 06/08/21 /from 1400 /to 1800",
                "done 1",
                "todo test 4",
                "delete 4",
                "list",
                "find test",
        };

        CheckedFunction[] expected = {
                (tasks) -> tasks.addTodo(commands[0]),
                (tasks) -> tasks.addDeadline(commands[1]),
                (tasks) -> tasks.addEvent(commands[2]),
                (tasks) -> tasks.taskDone(commands[3]),
                (tasks) -> tasks.addTodo(commands[4]),
                (tasks) -> tasks.taskDelete(commands[5]),
                TaskList::printFullList,
                (tasks) -> tasks.findInList(commands[7]),
        };

        for (int i = 0; i < commands.length; i++) {
            assertEquals(expected[i].execute(taskListExpected),
                    Parser.interpretCommand(commands[i]).execute(taskListActual));
        }
    }
}
