//package poseidon.parser;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertFalse;
//import static org.junit.jupiter.api.Assertions.assertTrue;
//import static org.junit.jupiter.api.Assertions.fail;
//
//import java.util.Arrays;
//
//import org.junit.jupiter.api.Test;
//
//import poseidon.exception.PoseidonException;
//
///**
// * Represents a testing class for {@code Parser}.
// *
// * @author Yeluri Ketan
// * @version CS2103T AY21/22 Sem 1 iP
// */
//public class ParserTest {
//
//    @Test
//    public void parse_byeCommand_correctStringArray() {
//        String byeCommand1 = "bye";
//        String byeCommand2 = "BYE";
//        String byeCommand3 = "Bye      ";
//
//        String[] byeCommandArr = new String[]{"bye"};
//
//        assertEquals(Arrays.toString(byeCommandArr), Arrays.toString(Parser.parse(byeCommand1)));
//        assertEquals(Arrays.toString(byeCommandArr), Arrays.toString(Parser.parse(byeCommand2)));
//        assertEquals(Arrays.toString(byeCommandArr), Arrays.toString(Parser.parse(byeCommand3)));
//    }
//
//    @Test
//    public void parse_listCommand_correctStringArray() {
//        String listCommand1 = "list";
//        String listCommand2 = "LIST";
//        String listCommand3 = "LisT      ";
//
//        String[] listCommandArr = new String[]{"list"};
//
//        assertEquals(Arrays.toString(listCommandArr), Arrays.toString(Parser.parse(listCommand1)));
//        assertEquals(Arrays.toString(listCommandArr), Arrays.toString(Parser.parse(listCommand2)));
//        assertEquals(Arrays.toString(listCommandArr), Arrays.toString(Parser.parse(listCommand3)));
//    }
//
//    @Test
//    public void parse_doneCommand_correctStringArray() {
//        String doneCommand1 = "done 1";
//        String doneCommand2 = "DONE       1";
//        String doneCommand3 = "DoNe      1       ";
//
//        String[] doneCommandArr = new String[]{"done", "1"};
//
//        assertEquals(Arrays.toString(doneCommandArr), Arrays.toString(Parser.parse(doneCommand1)));
//        assertEquals(Arrays.toString(doneCommandArr), Arrays.toString(Parser.parse(doneCommand2)));
//        assertEquals(Arrays.toString(doneCommandArr), Arrays.toString(Parser.parse(doneCommand3)));
//    }
//
//    @Test
//    public void parse_deleteCommand_correctStringArray() {
//        String deleteCommand1 = "delete 1";
//        String deleteCommand2 = "DELeTE       1";
//        String deleteCommand3 = "Delete      1       ";
//
//        String[] deleteCommandArr = new String[]{"delete", "1"};
//
//        assertEquals(Arrays.toString(deleteCommandArr), Arrays.toString(Parser.parse(deleteCommand1)));
//        assertEquals(Arrays.toString(deleteCommandArr), Arrays.toString(Parser.parse(deleteCommand2)));
//        assertEquals(Arrays.toString(deleteCommandArr), Arrays.toString(Parser.parse(deleteCommand3)));
//    }
//
//    @Test
//    public void parse_todoCommand_correctStringArray() {
//        String todoCommand1 = "todo finish all week 3 iP tasks";
//        String todoCommand2 = "TODO   finish all week 3 iP tasks";
//        String todoCommand3 = "TodO      finish all week 3 iP tasks        ";
//
//        String[] todoCommandArray = new String[]{"add", "todo", "finish all week 3 iP tasks"};
//
//        assertEquals(Arrays.toString(todoCommandArray), Arrays.toString(Parser.parse(todoCommand1)));
//        assertEquals(Arrays.toString(todoCommandArray), Arrays.toString(Parser.parse(todoCommand2)));
//        assertEquals(Arrays.toString(todoCommandArray), Arrays.toString(Parser.parse(todoCommand3)));
//    }
//
//    @Test
//    public void parse_emptyTodoCommand_correctErrorMessage() {
//        String emptyTodoCommand = "todo   ";
//        String errorMsg = "";
//
//        try {
//            Parser.parse(emptyTodoCommand);
//            fail();
//        } catch (PoseidonException e) {
//            errorMsg = e.getMessage();
//        }
//
//        String expectedErrorMsg = "The description of a TODO task cannot be empty.\nPlease try again.";
//
//        assertEquals(expectedErrorMsg, errorMsg);
//    }
//
//    @Test
//    public void parse_deadlineCommand_correctStringArray() {
//        String deadlineCommand1 = "deadline finish all week 3 iP tasks /by 2021 09 17 2359";
//        String deadlineCommand2 = "DEADLINE   finish all week 3 iP tasks     /by 2021 09 17 2359";
//        String deadlineCommand3 = "DeadLine      finish all week 3 iP tasks        /by 2021 09 17 2359";
//
//        String[] deadlineCommandArray = new String[]{"add", "deadline",
//            "finish all week 3 iP tasks", "2021 09 17 2359"};
//
//        assertEquals(Arrays.toString(deadlineCommandArray), Arrays.toString(Parser.parse(deadlineCommand1)));
//        assertEquals(Arrays.toString(deadlineCommandArray), Arrays.toString(Parser.parse(deadlineCommand2)));
//        assertEquals(Arrays.toString(deadlineCommandArray), Arrays.toString(Parser.parse(deadlineCommand3)));
//    }
//
//    @Test
//    public void parse_invalidDeadlineCommand_correctErrorMsg() {
//        String invalidDeadlineCommand = "deadline finish all week 3 iP tasks";
//        String errorMsg = "";
//
//        try {
//            Parser.parse(invalidDeadlineCommand);
//            fail();
//        } catch (PoseidonException e) {
//            errorMsg = e.getMessage();
//        }
//
//        String expectedErrorMsg = "There appears to be a typo in your DEADLINE command.\n"
//                + "The command should be of the form:\n"
//                + "  deadline 'description' /by 'yyyy mm dd hhmm'\n"
//                + "Please try again.";
//
//        assertEquals(expectedErrorMsg, errorMsg);
//    }
//
//    @Test
//    public void parse_eventCommand_correctStringArray() {
//        String eventCommand1 = "event week 3 team meeting /from 2021 09 17 2000 to 2021 09 17 2200";
//        String eventCommand2 = "EVENT   week 3 team meeting     /from 2021 09 17 2000 to 2021 09 17 2200";
//        String eventCommand3 = "EvenT      week 3 team meeting        /from 2021 09 17 2000 to 2021 09 17 2200";
//
//        String[] eventCommandArray = new String[]{"add", "event",
//            "week 3 team meeting", "2021 09 17 2000", "2021 09 17 2200"};
//
//        assertEquals(Arrays.toString(eventCommandArray), Arrays.toString(Parser.parse(eventCommand1)));
//        assertEquals(Arrays.toString(eventCommandArray), Arrays.toString(Parser.parse(eventCommand2)));
//        assertEquals(Arrays.toString(eventCommandArray), Arrays.toString(Parser.parse(eventCommand3)));
//    }
//
//    @Test
//    public void parse_findCommand_correctStringArray() {
//        String findCommand1 = "find team";
//        String findCommand2 = "FIND    team";
//        String findCommand3 = "FInd        team          ";
//
//        String[] findCommandArray = new String[]{"find", "team"};
//
//        assertEquals(Arrays.toString(findCommandArray), Arrays.toString(Parser.parse(findCommand1)));
//        assertEquals(Arrays.toString(findCommandArray), Arrays.toString(Parser.parse(findCommand2)));
//        assertEquals(Arrays.toString(findCommandArray), Arrays.toString(Parser.parse(findCommand3)));
//    }
//
//    @Test
//    public void parse_sortCommand_correctStringArray() {
//        String sortCommand1 = "list -s";
//        String sortCommand2 = "LIST -S    ";
//        String sortCommand3 = "LisT     -S      ";
//
//        String[] sortCommandArray = new String[]{"sort"};
//
//        assertEquals(Arrays.toString(sortCommandArray), Arrays.toString(Parser.parse(sortCommand1)));
//        assertEquals(Arrays.toString(sortCommandArray), Arrays.toString(Parser.parse(sortCommand2)));
//        assertEquals(Arrays.toString(sortCommandArray), Arrays.toString(Parser.parse(sortCommand3)));
//    }
//
//    @Test
//    public void parse_nonExistentCommand_correctStringArray() {
//        String failCommand1 = "hello";
//        String failCommand2 = "     sdadsdsdas    ";
//        String failCommand3 = "asadsadasd                  sdasdsad     adsdsda";
//
//        String[] failCommandArray = new String[]{"fail"};
//
//        assertEquals(Arrays.toString(failCommandArray), Arrays.toString(Parser.parse(failCommand1)));
//        assertEquals(Arrays.toString(failCommandArray), Arrays.toString(Parser.parse(failCommand2)));
//        assertEquals(Arrays.toString(failCommandArray), Arrays.toString(Parser.parse(failCommand3)));
//    }
//
//    @Test
//    public void parseIndex_intString_integer() {
//        assertEquals(1, Parser.parseIndex("1"));
//        assertEquals(23, Parser.parseIndex("23"));
//        assertEquals(-10, Parser.parseIndex("-10"));
//    }
//
//    @Test
//    public void isParsedBye_byeCommand() {
//        String byeCommand1 = "bye";
//        String byeCommand2 = "BYE";
//        String byeCommand3 = "Bye      ";
//        String byeCommand4 = "Not a bye";
//
//        assertTrue(Parser.isParsedBye(byeCommand1));
//        assertTrue(Parser.isParsedBye(byeCommand2));
//        assertTrue(Parser.isParsedBye(byeCommand3));
//        assertFalse(Parser.isParsedBye(byeCommand4));
//    }
//
//    @Test
//    public void parseDateTime_dateTimeString_correctDateTimeObject() {
//        String dateTimeString = "2021 09 17 2359";
//        String expectedDateTimeObjectToString = "2021-09-17T23:59";
//
//        assertEquals(expectedDateTimeObjectToString, Parser.parseDateTime(dateTimeString).toString());
//    }
//}
