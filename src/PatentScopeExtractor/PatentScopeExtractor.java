/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PatentScopeExtractor;

import static LensExtractor.LensConnector.printLenPagePatentInformation;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

/**
 *
 * @author Carlos
 */
public class PatentScopeExtractor {
    public static void printPatentData(WebDriver driver, int i){
        String patent_code = driver.findElement(By.xpath("/html/body/div[2]/div[4]/div/div[1]/div[2]/div/form[2]/div/div[1]/div/div/table/tbody/tr["+i+"]/td/div/div[2]/div/div[1]/span[2]/span[2]")).getText();
        String title = driver.findElement(By.xpath("/html/body/div[2]/div[4]/div/div[1]/div[2]/div/form[2]/div/div[1]/div/div/table/tbody/tr["+i+"]/td/div/div[1]/div[1]/span[2]/span/span")).getText();
        String abstract_p = driver.findElement(By.xpath("/html/body/div[2]/div[4]/div/div[1]/div[2]/div/form[2]/div/div[1]/div/div/table/tbody/tr["+i+"]/td/div/div[2]/div/div[2]/span")).getText();
        String applicant = driver.findElement(By.xpath("/html/body/div[2]/div[4]/div/div[1]/div[2]/div/form[2]/div/div[1]/div/div/table/tbody/tr["+i+"]/td/div/div[2]/div/div[1]/span[3]/span[2]")).getText();
        String inventors = driver.findElement(By.xpath("/html/body/div[2]/div[4]/div/div[1]/div[2]/div/form[2]/div/div[1]/div/div/table/tbody/tr["+i+"]/td/div/div[2]/div/div[1]/span[4]/span[2]")).getText();
        String ipc = driver.findElement(By.xpath("/html/body/div[2]/div[4]/div/div[1]/div[2]/div/form[2]/div/div[1]/div/div/table/tbody/tr["+i+"]/td/div/div[2]/div/div[1]/span[1]/span[2]/span[1]/span/a")).getText();
            
        System.out.println(patent_code+"##"+title+"##"+abstract_p+"##"+applicant+"##"+inventors+"##"+ipc);
    }
    
    public static void managePatentsExtraction(WebDriver driver){
        //http://dbpedia.org/page/Electrostatics
        System.setProperty("webdriver.chrome.driver", "chromeDriver.exe");
        driver.navigate().to("https://patentscope.wipo.int/search/en/result.jsf?_vid=P21-KFK498-91643#atapta0");
        
        try{
                Thread.sleep(42000);
            }catch(Exception e){
            }
        
        for(int i =1;i<=4016;i++){
            try{
                Thread.sleep(2000);
            }catch(Exception e){
            }
            for(int j=1;j<=200;j++){
                try{
                    
                    printPatentData(driver,j);
                    
                }catch(Exception e){
                    System.out.println("Error Extraction i: "+i+" j:"+j);
                }
            }
            if(i==1)
                driver.findElement(By.xpath("/html/body/div[2]/div[4]/div/div[1]/div[2]/div/form[1]/div/div[2]/a")).click();
            else
                driver.findElement(By.xpath("/html/body/div[2]/div[4]/div/div[1]/div[2]/div/form[1]/div/div[2]/a[2]")).click();
        }
    }
}
