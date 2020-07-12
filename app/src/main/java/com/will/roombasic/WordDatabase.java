package com.will.roombasic;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Word.class},version = 1,exportSchema = false)
public abstract class WordDatabase extends RoomDatabase {
    public abstract WordDao getWordDao();

    private volatile static WordDatabase INSTANCE;
    private static Byte[] bytes = new Byte[9];

    public static WordDatabase getInstance(Context context){
        if (null == INSTANCE) {
            synchronized (bytes){
                if (null == INSTANCE) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),WordDatabase.class,"word_database")
                            .build();
                }
            }
        }

        return INSTANCE;
    }

}
