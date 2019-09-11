public class Deadline extends Task {

    protected String by;
    protected String timeName;
    protected boolean timeValid = false;

    public Deadline(String description, String by) throws DukeCmdException, DukeFormatException {
        super(description);
        this.by = by;
        if(by.split(" ").length==2) {
            DateTime thisDateTime = null;
            try {
                thisDateTime = new DateTime(by.split(" ")[0], Integer.parseInt(by.split(" ")[1]));
                this.timeName = thisDateTime.printDateTime();
                this.timeValid = true;
            } catch (Exception e) {
                System.out.println("____________________________________________________________\n" +
                        "\t"+"Not a valid date and time, but task recorded.\n" +
                        "____________________________________________________________\n");
            }
        }
    }

    @Override
    public String toString() {
        if(this.timeValid) {
            return "[D][" + super.getStatusIcon() + "] " + super.getDescription().split("deadline ")[1] + " (by: " + this.timeName + ")";
        } else {
            return "[D][" + super.getStatusIcon() + "] " + super.getDescription().split("deadline ")[1] + " (by: " + by + ")";
        }
    }
}