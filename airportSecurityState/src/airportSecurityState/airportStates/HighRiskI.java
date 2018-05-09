package airportSecurityState.airportStates;

import airportSecurityState.util.FileProcessor;
import airportSecurityState.util.MyLogger;

public class HighRiskI implements AirportStateI {

    SecurityFactors sf;
    AirportStateI asi;
    private String data = "";
    private int traffic_count = 0, risk_count = 0, debugValue = 0;
    private FileProcessor fp = null;
    HighRiskI(SecurityFactors sf) {
        this.sf = sf;
        MyLogger.writeMessage("HighRiskI constructor", MyLogger.DebugLevel.CONSTRUCTOR);
    }

    public void tightenOrLoosenSecurity() {
        //System.out.println("1 3 5 7 9");
        try {
            HelperClass helper = new HelperClass();
            helper.processData(data, traffic_count, risk_count, debugValue, fp, sf);
            /*if((helper.avg_traffic>=8) || (helper.avg_items>=2)){
             fp.append("2 3 6 8 10\n");
             asi=new HighRiskI(sf);
             }else if((helper.avg_traffic>=4 && helper.avg_traffic<8) || (helper.avg_items>=1 && helper.avg_items<2)){
             fp.append("2 3 5 8 9\n");
             asi=new ModerateRiskI(sf);
             }else if((helper.avg_traffic>0 && helper.avg_traffic<4) || helper.avg_items<1){
             fp.append("1 3 5 7 9\n");
             asi=new LowRiskI(sf);
             }*/
            asi = helper.getAirportState();
            asi.setData(helper.getData());
            asi.setTrafficCount(helper.getTraffic());
            asi.setRiskCount(helper.getRisk());
            asi.setDebugValue(helper.getDebug_value());
            asi.setFileProcessor(fp);
            asi.tightenOrLoosenSecurity();
        } catch (Exception ex) {
        }
    }

    @Override
    public void setState(AirportStateI asi) {
        this.asi = asi;
    }

    @Override
    public void setData(String data) {
        this.data = data;
    }

    @Override
    public void setTrafficCount(int traffic_count) {
        this.traffic_count = traffic_count;
    }

    @Override
    public void setRiskCount(int risk_count) {
        this.risk_count = risk_count;
    }

    @Override
    public void setFileProcessor(FileProcessor fp) {
        this.fp = fp;
    }

    @Override
    public void setDebugValue(int debugValue) {
        this.debugValue = debugValue;
    }
}