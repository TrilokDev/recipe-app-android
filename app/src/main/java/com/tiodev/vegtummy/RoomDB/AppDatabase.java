package com.tiodev.vegtummy.RoomDB;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {User.class}, exportSchema = false, version = 3)
public abstract class AppDatabase extends RoomDatabase {
    public abstract UserDao userDao();
}
