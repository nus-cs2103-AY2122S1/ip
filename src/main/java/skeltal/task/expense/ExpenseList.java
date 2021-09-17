package skeltal.task.expense;

import java.util.ArrayList;

import skeltal.SkeltalException;
import skeltal.Storage;

public class ExpenseList {
    private static ArrayList<Expense> expenses = new ArrayList<>();

    /**
     * Adds expense to the expenseList.
     *
     * @param money The expense to be added.
     * @return A reply from Skeltal for adding the expense.
     * @throws SkeltalException
     */
    public static String addExpense(Expense money) throws SkeltalException {
        expenses.add(money);
        String reply = "Got it. I've added this expense\n"
                + money + "\n"
                + "Now you have " + expenses.size() + " expenses in the list.";
        Storage.write(Storage.EXPENSE_PATH, "expense");
        return reply;
    }

    public static String listExpenses() {
        String reply = "Here are the expenses in your list:\n";
        for (Expense e : expenses) {
            reply += e + "\n";
        }
        return reply.trim();
    }

    public static String deleteExpense(String index) throws SkeltalException {
        String reply = "";
        int i = Integer.parseInt(index) - 1;
        if (outOfRange(i)) {
            throw new SkeltalException("Choose a positive number that is within the range of the list!");
        }
        reply += expenses.get(i) + "\n";
        expenses.remove(i);
        reply += "Now you have " + expenses.size() + " expenses in the list.";
        Storage.write(Storage.EXPENSE_PATH, "expense");
        return reply;
    }

    public static int getIndex(Expense expense) {
        return expenses.indexOf(expense);
    }

    public static String sum() {
        int total = 0;
        for (Expense e : expenses) {
            total += e.getAmount();
        }
        return "Your total spending is: $" + total;
    }

    public static String expensesToFileFormat() {
        String toWrite = "";
        for (Expense expense : expenses) {
            toWrite += expense.store() + "\n";
        }
        return toWrite;
    }

    public static void loadExpenseList(ArrayList<Expense> expenseArrayList) {
        expenses = expenseArrayList;
    }

    private static boolean outOfRange(int i) {
        if (expenses.size() <= 0 || expenses.size() < i) {
            return true;
        } else {
            return false;
        }
    }
}
