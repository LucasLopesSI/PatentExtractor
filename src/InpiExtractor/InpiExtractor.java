/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package InpiExtractor;

import static LensExtractor.LensConnector.printLenPagePatentInformation;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

/**
 *
 * @author Carlos
 */
public class InpiExtractor {
    public static void printPatentData(WebDriver driver){
        String patent_code = driver.findElement(By.xpath("/html/body/form/div[2]/div/table[2]/tbody/tr[2]/td[2]/font")).getText();
            String title = driver.findElement(By.xpath("/html/body/form/div[2]/div/table[2]/tbody/tr[8]/td[2]/div/font")).getText();
            String abstract_p = driver.findElement(By.xpath("/html/body/form/div[2]/div/table[2]/tbody/tr[9]/td[2]/div/font")).getText();
            String applicant = driver.findElement(By.xpath("/html/body/form/div[2]/div/table[2]/tbody/tr[10]/td[2]/font")).getText();
            String inventors = driver.findElement(By.xpath("/html/body/form/div[2]/div/table[2]/tbody/tr[11]/td[2]/font")).getText();
            String ipc = driver.findElement(By.xpath("/html/body/form/div[2]/div/table[2]/tbody/tr[6]/td[2]/font/a")).getText();
            
            System.out.println(patent_code+"##"+title+"##"+abstract_p+"##"+applicant+"##"+inventors+"##"+ipc);
    }
    
    public static void managePatentsExtraction(WebDriver driver){
        //http://dbpedia.org/page/Electrostatics
        System.setProperty("webdriver.chrome.driver", "chromeDriver.exe");
        WebDriver patent_page = new ChromeDriver();
        
        for(int i =1;i<5166;i++){
            driver.navigate().to("https://gru.inpi.gov.br/pePI/servlet/PatenteServletController?Action=nextPage&Page="+i+"&Resumo=&Titulo=");
            
            try{
                Thread.sleep(6000);
            }catch(Exception e){
            }
            
            int j=1;
            
            for(;j<=5166;j++){
                try{
                    
                    if(driver.findElement(By.xpath("/html/body/div/form/div/div[1]/table/tbody[2]/tr[2]/td["+j+"]")).getText().length()>3){        
                        String patent_page_path = driver.findElement(By.xpath("/html/body/div/form/div/div[1]/table/tbody[2]/tr["+j+"]/td[1]")).getAttribute("href");
                        patent_page.navigate().to(patent_page_path);
                        printLenPagePatentInformation(patent_page);
                        try{
                             Thread.sleep(1000);
                        }catch(Exception e){
                        }
                    }
                }catch(Exception e){
                    e.printStackTrace();
                    System.out.println("Error Extraction i: "+i+" j:"+j);
                }
            }
            
        }
    }
    
}

