class Automaton(private val states : Array<String>, private val finalStates : Array<String>, private var rules : Array<Rule>) {
    private val alphabet = arrayOf(' ', 'a', 'b')
    private var initialState = "-1"

    init {
        initialState = states[0]
    }

    fun getRules() = rules

    fun getFinalStates() = finalStates

    fun getStates() = states

    fun getRulesByState(state : String) : Array<Rule> {
        val rulesByState = ArrayList<Rule>()
        rules.forEach {
            if (it.firstState == state)
                rulesByState.add(it)
        }

        return rulesByState.toTypedArray()
    }

    fun printAutomaton() {
        println("\n===============================================================\n")
        println("Alphabet: {e, ${alphabet[1]}, ${alphabet[2]}}")
        print("States: {")

        states.forEach {
            if (states.indexOf(it) != states.size - 1)
                print("$it, ")
            else
                print("$it}\n")
        }

        println("Initial State: $initialState")
        print("Final States: {")

        finalStates.forEach {
            if (finalStates.indexOf(it) != finalStates.size - 1)
                print("$it, ")
            else
                print("$it}\n")
        }

        println("Rules:")
        rules.forEach {
            println("d(${it.firstState}, ${it.char}, ${it.nextState})")
        }
    }
}
