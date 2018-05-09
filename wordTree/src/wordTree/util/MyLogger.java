package wordTree.util;

public class MyLogger{
    /*
    DEBUG_VALUE=4 [Print to stdout everytime a constructor is called]
    DEBUG_VALUE=3 [Print to stdout everytime a thread's run() method is called]
    DEBUG_VALUE=2 [YOU DECIDE and explain in README.txt]
    DEBUG_VALUE=1 [YOU DECIDE and explain in README.txt]
    DEBUG_VALUE=0 [No output should be printed from the application, except the line "The average preference value is X.Y" ] 
    */

    public static enum DebugLevel {NO_OUTPUT, ERROR, DEBUG_VALUE, CONSTRUCTOR, THREAD_RUN};

    private static DebugLevel debugLevel;


    public static void setDebugValue (int levelIn) {
		switch (levelIn) {
		  case 4: debugLevel = DebugLevel.CONSTRUCTOR; break;
		  case 3: debugLevel = DebugLevel.THREAD_RUN; break;  
		  case 2: debugLevel = DebugLevel.DEBUG_VALUE; break;
		  case 1: debugLevel = DebugLevel.ERROR; break;
		  case 0: debugLevel = DebugLevel.NO_OUTPUT; break;
		}
    }

    public static void setDebugValue (DebugLevel levelIn) {
	debugLevel = levelIn;
    }
	
	public static DebugLevel getDebugValue(int levelIn){
	  if(levelIn== 4){ 
		return DebugLevel.CONSTRUCTOR;
	  }else if(levelIn== 3){ 
		return DebugLevel.THREAD_RUN; 
	  }else if(levelIn== 2){ 
		return DebugLevel.DEBUG_VALUE; 
	  }else if(levelIn== 1){
		return DebugLevel.ERROR; 
	  }else if(levelIn== 0){ 
		return DebugLevel.NO_OUTPUT; 
	  } 
	  return null;
	}

    // @return None
    public static void writeMessage (String  message  ,
                                     DebugLevel levelIn ) {
        if (levelIn == debugLevel && levelIn!=DebugLevel.NO_OUTPUT)
	    System.out.println(message);
    }

    /**
	 * @return String
	 */
    public String toString() {
	return "Debug Level is " + debugLevel;
    }
}
