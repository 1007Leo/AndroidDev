@file:Suppress("UNUSED_PARAMETER")
package mmcs.assignment2

/**
 * Ячейка матрицы: row = ряд, column = колонка
 */
data class Cell(val row: Int, val column: Int)

/**
 * Интерфейс, описывающий возможности матрицы. E = тип элемента матрицы
 */
interface Matrix<E> {
    /** Высота */
    val height: Int

    /** Ширина */
    val width: Int

    /**
     * Доступ к ячейке.
     * Методы могут бросить исключение, если ячейка не существует или пуста
     */
    operator fun get(row: Int, column: Int): E

    operator fun get(cell: Cell): E

    /**
     * Запись в ячейку.
     * Методы могут бросить исключение, если ячейка не существует
     */
    operator fun set(row: Int, column: Int, value: E)

    operator fun set(cell: Cell, value: E)
}

/**
 * Метод для создания матрицы, должен вернуть РЕАЛИЗАЦИЮ Matrix<E>.
 * height = высота, width = ширина, e = чем заполнить элементы.
 * Бросить исключение IllegalArgumentException, если height или width <= 0.
 */
fun <E> createMatrix(height: Int, width: Int, e: E): Matrix<E> {
    if (height <= 0 || width <= 0)
        throw IllegalArgumentException("Matrix dimensions must be bigger than 0.")
    return MatrixImpl<E>(height, width, e)
}

/**
 * Реализация интерфейса "матрица"
 */

@Suppress("EqualsOrHashCode")
class MatrixImpl<E>(override val height: Int, override val width: Int, e: E) : Matrix<E> {

    private val values: MutableList<E> = MutableList(height * width) {e}

    override fun get(row: Int, column: Int): E {
        val pos = row * width + column
        if (pos >= values.size)
            throw IndexOutOfBoundsException()
        val value = values[pos]
        if (value == null) {
            throw NullPointerException()
        }
        return value
    }

    override fun get(cell: Cell): E {
        val pos = cell.row * width + cell.column
        if (pos >= values.size)
            throw IndexOutOfBoundsException()
        val value = values[pos]
        if (value == null) {
            throw NullPointerException()
        }
        return value
    }

    override fun set(row: Int, column: Int, value: E) {
        val pos = row * width + column
        if (pos >= values.size)
            throw IndexOutOfBoundsException()
        values[pos] = value
    }

    override fun set(cell: Cell, value: E) {
        val pos = cell.row * width + cell.column
        if (pos >= values.size)
            throw IndexOutOfBoundsException()
        values[pos] = value
    }

    override fun equals(other: Any?) : Boolean {
        if (other != null && other is MatrixImpl<*>) {
            if (this.width != other.width || this.height != other.height)
                return false
            if (this.values != other.values)
                return false
        }
        return true
    }

    override fun toString(): String {
        val sb = StringBuilder()
        var nlCount = 0
        for (value in values){
            sb.append(value.toString() + " ")
            nlCount += 1
            if (nlCount == this.width) {
                sb.append("\n")
                nlCount = 0
            }
        }
        return sb.toString()
    }
}
