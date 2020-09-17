package attitude.designs.blackturtleassignment.repo

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import attitude.designs.blackturtleassignment.models.Photo


@Database(entities = [Photo::class], version = 1 ,exportSchema = false)
abstract class MyDatabase : RoomDatabase() {

    abstract fun repoDao(): PhotoDao

    companion object {

        private var appDatabase: MyDatabase? = null


        fun getInstance(context: Context): MyDatabase {
            if (appDatabase == null) {
                appDatabase =
                    Room.databaseBuilder(context.getApplicationContext(), MyDatabase::class.java, "YourDatabaseName.db")
                        .allowMainThreadQueries()
                        .fallbackToDestructiveMigration()
                        .build()
            }
            return appDatabase!!
        }
    }

}
