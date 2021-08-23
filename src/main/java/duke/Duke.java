package duke;

import duke.ui.Ui;

/**
 * Duke class used to run the duke.Duke chat-bot.
 * Contains methods that
 * (i)    runs the chat-bot
 * (ii)   exits the chat-bot
 * (iii)  lists all tasks
 * (iv)   marks a given task as done
 * (v)    deletes a given task
 * (vi)   inputs a deadline task
 * (vii)  inputs an event task
 * (viii) inputs a todo task
 * (ix)   check user input for keywords
 * (x)    display separator line
 * (xi)   displays total number of tasks
 * (xii)  counts number of spaces in user input
 * (xiii) provide a user guide
 */
public class Duke {

    public static void main(String[] args) {
        Ui chatBot = new Ui();
        chatBot.run();
    }
}
