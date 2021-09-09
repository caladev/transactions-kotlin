package calculator

import transactions.Transaction
import java.math.BigDecimal

class CalculatorService {
    companion object {
        fun calculateRelativeBalance(transactions: Map<String, Transaction>, accountId: String): BigDecimal =
            transactions.filter { it.value.includesAccount(accountId) }.map {
                val transaction = it.value
                if (accountId == transaction.fromAccountId) transaction.amount.negate() else transaction.amount
            }.reduce { balance, amount -> balance.add(amount) }
    }
}
