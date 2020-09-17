package attitude.designs.blackturtleassignment.repo

import android.content.Context
import attitude.designs.blackturtleassignment.models.Photo

class DatabaseService(context: Context) : QueriesInterface {

    private val dao: PhotoDao = MyDatabase.getInstance(context).repoDao()


    override fun getAllOperations(): List<Photo>? {
        return dao.getAllOperations() as List<Photo>?

    }

    override fun insert(vararg threads: Photo): Int {
        return try {
            dao.insert(*threads)
            1
        } catch (e: Exception) {
            e.printStackTrace()
            0
        }    }


}