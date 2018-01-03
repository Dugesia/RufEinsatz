package com.example.meyer.rufeinsatz;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

/**
 * Created by meyer on 30.12.2017.
 */
@Dao
public interface DaoAccess {
    @Insert
    void insertEntry(ItemEntry itemEntry);

    @Delete
    void deleteEntry(ItemEntry itemEntry);

    @Query("SELECT * FROM ItemEntry")
    List<ItemEntry> getAll();

}
