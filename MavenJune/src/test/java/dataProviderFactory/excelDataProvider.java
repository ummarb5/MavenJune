package dataProviderFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class excelDataProvider {

	// if using xls use HSSF
	// if using xlsx file, us XSSF
	
	XSSFWorkbook wb;
	
	public excelDataProvider() throws FileNotFoundException, IOException {

		wb = new XSSFWorkbook(new FileInputStream(new File(System.getProperty("user.dir") + "/testdata/TestData.xlsx")));
		
	}
	public String getCellData(String sheetName, int row, int col)
	{
		XSSFCell cell = wb.getSheet(sheetName).getRow(row).getCell(col);
		String data = null; //or u can ""
		
		if(cell.getCellType() == CellType.STRING)			// Compares the cell value to see if its a string
		{
			data = cell.getStringCellValue();					
		}
		else if(cell.getCellType() == CellType.NUMERIC) 		//compares the cell value to see if its a number
		{
			data = String.valueOf((double)cell.getNumericCellValue());
		}
		else if(cell.getCellType() == CellType.BLANK)			//compares the cell value to see if its blank
		{
			data = "";
		}
		
		return data;											// returns cell data
	}
	public int getRows(String sheetName)
	{
		return wb.getSheet(sheetName).getPhysicalNumberOfRows(); // returns the total number of rows
	}
	public int getColumns(String sheetName)
	{
		return wb.getSheet(sheetName).getRow(0).getPhysicalNumberOfCells(); // returns the total number of columns in a row
		
	}
}
