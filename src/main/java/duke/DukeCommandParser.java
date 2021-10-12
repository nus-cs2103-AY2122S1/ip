package duke;

import duke.command.CommandAddTag;
import duke.command.CommandBye;
import duke.command.CommandDeadline;
import duke.command.CommandDelete;
import duke.command.CommandDeleteTag;
import duke.command.CommandDone;
import duke.command.CommandEvent;
import duke.command.CommandFind;
import duke.command.CommandList;
import duke.command.CommandListTag;
import duke.command.CommandTodo;
import duke.command.DukeCommand;
import duke.exception.DukeArgumentException;
import duke.exception.DukeCommandException;

public class DukeCommandParser {
    public static DukeCommand parseInput(String input) throws DukeCommandException, DukeArgumentException {
        String[] inputArr = input.split(" ", 2);
        DukeCommand dc;
        switch (inputArr[0]) {
        case "bye":
            dc = CommandBye.parseCommand(inputArr);
            break;
        case "list":
            dc = CommandList.parseCommand(inputArr);
            break;
        case "todo":
            dc = CommandTodo.parseCommand(inputArr);
            break;
        case "deadline":
            dc = CommandDeadline.parseCommand(inputArr);
            break;
        case "done":
            dc = CommandDone.parseCommand(inputArr);
            break;
        case "event":
            dc = CommandEvent.parseCommand(inputArr);
            break;
        case "delete":
            dc = CommandDelete.parseCommand(inputArr);
            break;
        case "find":
            dc = CommandFind.parseCommand(inputArr);
            break;
        case "addTag":
            dc = CommandAddTag.parseCommand(inputArr);
            break;
        case "deleteTag":
            dc = CommandDeleteTag.parseCommand(inputArr);
            break;
        case "listTag":
            dc = CommandListTag.parseCommand(inputArr);
            break;
        default:
            throw new DukeCommandException(inputArr[0]);
        }
        return dc;
    }
}
