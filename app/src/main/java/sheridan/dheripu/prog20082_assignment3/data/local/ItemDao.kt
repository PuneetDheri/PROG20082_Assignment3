package sheridan.dheripu.prog20082_assignment3.data.local
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow
import sheridan.dheripu.prog20082_assignment3.data.local.LocalItem


@Dao
interface ItemDao {

    @Query("SELECT * from items ORDER BY name ASC")
    fun getAllItemsStream(): Flow<List<LocalItem>>

    @Query("SELECT * from items WHERE id = :id")
    fun getItemByIdStream(id: Int): Flow<LocalItem?>

    // Specify the conflict strategy as IGNORE, when the user tries to add an
    // existing Item into the database Room ignores the conflict.
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertItem(item: LocalItem)

    @Update
    suspend fun updateItem(item: LocalItem)


    @Query("UPDATE items SET selected = :selected WHERE id = :id")
    suspend fun updateItemSelectedById(id: Int, selected: Boolean)

    @Delete
    suspend fun deleteItem(item: LocalItem)

    @Query("DELETE FROM items WHERE id = :id")
    suspend fun deleteItemById(id: Int)
}
