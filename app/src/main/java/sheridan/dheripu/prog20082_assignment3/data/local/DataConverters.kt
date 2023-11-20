package sheridan.dheripu.prog20082_assignment3.data.local

import androidx.room.TypeConverter

class DataConverters {

    @TypeConverter
    fun convertPriorityToInt(priority: Priority): Int{
        return priority.ordinal
    }

    @TypeConverter

    fun convertIntToPriority(index: Int): Priority {
        return Priority.values()[index]
    }
}

