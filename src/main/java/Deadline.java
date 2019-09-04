public class Deadline extends Task {

    protected String by;

    public Deadline(String description, String by) throws DukeCmdException, DukeFormatException {
        super(description);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D][" + super.getStatusIcon() + "] " + super.getDescription().split("deadline ")[1] + " (by: " + by + ")";
    }
}