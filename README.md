# Duke v0.9.0
> *"Be not afraid of growing slowly, be afraid of standing still."* - Chinese proverb

Duke offers you a:
- Hassle free
- Text based
- Minimalist
and completely **offline** experience of planning your tasks!

## Duke's features
- [x] Adding and saving tasks
- [ ] GUI (coming soon!)
- [ ] Mystery features 😜

## Setting up Duke
1. Download it [here](https://github.com/WangGLJoseph/ip/releases/download/A-Jar/ip.jar)
2. Double-click to run it
3. Add your tasks
4. Enjoy **mastery** over your tasks 😆

A snippet of the workings of controlling your tasks. Behold, you've got the power!
   ```java
    public String finishTask(int index) throws DukeException {
        if (index > taskArrayList.size()) {
            throw new DukeException("This task index is not in the task list!");
        }
        taskArrayList.get(index - 1).markAsDone();
        return (sandwich("Congratulations! You have finished this task: "
                + taskArrayList.get(index - 1).toString()));
    }
   ```
