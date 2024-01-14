package com.example.trainticketsystem_hashmapbeatstherest.object;

public class TrainSlot {
    private String id;
    private String code;
    private String originCode;
    private String destinationCode;
    private Long startTime;
    private Long duration;
    private String type;

    public TrainSlot() {
    }

    public TrainSlot(String id, String code, String originCode, String destinationCode, Long startTime, Long duration, String type) {
        this.id = id;
        this.code = code;
        this.originCode = originCode;
        this.destinationCode = destinationCode;
        this.startTime = startTime;
        this.duration = duration;
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getOriginCode() {
        return originCode;
    }

    public void setOriginCode(String originCode) {
        this.originCode = originCode;
    }

    public String getDestinationCode() {
        return destinationCode;
    }

    public void setDestinationCode(String destinationCode) {
        this.destinationCode = destinationCode;
    }

    public Long getStartTime() {
        return startTime;
    }

    public void setStartTime(Long startTime) {
        this.startTime = startTime;
    }

    public Long getDuration() {
        return duration;
    }

    public void setDuration(Long duration) {
        this.duration = duration;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
