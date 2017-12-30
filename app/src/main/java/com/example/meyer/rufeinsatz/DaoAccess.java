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
    void insertEntry(RufEinsatzEintrag rufEinsatzEintrag);

    @Delete
    void deleteEntry(RufEinsatzEintrag rufEinsatzEintrag);

    @Query("SELECT * FROM RufEinsatzEintrag")
    List<RufEinsatzEintrag> findEinsatz();

}
