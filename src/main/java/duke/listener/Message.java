package duke.listener;

import duke.constant.MessageType;

/**
 * This is the Message interface.
 */
public interface Message {

    /**
     * Shows messages.
     *
     * @param messageType MessageType object.
     * @param messages Multiple message strings or messages array.
     */
    public void show(MessageType messageType, String... messages);
}
