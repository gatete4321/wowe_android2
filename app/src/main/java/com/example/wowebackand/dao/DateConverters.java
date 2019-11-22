package com.example.wowebackand.dao;

import java.util.Date;

import androidx.room.TypeConverter;

/**
 * @gatete rugamba
 * for converting non primitive data types for romm
 */
public class DateConverters {
    @TypeConverter
    public long convertDateToLong(Date date){
        return date.getTime();
    }
    @TypeConverter
    public Date convertLongToDate(long time){
        return new Date(time);
    }
}
