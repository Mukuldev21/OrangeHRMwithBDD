package hooks;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import java.io.File;
import java.io.IOException;

public class ExtentManager {

    private static ExtentReports extent;

    public static ExtentReports getInstance(){
        if(extent == null){
            //String reportPath = System.getProperty("user.dir") + "/test-output/ExtentReport.html";
            String reportPath = System.getProperty("user.dir") + "/test-output/SparkReport/Spark.html";
            ExtentSparkReporter spark= new ExtentSparkReporter(reportPath);
            try{
                spark.loadXMLConfig(new File("src/test/java/hooks/spark-config.xml"));

            }catch (IOException e){
                e.printStackTrace();
            }

            spark.config().setReportName("Orange HRM Automation Report");
            spark.config().setDocumentTitle("Extent Spark Dashboard");

            extent = new ExtentReports();
            extent.attachReporter(spark);
        }
        return extent;
    }
}
