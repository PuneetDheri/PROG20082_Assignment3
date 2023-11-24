package sheridan.dheripu.prog20082_assignment3

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import sheridan.dheripu.prog20082_assignment3.data.local.ItemDao
import sheridan.dheripu.prog20082_assignment3.data.local.LocalItem
import sheridan.dheripu.prog20082_assignment3.data.local.Priority
import sheridan.dheripu.prog20082_assignment3.data.local.ShoppingListDatabase
import java.io.IOException

@RunWith(AndroidJUnit4::class)
class LocalItemDaoTest {

    private lateinit var itemDao: ItemDao
    private lateinit var shoppingListDatabase: ShoppingListDatabase
    private val item1 = LocalItem(1, "Apples",  Priority.LOW, false)
    private val item2 = LocalItem(2, "Bananas",  Priority.LOW, false)

    @Before
    fun createDb() {
        val context: Context = ApplicationProvider.getApplicationContext()
        // Using an in-memory database because the information stored here disappears when the
        // process is killed.
        shoppingListDatabase = Room.inMemoryDatabaseBuilder(context, ShoppingListDatabase::class.java)
            // Allowing main thread queries, just for testing.
            .allowMainThreadQueries()
            .build()
        itemDao = shoppingListDatabase.itemDao()
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        shoppingListDatabase.close()
    }

    @Test
    @Throws(Exception::class)
    fun daoInsert_insertsItemIntoDB() = runBlocking {
        addOneItemToDb()
        val allItems = itemDao.getAllItemsStream().first()
        assertEquals(allItems[0], item1)
    }

    @Test
    @Throws(Exception::class)
    fun daoGetAllItems_returnsAllItemsFromDB() = runBlocking {
        addTwoItemsToDb()
        val allItems = itemDao.getAllItemsStream().first()
        assertEquals(allItems[0], item1)
        assertEquals(allItems[1], item2)
    }


    @Test
    @Throws(Exception::class)
    fun daoGetItem_returnsItemFromDB() = runBlocking {
        addOneItemToDb()
        val item = itemDao.getItemByIdStream(1)
        assertEquals(item.first(), item1)
    }

    @Test
    @Throws(Exception::class)
    fun daoDeleteItems_deletesAllItemsFromDB() = runBlocking {
        addTwoItemsToDb()
        itemDao.deleteItem(item1)
        itemDao.deleteItem(item2)
        val allItems = itemDao.getAllItemsStream().first()
        assertTrue(allItems.isEmpty())
    }

    @Test
    @Throws(Exception::class)
    fun daoUpdateItems_updatesItemsInDB() = runBlocking {
        addTwoItemsToDb()
        itemDao.updateItem(LocalItem(1, "Apples",  Priority.LOW, true))
        itemDao.updateItem(LocalItem(2, "Bananas", Priority.LOW, false))

        val allItems = itemDao.getAllItemsStream().first()
        assertEquals(allItems[0], LocalItem(1, "Apples",  Priority.LOW, true))
        assertEquals(allItems[1], LocalItem(2, "Bananas",  Priority.LOW, false))
    }

    private suspend fun addOneItemToDb() {
        itemDao.insertItem(item1)
    }

    private suspend fun addTwoItemsToDb() {
        itemDao.insertItem(item1)
        itemDao.insertItem(item2)
    }
}
