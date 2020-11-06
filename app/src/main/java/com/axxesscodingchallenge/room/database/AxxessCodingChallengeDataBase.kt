package com.axxesscodingchallenge.room.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.axxesscodingchallenge.room.entity.Comment
import com.axxesscodingchallenge.utils.Constants


@Database(
    entities = [Comment::class],
    version = 4,
    exportSchema = false
)
abstract class AxxessCodingChallengeDataBase : RoomDatabase() {

    //abstract fun axxessDao(): AxxessCodingChallengeDataBase

    companion object {
        private var INSTANCE: AxxessCodingChallengeDataBase? = null
        fun getDatabase(context: Context): AxxessCodingChallengeDataBase? {
            if (INSTANCE == null) {
                synchronized(AxxessCodingChallengeDataBase::class) {
                    INSTANCE =
                        buildDatabase(
                            context
                        )
                }
            }
            return INSTANCE
        }

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                AxxessCodingChallengeDataBase::class.java,
                Constants.ROOM_DB_NAME
            )
                .fallbackToDestructiveMigration()
                .allowMainThreadQueries()
                .build()
    }
}

