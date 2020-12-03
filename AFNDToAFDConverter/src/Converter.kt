class Converter(private val automaton: Automaton)
{
    fun removeETransitions() : Automaton {
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

        return Automaton(newAutomatonStates, newAutomatonFinalStates.toTypedArray(), newAutomatonRules.toTypedArray())
    }
}