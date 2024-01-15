package com.example.trainticketsystem_hashmapbeatstherest.object;

import com.example.trainticketsystem_hashmapbeatstherest.enums.Station;
import com.example.trainticketsystem_hashmapbeatstherest.enums.TrainType;

import java.util.List;

public class TrainSlot {
    private String id;
    private String code;
    private Station originCode;
    private Station destinationCode;
    private Long startTime;
    private Long duration;
    private TrainType type;

    private List<String> coaches;


    public TrainSlot() {
    }

    public TrainSlot(String id, String code, String originCode, String destinationCode, Long startTime, Long duration, String type) {
        this.id = id;
        this.code = code;
        this.originCode = Station.valueOf(originCode);
        this.destinationCode = Station.valueOf(destinationCode);
        this.startTime = startTime;
        this.duration = duration;
        this.type = TrainType.valueOf(type);
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
        return originCode.name();
    }


    public void setOriginCode(String originCode) {
        this.originCode = Station.valueOf(originCode);
    }

    public String getDestinationCode() {
        return destinationCode.name();
    }


    public void setDestinationCode(String destinationCode) {
        this.destinationCode = Station.valueOf(destinationCode);
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
        return type.name();
    }

    public void setType(String type) {
        this.type = TrainType.valueOf(type);
    }

    public List<String> getCoaches() {
        return coaches;
    }

    public void setCoaches(List<String> coaches) {
        this.coaches = coaches;
    }
}
