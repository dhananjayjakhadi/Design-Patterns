
package airportSecurityState.util;

public class MyLogger{

    /*DEBUG_VALUE=4 [Print to stdout everytime a constructor is called]
      DEBUG_VALUE=3 [Print to stdout everytime the state is changed]
      DEBUG_VALUE=2 [FIXME: add your own scheme here]
      DEBUG_VALUE=1 [FIXME: add your own scheme here]
      DEBUG_VALUE=0 [No output should be printed from the applicatio to stdout. It is ok to write to the output file though" ]
    */

    public static enum DebugLevel {RELEASE, FROM_RESULTS, IN_RESULTS, IN_RUN, CONSTRUCTOR
                                   };

    private static DebugLevel debugLevel;


    public static void setDebugValue (int levelIn) {
		switch (levelIn) {
		  case 4: debugLevel = DebugLevel.CONSTRUCTOR; break;
		  case 3: debugLevel = DebugLevel.FROM_RESULTS; break;  
		  case 2: debugLevel = DebugLevel.IN_RESULTS; break;
		  case 1: debugLevel = DebugLevel.IN_RUN; break;
		  case 0: debugLevel = DebugLevel.RELEASE; break;
		}
    }

    public static void setDebugValue (DebugLevel levelIn) {
	debugLevel = levelIn;
    }
	
	public static DebugLevel getDebugValue(int levelIn){
	  if(levelIn== 4){ 
		return DebugLevel.CONSTRUCTOR;
	  }else if(levelIn== 3){ 
		return DebugLevel.FROM_RESULTS; 
	  }else if(levelIn== 2){ 
		return DebugLevel.IN_RESULTS; 
	  }else if(levelIn== 1){
		return DebugLevel.IN_RUN; 
	  }else if(levelIn== 0){ 
		return DebugLevel.RELEASE; 
	  } 
	  return null;
	}

    // @return None
    public static void writeMessage (String  message  ,
                                     DebugLevel levelIn ) {
        if (levelIn == debugLevel && levelIn!=DebugLevel.RELEASE)
	    System.out.println(message);
    }

    /**
	 * @return String
	 */
    public String toString() {
	return "Debug Level is " + debugLevel;
    }
}
