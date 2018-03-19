package demos;

import utilities.CSVParser;
import utilities.ExcelParser;

import java.io.File;
import java.util.List;

public class DataReaders {

    public static void main(String[] args) {
        readCSV();
        readExcel();
    }

    public static void readCSV(){
        File file = new File(DataReaders.class.getResource("/UserAccounts.csv").getFile());
        List<String[]> records = CSVParser.get(file.getPath());

        //Iterating through the list
        for(String[] record : records){
            for (String field: record){
                System.out.println(field);
            }
        }
    }

    public static void readExcel(){
        File file = new File(DataReaders.class.getResource("/UserLogin.xls").getFile());
        String[][] data = ExcelParser.get(file.getPath());

        //Iterating through the list
        for(String[] record : data){
                System.out.println("\nNEW RECORD");
                System.out.println(record[0]);
                System.out.println(record[1]);
                System.out.println(record[2]);
        }
    }
}
