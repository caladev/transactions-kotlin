package calculator

import transactions.TransactionService
import utils.CsvReader
import kotlin.test.Test
import kotlin.test.assertEquals


internal class CalculatorServiceTest {
    @Test
    fun `calculates correct relative balance`() {
        val accountId = "ACC334455"
        val ts = TransactionService(CsvReader.rows("transactions-test.csv"))
        val balance = CalculatorService.calculateRelativeBalance(ts.transactions, accountId)
        assertEquals("4.50", balance.toString())
    }
}
