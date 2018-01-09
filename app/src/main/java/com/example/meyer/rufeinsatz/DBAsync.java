package com.example.meyer.rufeinsatz;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.migration.Migration;
import android.content.ClipData;
import android.os.AsyncTask;

import java.util.List;

/**
 * Created by meyer on 01.01.2018.
 */

public class DBAsync {
    EntryDB db;

    public DBAsync(EntryDB db) {
        this.db = db;
    }

    public void delete(ItemEntry itemEntry)
    {
        new ASyncDelete().execute(itemEntry);
    }

    public void insert(ItemEntry itemEntry){
        new ASyncInsert().execute(itemEntry);
    }

    public List<ItemEntry> getAll(){
        List<ItemEntry> itemEntries=null;
        try {
            itemEntries=new ASyncGetAll().execute().get();
        }catch (Exception ex)
        {

        }
        return itemEntries;

    }

    private class ASyncGetAll extends AsyncTask<Void,Void,List<ItemEntry>>{
        @Override
        protected List<ItemEntry> doInBackground(Void... voids) {
            return db.daoAccess().getAll();
        }
    }

    private class ASyncDelete extends AsyncTask<ItemEntry, Void, Void>
    {
        @Override
        protected Void doInBackground(ItemEntry... itemEntries) {
            db.daoAccess().deleteEntry(itemEntries[0]);
            return null;
        }
    }
    private class ASyncInsert extends AsyncTask<ItemEntry,Void,Void>{

        @Override
        protected Void doInBackground(ItemEntry... itemEntries) {
            db.daoAccess().insertEntry(itemEntries[0]);
            return null;
        }
        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
        }
    }

    static final Migration MIGRATION_1_2 = new Migration(1, 2) {
        @Override
        public void migrate(SupportSQLiteDatabase database) {
            //database.execSQL("CREATE TABLE `Fruit` (`id` INTEGER, "
            //        + "`name` TEXT, PRIMARY KEY(`id`))");
            database.execSQL("CREATE TABLE 'ItemEntry' ('_id' INTEGER,'datum' varchar(20),'dauer' varchar(10),'einsatz' Text ,Primary KEY('_id')");
        }
    };

    static final Migration MIGRATION_2_3 = new Migration(2, 3) {
        @Override
        public void migrate(SupportSQLiteDatabase database) {

        }
    };

    static final Migration MIGRATION_3_4 = new Migration(3, 4) {
        @Override
        public void migrate(SupportSQLiteDatabase database) {

        }
    };

}
