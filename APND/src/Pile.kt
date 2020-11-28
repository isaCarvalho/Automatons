class Pile(private val initialSymbol : Char)
{
    private val pile = ArrayList<Char>()

    init {
        pile.add(initialSymbol)
    }

    // Adds the symbol right to left
    fun push(chain : String) {
        val array = chain.reversed().toCharArray()
        array.forEach {
            pile.add(it)
        }
    }

    // takes the last character and returns it
    fun pop() : Char {
        val index = pile.size - 1
        val lastChar = pile[index]

        pile.removeAt(index)
        return lastChar
    }
}