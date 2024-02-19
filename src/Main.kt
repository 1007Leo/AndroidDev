import kotlin.random.Random

enum class Players {P1, P2}
enum class GameState {P1Wins, P2Wins, Draw, Enabled, Disabled}

class Game(width: Int, height: Int) {
    fun start() {
        //resetGame()
        gameState = GameState.Enabled
        println("Game started")

        while (gameState == GameState.Enabled) {
            makeMove()
        }
        gameOver()
    }

    private fun makeMove() {
        if (gameState != GameState.Enabled)
            return

        // generate move
        var selectedColumn : Int
        do {
            selectedColumn = Random.nextInt(0, field[0].size)
        } while (field[0][selectedColumn] != 0)

        // drop chip
        var selectedRow = 0
        for (i in field.indices) {
            if (field[i][selectedColumn] != 0) {
                field[i - 1][selectedColumn] = currentTurn.ordinal + 1
                selectedRow = i - 1
                break
            }
            if (i == field.size - 1) {
                field[i][selectedColumn] = currentTurn.ordinal + 1
                selectedRow = i
            }
        }

        showGame(selectedColumn)
        updateGameState(selectedRow, selectedColumn)

        if (currentTurn == Players.P1)
            currentTurn = Players.P2
        else
            currentTurn = Players.P1
    }

    private fun updateGameState(row : Int, col : Int) {
        val emptyCols = field[0].count{ it == 0 }
        if (emptyCols == 0)
            gameState = GameState.Draw

        // check winner
        var sum1 = 0
        var sum2 = 0
        sum1 += countDir(row, col, 1)
        sum2 += countDir(row, col, 2)
        sum1 += countDir(row, col, 3)
        sum2 += countDir(row, col, 4)

        if (sum1 >= 3 || sum2 >= 3)
            if (currentTurn == Players.P1)
                gameState = GameState.P1Wins
            else
                gameState = GameState.P2Wins
        //gameState = GameState.Enabled
    }

    private fun countDir(i : Int, j : Int, dir : Int) : Int {
        var i = i
        var j = j
        val iInc =
            when (dir) {
                1, 2 -> -1
                3, 4 -> 1
                else -> 0
            }
        val jInc =
            when (dir) {
                1, 4 -> -1
                2, 3 -> 1
                else -> 0
            }
        var cnt = 0

        i += iInc
        j += jInc
        while (i >= 0 && j >= 0 && i < field.size && j < field[0].size){
            if (field[i][j] == currentTurn.ordinal + 1) {
                cnt++
            }
            if (field[i][j] == 0 || field[i][j] != currentTurn.ordinal + 1)
                return cnt
            i += iInc
            j += jInc
        }
        return cnt
    }

    private fun showGame(selectedColumn : Int) {
        println("Player ${currentTurn.ordinal + 1 } has moved")

        val selector = "-".repeat(field[0].size * 2 + 1).toCharArray()
        selector[selectedColumn * 2 + 1] = 'v'
        println(selector)

        for (row in field) {
            print('|')
            for (el in row) {
                val tmpEl =
                    when (el) {
                        1 -> '*'
                        2 -> '#'
                        else -> ' '
                    }
                print("$tmpEl|")
            }
            println()
        }
        println()
    }

    private fun gameOver() {
        val result =
            when (gameState) {
                GameState.P1Wins -> "Player 1 wins"
                GameState.P2Wins -> "Player 2 wins"
                GameState.Draw -> "Draw"
                else -> "Unknown"
            }
        println("Game over. $result")
        gameState = GameState.Disabled
    }

    /*private fun resetGame() {

    }*/

    private var currentTurn : Players = Players.P1
    private var gameState : GameState = GameState.Disabled
    private val field = Array(width) {IntArray(height) {0} }
}

fun main() {
    val game = Game(6, 7)
    game.start()
}
