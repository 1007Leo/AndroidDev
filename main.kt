import mmcs.assignment2.*

fun main() {
    val h = 3
    val w = 3
    var matrix = createMatrix(h,w, 1)
    var v = 1
    for (i in 0..<h)
        for (j in 0..<w) {
            matrix[i, j] = v
            v += 1
        }
    println("Matrix A")
    println(matrix.toString())
    println("Transpose A")
    println(transpose(matrix).toString())
    println("Rotate A by 180 deg")
    println(rotate(rotate(matrix)).toString())
    println("A plus A")
    println(matrix.plus(matrix).toString())
    println("Invert A")
    println(matrix.unaryMinus().toString())
    println("A times A")
    print(matrix.times(matrix).toString())

    println("-".repeat(10))

    matrix = createMatrix(5, 4, 0)
    matrix[0, 0] = 1
    matrix[0, 2] = 1
    matrix[1, 2] = 1
    matrix[2, 0] = 1
    matrix[3, 2] = 1
    println("Holes")
    print(matrix.toString())
    println(findHoles(matrix))

    println("\nKey - lock")
    val lock = createMatrix(3, 3, 1)
    lock[0, 1] = 0
    lock[1, 0] = 0
    lock[1, 2] = 0
    println(lock.toString())
    val key = createMatrix(2, 2, 0)
    key[0, 0] = 1
    key[1, 1] = 1
    println(key.toString())
    println(canOpenLock(key, lock))
}