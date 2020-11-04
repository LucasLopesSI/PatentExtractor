/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LensExtractor;

import java.util.LinkedList;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

/**
 *
 * @author Carlos
 */
public class LensConnector {
    //https://www.lens.org/lens/search/patent/list?p=0&n=100&s=cited_by_patent_count&d=%2B&f=false&e=false&l=en&authorField=author&dateFilterField=publishedDate&orderBy=%2Bcited_by_patent_count&presentation=false&stemmed=true&useAuthorId=false&j.must=BR
     public static void printLenPagePatentInformation(WebDriver driver){
        //http://dbpedia.org/page/Electrostatics
            
            String patent_code = driver.findElement(By.xpath("/html/body/div[1]/section/section/header/div[1]/div/header/div[2]/div/div[2]/a")).getText();
            String title = driver.findElement(By.xpath("/html/body/div[1]/section/section/header/div[1]/div/header/div[1]/h2/a")).getText();
            String abstract_p = driver.findElement(By.xpath("/html/body/div[1]/section/section/section/div/div/section/main/div/p")).getText();
            String applicant = driver.findElement(By.xpath("/html/body/div[1]/section/section/section/div/div/section/main/div/div[3]/div[1]/ul/li/a")).getText();
            String inventors = "";
            
            for(int i=1;i<10;i++){
                try{
                inventors+= driver.findElement(By.xpath("/html/body/div[1]/section/section/section/div/div/section/main/div/div[3]/div[2]/ul/li["+1+"]/a")).getText()+";";
                }catch(Exception e){
                    if(i==1){
                        inventors+= driver.findElement(By.xpath("/html/body/div[1]/section/section/section/div/div/section/main/div/div[3]/div[2]/ul/li/a")).getText()+";";
                    }
                    break;
                }
            }
            
            
            
            String ipc = "";
            
            for(int i=1;i<10;i++){
                try{
                    ipc+= driver.findElement(By.xpath("/html/body/div[1]/section/section/section/div/div/section/main/div/div[3]/div[3]/ul/li["+i+"]/a")).getText()+";";
                }catch(Exception e){
                    if(i==1){
                        ipc+= driver.findElement(By.xpath("/html/body/div[1]/section/section/section/div/div/section/main/div/div[3]/div[3]/ul/li/a")).getText()+";";
                    }
                    break;
                }
            }
            System.out.println(patent_code+"##"+title+"##"+abstract_p+"##"+applicant+"##"+inventors+"##"+ipc);
    }
    
     
    public static void managePatentsExtraction(WebDriver driver){
        //http://dbpedia.org/page/Electrostatics
        System.setProperty("webdriver.chrome.driver", "chromeDriver.exe");
        WebDriver patent_page = new ChromeDriver();
        
        for(int i =9;i<10000;i++){
            driver.navigate().to("https://www.lens.org/lens/search/patent/list?p="+i+"&n=50&s=cited_by_patent_count&d=%2B&f=false&e=false&l=en&authorField=author&dateFilterField=publishedDate&orderBy=%2Bcited_by_patent_count&presentation=false&stemmed=true&useAuthorId=false&j.must=BR");
            try{
                Thread.sleep(12000);
            }catch(Exception e){
            }
            
            int j=0;
            if(i==5){
                j=0;
            }else{
                j=0;
            }
            for(;j<100;j++){
                try{
                            
                    String patent_page_path = driver.findElement(By.xpath("/html/body/div[2]/main/section/div/section/main/div[1]/div/div["+(j+1)+"]/header[1]/div[1]/div[1]/h3/a")).getAttribute("href");
                    patent_page.navigate().to(patent_page_path);
                    printLenPagePatentInformation(patent_page);
                    try{
                         Thread.sleep(13000);
                    }catch(Exception e){
                    }
                }catch(Exception e){
                    System.out.println("Error Extraction i: "+i+" j:"+j);
                }
            }
            
        }
    }
}
