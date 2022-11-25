package com.history;
	import java.awt.AWTException;
	import java.awt.Robot;
	import java.awt.event.KeyEvent;
	import java.io.BufferedReader;
	import java.io.BufferedWriter;
	import java.io.File;
	import java.io.FileInputStream;
	import java.io.FileNotFoundException;
	import java.io.FileReader;
	import java.io.FileWriter;
	import java.io.IOException;
	import java.io.PrintWriter;
	import java.text.ParseException;
	import java.text.SimpleDateFormat;
	import java.util.Calendar;
	import java.util.HashMap;
	import java.util.Scanner;
	import java.util.TreeMap;
	import java.util.concurrent.TimeUnit;

	import org.apache.poi.ss.usermodel.Cell;
	import org.apache.poi.ss.usermodel.DataFormatter;
	import org.apache.poi.ss.usermodel.Row;
	import org.apache.poi.ss.usermodel.Sheet;
	import org.apache.poi.ss.usermodel.Workbook;
	import org.apache.poi.xssf.usermodel.XSSFWorkbook;
	import org.openqa.selenium.By;
	import org.openqa.selenium.WebDriver;
	import org.openqa.selenium.WebElement;
	import org.openqa.selenium.edge.EdgeDriver;

	import io.github.bonigarcia.wdm.WebDriverManager;

	public class MachineHistory {
			public static void main( String[] args ) throws IOException, InterruptedException, AWTException, ParseException
			{String filePath = "C:\\Users\\manir\\Desktop\\Log.txt";
			    TreeMap<String, String> map = new TreeMap<String, String>();

			    String line;
			    BufferedReader reader = new BufferedReader(new FileReader(filePath));
			    while ((line = reader.readLine()) != null)
			    {
			        String[] parts = line.split("---", 2);
			        if (parts.length >= 2)
			        {
			            String key = parts[0];
			            String value = parts[1];
			            map.put(key, value);
			            
			        } else {
			            System.out.println("ignoring line: " + line);
			        }
			    }
			    
					
				

			    for (String key : map.keySet())
			    {
			        System.out.println(key + ":" + map.get(key));
			        SimpleDateFormat time2=new SimpleDateFormat("HH:mm:ss:SSS");
		    		java.util.Date parse = time2.parse(key);   		
		    		SimpleDateFormat time3=new SimpleDateFormat("HHmmssSSS");
		    		String parse1 = time3.format(parse);    		
		    		Integer t=Integer.valueOf(parse1);
		    		int t2=t-5000;
				    
		    		String time=new SimpleDateFormat("HHmmssSSS").format(Calendar.getInstance().getTime());
		            Integer t1=Integer.valueOf(time);
				    if (t1>=t2) {
			        String a1 = map.get(key);
			       String filePath1 = "C:\\Users\\manir\\Desktop\\Log1.txt";
			            TreeMap<String, String> map1 = new TreeMap<String, String>();

			            String line1 = null;
			            BufferedReader reader1 = new BufferedReader(new FileReader(filePath1));
			            
			            if(line1== null) {
							FileWriter fw=new FileWriter(filePath1);
							PrintWriter pw=new PrintWriter(fw);
							pw.println("Time---Value");
							pw.close();
							}else {			            
			            while ((line1 = reader1.readLine()) != null)
			            {
			                String[] parts1 = line1.split("---", 2);
			                if (parts1.length >= 2)
			                {
			                    String key1 = parts1[0];
			                    String value1 = parts1[1];
			                    map1.put(key1, value1);
			                    
			                    
			                } else {
			                    System.out.println("ignoring line: " + line1);
			                }
			            }
							

			            for (String key1 : map1.keySet())
			            {
			                System.out.println(key1 + ":" + map1.get(key1));
			                
			                String a2 = map1.get(key1);
			                
			                if (a1.equals(key1)) {
								System.out.println(a2);
							}else {
								WebDriverManager.edgedriver().setup();
								
								WebDriver driver = new EdgeDriver();
								driver.get("https://www.bing.com/search?q=chinines+to+english+translate&cvid=43939696cb1f444a89352acb9c4355ff&aqs=edge.1.69i57j69i59.2971j0j1&pglt=41&FORM=ANNTA1&PC=LCTS");
								driver.manage().timeouts().implicitlyWait(5000,TimeUnit.SECONDS);
								WebElement text = driver.findElement(By.xpath("//*[@id=\"tta_input_ta\"]"));
								text.clear();
								text.sendKeys(a1);
								Thread.sleep(5000);
								WebElement dest = driver.findElement(By.xpath("//*[@id=\"tta_output_ta\"]"));
								String text2 = dest.getAttribute("value");
								
							/*driver.navigate().to("https://web.whatsapp.com/");
								File f2 = new File("C:\\Users\\manir\\Desktop\\Java notes\\data.xlsx");
								FileInputStream file2 = new FileInputStream(f2);
								Workbook w = new XSSFWorkbook(file2);
								Sheet sheet2 = w.getSheet("s2");
								for (int i = 0; i < sheet2.getPhysicalNumberOfRows(); i++) {
									Row row = sheet2.getRow(i);
									for (int j = 0; j < row.getPhysicalNumberOfCells(); j++) {
										Cell cell = row.getCell(j);
										DataFormatter d = new DataFormatter();
										String fcv = d.formatCellValue(cell);
										System.out.print("  "+fcv);
										
										Robot robot=new Robot();
										
										WebElement text3 = driver.findElement(By.xpath("//*[@id=\"side\"]/div[1]/div/div/div[2]/div/div[2]"));
										text3.sendKeys(fcv);
										robot.keyPress(KeyEvent.VK_ENTER);
										robot.keyRelease(KeyEvent.VK_ENTER);
										WebElement msg = driver.findElement(By.xpath("//*[@id=\"main\"]/footer/div[1]/div/span[2]/div/div[2]/div[1]/div/div[1]/p"));
										msg.sendKeys(a1+"---"+text2);
										robot.keyPress(KeyEvent.VK_ENTER);
										robot.keyRelease(KeyEvent.VK_ENTER);
										Thread.sleep(2000);
								
							}}
							*/
								FileWriter fw1=new FileWriter(filePath1,true);
							PrintWriter pw1=new PrintWriter(fw1);
							pw1.println(a1+"---"+text2);
							pw1.close();

							driver.close();
							}
								
							}
			                
			            
			            reader1.close();
			    }}else {
					Thread.sleep(2000);
				}}
			    reader.close();
			}}


