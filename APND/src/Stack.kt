class Stack(private val initialSymbol : Char)
{
    private val stack = ArrayList<Char>()

    init {
        stack.add(initialSymbol)
    }

    // Adds the symbol right to left
    fun push(chain : String) {
        val array = chain.reversed().toCharArray()
        array.forEach {
            stack.add(it)
        }
    }

    // takes the last character and returns it
    fun pop() : Char {
        val index = stack.size - 1
        val lastChar = stack[index]

        stack.removeAt(index)
        return lastChar
    }
}