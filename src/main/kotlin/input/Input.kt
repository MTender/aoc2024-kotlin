package input

import kotlin.io.path.Path
import kotlin.io.path.readLines

object Input {

    fun read(path: String): List<String> {
        return Path(path).readLines()
    }
}