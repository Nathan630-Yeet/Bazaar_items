import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Dooley {
    public static void printData(String fileName) {
        File file = new File(fileName);
        try{
            Scanner scan = new Scanner(file);
            scan.useDelimiter(",");
            while(scan.hasNext()){
                String data = scan.next();
                if(data == "Small" || (data == "Medium"|| data == "Size")){
                    System.out.println(data + "|");
                }
                else{
                    System.out.print(data + "|");
                }
            }
        }catch (FileNotFoundException e){

            e.printStackTrace();
        }
    }
}
