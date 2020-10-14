package csci240.prinCad.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import csci240.prinCad.ui.SettingsInterface.LoggingLevel;


public class Log {
	
	private interface Logging {
		//Write error to log file
		public void error(String errorText);
		//Write exception to log file
		public void error(String text, Exception ex);
		//Write info to log file
		public void info(String infoText);
		
	}
	
	//No Logging
	private class LogNone implements Logging {
		//Do not write error to log file
		public void error(String errorText) {
		}
		//Do not write exception to log file
		public void error(String text, Exception ex) {
		}
		//Do not write info to log file
		public void info(String infoText) {
		}
	}
	
	//Error Logging
	private class LogError implements Logging {
		//Write error to log file
		public void error(String errorText) {
			write(errorText);
		}
		//Write exception to log file
		public void error(String text, Exception ex) {
			String errorText = String.format("%s %s", text, ex.toString());
			error(errorText);
		}
		//Do not write info to log file
		public void info(String infoText) {
		}
	}
	
	//Info Logging
	private class LogInfo implements Logging {
		//Write error to log file
		public void error(String errorText) {
			write("ERROR: " + errorText);
		}
		//Write exception to log file
		public void error(String text, Exception ex) {
			String errorText = String.format("%s %s", text, ex.toString());
			error("ERROR: " + errorText);
		}
		//Write info to log file
		public void info(String infoText) {
			write(infoText);
		}
	}
	
	//The one and only instance of Log object - singleton design pattern
	private static Log _instance = new Log();
	
	//The logging level instance - result of the factory design pattern
	private Logging _logging;
	
	//Private constructor to create the correct logging level object
	private Log() {
		//Factory design pattern
		//TODO: FIX
		LoggingLevel loggingLevel = csci240.prinCad.ui.SettingsInterface.defaultLoggingLevel;
		
		switch (loggingLevel) {
		case None:
			_logging = new LogNone();
			break;
		case Error:
			_logging = new LogError();
			break;
		case Information:
			_logging = new LogInfo();
			break;
		default:
			_logging = new LogError();	
		}
	}
	
	//Write error to log file
	public static void error(String errorText) {
		_instance._logging.error(errorText);
	}
	
	//Write exception to log file
	public static void error(String errorText, Exception ex) {
		_instance._logging.error(errorText, ex);
	}
	
	//Write info to log file
	public static void info(String infoText) {
		_instance._logging.info(infoText);
	}
	
	
	//Path to log file
	private final static String logFilePath = "PrinCad.log";
	
	//create formatter
	final static DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
	
	//Write text to log file
	private static void write (String text) {
		
		System.out.println(text);
		
		try {
			File file = new File(logFilePath);
			FileWriter fw = new FileWriter(file, true); // append to file
			BufferedWriter bw = new BufferedWriter(fw);
			PrintWriter out = new PrintWriter(bw, true);
			
			//Local date time instance
			LocalDateTime localDateTime = LocalDateTime.now();
			
			//Get formatted String
			String ldtString = dateTimeFormatter.format(localDateTime);
			
			out.println(String.format("%s - %s",  ldtString, text));
			
			out.flush();
			out.close();
		}
		catch(Exception e) {
			System.out.println(e);
		}
				
	}
	
}
