package attitude.designs.blackturtleassignment.repo

import attitude.designs.blackturtleassignment.models.Photo

interface QueriesInterface {

    fun getAllOperations(): List<Photo>?

    fun insert(vararg threads: Photo): Int

}