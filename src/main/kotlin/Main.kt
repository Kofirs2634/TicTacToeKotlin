fun main() {
    // инициализация поля и первого хода
    val field = Field()
    var turn = false

    // собственно игра
    while (true) {
        field.render()

        if (turn) print("Ходят нолики: ")
        else print("Ходят крестики: ")

        // заставляем ввести только разрешенное число (проверка внутри Field.putCell)
        var pos = readLine()?.toIntOrNull() ?: 0
        while (!field.putCell(pos, turn))
            pos = readLine()?.toIntOrNull() ?: 0

        turn = !turn  // переход хода

        val endData = field.checkEnd()
        if (endData["isEnd"] == true) {
            field.render()
            println("Игра окончена!")
            if (endData["winner"] == null) println("У нас ничья!")
            else println("Победили ${if (endData["winner"] == true) "нолики" else "крестики"}!")
            break
        }
    }
}