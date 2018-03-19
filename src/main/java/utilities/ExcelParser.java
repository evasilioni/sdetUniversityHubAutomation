package utilities;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class ExcelParser {

    // Thus method will read and return Excel data into a double array
    public static String[][] get(String fileName) {
        String[][] dataTable = null;
        File file = new File(fileName);

        try{
            //Create a file input stream to read Excel workbook and worksheet
            FileInputStream xlsFile = new FileInputStream(file);
            HSSFWorkbook xlwb = new HSSFWorkbook(xlsFile);
            HSSFSheet xlSheet = xlwb.getSheetAt(0);

            //Get the number of rows and columns
            int numRows = xlSheet.getLastRowNum() + 1;
            int numCols = xlSheet.getRow(0).getLastCellNum();

            // Create double array data table = rows * cols
            // we will return this data table
            dataTable = new String[numRows][numCols];

            //for each row create a HSSFRow, then iterate through the columns
            //for each column create an HSSFCell to grab the value at the specified cell(i,j)
            for(int i=0; i < numRows; i++){
                HSSFRow xlRow = xlSheet.getRow(i);
                for(int j=0; j< numCols; j++){
                    HSSFCell xlCell = xlRow.getCell(j);
                    dataTable[i][j] = xlCell.toString();
                }
            }
        } catch (IOException ex){
            System.out.println("ERROR FILE HANDLING " + ex.toString());
        }

        return dataTable;
    }
}
