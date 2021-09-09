package transactions

import utils.CsvReader
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals

internal class TransactionServiceTest {

    private lateinit var ts: TransactionService

    @BeforeTest
    fun setUp() {
        ts = TransactionService(CsvReader.rows("transactions-test.csv"))
    }

    @Test
    fun `returns transactions with accountId in time period`() {
        val accountId = "ACC334455"
        val dateTimeFrom = DateTime("20/10/2018 10:00:00")
        val dateTimeTo = DateTime("20/10/2018 17:00:00")
        val transactions = ts.findTransactions(accountId, dateTimeFrom, dateTimeTo)
        assertEquals(2, transactions.size)
    }

    @Test
    fun `returns reversed transactions with accountId`() {
        val accountId = "ACC778899"
        val transactions = ts.reversedTransactions(accountId)
        assertEquals(1, transactions.size)
    }
}
