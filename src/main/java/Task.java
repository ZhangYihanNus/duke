public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) throws DukeCmdException, DukeFormatException {
        String tempCmd = description.split(" ")[0];
        if (description.equals("")) {
            throw new DukeFormatException("Oops, there seems to be some format error!");
        }
        else if (!((tempCmd.equals("bye"))||(tempCmd.equals("deadline"))||(tempCmd.equals("event"))||(tempCmd.equals("todo"))||(tempCmd.equals("list")))) {
            //still need to identify whether unknown in here
            throw new DukeCmdException("Oops, command not found!");
        } else {
            this.description = description;
            this.isDone = false;
        }
    }

    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    public void markAsDone() {
        isDone = true;
    }

    public String getDescription() {
        return description;
    }
}
