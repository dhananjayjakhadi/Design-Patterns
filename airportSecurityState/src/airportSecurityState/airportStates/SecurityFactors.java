package airportSecurityState.airportStates;

import airportSecurityState.util.FileProcessor;

public class SecurityFactors {

    AirportStateI aState;
    String current_state="";
    public SecurityFactors() {
        setState(new LowRiskI(this));
    }

    public void setState(AirportStateI state) {
        this.aState = new LowRiskI(this);
    }
    
    public void setCurrentState(String state){
        this.current_state=state;
    }
    
    public AirportStateI getState(){
        return this.aState;
    }

    public String getCurrent_state() {
        return current_state;
    }

    public void tightenOrLoosenSecurity(String data, FileProcessor fp, int debugValue) {
        this.aState.setData(data);
        this.aState.setFileProcessor(fp);
        this.aState.setDebugValue(debugValue);
        this.aState.tightenOrLoosenSecurity();
    }
}