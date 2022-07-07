package friendoo.calculation.utils;

public class TimeConverter {
    public static TimeUnits convert(Long timeInSecond){
        if (timeInSecond <60) return TimeUnits.MINUTE;
        if (timeInSecond >60 && timeInSecond < 3600)  return TimeUnits.HOUR;
        return TimeUnits.DAY;
    }
}
