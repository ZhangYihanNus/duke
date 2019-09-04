import java.io.*;

public class ReadTxt {
    private String[] lines = new String[100];
    private String path;

    public ReadTxt(String filePath) {
        this.path = filePath;
    }

    public void setLines(String[] lines) {
        this.lines = lines;
    }

    public String[] getLines() {
        return lines;
    }

    public void readFile() {
        //String pathname = "C:\\Users\\Min\\Desktop\\o\\NUS\\1920-Year2-sem-1\\courses\\CS2113T\\duke\\localList.txt";
        String pathname = this.path;
        try (FileReader reader = new FileReader(pathname);
             BufferedReader br = new BufferedReader(reader)
        ) {
            String line;
            int i = 0;
            while ((line = br.readLine()) != null) {
                // read in each line
                System.out.println("reading from file: "+line);
                this.lines[i] = line;
                i++;
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("\tcurrently no list to load from local :)\n" +
                    "____________________________________________________________\n");
        }
    }


    public void writeFile() {
        try {
            File writeName = new File(this.path);
            //writeName.createNewFile(); // overlaps file with same name
            try (FileWriter writer = new FileWriter(writeName, true);
                 BufferedWriter out = new BufferedWriter(writer)
            ) {
                for(int i = 0; this.lines[i] != null; i++)
                out.write(this.lines[i]+"\r\n"); // \r\n is to switch line
                out.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void clearFile() {
        try {
            File writeName = new File(this.path);
            //writeName.createNewFile(); // overlaps file with same name
            try (FileWriter writer = new FileWriter(writeName);
                 BufferedWriter out = new BufferedWriter(writer)
            ) {
                out.write("");
                out.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
