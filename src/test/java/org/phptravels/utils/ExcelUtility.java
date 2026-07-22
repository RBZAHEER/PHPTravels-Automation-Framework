package org.phptravels.utils;

import org.apache.poi.ss.usermodel.*;

import java.io.File;
import java.io.FileInputStream;

public class ExcelUtility {
    public static Object[][] readExcel(String filePath, String sheetName){
        //Create 2D array
        Object[][] data = null;

        try{
            FileInputStream excelFs = new FileInputStream(filePath);

            //Create WorkBook object that is entire excel file
            Workbook wb = WorkbookFactory.create(excelFs);

            //Select Sheet
            Sheet sheet = wb.getSheet(sheetName);

            //Count Rows and cols
            int rows = sheet.getPhysicalNumberOfRows();
            int cols = sheet.getRow(0).getPhysicalNumberOfCells();

            //Create object with size of row-1 and cols
            data = new Object[rows-1][cols];

            //Insert data into object from cell
            for(int i=1;i<rows;i++){
                //Get current row value
                Row row = sheet.getRow(i);
                //loop though every column
                for(int j=0;j<cols;j++){
                    //Get current cell value
                    Cell cell = row.getCell(j);
                    //Store cell into object
                    data[i-1][j] = cell.toString();

                }
            }
            //close workbook
            wb.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return data;
    }
}
