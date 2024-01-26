package com.cavisson.ata.services;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ExceptionService extends Thread
{
  int varclass = 88;
  String strclass = "cav";
  public int b = 100; //
  public static int c = 789; //
  private String str = "yash";
  private double dd = 67896; //
  private float ff = 67.887f; //
  public boolean bt = true;
  public static float fy = 76.97f; //
  public static String scav = "cavisson yash";

  public static String normalException()
  {
	  int i = 5 / 0;
   
      System.out.println("tryCatch() called");
    
    return "normal exception executed";
  }

  public static String nestedException() throws Exception
  {
    try
    {
      int i = 5 / 0;
    }
    catch(Exception e)
    {
      System.out.println("tryCatch called under Exception.");
      try
      {
        throw new IOException("new exception");
      }
      catch(Exception ex)
      {
    	  throw ex;
    	  
      }
    }
    return "nested exception executed";
  }

  public static String defUncaugtExcep(int choice)
  {
    String str = "";
    ExceptionService threadobj = new ExceptionService();

    ExceptionService.UncaughtExcep uexcp = threadobj.new UncaughtExcep();
    ExceptionService.defUncaughtExcep defuexcp = threadobj.new defUncaughtExcep();

    //		Registering both DefaultUncaughtExceptionHandler and UncaughtExceptionHandler
    if(choice == 1)
    {
      threadobj.setDefaultUncaughtExceptionHandler(defuexcp);
      threadobj.setUncaughtExceptionHandler(uexcp);
      str = " UncaughtException executed !";

      //	    Registering only DefaultUncaughtExceptionHandler
    }
    else if(choice == 2)
    {
      threadobj.setDefaultUncaughtExceptionHandler(defuexcp);
      //			threadobj.setUncaughtExceptionHandler(uexcp);         	
      str = " Default UncaughtException executed !";
    }
    threadobj.start();

    return str;

  }

  public void run()
  {
    generateArithExcep(10, 0);

  }

  public int generateArithExcep(int num1, int num2)
  {
    return num1 / num2;
  }

  class UncaughtExcep implements Thread.UncaughtExceptionHandler
  {

    @Override
    public void uncaughtException(Thread t, Throwable e)
    {
      // TODO Auto-generated method stub
      System.out.println("uncaught excep handler");
      e.printStackTrace();
      System.out.println("--------------------------");
    }
  }

  class defUncaughtExcep implements Thread.UncaughtExceptionHandler
  {

    @Override
    public void uncaughtException(Thread t, Throwable e)
    {
      // TODO Auto-generated method stub
      System.out.println("default excep handler");
      e.printStackTrace();
      System.out.println("--------------------------");
    }

  }

  public static String customException() throws Exception
  {
    ExceptionService sy = new ExceptionService();
    do_work(99.99, 67, "cavisson");
    return "custom mthd exception";
  }

  static void except() throws Exception
  {
    int a = 10, b = 100;
    String str = "ten";
    // System.out.println(" in throw excep "+a);
    throw new Exception("new-exception");
  }

  static void do_work(double d, int n, String str) throws Exception
  {

    int ch = 99, yye = 765, uur = 780;
    float fr = 6678.99f;
    double dd = 456.99;
    
      int zz = 1000;
      int hy = 889;
      except();
   
    
    String stnew = "new var";
    int r = 86;
  }

  public static String numberform()
  {
    double db = 67890.86;
    try
    {
      // "akki" is not a number
      int num = Integer.parseInt("akki");

      System.out.println(num);
    }
    catch(NumberFormatException e)
    {
      e.printStackTrace();
      // System.out.println("-----------------Number format exception");
    }
    return "number format exception";
  }

  public static String Stringindx()
  {
    float fl = 2.876f;
	
    String a = "This is like chipping "; // length is 22
      char c = a.charAt(24); // accessing 25th element
      System.out.println(c);
		
    return "String index out of bound exception";
  }

  void except_meth() throws Exception
  {
    int a = 10, b = 100;
    String str = "ten";
    int arr[] =
    { 95, 98, 55, 65, 77 };

    Map<String, String> mp = new HashMap<String, String>();

    String key = "key_cavisson";
    String val = "val_systems";

    mp.put(key, val);

    // System.out.println(" in throw excep "+a);
    throw new Exception("new-exception");

  }

  void doWork(double d, int n, String str) throws Exception
  {
    int ch = 99, yye = 765, uur = 780; //
    float fr = 6678.99f; //
    double dd = 456.99; //
	
      int zz = 1000; //
      int hy = 889; //
      except_meth();
    
    String stnew = "new var";
    int r = 86; //
  }

  public String method7()throws Exception
  {
    long starttime = System.currentTimeMillis();
    ExceptionService sy = new ExceptionService();
    doWork(99.99, 67, "cavisson");
    long endtime = System.currentTimeMillis();
    long exctime = endtime - starttime;
    System.out.println("execution time in app = " + exctime);
    return "custom mthd exception";
  }

}
