package com.example.meyer.rufeinsatz;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

/**
 * Created by meyer on 30.12.2017.
 */
@Entity
public class ItemEntry {

    @PrimaryKey(autoGenerate = true)
    private int _id;

    public int get_id() {
        return _id;
    }

    public String getDate() {
        return Date;
    }

    public String getDuration() {
        return Duration;
    }

    public String getTask() {
        return Task;
    }

    public boolean getAbgerechnet() {
        return Abgerechnet;
    }

    public String getDayType() {
        return DayType;
    }

    @ColumnInfo(name = "datum")
    private String Date;

    @ColumnInfo(name = "dauer")
    private String Duration;

    @ColumnInfo(name = "einsatz")
    private String Task;

    @ColumnInfo(name = "abgerechnet")
    private boolean Abgerechnet;

    @ColumnInfo(name="DayType")
    private String DayType;

    public void set_id(int _id) {
        this._id = _id;
    }

    public void setDate(String _datum) {
        this.Date = _datum;
    }

    public void setDuration(String _dauer) {
        this.Duration = _dauer;
    }

    public void setTask(String _Task) {
        this.Task = _Task;
    }

    public void setAbgerechnet(boolean _Abgerechnet) {
        this.Abgerechnet = _Abgerechnet;
    }

    public void setDayType(String _DayType) {
        this.DayType = _DayType;
    }
}
