package sheridan.dheripu.prog20082_assignment3.domain

import sheridan.dheripu.prog20082_assignment3.data.local.Priority

data class Item(
    val id: Int = 0,
    val name: String = "No Name",
    val price: Double = 0.0,
    val priority: Priority = Priority.LOW,
    val selected: Boolean = false
)
