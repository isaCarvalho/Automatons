fun main() {
    val rules = arrayOf(
        Rule(1, 'a', 2),
        Rule(1, ' ', 3),
        Rule(2, 'a', 3),
        Rule(3, 'b', 2)
    )

    val automaton = Automaton(arrayOf(1, 2, 3), arrayOf(2), rules)
    automaton.printAutomaton()

    val convertedAutomaton = Converter(automaton)
            .removeETransitions()
            .printAutomaton()
            .assembleStatesTable()
            .printStatesTable()
            .convert()
            .printAutomaton()

}