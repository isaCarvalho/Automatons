fun main() {
    var rules = arrayOf(
        Rule("1", 'a', "2"),
        Rule("1", ' ', "3"),
        Rule("2", 'a', "3"),
        Rule("3", 'b', "2")
    )

    var automaton = Automaton(arrayOf("1", "2", "3"), arrayOf("2"), rules)
    automaton.printAutomaton()

     Converter(automaton)
            .removeETransitions()
            .printAutomaton()
            .assembleStatesTable()
            .printStatesTable()
            .convert()
            .printAutomaton()
            .statesTableToAutomaton()
            .printAutomaton()

    println("\n\n==========================================================================================\n\n")

    rules = arrayOf(
            Rule("1", 'a', "1"),
            Rule("1", 'b', "1"),
            Rule("1", 'b', "2")
    )

    automaton = Automaton(arrayOf("1", "2"), arrayOf("2"), rules)
    automaton.printAutomaton()

    Converter(automaton)
            .removeETransitions()
            .printAutomaton()
            .assembleStatesTable()
            .printStatesTable()
            .convert()
            .printStatesTable()
            .statesTableToAutomaton()
            .printAutomaton()

    println("\n\n==========================================================================================\n\n")

    rules = arrayOf(
            Rule("1", 'a', "1"),
            Rule("1", 'a', "2"),
            Rule("1", 'b', "1"),
            Rule("1", 'b', "3"),
            Rule("2", 'a', "2"),
            Rule("2", 'b', "2"),
            Rule("3", 'b', "2")
    )

    automaton = Automaton(arrayOf("1", "2", "3"), arrayOf("2"), rules)
    automaton.printAutomaton()

    Converter(automaton)
            .removeETransitions()
            .printAutomaton()
            .assembleStatesTable()
            .printStatesTable()
            .convert()
            .printStatesTable()
            .statesTableToAutomaton()
            .printAutomaton()
}