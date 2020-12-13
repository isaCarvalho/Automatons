class TuringMachine(
    private val states : Array<String>,
    private val finalStates : Array<String>,
    private val rules : Array<Rule>,
    private val auxSymbols : Array<Char>,
    private val whiteSymbol : Char,
    private val initialSymbol : Char,
    private val alphabet : Array<Char>,
    private val initialState : String
) {

    fun checkChain(chain : String) : Boolean
    {
        // creates the queue with the initial symbol, the chain's characters and the white symbol
        val queue = ArrayList<Char>().apply {
            add(initialSymbol)
            chain.forEach {
                if (!alphabet.contains(it)) { return false }
                add(it)
            }
            add(whiteSymbol)
        }

        return checkChainAux(queue, initialState, 0)
    }

    private fun checkChainAux(queue : ArrayList<Char>, actualState : String = initialState, index : Int) : Boolean {

        // acceptance by final state
        if (finalStates.contains(actualState))
            return true

        // checks if it is a valid state
        if (!states.contains(actualState))
            return false

        val letter = queue[index]
        val rulesByState = getRulesByState(actualState)

        rulesByState.forEach {
            // if we find the transition
            if (it.firstState == actualState && it.char == letter) {

                // if the new symbol's not in the aux symbols or the alphabet, returns false
                if (!auxSymbols.contains(it.newChar) && !alphabet.contains(it.newChar))
                    return false

                // we write the new symbol
                queue[index] = it.newChar

                printTransition(queue.joinToString(""), index, it)

                // check if we're going to the left or to the right
                return if (it.direction == 'R')
                    checkChainAux(queue, it.nextState, index + 1)
                else
                    checkChainAux(queue, it.nextState, index - 1)
            }
        }

        return false
    }

    private fun getRulesByState(state : String): Array<Rule> {
        return ArrayList<Rule>().apply {
            rules.forEach {
                if (it.firstState == state)
                    add(it)
            }
        }.toTypedArray()
    }

    private fun printTransition(chain : String, index : Int, rule : Rule) {
        println(chain)
        println("index: $index")
        println("$rule\n")
    }
}