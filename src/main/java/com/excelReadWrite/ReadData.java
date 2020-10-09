package com.excelReadWrite;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ReadData {
Properties property;
	
	public Row input_file() throws IOException {
//		FileInputStream fs=new FileInputStream(System.getProperty("user.dir")+"\\config.properties");
//		property = new Properties();
//		property.load(fs);
		FileInputStream stream = new FileInputStream("C:\\Users\\user\\Desktop\\CTS_Onboarding\\SeleniumProject\\Selenium_tarining_group-5\\Input_ExcelSheet\\Hospital_Loc.xlsx");
		Workbook wrk = new XSSFWorkbook(stream);
		Sheet shit=wrk.getSheetAt(0);
		Row row=shit.getRow(1);
		return row;
		
	}
	
	public String input_city(Row row) throws IOException
	{
		String city=row.getCell(0).getStringCellValue();
		return city;
		
	}
	
	public String input_searchitem(Row row) throws IOException
	{
		String searchitem=row.getCell(1).getStringCellValue();
		return searchitem;
		
	}

}
