package attitude.designs.blackturtleassignment.repo

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import attitude.designs.blackturtleassignment.models.Photo


@Dao
public  interface PhotoDao {

    @Insert
    fun insert(vararg photos: Photo?)

    @Query("SELECT * FROM photos")
    fun getAllOperations(): List<Photo?>?
}