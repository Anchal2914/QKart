package QKART_TESTNG;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.apache.poi.ss.usermodel.DataFormatter;


public class DP {
    @DataProvider (name = "data-provider")
    public Object[][] dpMethod (Method m) throws IOException{
        File flObj = new File("/home/crio-user/workspace/anchalsingh2914-ME_QKART_QA/app/src/test/resources/Dataset.xlsx");
        FileInputStream fileInputObj = new FileInputStream(flObj);
        XSSFWorkbook wbObj = new XSSFWorkbook(fileInputObj);
        switch (m.getName()) {
            case "TestCase01": 
                XSSFSheet sh01 = wbObj.getSheet("TestCase01");
                int TC1_rowCount = sh01.getLastRowNum();
                String TC1_username1 = ""; String TC1_username2 = "";String TC1_username3 = "";
                String TC1_password1 = "";String TC1_password2 = "";String TC1_password3 = ""; 
                for(int i=1; i<=TC1_rowCount; i++){
                    Row row = sh01.getRow(i);
                    Cell cell = row.getCell(1);
                    String testData = cell.getStringCellValue();
                    if(testData.equalsIgnoreCase("testUsername")){
                        TC1_username1 = testData;
                        TC1_password1 = row.getCell(2).getStringCellValue();
                    } else if(testData.equalsIgnoreCase("testUsername123")){
                        TC1_username2 = testData;
                        TC1_password2 = row.getCell(2).getStringCellValue();
                    } else if(testData.equalsIgnoreCase("test_123")){
                        TC1_username3 = testData;
                        TC1_password3 = row.getCell(2).getStringCellValue();
                    }   
                }
            return new Object[][] {{TC1_username1, TC1_password1}, {TC1_username2, TC1_password2}, {TC1_username3, TC1_password3}};
	
            case "TestCase02":  
            XSSFSheet sh02 = wbObj.getSheet("TestCase02");
            int TC2_rowCount = sh02.getLastRowNum();
            String TC2_username1 = ""; String TC2_username2 = ""; String TC2_username3 = "";
            String TC2_password1 = ""; String TC2_password2 = ""; String TC2_password3 = ""; 
            for(int i=1; i<=TC2_rowCount; i++){
                Row row = sh02.getRow(i);
                    Cell cell = row.getCell(1);
                    String testData = cell.getStringCellValue();
                    if(testData.equalsIgnoreCase("testUsername")){
                        TC2_username1 = testData;
                        TC2_password1 = row.getCell(2).getStringCellValue();
                    } else if(testData.equalsIgnoreCase("testUsername123")) {
                        TC2_username2 = testData;
                        TC2_password2 = row.getCell(2).getStringCellValue();
                    } else if(testData.equalsIgnoreCase("test_123")) {
                        TC2_username3 = testData;
                        TC2_password3 = row.getCell(2).getStringCellValue();
                    }   
            }
            return new Object[][] {{TC2_username1, TC2_password1}, {TC2_username2, TC2_password2}, {TC2_username3, TC2_password3}};
		}
		return null;
    }

        //  public Object[][] TestCase01 () throws IOException {
        //     File fileObj = new File("app/src/test/resources/Dataset.xlsx");
        //     FileInputStream fileInputObj = new FileInputStream(fileObj);
        //     XSSFWorkbook wbObj = new XSSFWorkbook(fileInputObj);
        //     XSSFSheet shObj = wbObj.getSheet("TestCase01");
        //     XSSFRow Row=shObj.getRow(0);
        //     int RowNum = shObj.getPhysicalNumberOfRows();
        //     int ColNum= Row.getLastCellNum();
        //     Object Data[][]= new Object[RowNum-1][ColNum];
        //     for(int i=0; i<RowNum-1; i++) {
        //         XSSFRow row= shObj.getRow(i+1);
        //         for (int j=0; j<ColNum; j++) {
        //             if(row==null)
        //             Data[i][j]= "";
        //             else {
        //                 XSSFCell cell= row.getCell(j);
        //                 if(cell==null)
        //                 Data[i][j]= ""; {
        //                     cell.getCellType();
        //                     String value=cell.getStringCellValue();
        //                     Data[i][j]=value; //This formatter get my all values as string i.e integer, float all type data value
        //                 }
        //             }
        //         }
        //     }        
        //     return Data;
        // }

        }