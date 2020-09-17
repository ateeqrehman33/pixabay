package attitude.designs.blackturtleassignment.models


import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable


@Entity(tableName = "photos")
data class Photo(
    @PrimaryKey
    val id: Int ? = null,
    val previewURL: String ? =  null,
    val webformatURL: String ? =  null,
    val isfav: String ? =  null ): Serializable{

}

