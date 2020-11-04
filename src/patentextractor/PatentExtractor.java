/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package patentextractor;

import LensExtractor.LensConnector;
import InpiExtractor.InpiExtractor;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

/**
 *
 * @author Carlos
 */
public class PatentExtractor {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver", "chromeDriver.exe");
        WebDriver patent_extractor = new ChromeDriver();
        PatentScopeExtractor.PatentScopeExtractor.managePatentsExtraction(patent_extractor);
    }
    
}
