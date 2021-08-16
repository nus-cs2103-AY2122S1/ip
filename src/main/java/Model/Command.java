package Model;

/**
 * list of commands available for dude chatting bot
 * <ol>
 *
 *  <li>
 *      BYE : to end the chat, no arguments requires, any extra arguments would be ignored, invoked using "bye"
 *  </li>
 *  <li>
 *      LIST : list the entire tasks array
 *  </li>
 *  <li>
 *      ADD : add the whole sentence to the storage
 *  </li>
 *  <li>
 *      DONE : mark the task as done, 1 argument is required which is the number of task (the rest is ignored)
 *  </li>
 *  <li>
 *      INVALID : any other commands other than bye, the whole sentence would be echoed back instead
 *  </li>
 * </ol>
 */
public enum Command {
	BYE,
	LIST,
	ADD,
	DONE,
	INVALID // special command for invalid input
}
