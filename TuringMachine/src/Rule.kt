data class Rule(
    val firstState : String,
    val char : Char,
    val nextState : String,
    val newChar : Char,
    val direction : Char
) {
    override fun toString(): String {
        return "$firstState $char $nextState $newChar $direction"
    }
}