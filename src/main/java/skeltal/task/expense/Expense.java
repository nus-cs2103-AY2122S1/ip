package skeltal.task.expense;

import skeltal.Parser;
import skeltal.SkeltalException;
import skeltal.task.Task;

/**
 * The class expense which is a subclass of Task and has a integer attribute.
 */
public class Expense extends Task {
    private int amount;

    private Expense(String description) {
        super(description);
    }

    /**
     * A factory method to initialise a Expense object.
     *
     * @param description The description
     * @return The expense object.
     * @throws SkeltalException
     */
    public static Expense of(String description) throws SkeltalException {

        String[] moneyArr = Parser.parseExpense(description);
        String entryName = moneyArr[0];
        int amount = parseAmount(moneyArr);

        Expense expense = new Expense(entryName);
        expense.setAmount(amount);

        return expense;
    }

    /**
     * A method to check if the string is an integer.
     *
     * @param string The string.
     * @return A boolean. If the string is an integer, return true, else return false.
     */
    public static boolean isInteger(String string) {
        try {
            Integer.valueOf(string);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * Gets amount.
     *
     * @return the amount
     */
    public int getAmount() {
        return this.amount;
    }

    @Override
    public String store() {
        String formatted = this.amount + " | " + this.getTaskDescription();
        return formatted;
    }

    @Override
    public String toString() {
        int num = ExpenseList.getIndex(this) + 1;
        return "[" + num + "] $" + this.amount + " -> " + this.getTaskDescription();
    }

    private void setAmount(int amount) {
        this.amount = amount;
    }

    private static int parseAmount(String[] description) throws SkeltalException {
        if (description.length == 1 || !isInteger(description[1])) {
            throw new SkeltalException("Error! You must input a number!");
        }
        int amount = Integer.parseInt(description[1]);
        return amount;
    }

}
