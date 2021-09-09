package transactions

enum class TransactionColumn(val index: Int) {
    TRANSACTION_ID(0),
    FROM_ACCOUNT_ID(1),
    TO_ACCOUNT_ID(2),
    CREATED_AT(3),
    AMOUNT(4),
    TRANSACTION_TYPE(5),
    RELATED_TRANSACTION_ID(6);
}
