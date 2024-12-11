package Utility;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class PropertyReader {

	/*Config File Reader*/
	public static String readProperty(String value) throws IOException
	{
		String path = System.getProperty("user.dir") + "\\src\\test\\resources\\config.properties";
		
		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream(path);
		prop.load(fis);
		
		String text = prop.getProperty(value);
		return text;
	}

	/*Excel File Reader*/
	public static String readExcelData(String sheetName, int rowNo, int cellNo) throws EncryptedDocumentException, IOException
	{
		String path = System.getProperty("user.dir") + "\\src\\test\\resources\\data.xlsx";
		
		FileInputStream fis = new FileInputStream(path);
		
		String data = WorkbookFactory.create(fis).getSheet(sheetName).getRow(rowNo).getCell(cellNo).getStringCellValue();
		
		return data;
	}
}
