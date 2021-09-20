package tests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import sariel.util.commands.DelCommand;
import sariel.util.parser.Parser;
import sariel.util.tasks.DateTaskTable;
import sariel.util.tasks.DatedTask;
import sariel.util.tasks.Deadline;
import sariel.util.tasks.DukeException;
import sariel.util.tasks.TaskList;
import sariel.util.ui.Ui;


/**
 * Test class for Sariel.
 *
 */
public class SarielTester {

    /**
     * Function to test the dated task table.
     *
     */
    @Test
    public void testDatedTaskTable() {
        try {
            DateTaskTable t = new DateTaskTable();
            DatedTask[] arr = new DatedTask[3];
            arr[0] = Deadline.of("Working by life /by 2020-08-20");
            arr[1] = Deadline.of("Sleeping in life /by 2020-08-20");
            arr[2] = Deadline.of("kdsfmekfsdf /by 2020-08-20");


            t.add(arr[0]);
            t.add(arr[1]);
            t.add(arr[2]);


            ArrayList<DatedTask> ls = t.get(Parser.dateParse("2020-08-20"));
            assertEquals(3, ls.size());
            for (int i = 0; i < ls.size(); i++) {
                assertEquals(arr[i], t.get(Parser.dateParse("2020-08-20")).get(i));
            }
        } catch (DukeException e) {
            System.err.println(e.getMessage());

        }
    }


    /**
     * Method to test the delete command.
     *
     */
    @Test
    public void testDeleteCommand() {
        try {
            DateTaskTable t = new DateTaskTable();
            TaskList tasklist = new TaskList();
            t.add(Deadline.of("Driving a car /by 2020-08-20"));
            t.add(Deadline.of("Driving a traing/by2020-08-20"));
            t.add(Deadline.of("Building a car/by 2020-08-20"));
            tasklist.add(Deadline.of("Driving a car /by 2020-08-20"));
            tasklist.add(Deadline.of("Driving a traing/by2020-08-20"));
            tasklist.add(Deadline.of("Building a car/by 2020-08-20"));
            DelCommand removeDrCar = new DelCommand(1, tasklist, t);
            removeDrCar.execute();
            assertEquals(Deadline.of("Driving a traing/by2020-08-20"), tasklist.get(0));
            assertEquals(Deadline.of("Driving a traing/by2020-08-20"), t.get(Parser.dateParse("2020-08-20")).get(0));
        } catch (DukeException e) {
            System.err.println(e.getMessage());
        }
    }

    /**
     * A method to test the parser.
     *
     */
    @Test
    public void testParser() {
        DateTaskTable t = new DateTaskTable();
        TaskList tasklist = new TaskList();
        Parser p = new Parser(new Ui(), tasklist, t);
        try {
            p.inputsParser("deadline fly a car /by 2020-08-01").executeAll();
            p.inputsParser("todo Build the empire states building").executeAll();
            assertEquals(2, tasklist.size());
            assertEquals(t.get(Parser.dateParse("2020-08-01")).get(0), tasklist.get(0));



        } catch (DukeException e) {
            System.err.println(e.getMessage());
        }


    }



}
