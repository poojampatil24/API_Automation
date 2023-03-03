package org.Samples.Utilities;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.util.HashMap;

public class ExcelDataConfig {

    public static XSSFWorkbook workbook;
    public static XSSFSheet excelsheet;
    public static FileInputStream inputStream;
    public static HashMap<String,String> map;

    public HashMap<String,String> getData(String workbookname, String sheetname){
        String filepath= (System.getProperty("user.dir")+"\\src\\main\\java\\org\\Samples\\DataFiles\\"+workbookname+".xlsx");
        try {
            inputStream = new FileInputStream(filepath);
            workbook = new XSSFWorkbook(inputStream);
            excelsheet=workbook.getSheet(sheetname);

            int totalrows= excelsheet.getLastRowNum();

            map=new HashMap<String,String>();

            for(int i=1;i<=totalrows;i++) {
                String value ="";
                String key = "";
                XSSFCell cell = excelsheet.getRow(i).getCell(1);
                XSSFCell cell1 = excelsheet.getRow(i).getCell(0);

                if(cell != null && cell1 != null) {
                    key= cell1.getStringCellValue();
                    value = cell.getStringCellValue();
                }
                map.put(key, value);
            }
        }catch (Exception e) {

            e.printStackTrace();
        }
        return map;
    }
}
