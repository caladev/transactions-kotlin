package transactions

import java.math.BigDecimal

class TransactionService(rawTransactions: List<String>) {

    var transactions: Map<String, Transaction>

    // parse raw transaction records
    init {
        val results = mutableMapOf<String, Transaction>()
        rawTransactions.forEach {
            val values = it.split(",")
            fun value(column: TransactionColumn) = values[column.index]

            val transactionId = value(TransactionColumn.TRANSACTION_ID)
            val relatedTransactionId = value(TransactionColumn.RELATED_TRANSACTION_ID)

            var relatedTransaction: Transaction? = null
            if (relatedTransactionId.isNotEmpty() && results.containsKey(relatedTransactionId))
                relatedTransaction = results[relatedTransactionId]

            val transaction = Transaction(
                transactionId,
                value(TransactionColumn.FROM_ACCOUNT_ID),
                value(TransactionColumn.TO_ACCOUNT_ID),
                DateTime(value(TransactionColumn.CREATED_AT)),
                BigDecimal(value(TransactionColumn.AMOUNT)),
                Transaction.Type.getByValue(value(TransactionColumn.TRANSACTION_TYPE)),
                relatedTransaction
            )
            results[transactionId] = transaction
        }
        transactions = results.toMap()
    }

    fun findTransactions(accountId: String, dateTimeFrom: DateTime, dateTimeTo: DateTime) = transactions.filter {
        val transaction = it.value
        transaction.type == Transaction.Type.PAYMENT && transaction.includesAccount(accountId) && transaction.createdAt.isBetween(
            dateTimeFrom,
            dateTimeTo
        )
    }

    fun reversedTransactions(accountId: String): Map<String, Transaction> {
        val ids = transactions.filter {
            val transaction = it.value
            transaction.type == Transaction.Type.REVERSAL && transaction.includesAccount(accountId)
        }.map { it.value.related?.id }
        return transactions.filter { it.key in ids }
    }

}
