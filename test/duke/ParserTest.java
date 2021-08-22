package duke;

import duke.command.AddCommand;
import duke.command.DateCommand;
import duke.command.DeleteCommand;
import duke.command.ListCommand;
import duke.command.MarkCommand;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ParserTest {

    private static final LocalDate TIME = LocalDate.parse("2020-01-01");

    @Test
    void parse_listInput_listCommand() {
        ListCommand listCommand = new ListCommand("list");

        assertEquals(listCommand, Parser.parse("list"));
    }

    @Test
    void parse_listExtra_AddCommand() {
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
        String validInput = "done 1";
        String validInputCommand = Ui.Commands.DONE.getCommand();
        String validInputExceptionMessage = Ui.exceptionMissingIndexForMarking();

        String validInput1 = "todo task";
        String validInput1Command = Ui.Commands.TODO.getCommand();
        String validInput1ExceptionMessage = Ui.exceptionMissingIndexForDelete();

        String validInput2 = "event task /at 1/1/2020";
        String validInput2Command = Ui.Commands.EVENT.getCommand();
        String validInput2ExceptionMessage = Ui.exceptionMissingTaskDescription(validInput2Command);

        Parser.checkInputValidity(validInput, validInputCommand, validInputExceptionMessage);
        Parser.checkInputValidity(validInput1, validInput1Command, validInput1ExceptionMessage);
        Parser.checkInputValidity(validInput2, validInput2Command, validInput2ExceptionMessage);
    }

    @Test
    void checkInputValidity_invalidInputs_dukeExceptionThrown() {
        String invalidInput = "done1";
        String invalidInputCommand = Ui.Commands.DONE.getCommand();
        String invalidInputExceptionMessage = Ui.exceptionMissingSpaceAfterCommand(invalidInputCommand);

        String invalidInput1 = "delete ";
        String invalidInput1Command = Ui.Commands.DELETE.getCommand();
        String invalidInput1ExceptionMessage = Ui.exceptionMissingIndexForDelete();

        String invalidInput2 = "done";
        String invalidInput2Command = Ui.Commands.DONE.getCommand();
        String invalidInput2ExceptionMessage = Ui.exceptionMissingIndexForMarking();

        DukeException dukeException = assertThrows(DukeException.class,
                () -> Parser.checkInputValidity(invalidInput, invalidInputCommand, invalidInputExceptionMessage));

        assertEquals(invalidInputExceptionMessage, dukeException.getErrorMessage());

        DukeException dukeException1 = assertThrows(DukeException.class,
                () -> Parser.checkInputValidity(invalidInput1, invalidInput1Command, invalidInput1ExceptionMessage));

        assertEquals(invalidInput1ExceptionMessage, dukeException1.getErrorMessage());

        DukeException dukeException2 = assertThrows(DukeException.class,
                () -> Parser.checkInputValidity(invalidInput2, invalidInput2Command, invalidInput2ExceptionMessage));

        assertEquals(invalidInput2ExceptionMessage, dukeException2.getErrorMessage());
    }

    @Test
    void parseLocalDate_localDate_dateString() throws DukeException {
        assertEquals("1 January 2020", Parser.parseLocalDate(TIME));
    }

    @Test
    void toLocalDate_validDateString_localDate() throws DukeException {
        String dateString = "1/1/2020";
        assertEquals(TIME, Parser.toLocalDate(dateString));
    }

    @Test
    void toLocalDate_invalidDateStrings_dukeExceptionThrown() {
        String invalidDateString = "100/100/100";
        String invalidDateString1 = "100/100";

        DukeException dukeException = assertThrows(DukeException.class,
                () -> Parser.toLocalDate(invalidDateString));

        assertEquals(Ui.exceptionInvalidDateTimeFormat(), dukeException.getErrorMessage());

        DukeException dukeException1 = assertThrows(DukeException.class,
                () -> Parser.toLocalDate(invalidDateString1));

        assertEquals(Ui.exceptionInvalidDateTimeFormat(), dukeException1.getErrorMessage());
    }

    @Test
    void parseUserNumInput_validNumInput_int() throws DukeException {
        String validNumInput = "done 1";
        Ui.Commands validNumInputCommand = Ui.Commands.DONE;

        assertEquals(1, Parser.parseUserNumInput(validNumInput, validNumInputCommand));
    }

    @Test
    void parseUserNumInput_invalidNumInput_dukeExceptionThrown() {
        String invalidNumInput = "done abc";
        Ui.Commands invalidNumInputCommand = Ui.Commands.DONE;
        String invalidNumInputExceptionMessage = Ui.exceptionInvalidNumberInput(invalidNumInputCommand);

        DukeException dukeException = assertThrows(DukeException.class,
                () -> Parser.parseUserNumInput(invalidNumInput, invalidNumInputCommand));

        assertEquals(invalidNumInputExceptionMessage, dukeException.getErrorMessage());
    }

    @Test
    void parseUserDescriptionInput_validDescriptionInput_arrayDescriptionTime() throws DukeException {
        Ui.Commands command = Ui.Commands.EVENT;
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
        Ui.Commands command = Ui.Commands.EVENT;
        Ui.Descriptors descriptor = Ui.Descriptors.AT;
        char separator = '/';

        // No task description.
        String invalidDescriptionInput = "/at 1/1/2020";
        String exceptionMessage = Ui.exceptionMissingTaskDescription(command.getCommand());
        // No separator.
        String invalidDescriptionInput1 = "at 1/1/2020";
        String exceptionMessage1 = Ui.exceptionMissingDescriptor(descriptor, command);
        // No space before descriptor.
        String invalidDescriptionInput2 = "task description/at 1/1/2020";
        String exceptionMessage2 = Ui.exceptionMissingSpaceBeforeDescriptor(descriptor);
        // No space after descriptor.
        String invalidDescriptionInput3 = "task description /at1/1/2020";
        String exceptionMessage3 = Ui.exceptionMissingSpaceAfterDescriptor(descriptor);
        // Wrong descriptor used.
        String invalidDescriptionInput4 = "task description /by 1/1/2020";
        String exceptionMessage4 = Ui.exceptionWrongDescriptor(command, descriptor);
        // No space after descriptor
        String invalidDescriptionInput5 = "task description /at";
        String exceptionMessage5 = Ui.exceptionMissingDescriptor(descriptor, command);

        DukeException dukeException = assertThrows(DukeException.class,
                () -> Parser.parseUserDescriptionInput(invalidDescriptionInput, descriptor, separator, command));

        assertEquals(exceptionMessage, dukeException.getErrorMessage());

        DukeException dukeException1 = assertThrows(DukeException.class,
                () -> Parser.parseUserDescriptionInput(invalidDescriptionInput1, descriptor, separator, command));

        assertEquals(exceptionMessage1, dukeException1.getErrorMessage());

        DukeException dukeException2 = assertThrows(DukeException.class,
                () -> Parser.parseUserDescriptionInput(invalidDescriptionInput2, descriptor, separator, command));

        assertEquals(exceptionMessage2, dukeException2.getErrorMessage());

        DukeException dukeException3 = assertThrows(DukeException.class,
                () -> Parser.parseUserDescriptionInput(invalidDescriptionInput3, descriptor, separator, command));

        assertEquals(exceptionMessage3, dukeException3.getErrorMessage());

        DukeException dukeException4 = assertThrows(DukeException.class,
                () -> Parser.parseUserDescriptionInput(invalidDescriptionInput4, descriptor, separator, command));

        assertEquals(exceptionMessage4, dukeException4.getErrorMessage());

        DukeException dukeException5 = assertThrows(DukeException.class,
                () -> Parser.parseUserDescriptionInput(invalidDescriptionInput5, descriptor, separator, command));

        assertEquals(exceptionMessage5, dukeException5.getErrorMessage());
    }
}