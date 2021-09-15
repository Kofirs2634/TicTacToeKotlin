class FieldCell (val pos: Int, var team: Boolean? = null) {
    /**
     * Отрисовывает клетку
     * @return Строку с отрисованной клеткой
     */
    fun render(): String = when (team) {
        null -> "[ ]"
        false -> "[x]"
        true -> "[o]"
    }

    /**
     * Присваивает клетке принадлежность к крестикам или ноликам, но только
     * пока она пустая
     * @return `true`, если операция удалась
     */
    fun setTeam(team: Boolean): Boolean {
        if (this.team != null) return false
        this.team = team
        return true
    }

    /**
     * Проверяет, пуста ли клетка
     * @return `true`, если клетка пуста
     */
    fun isEmpty(): Boolean = team == null
}