package sheridan.dheripu.prog20082_assignment3.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "items")
data class LocalItem(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name: String,
    val price: Double,
    val priority: Priority,
    val selected: Boolean
)
