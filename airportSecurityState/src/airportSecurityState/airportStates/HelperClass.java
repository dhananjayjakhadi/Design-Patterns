/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package airportSecurityState.airportStates;

import airportSecurityState.util.FileProcessor;
import airportSecurityState.util.MyLogger;
import java.util.HashMap;
import java.util.StringTokenizer;

/**
 *
 * @author User
 */
public class HelperClass {
    HashMap<String, String> hm = new HashMap<String, String>();
    int traffic = 0, risk_count = 0, debugValue=0;
    float avg_traffic = 0f, avg_items = 0f;
    String data = "";
    AirportStateI asi = null;

    public String getData() {
        return data;
    }

    public int getTraffic() {
        return traffic;
    }

    public int getRisk() {
        return risk_count;
    }

    public int getDebug_value() {
        return debugValue;
    }
    
    public AirportStateI getAirportState() {
        return asi;
    }
    
    public void setAirportState(AirportStateI asi){
        this.asi=asi;
    }

    public void processData(String data, int traffic, int risk_count, int debugValue, FileProcessor fp, SecurityFactors sf) {
        this.data = data;
        this.traffic = traffic;
        this.risk_count = risk_count;
        this.debugValue=debugValue;
        hm.put("Gun", "Gun");
        hm.put("NailCutter", "NailCutter");
        hm.put("Blade", "Blade");
        hm.put("Knife", "Knife");
        try {
            try {
                if (data.trim().length() == 0) {
                    System.exit(0);
                }
                StringTokenizer st = new StringTokenizer(data);
                String day_str = "", old_state = "";
                String output = "";
                //while(st.hasMoreTokens())
                {
                    StringTokenizer st2 = new StringTokenizer(st.nextToken("\n"));
                    String line1 = st2.nextToken();
                    StringTokenizer st3 = new StringTokenizer(line1);
                    String str_day = st3.nextToken(";");
                    String Day = str_day.substring(str_day.indexOf(":") + 1);
                    //System.out.println("day "+Day);
                    //traffic++;
                    if (day_str.equals(str_day)) {
                        //day_count++;
                        this.traffic++;
                    } else {
                        //day_count=1;
                        //risk_count=0;
                        this.traffic = 0;
                    }

                    day_str = str_day;

                    String str_tod = st3.nextToken(";");
                    String TOD = str_tod.substring(str_tod.indexOf(":") + 1);

                    String str_airline = st3.nextToken(";");
                    String Airline = str_airline.substring(str_airline.indexOf(":") + 1);

                    String str_item = st3.nextToken(";");
                    String Item = str_item.substring(str_item.indexOf(":") + 1);

                    if (hm.containsKey(Item)) {
                        this.risk_count++;
                    }

                    this.avg_traffic = this.AverageTraficPerDay(this.traffic, Integer.parseInt(Day));
                    this.avg_items = this.AverageProhibitedItemsPerDay(this.risk_count, Integer.parseInt(Day));

                    String current_state=sf.getCurrent_state();
                    if ((this.avg_traffic >= 8) || (this.avg_items >= 2)) {
                        sf.setCurrentState("HIGH_RISK");
                        if (!sf.getCurrent_state().equals(current_state)) {
                            MyLogger.setDebugValue(debugValue);
                            MyLogger.DebugLevel debugLevel = MyLogger.DebugLevel.FROM_RESULTS;
                            MyLogger.writeMessage("State Changed to High Risk", debugLevel);
                        }
                        MyLogger.writeMessage("2 3 6 8 10\n", MyLogger.DebugLevel.IN_RESULTS);
                        fp.append("2 3 6 8 10\n");
                        this.asi = new HighRiskI(sf);
                    } else if ((this.avg_traffic >= 4 && this.avg_traffic < 8) || (this.avg_items >= 1 && this.avg_items < 2)) {
                        sf.setCurrentState("MODERATE_RISK");
                        if (!sf.getCurrent_state().equals(current_state)) {
                            MyLogger.setDebugValue(debugValue);
                            MyLogger.DebugLevel debugLevel = MyLogger.DebugLevel.FROM_RESULTS;
                            MyLogger.writeMessage("State Changed to Moderate Risk", debugLevel);
                        }
                        MyLogger.writeMessage("2 3 5 8 9\n", MyLogger.DebugLevel.IN_RESULTS);
                        fp.append("2 3 5 8 9\n");
                        this.asi = new ModerateRiskI(sf);
                    } else if ((this.avg_traffic >= 0 && this.avg_traffic < 4) || this.avg_items < 1) {
                        sf.setCurrentState("LOW_RISK");
                        if (!sf.getCurrent_state().equals(current_state)) {
                            MyLogger.setDebugValue(debugValue);
                            MyLogger.DebugLevel debugLevel = MyLogger.DebugLevel.FROM_RESULTS;
                            MyLogger.writeMessage("State Changed to Moderate Risk", debugLevel);
                        }
                        MyLogger.writeMessage("1 3 5 7 9\n", MyLogger.DebugLevel.IN_RESULTS);
                        fp.append("1 3 5 7 9\n");
                        this.asi = new LowRiskI(sf);
                    }
                    
                    //System.out.println(current_state);
                    //System.out.println(sf.getCurrent_state());
                    
                }
                this.data = data.substring(data.indexOf("\n") + 1);
            } catch (Exception ex1) {
                MyLogger.DebugLevel debugLevel = MyLogger.DebugLevel.IN_RUN;
                MyLogger.writeMessage("Exception " + ex1, debugLevel);
                System.exit(1);
            } finally {
            }
        } catch (Exception ex) {
            MyLogger.DebugLevel debugLevel = MyLogger.DebugLevel.IN_RUN;
            MyLogger.writeMessage("Exception1 " + ex.toString(), debugLevel);
            System.exit(1);
        }
    }

    public float AverageTraficPerDay(int number_of_travellers, int number_of_days) {
        return number_of_travellers / number_of_days;
    }

    public float AverageProhibitedItemsPerDay(int number_of_prohibited_item, int number_of_days) {
        return number_of_prohibited_item / number_of_days;
    }
}
