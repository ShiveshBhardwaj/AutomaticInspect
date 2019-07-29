package pack2;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class AutomaticInspection {
	public static void function(String url,String XMLfile) throws InterruptedException, AWTException, ParserConfigurationException, SAXException, IOException 
	{
		System.setProperty("webdriver.chrome.driver","locationOfChromeDriver");
		WebDriver driver=new ChromeDriver();
		
		driver.manage().window().maximize();
		driver.get(url);
		
		
		Actions action=new Actions(driver);
		Robot robot=new Robot();
        
       
		WebElement rightClick=driver.findElement(By.cssSelector(".container"));//Find some WebElement using xpath/class/id/cssSelector
		action.contextClick(rightClick).build().perform();
		robot.setAutoDelay(500);
		robot.keyPress(KeyEvent.VK_UP);
		robot.setAutoDelay(500);
		robot.keyPress(KeyEvent.VK_UP);
		robot.setAutoDelay(500);
		robot.keyPress(KeyEvent.VK_UP);
		robot.setAutoDelay(500);
		robot.keyRelease(KeyEvent.VK_UP);
		robot.setAutoDelay(500);
		robot.keyPress(KeyEvent.VK_ENTER);
		robot.setAutoDelay(500);
		robot.keyRelease(KeyEvent.VK_ENTER);

		robot.setAutoDelay(500);

		

		robot.setAutoDelay(500);
		robot.keyPress(KeyEvent.VK_META);
		robot.keyPress(KeyEvent.VK_F);
		robot.setAutoDelay(500);
		robot.keyRelease(KeyEvent.VK_META);
		robot.setAutoDelay(500);
		robot.keyRelease(KeyEvent.VK_F);
		robot.setAutoDelay(500);

		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		Document document = builder.parse(new File(XMLfile));

		NodeList nodeList = document.getDocumentElement().getChildNodes();
		for (int i = 0; i < nodeList.getLength(); i++) {
			Node node = nodeList.item(i);

			if (node.getNodeType() == Node.ELEMENT_NODE) {
				Element elem = (Element) node;

				String Element = node.getAttributes().getNamedItem("element").getNodeValue();
				String XpathExpression = elem.getElementsByTagName("xpath")
						.item(0).getChildNodes().item(0).getNodeValue();
				System.out.println(" Element :- "+Element+" and Xpath :- "+XpathExpression);
				StringSelection ss=new StringSelection(XpathExpression);
				Toolkit.getDefaultToolkit().getSystemClipboard().setContents(ss,null);
				robot.setAutoDelay(500);
				
				
				
				robot.keyPress(KeyEvent.VK_META);
				robot.keyPress(KeyEvent.VK_V);
				robot.keyRelease(KeyEvent.VK_META);
				robot.keyRelease(KeyEvent.VK_V);
				robot.setAutoDelay(500);

				robot.keyPress(KeyEvent.VK_ENTER);



				try {driver.findElement(By.xpath(XpathExpression));}
				catch(NoSuchElementException e){
					System.out.println("Element "+Element+" not present ");

					robot.setAutoDelay(500);
					robot.keyPress(KeyEvent.VK_META);
					robot.keyPress(KeyEvent.VK_Z);
					robot.keyRelease(KeyEvent.VK_META);
					robot.keyRelease(KeyEvent.VK_Z);
					robot.setAutoDelay(500);
					continue;
				}
				
				robot.setAutoDelay(500);
				robot.keyPress(KeyEvent.VK_META);
				robot.keyPress(KeyEvent.VK_Z);
				robot.keyRelease(KeyEvent.VK_META);
				robot.keyRelease(KeyEvent.VK_Z);
				robot.setAutoDelay(500);



			}
		}
		driver.quit();
	}
	
}
