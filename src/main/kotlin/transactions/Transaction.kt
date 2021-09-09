package transactions

import java.math.BigDecimal

data class Transaction(
    val id: String,
    val fromAccountId: String,
    val toAccountId: String,
    val createdAt: DateTime,
    val amount: BigDecimal,
    val type: Type,
    val related: Transaction? = null
) {
    fun includesAccount(id: String) = id in listOf(fromAccountId, toAccountId)

    enum class Type(private val value: String) {
        PAYMENT("PAYMENT"), REVERSAL("REVERSAL");

        companion object {
            fun getByValue(value: String) = values().first { it.value == value }
        }
    }
}
