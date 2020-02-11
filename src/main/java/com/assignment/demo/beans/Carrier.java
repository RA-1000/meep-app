package com.assignment.demo.beans;

import java.io.Serializable;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import javax.persistence.*;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "id",
        "name",
        "x",
        "y",
        "licencePlate",
        "range",
        "batteryLevel",
        "seats",
        "model",
        "resourceImageId",
        "pricePerMinuteParking",
        "pricePerMinuteDriving",
        "realTimeData",
        "engineType",
        "resourceType",
        "companyZoneId",
        "helmets"
})
@Entity
@Table(name = "carriers")
public class Carrier implements Serializable {
    @Id
    @GeneratedValue
    @Column(name = "id", nullable = false)
    private long id;

    @JsonProperty("id")
    @Column(name = "fid", length = 240, nullable = false)
    private String fid;

    @JsonProperty("name")
    @Column(name = "name", length = 240)
    private String name;

    @JsonProperty("x")
    @Column(name = "x", nullable = false)
    private double x;

    @JsonProperty("y")
    @Column(name = "y", nullable = false)
    private double y;

    @JsonProperty("licencePlate")
    @Column(name = "licencePlate", length = 240)
    private String licencePlate;

    @JsonProperty("range")
    @Column(name = "range")
    private int range;

    @JsonProperty("batteryLevel")
    @Column(name = "batteryLevel")
    private int batteryLevel;

    @JsonProperty("seats")
    @Column(name = "seats")
    private int seats;

    @JsonProperty("model")
    @Column(name = "model", length = 240)
    private String model;

    @JsonProperty("resourceImageId")
    @Column(name = "resourceImageId", length = 240)
    private String resourceImageId;

    @JsonProperty("pricePerMinuteParking")
    @Column(name = "pricePerMinuteParking")
    private double pricePerMinuteParking;

    @JsonProperty("pricePerMinuteDriving")
    @Column(name = "pricePerMinuteDriving")
    private double pricePerMinuteDriving;

    @JsonProperty("realTimeData")
    @Column(name = "realTimeData")
    private boolean realTimeData;

    @JsonProperty("engineType")
    @Column(name = "engineType", length = 240)
    private String engineType;

    @JsonProperty("resourceType")
    @Column(name = "resourceType", length = 240)
    private String resourceType;

    @JsonProperty("companyZoneId")
    @Column(name = "companyZoneId")
    private int companyZoneId;

    @JsonProperty("helmets")
    @Column(name = "helmets")
    private int helmets;

    private final static long serialVersionUID = -7486763420535659990L;

    /**
     * No args constructor for use in serialization
     */
    public Carrier() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @JsonProperty("id")
    public String getFid() {
        return fid;
    }

    @JsonProperty("id")
    public void setFid(String fid) {
        this.fid = fid;
    }

    @JsonProperty("name")
    public String getName() {
        return name;
    }

    @JsonProperty("name")
    public void setName(String name) {
        this.name = name;
    }

    @JsonProperty("x")
    public double getX() {
        return x;
    }

    @JsonProperty("x")
    public void setX(double x) {
        this.x = x;
    }

    @JsonProperty("y")
    public double getY() {
        return y;
    }

    @JsonProperty("y")
    public void setY(double y) {
        this.y = y;
    }

    @JsonProperty("licencePlate")
    public String getLicencePlate() {
        return licencePlate;
    }

    @JsonProperty("licencePlate")
    public void setLicencePlate(String licencePlate) {
        this.licencePlate = licencePlate;
    }

    @JsonProperty("range")
    public int getRange() {
        return range;
    }

    @JsonProperty("range")
    public void setRange(int range) {
        this.range = range;
    }

    @JsonProperty("batteryLevel")
    public int getBatteryLevel() {
        return batteryLevel;
    }

    @JsonProperty("batteryLevel")
    public void setBatteryLevel(int batteryLevel) {
        this.batteryLevel = batteryLevel;
    }

    @JsonProperty("seats")
    public int getSeats() {
        return seats;
    }

    @JsonProperty("seats")
    public void setSeats(int seats) {
        this.seats = seats;
    }

    @JsonProperty("model")
    public String getModel() {
        return model;
    }

    @JsonProperty("model")
    public void setModel(String model) {
        this.model = model;
    }

    @JsonProperty("resourceImageId")
    public String getResourceImageId() {
        return resourceImageId;
    }

    @JsonProperty("resourceImageId")
    public void setResourceImageId(String resourceImageId) {
        this.resourceImageId = resourceImageId;
    }

    @JsonProperty("pricePerMinuteParking")
    public double getPricePerMinuteParking() {
        return pricePerMinuteParking;
    }

    @JsonProperty("pricePerMinuteParking")
    public void setPricePerMinuteParking(double pricePerMinuteParking) {
        this.pricePerMinuteParking = pricePerMinuteParking;
    }

    @JsonProperty("pricePerMinuteDriving")
    public double getPricePerMinuteDriving() {
        return pricePerMinuteDriving;
    }

    @JsonProperty("pricePerMinuteDriving")
    public void setPricePerMinuteDriving(double pricePerMinuteDriving) {
        this.pricePerMinuteDriving = pricePerMinuteDriving;
    }

    @JsonProperty("realTimeData")
    public boolean isRealTimeData() {
        return realTimeData;
    }

    @JsonProperty("realTimeData")
    public void setRealTimeData(boolean realTimeData) {
        this.realTimeData = realTimeData;
    }

    @JsonProperty("engineType")
    public String getEngineType() {
        return engineType;
    }

    @JsonProperty("engineType")
    public void setEngineType(String engineType) {
        this.engineType = engineType;
    }

    @JsonProperty("resourceType")
    public String getResourceType() {
        return resourceType;
    }

    @JsonProperty("resourceType")
    public void setResourceType(String resourceType) {
        this.resourceType = resourceType;
    }

    @JsonProperty("companyZoneId")
    public int getCompanyZoneId() {
        return companyZoneId;
    }

    @JsonProperty("companyZoneId")
    public void setCompanyZoneId(int companyZoneId) {
        this.companyZoneId = companyZoneId;
    }

    @JsonProperty("helmets")
    public int getHelmets() {
        return helmets;
    }

    @JsonProperty("helmets")
    public void setHelmets(int helmets) {
        this.helmets = helmets;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Carrier carrier = (Carrier) o;
        return Objects.equals(fid, carrier.fid);
    }
}