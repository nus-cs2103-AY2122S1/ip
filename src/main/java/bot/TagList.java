package bot;

import java.util.ArrayList;

/**
 * A class that encapsulates the list of tags that belong to a task.
 */
public class TagList {

    private ArrayList<Tag> tags;

    /**
     * Constructor for the TagList class.
     */
    public TagList() {
        this.tags = new ArrayList<>();
    }

    /**
     * Adds the given Tag to the end of the TagList.
     *
     * @param tag The new Tag object to be added to TagList.
     */
    public void addTag(Tag tag) {
        this.tags.add(tag);
    }

    /**
     * Returns an integer corresponding to the size of the TagList, i.e. the number of tags in the TagList.
     *
     * @return The size of the TagList.
     */
    public int getSize() {
        return this.tags.size();
    }

    /**
     * Returns a string representation of the TagList object.
     *
     * @return A String representation of all the tags in the TagList.
     */
    @Override
    public String toString() {
        String result = " ";
        for (int i = 0; i < getSize(); i++) {
            result += this.tags.get(i).toString() + " ";
        }
        return result;
    }
}
