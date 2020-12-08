class Converter(private var automaton: Automaton)
{
    private val stateTable : StateTable by lazy {
        StateTable()
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
        println("\n===============================================================\n")
        automaton.printAutomaton()

        return this
    }

    fun assembleStatesTable() : Converter {
        automaton.getStates().forEach {
            val rulesByState = automaton.getRulesByState(it)

            val stateItem = StateTableItem(it.toString(), mutableSetOf<Int>(), mutableSetOf<Int>())
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

        while (!isConverted) {
            var isStateConverted = true

            stateTable.getItems().forEach {
                if (it.goingToA.size > 1 || it.goingToB.size > 1)
                    isStateConverted = false
                else {
                    // continue from here
                }
            }

            if (isStateConverted)
                isConverted = isStateConverted
        }

        return this
    }
}