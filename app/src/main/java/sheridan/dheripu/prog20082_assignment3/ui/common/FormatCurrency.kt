package sheridan.dheripu.prog20082_assignment3.ui.common


import java.text.NumberFormat

fun formatCurrency(value: Double): String =
    NumberFormat.getCurrencyInstance().format(value)