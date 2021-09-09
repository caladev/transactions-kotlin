package utils

import java.io.File

class CsvReader {
    companion object {
        fun rows(fileName: String) = File(fileName).bufferedReader().useLines { lines -> lines.drop(1).toList() }
    }
}
