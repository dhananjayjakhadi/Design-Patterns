package airportSecurityState.driver;

import airportSecurityState.airportStates.SecurityFactors;
import airportSecurityState.util.FileProcessor;
import airportSecurityState.util.MyLogger;

public class Driver {

    public static void main(String args[]) {
        SecurityFactors sf = new SecurityFactors();
        FileProcessor fp;

        if (args.length != 3) {
            MyLogger.setDebugValue(1);
            MyLogger.DebugLevel debugLevel = MyLogger.DebugLevel.IN_RUN;
            MyLogger.writeMessage("Arguments are missing, Enter Input File, Output File & Debug Value", debugLevel);
            System.exit(1);
        }

        int debuglvl = Integer.parseInt(args[2]);
        if (debuglvl < 0 || debuglvl > 4) {
            MyLogger.setDebugValue(1);
            MyLogger.DebugLevel debugLevel = MyLogger.DebugLevel.IN_RUN;
            MyLogger.writeMessage("Last Argument should be in 0-4 range", debugLevel);
            System.exit(1);
        }

        fp = new FileProcessor(args[0], args[1]);
        String everything = fp.readLine();
        if(everything.trim().length()==0){
            MyLogger.setDebugValue(1);
            MyLogger.DebugLevel debugLevel = MyLogger.DebugLevel.IN_RUN;
            MyLogger.writeMessage("Input file is empty", debugLevel);
            System.exit(1);
        }
        sf.tightenOrLoosenSecurity(everything, fp, debuglvl);
    }
}