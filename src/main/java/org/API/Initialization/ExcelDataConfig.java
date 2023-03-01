package org.API.Initialization;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.util.HashMap;

public class ExcelDataConfig {

    public static XSSFWorkbook workbook;
    public static XSSFSheet excelsheet;
    public static FileInputStream inputStream;
//    String folderName =System.getProperty("user.dir")+ File.separator + "excel"+File.separator;
static String folderName = System.getProperty("user.dir")+"\\src\\main\\java\\org\\API\\DataFiles";
    public static HashMap<String,String> map;


    public static HashMap<String,String> getData(String workbookname, String sheetname){
        String filepath= (System.getProperty("user.dir")+"\\src\\main\\java\\org\\API\\DataFiles\\"+workbookname+".xlsx");
        try {
            inputStream = new FileInputStream(filepath);
            workbook = new XSSFWorkbook(inputStream);
            excelsheet=workbook.getSheet(sheetname);

            int totalrows= excelsheet.getLastRowNum();

            int totalcol= excelsheet.getRow(0).getLastCellNum();

            map=new HashMap<String,String>();
            for(int i=1;i<=totalrows;i++) {
                String key= excelsheet.getRow(i).getCell(0).getStringCellValue();

                String value=excelsheet.getRow(i).getCell(1).getStringCellValue();

                map.put(key, value);
            }

        }catch (Exception e) {

            e.printStackTrace();
        }
        return map;

    }
}
