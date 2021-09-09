import calculator.CalculatorService
import transactions.DateTime
import transactions.TransactionService
import utils.CsvReader
import java.math.BigDecimal
import java.text.NumberFormat

private const val TRANSACTIONS_CSV = "transactions.csv"

fun main() {
    val accountId = input("accountId: ").uppercase()
    val dateTimeFrom = DateTime(input("from: "))
    val dateTimeTo = DateTime(input("to: "))

    val ts = TransactionService(CsvReader.rows(TRANSACTIONS_CSV))
    val transactions = ts.findTransactions(accountId, dateTimeFrom, dateTimeTo)
    val validTransactions = transactions.filter { it.key !in ts.reversedTransactions(accountId).keys }

    printStatement(CalculatorService.calculateRelativeBalance(validTransactions, accountId), validTransactions.size)
}

private fun input(prompt: String = ""): String {
    print(prompt)
    val result = readLine()
    return result.toString()
}

private fun printStatement(relativeBalance: BigDecimal, numTransactions: Int) {
    println(
        """
    Relative balance for the period is: ${NumberFormat.getCurrencyInstance().format(relativeBalance)}
    Number of transactions included is: $numTransactions
    """.trimIndent()
    )
}
