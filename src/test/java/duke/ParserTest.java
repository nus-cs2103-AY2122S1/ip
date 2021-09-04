package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import duke.Ui.UserCommands;
import duke.command.AddCommand;
import duke.command.DateCommand;
import duke.command.DeleteCommand;
import duke.command.ListCommand;
import duke.command.MarkCommand;
import duke.exception.DukeException;
import duke.exception.InvalidDateTimeFormatException;
import duke.exception.InvalidNumberInputException;
import duke.exception.MissingDateException;
import duke.exception.MissingDescriptorException;
import duke.exception.MissingIndexException;
import duke.exception.MissingSpaceAfterCommandException;
import duke.exception.MissingSpaceAfterDescriptorException;
import duke.exception.MissingSpaceBeforeDescriptorException;
import duke.exception.MissingTaskDescriptionException;
import duke.exception.WrongDescriptorException;

class ParserTest {

    private static final LocalDate TIME = LocalDate.parse("2020-01-01");

    @Test
    void parse_listInput_listCommand() {
        ListCommand listCommand = new ListCommand("list");

        assertEquals(listCommand, Parser.parse("list"));
    }

    @Test
    void parse_listExtra_addCommand() {
        AddCommand addCommand = new AddCommand("listExtra");

        assertEquals(addCommand, Parser.parse("listExtra"));
    }

    @Test
    void parse_doneInput_markCommand() {
        MarkCommand markCommand = new MarkCommand("done 1");

        assertEquals(markCommand, Parser.parse("done 1"));
    }

    @Test
    void parse_deleteInput_deleteCommand() {
        DeleteCommand deleteCommand = new DeleteCommand("delete 1");

        assertEquals(deleteCommand, Parser.parse("delete 1"));
    }

    @Test
    void parse_addInput_addCommand() {
        AddCommand addCommand = new AddCommand("todo task");

        assertEquals(addCommand, Parser.parse("todo task"));
    }

    @Test
    void parse_dateInput_dateCommand() {
        DateCommand dateCommand = new DateCommand("date 1/1/2020");

        assertEquals(dateCommand, Parser.parse("date 1/1/2020"));
    }

    @Test
    void checkInputValidity_validInputs_noExceptionThrown() throws DukeException {
        DukeException dummyException = new DukeException("dummy");

        String validInput = "done 1";
        UserCommands validInputCommand = UserCommands.DONE;

        String validInput1 = "todo task";
        UserCommands validInput1Command = UserCommands.TODO;

        String validInput2 = "event task /at 1/1/2020";
        UserCommands validInput2Command = UserCommands.EVENT;

        Parser.checkInputValidity(validInput, validInputCommand, dummyException);
        Parser.checkInputValidity(validInput1, validInput1Command, dummyException);
        Parser.checkInputValidity(validInput2, validInput2Command, dummyException);
    }

    @Test
    void checkInputValidity_invalidInputs_dukeExceptionThrown() {
        String invalidInput = "done1";
        UserCommands invalidInputCommand = UserCommands.DONE;
        MissingSpaceAfterCommandException invalidInputException =
                new MissingSpaceAfterCommandException(UserCommands.DONE);

        String invalidInput1 = "delete ";
        UserCommands invalidInput1Command = UserCommands.DELETE;
        MissingIndexException invalidInput1Exception = new MissingIndexException(UserCommands.DELETE);

        String invalidInput2 = "done";
        UserCommands invalidInput2Command = UserCommands.DONE;
        MissingIndexException invalidInput2Exception = new MissingIndexException(UserCommands.DONE);

        DukeException dukeException = assertThrows(DukeException.class, () -> {
            Parser.checkInputValidity(invalidInput, invalidInputCommand, invalidInputException);
        });

        assertEquals(invalidInputException.getErrorMessage(), dukeException.getErrorMessage());

        DukeException dukeException1 = assertThrows(MissingIndexException.class, () -> {
            Parser.checkInputValidity(invalidInput1, invalidInput1Command, invalidInput1Exception);
        });

        assertEquals(invalidInput1Exception.getErrorMessage(), dukeException1.getErrorMessage());

        DukeException dukeException2 = assertThrows(DukeException.class, () -> {
            Parser.checkInputValidity(invalidInput2, invalidInput2Command, invalidInput2Exception);
        });

        assertEquals(invalidInput2Exception.getErrorMessage(), dukeException2.getErrorMessage());
    }

    @Test
    void parseLocalDate_localDate_dateString() throws DukeException {
        assertEquals("1 January 2020", Parser.parseLocalDate(TIME));
    }

    @Test
    void toLocalDate_validDateString_localDate() throws DukeException {
        String dateString = "1/1/2020";
        assertEquals(TIME, Parser.dateToLocalDate(dateString));
    }

    @Test
    void toLocalDate_invalidDateStrings_dukeExceptionThrown() {
        String invalidDateString = "100/100/100";
        String invalidDateString1 = "100/100";

        assertThrows(InvalidDateTimeFormatException.class, () -> {
            Parser.dateToLocalDate(invalidDateString);
        });

        assertThrows(InvalidDateTimeFormatException.class, () -> {
            Parser.dateToLocalDate(invalidDateString1);
        });
    }

    @Test
    void parseUserNumInput_validNumInput_int() throws DukeException {
        String validNumInput = "done 1";
        UserCommands validNumInputCommand = UserCommands.DONE;

        assertEquals(1, Parser.parseUserNumInput(validNumInput, validNumInputCommand));
    }

    @Test
    void parseUserNumInput_invalidNumInput_dukeExceptionThrown() {
        String invalidNumInput = "done abc";
        UserCommands invalidNumInputCommand = UserCommands.DONE;
        InvalidNumberInputException invalidNumInputException =
                new InvalidNumberInputException(invalidNumInputCommand);

        InvalidNumberInputException exception = assertThrows(InvalidNumberInputException.class, () -> {
            Parser.parseUserNumInput(invalidNumInput, invalidNumInputCommand);
        });

        assertEquals(invalidNumInputException.getErrorMessage(), exception.getErrorMessage());
    }

    @Test
    void parseUserDescriptionInput_validDescriptionInput_arrayDescriptionTime() throws DukeException {
        UserCommands command = UserCommands.EVENT;
        Ui.Descriptors descriptor = Ui.Descriptors.AT;
        char separator = '/';

        String validDescriptionInput = "task description /at 1/1/2020";

        String[] expected = {"task description", "1/1/2020"};

        String[] actual = Parser.parseUserDescriptionInput(validDescriptionInput, descriptor, separator, command);

        assertEquals(expected.length, actual.length);
        assertEquals(expected[0], actual[0]);
        assertEquals(expected[1], actual[1]);
    }

    @Test
    void parserUserDescriptionInput_invalidDescriptionInput_dukeExceptionThrown() {
        UserCommands command = UserCommands.EVENT;
        Ui.Descriptors descriptor = Ui.Descriptors.AT;
        char separator = '/';

        // No task description.
        String invalidDescriptionInput = "/at 1/1/2020";
        MissingTaskDescriptionException exception = new MissingTaskDescriptionException(command);
        // No separator.
        String invalidDescriptionInput1 = "at 1/1/2020";
        MissingDescriptorException exception1 = new MissingDescriptorException(descriptor, command);
        // No space before descriptor.
        String invalidDescriptionInput2 = "task description/at 1/1/2020";
        MissingSpaceBeforeDescriptorException exception2 = new MissingSpaceBeforeDescriptorException(descriptor);
        // No space after descriptor.
        String invalidDescriptionInput3 = "task description /at1/1/2020";
        MissingSpaceAfterDescriptorException exception3 = new MissingSpaceAfterDescriptorException(descriptor);
        // Wrong descriptor used.
        String invalidDescriptionInput4 = "task description /by 1/1/2020";
        WrongDescriptorException exception4 = new WrongDescriptorException(descriptor, command);

        String invalidDescriptionInput5 = "task description /at";

        DukeException dukeException = assertThrows(MissingTaskDescriptionException.class, () -> {
            Parser.parseUserDescriptionInput(invalidDescriptionInput, descriptor, separator, command);
        });

        assertEquals(exception.getErrorMessage(), dukeException.getErrorMessage());

        DukeException dukeException1 = assertThrows(MissingDescriptorException.class, () -> {
            Parser.parseUserDescriptionInput(invalidDescriptionInput1, descriptor, separator, command);
        });

        assertEquals(exception1.getErrorMessage(), dukeException1.getErrorMessage());

        DukeException dukeException2 = assertThrows(MissingSpaceBeforeDescriptorException.class, () -> {
            Parser.parseUserDescriptionInput(invalidDescriptionInput2, descriptor, separator, command);
        });

        assertEquals(exception2.getErrorMessage(), dukeException2.getErrorMessage());

        DukeException dukeException3 = assertThrows(MissingSpaceAfterDescriptorException.class, () -> {
            Parser.parseUserDescriptionInput(invalidDescriptionInput3, descriptor, separator, command);
        });

        assertEquals(exception3.getErrorMessage(), dukeException3.getErrorMessage());

        DukeException dukeException4 = assertThrows(WrongDescriptorException.class, () -> {
            Parser.parseUserDescriptionInput(invalidDescriptionInput4, descriptor, separator, command);
        });

        assertEquals(exception4.getErrorMessage(), dukeException4.getErrorMessage());

        assertThrows(MissingDateException.class, () -> {
            Parser.parseUserDescriptionInput(invalidDescriptionInput5, descriptor, separator, command);
        });
    }
}
