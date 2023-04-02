package com.ClienteApiRestSnider.Helpers;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class TimeResponse {
    public LocalDate currentDateTime;
    public String utcOffset;
    public boolean isDayLightSavingsTime;
    public String dayOfTheWeek;
    public String timeZoneName;

    public TimeResponse(LocalDate currentDateTime, String utcOffset, boolean isDayLightSavingsTime, String dayOfTheWeek, String timeZoneName) {
        this.currentDateTime = currentDateTime;
        this.utcOffset = utcOffset;
        this.isDayLightSavingsTime = isDayLightSavingsTime;
        this.dayOfTheWeek = dayOfTheWeek;
        this.timeZoneName = timeZoneName;
    }
    public TimeResponse(){}
}
