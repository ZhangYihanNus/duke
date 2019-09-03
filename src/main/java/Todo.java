public class Todo extends Task {

    public Todo(String description) throws DukeCmdException, DukeFormatException {
        super(description);
    }

    public String getType() {
        return "[T]";
    }

    @Override
    public String toString() {
        return "[T][" + super.getStatusIcon() + "] " + super.getDescription().split("todo ")[1];
    }
}