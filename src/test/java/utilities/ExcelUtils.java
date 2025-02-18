package utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtils {
	
	public FileInputStream fi;   //variable declaration
	public FileOutputStream fo;
	public XSSFWorkbook wb ;
	public XSSFSheet sh ;
	public XSSFRow row ;
	public XSSFCell cell ;
	public CellStyle cs ;
	String path;
	
	public ExcelUtils(String path)
	{
		this.path= path;	
				}

	public int getrowcount(String xlsheet) throws IOException
	{
	 fi = new FileInputStream(path);
	 wb = new XSSFWorkbook(fi);
	 sh = wb.getSheet(xlsheet);
	 int rowcount =sh.getLastRowNum();
	 wb.close();
	 fi.close();
	 return rowcount;
	 	}
	public int getcellcount(String xlsheet,int rownum) throws IOException
	{
	 fi = new FileInputStream(path);
	 wb = new XSSFWorkbook(fi);
	 sh = wb.getSheet(xlsheet);
	 row = sh.getRow(rownum);
	 int cellcount =row.getLastCellNum();
	 wb.close();
	 fi.close();
	 return cellcount;
	 	}
	public String getcelldata(String xlsheet,int rownum,int cellnum) throws IOException
	{
	 fi = new FileInputStream(path);
	 wb = new XSSFWorkbook(fi);
	 sh = wb.getSheet(xlsheet);
	 row = sh.getRow(rownum);
	 cell =row.getCell(cellnum);
	 
	 String data;
	 try
	 {
	 //data = cell.toString(); //returns value in non formatted type
	 DataFormatter df = new DataFormatter();
	 data = df.formatCellValue(cell); //returns the formatted value of cell regardless of cell type
	 }
	 catch(Exception e)
	 {
	  data = "";	 
	 }
	 wb.close();
	 fi.close();
	 return data;
	 	}
	public void setcelldata(String xlsheet,int rownum,int cellnum,String data) throws IOException
	{
	 //If file not exists,create new file
	 File xlfile = new File(path);
	 if(!xlfile.exists())
	 {
	  wb = new XSSFWorkbook();
	  fo = new FileOutputStream(path);
	  wb.write(fo);
	 }
	  
	 fi = new FileInputStream(path); //reading
	 wb = new XSSFWorkbook(fi);
	 
	 //If sheet not exists,create new sheet
	 if(wb.getSheetIndex(xlsheet)==-1)
	 {
		 wb.createSheet(xlsheet);
		 sh = wb.getSheet(xlsheet); 
	 }
	 
	 //If row not exists,then create new row
	 if(sh.getRow(rownum)==null)
	 {
		sh.createRow(rownum); 
		row = sh.getRow(rownum);
			 }
	 	 
	 cell =row.createCell(cellnum);   //writing //updating result value in new cell
	  cell.setCellValue(data);                            
	 fo = new FileOutputStream(path);
	 wb.write(fo);
	 wb.close();
	 fi.close();
	 fo.close();
	}
	public void fillgreencolor(String xlsheet,int rownum,int cellnum) throws IOException
	{
	 fi = new FileInputStream(path); //reading
	 wb = new XSSFWorkbook(fi);
	 sh = wb.getSheet(xlsheet);
	 row = sh.getRow(rownum);
	 cell = row.getCell(cellnum);
	 
	 cs = wb.createCellStyle();
	 cs.setFillForegroundColor(IndexedColors.GREEN.getIndex());
	cs.setFillPattern(FillPatternType.SOLID_FOREGROUND);
	 cell.setCellStyle(cs);
	 
	 fo = new FileOutputStream(path);
	 wb.write(fo);
	 wb.close();
	 fi.close();
	 fo.close();
	}
	public void fillredcolor(String xlsheet,int rownum,int cellnum) throws IOException
	{
	 fi = new FileInputStream(path); //reading
	 wb = new XSSFWorkbook(fi);
	 sh = wb.getSheet(xlsheet);
	 row = sh.getRow(rownum);
	 cell = row.getCell(cellnum);
	 
	 cs = wb.createCellStyle();
	 cs.setFillForegroundColor(IndexedColors.RED.getIndex());
	cs.setFillPattern(FillPatternType.SOLID_FOREGROUND);
	 cell.setCellStyle(cs);
	 
	 fo = new FileOutputStream(path);
	 wb.write(fo);
	 wb.close();
	 fi.close();
	 fo.close();
	}
	

	}
