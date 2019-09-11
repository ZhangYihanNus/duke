public class Event extends Task {

    protected String at;
    protected String timeName;
    protected boolean timeValid = false;

    public Event(String description, String at) throws DukeCmdException, DukeFormatException {
        super(description);
        this.at = at;
        if(at.split(" ").length==2) {
            DateTime thisDateTime = null;
            try {
                thisDateTime = new DateTime(at.split(" ")[0], Integer.parseInt(at.split(" ")[1]));
                this.timeName = thisDateTime.printDateTime();
                this.timeValid = true;
            }
            catch (Exception e) {
                System.out.println("____________________________________________________________\n" +
                        "\t"+"Not a valid date and time, but task recorded.\n" +
                        "____________________________________________________________\n");
            }
        }
    }

    @Override
    public String toString() {
        if(this.timeValid) {
            return "[E][" + super.getStatusIcon() + "] " + super.getDescription().split("event ")[1] + " (at: " + this.timeName + ")";
        } else {
            return "[E][" + super.getStatusIcon() + "] " + super.getDescription().split("event ")[1] + " (at: " + at + ")";
        }
    }

    @Override
    public String getDescription() {
        return super.getDescription();
    }
}