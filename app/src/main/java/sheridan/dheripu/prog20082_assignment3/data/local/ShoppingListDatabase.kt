package sheridan.dheripu.prog20082_assignment3.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

/**
 * Database class with a singleton Instance object.
 */
@Database(entities = [LocalItem::class], version = 2, exportSchema = false)
@TypeConverters(DataConverters::class)
abstract class ShoppingListDatabase : RoomDatabase() {

    abstract fun itemDao(): ItemDao
}
