package data_manager;

import java.sql.Timestamp;

public class Flight {

    /*FLIGHT DURATIONS:
    LNK TO IOW = 01:45
    LNK TO EVA = 01:45
    LNK TO LAF = 01:45
    IOW TO EVA = 01:05
    IOW TO LAF = 01:00
    EVA TO LAF = 00:22
    REMEMBER THAT ANYTIME YOU FLY TO OR FROM LAF YOU HAVE TO ADD OR SUBTRACT AN HOUR APPROPRIATELY

    WEATHER KEYWORDS ARE Partly Cloudy, Cloudy, Sunny, Rainy, Thunderstorm, Windy, Foggy, and Snowy */

    private int flightID;
    private String planeNum;
    private String flightNum;
    private String weather;
    private String originAirport;
    private String destAirport;
    private Timestamp scheduledDeparture;
    private Timestamp estimatedDeparture;
    private Timestamp actualDeparture;
    private Timestamp scheduledArrival;
    private Timestamp estimatedArrival;
    private Timestamp actualArrival;
    private String status;
    private String created;

    // Default constructor.
    public Flight () {
    }

    public Flight(int flightID, String planeNum, String flightNum, String weather, String originAirport,
                  String destAirport, Timestamp scheduledDeparture, Timestamp estimatedDeparture,
                  Timestamp actualDeparture, Timestamp scheduledArrival, Timestamp estimatedArrival,
                  Timestamp actualArrival, String status, String created) {
        this.flightID = flightID;
        this.weather = weather;
        this.planeNum = planeNum;
        this.flightNum = flightNum;
        this.originAirport = originAirport;
        this.destAirport = destAirport;
        this.actualDeparture = actualDeparture;
        this.scheduledDeparture = scheduledDeparture;
        this.estimatedDeparture = estimatedDeparture;
        this.scheduledArrival = scheduledArrival;
        this.estimatedArrival = estimatedArrival;
        this.actualArrival = actualArrival;
        this.status = status;
        this.created = created;
    }

    public Flight (String flightNum, String planeNum, String weather, String originAirport,
                   String destAirport, Timestamp scheduledDeparture, Timestamp estimatedDeparture,
                   Timestamp actualDeparture, Timestamp scheduledArrival, Timestamp estimatedArrival,
                   Timestamp actualArrival, String status, String created) {
        this.flightNum = flightNum;
        this.planeNum = planeNum;
        this.weather = weather;
        this.originAirport = originAirport;
        this.destAirport = destAirport;
        this.scheduledDeparture = scheduledDeparture;
        this.estimatedDeparture = estimatedDeparture;
        this.actualDeparture = actualDeparture;
        this.scheduledArrival = scheduledArrival;
        this.estimatedArrival = estimatedArrival;
        this.actualArrival = actualArrival;
        this.status = status;
        this.created = created;
    }

    public int getFlightID() {
        return flightID;
    }

    public void setFlightId(int flightId) {
        this.flightID = flightId;
    }

    public String getWeather() {
        return weather;
    }

    public void setWeather(String weather) {
        this.weather = weather;
    }

    public String getPlaneNum() {
        return planeNum;
    }

    public void setAirplanesID(String planeNum) {
        this.planeNum = planeNum;
    }

    public String getFlightNum() {
        return flightNum;
    }

    public void setFlightNum(String flightNum) {
        this.flightNum = flightNum;
    }

    public String getOriginAirport() {
        return originAirport;
    }

    public void setOriginAirport(String originAirport) {
        this.originAirport = originAirport;
    }

    public String getdestAirport() {
        return destAirport;
    }

    public void setdestAirport(String destAirport) {
        this.destAirport = destAirport;
    }

    public Timestamp getScheduledDeparture() {
        return scheduledDeparture;
    }

    public void setScheduledDeparture(Timestamp scheduledDeparture) {
        this.scheduledDeparture = scheduledDeparture;
    }

    public Timestamp getEstimatedDeparture() {
        return estimatedDeparture;
    }

    public void setEstimatedDeparture(Timestamp estimatedDeparture) {
        this.estimatedDeparture = estimatedDeparture;
    }

    public Timestamp getActualDeparture() {
        return actualDeparture;
    }

    public void setActualDeparture(Timestamp actualDeparture) {
        this.actualDeparture = actualDeparture;
    }

    public Timestamp getScheduledArrival() {
        return scheduledArrival;
    }

    public void setScheduledArrival(Timestamp scheduledArrival) {
        this.scheduledArrival = scheduledArrival;
    }

    public Timestamp getEstimatedArrival() {
        return estimatedArrival;
    }

    public void setEstimatedArrival(Timestamp estimatedArrival) {
        this.estimatedArrival = estimatedArrival;
    }

    public Timestamp getActualArrival() {
        return actualArrival;
    }

    public void setActualArrival(Timestamp actualArrival) {
        this.actualArrival = actualArrival;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    @Override
    public String toString() {
        return "Flight{" +
                "flightID=" + flightID +
                ", planeNum=" + planeNum +
                ", flightNum='" + flightNum + '\'' +
                ", weather='" + weather + '\'' +
                ", originAirport='" + originAirport + '\'' +
                ", destAirport='" + destAirport + '\'' +
                ", scheduledDeparture=" + scheduledDeparture +
                ", estimatedDeparture=" + estimatedDeparture +
                ", actualDeparture=" + actualDeparture +
                ", scheduledArrival=" + scheduledArrival +
                ", estimatedArrival=" + estimatedArrival +
                ", actualArrival=" + actualArrival +
                ", status='" + status + '\'' +
                ", created='" + created + '\'' +
                '}';
    }
}