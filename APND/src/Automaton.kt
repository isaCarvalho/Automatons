class Automaton(private val states : Array<Int>, private val finalStates : Array<Int>, private val rules : Array<Rule>)
{
    /** The acceptance of this automaton is by final states */
    private val inputAlphabet = arrayOf('a', 'b', ' ')
    private val pileAlphabet = arrayOf('I', 'A')
    private val initialState : Int = states[0]
    private val initialSymbol = pileAlphabet[0]
    private val pile : Pile = Pile(initialSymbol)

    fun checkChain(chain : String, actualState : Int = initialState) : Boolean {

        // state does not belong to the automaton
        if (!states.contains(actualState) && states.indexOf(actualState) == actualState - 1)
            return false

        // if we are at the last char in the chain, it has to be a final state
        if (chain.isEmpty() ){
            if (finalStates.contains(actualState) && pile.pop() == initialSymbol)
                return true

            // checks if the chain is empty but we can go with e to some final state
            val rules = getRulesByState(actualState)
            rules.forEach {
                if (finalStates.contains(it.nextState) && it.char == ' ')
                    return true
            }

            if (!finalStates.contains(actualState) || pile.pop() != initialSymbol)
                return false
        }

        var isAccepted = false
        val letter = chain[0]

        // if the alphabet contains the letter
        if (inputAlphabet.contains(letter)) {
            val rulesByState = getRulesByState(actualState)

            rulesByState.forEach {
                // removes the last pile char just to consult, and puts it back
                val pileChar = pile.pop()
                pile.push(pileChar.toString())

                if (it.char == letter && pileChar == it.pileChar) {
                    pile.pop()
                    pile.push(it.chain)
                    isAccepted = checkChain(chain.substring(1), it.nextState)
                }
                else if (it.char == ' ' && pileChar == it.pileChar) {
                    pile.pop()
                    pile.push(it.chain)
                    isAccepted = checkChain(chain, it.nextState)
                }

                if (isAccepted)
                    return true
            }
        }

        return false
    }

    private fun getRulesByState(state : Int) : Array<Rule> {
        val rulesByState = ArrayList<Rule>()
        rules.forEach {
            if (it.firstState == state)
                rulesByState.add(it)
        }

        return rulesByState.toTypedArray()
    }
}