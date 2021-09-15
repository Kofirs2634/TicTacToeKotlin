class Field {
    private val cells = mutableMapOf<Int, FieldCell>()
    init {
        for (i in 1..9) cells[i] = FieldCell(i)
    }

    /**
     * Помещает в указанное место заполненную клетку
     * @param pos Место в сетке, от 1 до 9
     * @param team Кто владеет клеткой; `false` - крестики, `true` - нолики
     * @return `true`, если операция удалась
     */
    fun putCell(pos: Int, team: Boolean = false): Boolean {
        if (pos !in 1..9) return false
        return cells[pos]!!.setTeam(team)
    }

    /**
     * Отрисовывает сетку и выводит ее в консоль
     */
    fun render() {
        cells.forEach { (pos, cell) ->
            print(cell.render())
            if (pos % 3 == 0) print("\n")
        }
    }

    /**
     * Проверяет, закончена ли игра
     * @return Ассоциированный массив с данными, где `isEnd` - показатель завершенности игры,
     * а `winner` - победившая команда (в случае ничьей `null`)
     */
    fun checkEnd(): Map<String, Boolean?> {
        // выписываем все возможные комбинации на 3x3
        val wins = listOf("123", "456", "789", "147", "258", "369", "159", "357")

        // каждую комбинацию обходим
        wins.forEach { seq ->
            // набираем клеток на каждый индекс комбинации
            val poses = seq.split("").filter { e -> e.isNotEmpty() }.map { e -> e.toInt() }
            val checking = mutableListOf<Boolean?>()
            poses.forEach { pos -> checking.add(cells[pos]?.team) }

            // если нуллов нет (ряд заполнен), проверяем
            if (!checking.contains(null)) {
                if (
                    checking[0] == checking[1] &&
                    checking[1] == checking[2]
                ) return mapOf("isEnd" to true, "winner" to checking[0])
            }
        }

        // определяем ничью
        if (cells.filterValues { e -> e.isEmpty() }.isEmpty())
            return mapOf("isEnd" to true, "winner" to null)

        // игра еще не закончена
        return mapOf("isEnd" to false, "winner" to null)
    }
}