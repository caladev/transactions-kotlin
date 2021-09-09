package transactions

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

private const val DATE_TIME_FORMAT = "dd/MM/yyyy HH:mm:ss"

data class DateTime(private val text: String) {

    private val parsed = LocalDateTime.parse(text, DateTimeFormatter.ofPattern(DATE_TIME_FORMAT))

    fun isBetween(start: DateTime, end: DateTime) = !parsed.isBefore(start.parsed) && !parsed.isAfter(end.parsed)

}
