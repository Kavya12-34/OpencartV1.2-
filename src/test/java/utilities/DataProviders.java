package utilities;

import java.io.IOException;

import org.testng.annotations.DataProvider;

public class DataProviders {
	
	//Data Provider1
	
@DataProvider(name="LoginData")
public String[][] getData() throws IOException
{
 String path = ".\\testData\\OpenCart_LoginData.xlsx"; //taking excel file  from testData
 ExcelUtils xlutil = new ExcelUtils(path); //creating object for ExcelUtils
 
 int totalrows = xlutil.getrowcount("Sheet1");
 int totalcols = xlutil.getcellcount("Sheet1", 1);
 
 String logindata[][]= new String[totalrows][totalcols];
 
 for(int i=1;i<=totalrows;i++)  //starting from 2nd row
 {
	 for(int j=0;j<totalcols;j++) 
	 {
		logindata[i-1][j]= xlutil.getcelldata("Sheet1", i, j); 
	 }
 }
 return logindata; //returning two dimensional array
}

//DataProvider2
//DataProvider3
}
