package model;

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
 *     Adding a Task: divided into 3 smaller parts :
 *     <ul>
 *      <li>
 *       DEADLINE : add a new deadline, takes 3 arguments section: description, \by, deadline
 *      </li>
 *      <li>
 *     EVENT : add a new deadline, takes 3 arguments section: description, \at, deadline
 *      </li>
 *      <li>
 *        TODOS : add a new deadline, takes 1 argument : description
 *      </li>
 *    </ul>
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
	DEADLINE,
	EVENT,
	TODOS,
	DELETE,
	DONE,
}
