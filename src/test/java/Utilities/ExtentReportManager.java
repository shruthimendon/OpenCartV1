package Utilities;


	import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
	import java.util.Date;
import java.util.List;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.ImageHtmlEmail;
import org.apache.commons.mail.resolver.DataSourceUrlResolver;
import org.apache.logging.log4j.core.tools.picocli.CommandLine.Help.Ansi.Text;
import org.testng.ITestContext;
	import org.testng.ITestListener;
	import org.testng.ITestResult;

	import com.aventstack.extentreports.ExtentReports;
	import com.aventstack.extentreports.ExtentTest;
	import com.aventstack.extentreports.Status;
	import com.aventstack.extentreports.reporter.ExtentSparkReporter;
	import com.aventstack.extentreports.reporter.configuration.Theme;

import net.bytebuddy.implementation.bytecode.constant.TextConstant;
import testBase.BaseClass;

	public class ExtentReportManager implements ITestListener
	{
		public ExtentSparkReporter sparkReporter;
		public ExtentReports extent;
		public ExtentTest test;
		
		String repName;
		
		public void onStart(ITestContext testContext)
	
		{
			/*SimpleDateFormat df=new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss");
			Date dt=new Date();
			String currenttimestamp=df.format(dt);
			*/ 	 	
			String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
			repName = "Test-Report-"+timeStamp +".html";
			
			sparkReporter = new ExtentSparkReporter(".\\reports\\"+repName);
			
			sparkReporter.config().setDocumentTitle("OpenCartProject");
			sparkReporter.config().setReportName("Opencart");
			sparkReporter.config().setTheme(Theme.DARK);
			
			extent = new ExtentReports();
			extent.attachReporter(sparkReporter);
			extent.setSystemInfo("Application", "User API");
			extent.setSystemInfo("OS", System.getProperty("os.name"));
			extent.setSystemInfo("User Name", System.getProperty("user.name"));
			extent.setSystemInfo("Env", "QA");
			extent.setSystemInfo("tester", "SM");
			
			String os =testContext.getCurrentXmlTest().getParameter("os");
			extent.setSystemInfo("Operating system", os);
			
			String browser =testContext.getCurrentXmlTest().getParameter("browser");
			extent.setSystemInfo("Browser", os);
			
			
			List<String> includedGroups=testContext.getCurrentXmlTest().getIncludedGroups();
			if(includedGroups.isEmpty()){
				extent.setSystemInfo("Groups", includedGroups.toString());
			}
			
			{
				
			}
			
		}
		
		public void onTestSuccess(ITestResult result)
		{
			test = extent.createTest(result.getName());
			test.assignCategory(result.getMethod().getGroups());
			test.log(Status.PASS,result.getName()+"got successfully executed");
		}
		
		public void onTestFailure(ITestResult result)
		{
			test = extent.createTest(result.getName());
			test.assignCategory(result.getMethod().getGroups());
			test.log(Status.FAIL,result.getName()+"got failed");
			test.log(Status.INFO,result.getThrowable().getMessage());
			
			try
			{
				String imgPath =new BaseClass().CaptureScreen(result.getName());
				test.addScreenCaptureFromPath(imgPath);
			}catch(IOException e1)
			{
				e1.printStackTrace();
			}
			
		}
		public void onTestSkipped(ITestResult result)
		{
			test = extent.createTest(result.getName());
			test.assignCategory(result.getMethod().getGroups());
			test.log(Status.SKIP,result.getName()+"got skipped");
			test.log(Status.INFO,result.getThrowable().getMessage());
		}
	    public void onFinish(ITestContext testContext)
	    {
	    	extent.flush(); 	
	    	
	    	String pathofExtentReport = System.getProperty("user.dir")+"\\reports"+repName;
	    	File extentReport =new File(pathofExtentReport);
	    	
	    	try {
	    		Desktop.getDesktop().browse(extentReport.toURI());
	    	}catch(IOException e)
	    	{
	    		e.printStackTrace();
	    	}
	    	
	    	try {
	    		URL url = new URL("file:///"+System.getProperty("user.dir")+"\\reports\\"+repName);
	    		
	    		//Create the email message 
	    		
	    		ImageHtmlEmail email = new ImageHtmlEmail();
	    		email.setDataSourceResolver(new DataSourceUrlResolver(url));
	    		email.setHostName("smtp.googleemail.com");
	    		email.setSmtpPort(465);
	    		email.setAuthenticator(new DefaultAuthenticator("pavanoltraning@gmail.com" , "password"));
	    		email.setSSLOnConnect(true);
	    		email.setFrom("pavanoltraining@gmail.com");//sender
	    		email.setSubject("Test Results");
	    		email.setMsg("Pleasde find Attached Report ......");
	    		email.addTo("pavankumar.busyqa@gmail.com");//Receiver
	    		email.attach(url,"extent report", "please check report...");
	    		email.send(); //send the email 
	    		
	    	}
	    	catch(Exception e) {
	    		e.printStackTrace();
	    	}
	    		
	    }

		@Override
		public void onTestStart(ITestResult result) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
			// TODO Auto-generated method stub
			
		}
	}

