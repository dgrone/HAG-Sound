package HAGSound.Global;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Random;

public class global {
	static boolean _ExecutionVollZugange = false;
	
	public static String NowDate() {
		return NowDate("yyyy.MM.dd - HH:mm:ss ");
	}
	
	public static String NowDate(String aFormat) {
        SimpleDateFormat formatter = new SimpleDateFormat(aFormat);
        Date currentTime = new Date();
        return (formatter.format(currentTime));
    }
	
	public static int Rand(int min, int max) {
		if (min >= max) {
			throw new IllegalArgumentException("max must be greater than min");
		}
		Random r = new Random();
		return r.nextInt((max - min) + 1) + min;
	}
	
	public static boolean Between(double i, double minValueInclusive, double maxValueInclusive) {
	    return (i >= minValueInclusive && i <= maxValueInclusive);
	}
	
	public static void WriteOutput(String aStr) {
		LocalDateTime lDate = LocalDateTime.now();
		DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
		System.out.println(lDate.format(myFormatObj) + ": " + aStr);
	}
	
	public static void Sleep(int inMS) {
		try {
			Thread.sleep(inMS);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
