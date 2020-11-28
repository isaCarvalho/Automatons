class Automaton(private val states : Array<Int>, private val finalStates : Array<Int>, private val rules : Array<Rule>)
{
    private val alphabet = arrayOf(' ', 'a', 'b')
    private var initialState = -1

    init {
        initialState = states[0]
    }

    fun checkChain(chain : String, actualState : Int) : Boolean {

        // state does not belong to the automaton
        if (!states.contains(actualState) && states.indexOf(actualState) == actualState - 1)
            return false

        // if we are at the last char in the chain, it has to be a final state
        if (chain.isEmpty() && finalStates.contains(actualState)) {
            return true
        } else if (chain.isEmpty() && !finalStates.contains(actualState)) {
            return false
        }

        var isAccepted = false
        val letter = chain[0]

        // if the alphabet contains the letter
        if (alphabet.contains(letter)) {
            // get all the rules for the actual state
            val rulesByState = getRulesByState(actualState)

            rulesByState.forEach {
                if (it.char == letter)
                    isAccepted = checkChain(chain.substring(1), it.nextState)
                else if (it.char == ' ')
                    isAccepted = checkChain(chain, it.nextState)

                if (isAccepted)
                    return true
            }
        }

        return isAccepted
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