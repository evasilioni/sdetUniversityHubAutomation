package utilities;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CSVParser {

    public static List<String[]> get(String fileName){
        List<String[]> data = new ArrayList<>();
        String testRow;

        //Open and read the file
        try(BufferedReader bf= new BufferedReader(new FileReader(fileName))) {
            while((testRow = bf.readLine()) !=null){
                String[] line = testRow.split(",");
                data.add(line);
            }
        } catch(IOException ex){

        }

        return data;
    }
}
