public class Event extends Task {

    protected String at;

    public Event(String description, String at) throws DukeCmdException, DukeFormatException {
        super(description);
        this.at = at;
    }

    public String getType() {
        return "[E]";
    }

    @Override
    public String toString() {
        return "[E][" + super.getStatusIcon() + "] " + super.getDescription().split("event ")[1] + " (at: " + at + ")";
    }
}