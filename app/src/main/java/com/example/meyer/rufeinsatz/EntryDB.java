package com.example.meyer.rufeinsatz;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

/**
 * Created by meyer on 30.12.2017.
 */
@Database(entities = {ItemEntry.class},version = 3)
public abstract class EntryDB extends RoomDatabase {
    public abstract DaoAccess daoAccess();
}
