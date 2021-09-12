package yoyo.core;

import org.junit.jupiter.api.Test;
import yoyo.command.CommandBye;
import yoyo.command.CommandDeadline;
import yoyo.command.CommandDelete;
import yoyo.command.CommandDone;
import yoyo.command.CommandEvent;
import yoyo.command.CommandFind;
import yoyo.command.CommandList;
import yoyo.command.CommandTag;
import yoyo.command.CommandTodo;
import yoyo.exception.YoyoException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class ParserTest {
    @Test
    public void parserParse_addTaskCommands_success() {
        String todoInput = "todo eat";
        String deadlineInput = "deadline /by 1999-11-11 1900";
        String eventInput = "event /at 1999-11-11 1899";
        String[] todoInputToken = {"todo", "eat"};
        String[] deadlineInputToken = {"deadline", "/by 1999-11-11 1900"};
        String[] eventInputToken = {"event", "/at 1999-11-11 1899"};

        try {
            assertEquals(new CommandTodo(todoInputToken), Parser.parse(todoInput));
            assertEquals(new CommandDeadline(deadlineInputToken), Parser.parse(deadlineInput));
            assertEquals(new CommandEvent(eventInputToken), Parser.parse(eventInput));
        } catch (YoyoException e) {
            fail();
        }
    }

    @Test
    public void parserParse_singleTokenCommands_success() {
        String byeInput = "bye";
        String listInput = "list";
        String[] byeInputToken = {"bye"};
        String[] listInputToken = {"list"};

        try {
            assertEquals(new CommandBye(byeInputToken), Parser.parse(byeInput));
            assertEquals(new CommandList(listInputToken), Parser.parse(listInput));
        } catch (YoyoException e) {
            fail();
        }
    }

    @Test
    public void parserParse_modifyStatusCommands_success() {
        String doneInput = "done 3";
        String deleteInput = "delete 3";
        String tagInput = "tag 3 housework school";
        String[] doneInputToken = {"done", "3"};
        String[] deleteInputToken = {"delete", "3"};
        String[] tagInputToken = {"tag", "3 housework school"};

        try {
            assertEquals(new CommandDone(doneInputToken), Parser.parse(doneInput));
            assertEquals(new CommandDelete(deleteInputToken), Parser.parse(deleteInput));
            assertEquals(new CommandTag(tagInputToken), Parser.parse(tagInput));
        } catch (YoyoException e) {
            fail();
        }
    }

    @Test
    public void parserParse_findCommand_success() {
        String findInput = "find cs2103";
        String[] findInputToken = {"find", "cs2103"};

        try {
            assertEquals(new CommandFind(findInputToken), Parser.parse(findInput));
        } catch (YoyoException e) {
            fail();
        }
    }
}
