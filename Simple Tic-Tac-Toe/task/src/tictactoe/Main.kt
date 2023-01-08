package tictactoe


fun main() {
    gameSimulation()
}

fun gameSimulation() {
    val grid = MutableList(3) {
        MutableList(3) { '_' }
    }
    showGrid(grid)
    var steps = 0
    while(steps < 9) {
        if (steps % 2 == 0) {
            addSymbol('O',grid)
            steps++
        } else {
            addSymbol('X',grid)
            steps++
        }
        showGrid(grid)
        if(steps >= 4) {
            if(gameFinished(grid)) return
        }
    }
}

fun showGrid(grid: MutableList<MutableList<Char>>) {
    println("---------")
    for(i in 0 until grid.size) {
        println("| ${grid[i][0]} ${grid[i][1]} ${grid[i][2]} |")
    }
    println("---------")
}

fun checkLine(symbol: Char, grid: MutableList<MutableList<Char>>): Boolean {
    var isLine = false
    if(symbol == grid[0][0] && symbol == grid[0][1] && symbol == grid[0][2]) isLine = true
    if(symbol == grid[1][0] && symbol == grid[1][1] && symbol == grid[1][2]) isLine = true
    if(symbol == grid[2][0] && symbol == grid[2][1] && symbol == grid[2][2]) isLine = true
    if(symbol == grid[0][0] && symbol == grid[1][0] && symbol == grid[2][0]) isLine = true
    if(symbol == grid[0][1] && symbol == grid[1][1] && symbol == grid[2][1]) isLine = true
    if(symbol == grid[0][2] && symbol == grid[1][2] && symbol == grid[2][2]) isLine = true
    if(symbol == grid[0][0] && symbol == grid[1][1] && symbol == grid[2][2]) isLine = true
    if(symbol == grid[0][2] && symbol == grid[1][1] && symbol == grid[2][0]) isLine = true
    return isLine
}

fun addSymbol(symbol: Char, grid: MutableList<MutableList<Char>>) {
    while(true) {
        try {
            var coordinates = readln().split(" ").map { it.toInt() }
            if (grid[coordinates.first() - 1][coordinates.last() - 1] != '_') {
                println("This cell is occupied! Choose another one!")
                continue
            }
            grid[coordinates.first() - 1][coordinates.last() - 1] = symbol
            break
        } catch (e: java.lang.NumberFormatException) {
            println("You should enter numbers!")
            continue
        } catch (e: java.lang.IndexOutOfBoundsException) {
            println("Coordinates should be from 1 to 3!")
            continue
        }

    }
}

fun gameFinished(grid: MutableList<MutableList<Char>>): Boolean {
    var countSpace = 0

    for (i in grid.indices) {
        countSpace += grid[i].count { it == '_' }
    }

    val hasLineX = checkLine('X', grid)
    val hasLineO = checkLine('O', grid)

    if (hasLineX && !hasLineO) {
        println("X wins")
        return true
    } else if (hasLineO && !hasLineX) {
        println("O wins")
        return true
    } else {
        if (countSpace > 0) {
            return false
        }
        println("Draw")
        return true
    }
}


