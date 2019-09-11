import java.util.*;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.layout.Region;
import javafx.scene.control.Label;

public class Duke extends Application{

    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput_st;
    private Button sendButton;
    private Scene scene;

    private static Scanner userInput = new Scanner(System.in);

    private Task[] taskList = new Task[100];
    private String[] taskFileList = new String[100];

    public Duke() throws DukeCmdException, DukeFormatException {
        ReadTxt fileOpt = new ReadTxt("C:\\Users\\Min\\Desktop\\o\\NUS\\1920-Year2-sem-1\\courses\\CS2113T\\duke\\localList.txt");
        fileOpt.readFile();
        this.taskFileList = fileOpt.getLines();
        String[] rawList = this.taskFileList;

        int i=0;
        while(rawList[i] != null) {
            String thisLine = rawList[i];
            String[] lineItems = thisLine.split(" \\| ");
            if(lineItems[0].equals("T")) {
                // this is a todo
                Todo thisTd = new Todo("todo "+lineItems[2]);
                if(lineItems[1].equals("1")) thisTd.markAsDone();
                this.taskList[i] = thisTd;
                i++;
            } else if (lineItems[0].equals("E")) {
                // this is an event
                Event thisEvt = new Event("event "+lineItems[2], lineItems[3]);
                if(lineItems[1].equals("1")) thisEvt.markAsDone();
                this.taskList[i] = thisEvt;
                i++;
            } else if (lineItems[0].equals("D")) {
                // this is a deadline
                Deadline thisDdl = new Deadline("deadline "+lineItems[2], lineItems[3]);
                if(lineItems[1].equals("1")) thisDdl.markAsDone();
                this.taskList[i] = thisDdl;
                i++;
            }
        }

        String startDuke = "____________________________________________________________\n" +
                "   Hello! I'm Duke\n" +
                "   What can I do for you?\n" +
                "____________________________________________________________";

        System.out.println(startDuke);
        
        //int i = 0;
        boolean listChanged;

         while(true) {
             listChanged = false;
             String fileInputLine="";
             String cmd = userInput.nextLine();
             if(cmd.equals("bye")) {
                 String bye = "____________________________________________________________\n" +
                         "\t"+"Bye! See you next time!\n" +
                         "____________________________________________________________\n";
                 System.out.println(bye);
                 break;
             } else if(cmd.equals("list")){
                 System.out.println("____________________________________________________________\n\tHere are the tasks in the list:\n");
                 for(int j=0; j<i; j++) {
                     String index = Integer.toString(j + 1);
                     String thisCmd = this.taskList[j].toString();
                     System.out.println("\t" + index + ". " + thisCmd + "\n");
                 }
                 System.out.println("____________________________________________________________\n");
             } else if(cmd.split(" ")[0].equals("done")) {
                 if(cmd.split(" ").length != 2) {
                     //format error
                     System.out.println("____________________________________________________________\n" +
                             "Oops, there seems to be some format error!\n" +
                             "please enter again! :)\n" +
                             "____________________________________________________________\n");
                 } else {
                     int itemDone = Integer.parseInt(cmd.split(" ")[1]);
                     this.taskList[itemDone-1].markAsDone();
                     listChanged = true;
                     fileInputLine = rawList[itemDone-1];
                     String temp1 = fileInputLine.split("\\| 0 \\|")[0];
                     String temp2 = fileInputLine.split("\\| 0 \\|")[1];
                     fileInputLine = temp1 + "| 1 |" + temp2;
                     rawList[itemDone-1] = fileInputLine;
                     System.out.println("____________________________________________________________\n" +
                             "Task " + itemDone + " marked as done!\n" +
                             "____________________________________________________________\n");
                 }
             } else if(cmd.split(" ")[0].equals("deadline")){
                 String ddlItem, ddlTime;
                 if(cmd.split("/by ").length == 2) {
                     ddlTime = cmd.split("/by ")[1];
                 } else {
                     ddlTime = "";
                 }
                 String ddlFront = cmd.split(" /by ")[0];
                 if(ddlFront.split("deadline ").length == 2) {
                     ddlItem = ddlFront;
                 } else {
                     ddlItem = "";
                 }

                 Deadline thisDdl = null;
                 try {
                     thisDdl = new Deadline(ddlItem, ddlTime);
                 } catch (DukeFormatException e) {
                     System.out.println("____________________________________________________________\n" +
                             e.getMessage() +
                             "\nplease enter again! :)\n" +
                             "____________________________________________________________\n");
                     continue;
                 }
                 this.taskList[i] = thisDdl;
                 listChanged = true;
                 fileInputLine = "D | 0 | " + ddlItem.split("deadline ")[1] + " | " + ddlTime;
                 rawList[i] = fileInputLine;
                 i++;
                 String thisItem = thisDdl.toString();
                 String reply = "____________________________________________________________\n\tAdded deadline into list:\n" +
                         "\t"+thisItem+"\n" +
                         "\tCurently "+i+" items in the list"+"\n" +
                         "____________________________________________________________\n";
                 System.out.println(reply);
             } else if(cmd.split(" ")[0].equals("todo")){
                 String todoItem;
                 if(cmd.split("todo ").length == 2) {
                     todoItem = cmd;
                 } else {
                     todoItem = "";
                 }

                 Todo thisTodo = null;
                 try {
                     thisTodo = new Todo(todoItem);
                 } catch (DukeFormatException e) {
                     System.out.println("____________________________________________________________\n" +
                             e.getMessage() +
                             "\nplease enter again! :)\n" +
                             "____________________________________________________________\n");
                     continue;
                 }
                 this.taskList[i] = thisTodo;
                 listChanged = true;
                 fileInputLine = "T | 0 | " + todoItem.split("todo ")[1];
                 rawList[i] = fileInputLine;
                 i++;
                 String thisItem = thisTodo.toString();
                 String reply = "____________________________________________________________\n\tAdded todo into list:\n" +
                         "\t"+thisItem+"\n" +
                         "\tCurently "+i+" items in the list"+"\n" +
                         "____________________________________________________________\n";
                 System.out.println(reply);
             } else if(cmd.split(" ")[0].equals("event")) {
                 String evtItem, evtTime;
                 if(cmd.split("/at ").length == 2) {
                     evtTime = cmd.split("/at ")[1];
                 } else {
                     evtTime = "";
                 }
                 String evtFront = cmd.split(" /at ")[0];
                 if(evtFront.split("event ").length == 2) {
                     evtItem = evtFront;
                 } else {
                     evtItem = "";
                 }

                 Event thisEvt = null;
                 try {
                     thisEvt = new Event(evtItem, evtTime);
                 } catch (DukeFormatException e) {
                     System.out.println("____________________________________________________________\n" +
                             e.getMessage() +
                             "\nplease enter again! :)\n" +
                             "____________________________________________________________\n");
                     continue;
                 }
                 this.taskList[i] = thisEvt;
                 listChanged = true;
                 fileInputLine = "E | 0 | " + evtItem.split("event ")[1] + " | " + evtTime;
                 rawList[i] = fileInputLine;
                 i++;
                 String thisItem = thisEvt.toString();
                 String reply = "____________________________________________________________\n\tAdded event into list:\n" +
                         "\t" + thisItem + "\n" +
                         "\tCurently "+i+" items in the list"+"\n" +
                         "____________________________________________________________\n";
                 System.out.println(reply);
             } else {
                 //generate a new task, then activate exception there(not here)
                 try {
                     Task falseTask = new Task(cmd);
                 }
                 catch (DukeFormatException | DukeCmdException e) {
                     System.out.println("____________________________________________________________\n" +
                             e.getMessage() +
                             "\nplease enter again! :)\n" +
                             "____________________________________________________________\n");
                     continue;
                 }
             }

             // update list in the txt file
             if(listChanged) {
                 fileOpt.clearFile();
                 fileOpt.setLines(rawList);
                 fileOpt.writeFile();
             }
        }


    }

    @Override
    public void start(Stage stage) {
        //Step 1. Setting up required components

        //The container for the content of the chat to scroll.
        scrollPane = new ScrollPane();
        dialogContainer = new VBox();
        scrollPane.setContent(dialogContainer);

        userInput_st = new TextField();
        sendButton = new Button("Send");

        AnchorPane mainLayout = new AnchorPane();
        mainLayout.getChildren().addAll(scrollPane, userInput_st, sendButton);

        scene = new Scene(mainLayout);

        stage.setScene(scene);
        stage.show();

        //Step 2. Formatting the window to look as expected
        stage.setTitle("Duke");
        stage.setResizable(false);
        stage.setMinHeight(600.0);
        stage.setMinWidth(400.0);

        mainLayout.setPrefSize(400.0, 600.0);

        scrollPane.setPrefSize(385, 535);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);

        scrollPane.setVvalue(1.0);
        scrollPane.setFitToWidth(true);

        // You will need to import `javafx.scene.layout.Region` for this.
        dialogContainer.setPrefHeight(Region.USE_COMPUTED_SIZE);

        userInput_st.setPrefWidth(325.0);

        sendButton.setPrefWidth(55.0);

        AnchorPane.setTopAnchor(scrollPane, 1.0);

        AnchorPane.setBottomAnchor(sendButton, 1.0);
        AnchorPane.setRightAnchor(sendButton, 1.0);

        AnchorPane.setLeftAnchor(userInput_st , 1.0);
        AnchorPane.setBottomAnchor(userInput_st, 1.0);

        //Step 3. Add functionality to handle user input.
        sendButton.setOnMouseClicked((event) -> {
            dialogContainer.getChildren().add(getDialogLabel(userInput_st.getText()));
            userInput_st.clear();
        });

        userInput_st.setOnAction((event) -> {
            dialogContainer.getChildren().add(getDialogLabel(userInput_st.getText()));
            userInput_st.clear();
        });

        // more code to be added here later
    }

    /**
     * Iteration 1:
     * Creates a label with the specified text and adds it to the dialog container.
     * @param text String containing text to add
     * @return a label with the specified text that has word wrap enabled.
     */
    private Label getDialogLabel(String text) {
        // You will need to import `javafx.scene.control.Label`.
        Label textToAdd = new Label(text);
        textToAdd.setWrapText(true);

        return textToAdd;
    }

    public static void main(String[] args) throws DukeCmdException, DukeFormatException {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);


        Duke theDuke = new Duke();
    }

}
