package airportSecurityState.airportStates;

import airportSecurityState.util.FileProcessor;

public interface AirportStateI{
        public String data="";
        public int traffic_count=0;
        public int risk_count=0;
        public FileProcessor fp=null;
        public int debugValue=0;
        
        public void setState(AirportStateI asi);
        public void setData(String data);
        public void setTrafficCount(int traffic_count);
        public void setRiskCount(int risk_count);
        public void setFileProcessor(FileProcessor fp);
        public void setDebugValue(int debugValue);
	public void tightenOrLoosenSecurity();
}