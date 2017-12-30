package com.example.meyer.rufeinsatz;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

/**
 * Created by meyer on 30.12.2017.
 */
@Entity
public class RufEinsatzEintrag {
    @PrimaryKey(autoGenerate = true)
    private int _id;

    public int get_id() {
        return _id;
    }

    public String get_datum() {
        return _datum;
    }

    public String get_dauer() {
        return _dauer;
    }

    public String get_einsatz() {
        return _einsatz;
    }

    @ColumnInfo(name = "datum")
    private String _datum;

    @ColumnInfo(name = "dauer")
    private String _dauer;

    @ColumnInfo(name = "einsatz")
    private String _einsatz;


    public void set_id(int _id) {
        this._id = _id;
    }

    public void set_datum(String _datum) {
        this._datum = _datum;
    }

    public void set_dauer(String _dauer) {
        this._dauer = _dauer;
    }

    public void set_einsatz(String _einsatz) {
        this._einsatz = _einsatz;
    }
}
