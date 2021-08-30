package ui;

import java.util.List;

/**
 * Default implementation of UI which prints items using the following format:
 * ------------------
 * something
 * ------------------
 */
public class UiImpl implements IUi {
    /**
     * {@inheritDoc}
     * Displays a list for the CLI using --- + ordered list + ---- format.
     *
     * @param list List.
     * @param <T> Parameter, implicitly inferred.
     */
    @Override
    public <T> void printIndexedList(List<T> list) {
        int numbering = 1;
        StringBuilder string = new StringBuilder();
        
        for (T item : list) {
            string.append(" ")
                    .append(numbering)
                    .append(". ")
                    .append(item.toString())
                    .append("\n");
            
            numbering++;
        }
        // remove the last extra \n if there is at least an item in the list
        if (list.size() > 0) {
            string.deleteCharAt(string.length() - 1);
        }
        
        printSentence(string.toString());
    }
    
    /**
     * {@inheritDoc}
     * Displays the sentence for the CLI using ---- + sentence + ----- format.
     *
     * @param sentence String to be printed.
     */
    @Override
    public void printSentence(String sentence) {
        System.out.println(("\t____________________________________________________________\n"
                + "\t" + sentence.replace("\n", "\n\t")
                + "\n\t____________________________________________________________\n"));
    }
}
