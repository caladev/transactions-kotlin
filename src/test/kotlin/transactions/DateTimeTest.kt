package transactions

import kotlin.test.Test
import kotlin.test.assertFalse
import kotlin.test.assertTrue

internal class DateTimeTest {
    @Test
    fun `ensures date-time in range, start-end inclusive`() {
        val start = DateTime("01/01/2021 09:00:00")
        val end = DateTime("01/01/2021 15:00:00")
        assertTrue(DateTime("01/01/2021 12:00:00").isBetween(start, end))
        assertTrue(start.isBetween(start, end))
        assertTrue(end.isBetween(start, end))
        assertFalse(DateTime("01/01/2021 08:00:00").isBetween(start, end))
        assertFalse(DateTime("01/01/2021 16:00:00").isBetween(start, end))
    }
}
