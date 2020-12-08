class Converter(private var automaton: Automaton)
{
    private val newFinalStates = mutableSetOf<String>()
    private val newStates = mutableSetOf<String>()

    private val stateTable : StateTable by lazy {
        StateTable()
    }

    init {
        automaton.getFinalStates().forEach {
            newFinalStates.add(it)
        }

        automaton.getStates().forEach {
            newStates.add(it)
        }
    }

    fun removeETransitions() : Converter {
        val newAutomatonStates = automaton.getStates()
        val newAutomatonFinalStates = automaton.getFinalStates().toMutableList()
        val newAutomatonRules = automaton.getRules().toMutableList()

        automaton.getRules().forEach {
            // checks if there's any E transition
            if (it.char == ' ') {
                // if there is an E transition, gets all the rules for that state
                if (automaton.getFinalStates().contains(it.nextState)) {
                    newAutomatonFinalStates.add(it.firstState)
                } else {
                    val rulesByState = automaton.getRulesByState(it.nextState)

                    // for each of the rules, checks if the next state is final
                    rulesByState.forEach { rule ->
                        newAutomatonRules.add(Rule(it.firstState, rule.char, rule.nextState))
                    }
                }

                newAutomatonRules.remove(it)
            }
        }

        automaton = Automaton(newAutomatonStates, newAutomatonFinalStates.toTypedArray(), newAutomatonRules.toTypedArray())

        return this
    }

    fun printAutomaton() : Converter {
        automaton.printAutomaton()

        return this
    }

    fun assembleStatesTable() : Converter {
        automaton.getStates().forEach {
            val rulesByState = automaton.getRulesByState(it)

            val stateItem = StateTableItem(it, mutableSetOf(), mutableSetOf())
            rulesByState.forEach {rule ->
                if (rule.char == 'a')
                    stateItem.goingToA.add(rule.nextState)
                else
                    stateItem.goingToB.add(rule.nextState)
            }

            stateTable.addItem(stateItem)
        }

        return this
    }

    fun printStatesTable() : Converter {
        println("\n===============================================================\n")
        println(stateTable.toString())

        return this
    }

    fun convert() : Converter {
        var isConverted = false

        // while the whole automaton isn't converted
        while (!isConverted) {
            // we assume that we do not have to create new states
            var hasToCreateNewStates = false

            // checks if all the states are created
            stateTable.getItems().forEach {

                if (stateTable.getItemByState(it.goingToA.joinToString(",")) == null) {
                    hasToCreateNewStates = true
                    createNewStateItem(it, true)
                }

                if (stateTable.getItemByState(it.goingToB.joinToString(",")) == null) {
                    hasToCreateNewStates = true
                    createNewStateItem(it, false)
                }

                // the conversion finishes when we don't have to create any other state
                if (!hasToCreateNewStates)
                    isConverted = true
            }
        }

        return this
    }

    fun statesTableToAutomaton() : Automaton {
        val automatonRules = arrayListOf<Rule>()

        stateTable.getItems().forEach {
            it.goingToA.forEach { stateA ->
                newStates.add(stateA)
            }

            it.goingToB.forEach { stateB ->
                newStates.add(stateB)
            }

            val stateGoingToA = it.goingToA.joinToString(",")
            val stateGoingToB = it.goingToB.joinToString(",")
            if (it.state.isNotEmpty()) {
                if (stateGoingToA.isNotEmpty()) {
                    val rule = Rule(it.state, 'a', stateGoingToA)
                    automatonRules.add(rule)
                }

                if (stateGoingToB.isNotEmpty()) {
                    val rule = Rule(it.state, 'b', stateGoingToB)
                    automatonRules.add(rule)
                }
            }
        }

        return Automaton(newStates.toTypedArray(), newFinalStates.toTypedArray(), automatonRules.toTypedArray())
    }

    private fun createNewStateItem(stateTableItem: StateTableItem, isNewStateGoingToA : Boolean) {

        val newGoingToA: MutableSet<String>
        val newGoingToB: MutableSet<String>
        var newState = ""

        if (isNewStateGoingToA) {
            newState = stateTableItem.goingToA.joinToString(",")

            newGoingToA = createState(stateTableItem.goingToA, isNewStateGoingToA)
            newGoingToB = createState(stateTableItem.goingToB, false)
        } else {
            newState = stateTableItem.goingToB.joinToString(",")

            newGoingToA = createState(stateTableItem.goingToA, true)
            newGoingToB = createState(stateTableItem.goingToB, isNewStateGoingToA)
        }

        val newStateItem = StateTableItem(newState, newGoingToA, newGoingToB)

        if (newState.isNotEmpty() && newGoingToA.joinToString(",").isNotEmpty()
                && newGoingToB.joinToString(",").isNotEmpty())
            stateTable.addItem(newStateItem)
    }

    private fun createState(goingTo : MutableSet<String>, isGoingToA : Boolean) : MutableSet<String> {
        // creates the new state
        val newGoingToState = mutableSetOf<String>()

        goingTo.forEach { state ->
            // gets the line of that states to see where it's going
            val stateItem = stateTable.getItemByState(state)

            if (stateItem != null) {
                addFinalState(state, goingTo.joinToString(","))

                if (isGoingToA)
                    stateItem.goingToA.forEach { stateA ->
                        newGoingToState.add(stateA)
                    }
                else
                    stateItem.goingToB.forEach { stateB ->
                        newGoingToState.add(stateB)
                    }
            }
        }

        return newGoingToState
    }

    private fun addFinalState(state : String, stateToAdd : String) {
        if (automaton.getFinalStates().contains(state) && stateToAdd != "" && state != "")
            newFinalStates.add(stateToAdd)
    }
}