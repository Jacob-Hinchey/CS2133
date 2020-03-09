import java.io.*;
import java.security.*;
import java.util.*;

public class Caesar {

    public static void main(String[] args) {
        File input;
        File output;
        int base;
        boolean coded = true;
        if(args.length != 4)
        {
            System.out.println("Not a valid use!");
            System.out.println("Usage Example: java Caesar usage base infile outfile");
            System.out.println("d to decrypt, e to encrypt");
            System.exit(0);
        }
        output = new File(args[3]);
        base = Integer.parseInt(args[1]);
        if(args[0].equals("e")){
            coded = true;
        }
        else if (args[0].equals("d")){
            coded = false;
        }
        else {
            System.out.println("Not a valid use!");
            System.out.println("Usage Example: java Caesar usage base infile output");
            System.out.println("d to decrypt, e to encrypt");
            System.exit(0);
        }
    }

    public File input;
    public File output;
    public int base;

    public Caesar(File input, File output, int base){
        this.input = input;
        this.output = output;
        this.base = base;
    }

    public String codeStatement(String valueInput, int base){
        String valueOut = "";
        for(int i = 0; i < valueInput.length(); i++){
            char charValue = valueInput.charAt(i);
            if (charValue > 32 || charValue < 126){
               charValue = (char)charValue;
            }
            charValue = (char)(charValue + base);
            if (charValue > 126) {
              charValue = (char)(charValue - 95);
            }
            else if (charValue < 32){
              charValue = (char)(charValue  + 95);
            }
            valueOut += charValue;
        }
        return valueOut;
    }

    public String decyperStatement(String valueInput, int base){
        String valueOut = "";
        for(int i = 0; i < valueInput.length(); i++){
            char charValue = valueInput.charAt(i);
            if (charValue > 32 || charValue < 126) {
              charValue = (char)charValue;
            }
            charValue = (char)(charValue - base);
            if (charValue > 126) {
              charValue = (char)(charValue - 95);
            }
            else if (charValue < 32) {
              charValue = (char)(charValue  + 95);
            }
            valueOut += charValue;
        }
        return valueOut;
    }

    public void codeText(){
        try{
            Scanner scan = new Scanner(input);
            PrintWriter writer = new PrintWriter(output);
            while (scan.hasNextLine()){
                String inputText = scan.nextLine();
                String codedText = codeStatement(inputText, base);
                writer.write(codedText + "\n");
            }
            writer.flush();
            writer.close();
        }
        catch (FileNotFoundException ex){
            System.out.println("File was not found!");
            System.exit(0);
        }
    }

    public void decyperText(){
        try{
            Scanner scan = new Scanner(input);
            PrintWriter writer = new PrintWriter(output);
            while (scan.hasNextLine()){
                String inputText = scan.nextLine();
                String decyper = decyperStatement(inputText, base);
                writer.write(decyper + "\n");
            }
            writer.flush();
            writer.close();
        }
        catch (FileNotFoundException ex){
            System.out.println("File was not found!");
        }
    }
}
