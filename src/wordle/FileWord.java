/*import java.io.*;

public class FileWord {
    public static void main(String[] args) throws IOException {
        FileReader r = new FileReader("parole.txt");
        BufferedReader br = new BufferedReader(r);

        FileWriter w = new FileWriter("words7Char.txt",true);
        BufferedWriter bw = new BufferedWriter(w);

        String line;
        while((line = br.readLine()) != null){
            if(line.length() == ){
                bw.write((line + "\n"));
            }
        }

        bw.close();
        w.close();
    }
}*/