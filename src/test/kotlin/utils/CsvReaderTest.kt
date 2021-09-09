package utils

import kotlin.test.Test
import kotlin.test.assertEquals


internal class CsvReaderTest {
    @Test
    fun `reads all rows excluding header`() {
        val rows = CsvReader.rows("transactions-test.csv")
        assertEquals(4, rows.size)
    }
}
