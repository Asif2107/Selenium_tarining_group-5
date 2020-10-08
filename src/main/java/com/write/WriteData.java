package com.write;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.WebDriver;

import com.pages.BasePage;

public class WriteData  extends BasePage  {

	public WriteData(WebDriver driver) 
	{
		super(driver);
		// TODO Auto-generated constructor stub
	}
	
	public void doWrite(List<String> hname)throws FileNotFoundException, IOException 
	{
		File src;
		
		src=new File(System.getProperty("user.dir")+"\\Output_ExcelSheet\\Hospital.xlsx");	//to get the location of the excel file
		FileInputStream fis=new FileInputStream(src);	//create an object of Workbook and pass the FileInputStream object into it to create a pipeline between the sheet and eclipse.
        
	    XSSFWorkbook wb=new XSSFWorkbook();
	    
	    Sheet sheet1=wb.createSheet("TestCase");		//assigning name to the sheet
	    
	    Row row1=sheet1.createRow(0);					//creating the first row
	    Cell cell1=row1.createCell(0);					//creating first column of the first row
	    cell1.setCellValue("Hospital names");
	    
	    for(int i=0;i<hname.size();i++)
	    {
	    	Row row2=sheet1.createRow(i+1);
	    	Cell cell3=row2.createCell(0);
	    	cell3.setCellValue(hname.get(i));
	    }
	
	  //To adjust the cell size
	    sheet1.autoSizeColumn(0);
	    
	  //close input stream
	    fis.close();
	    
	    FileOutputStream fos = new FileOutputStream(src);	

	    //write data in the excel file
	    wb.write(fos);
	    
	    //close output stream
	    fos.close();
	    
	    wb.close();			//closing the workbook

	}
	
	
	
	
	
	

}

