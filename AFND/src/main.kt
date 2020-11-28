fun main() {

    // first automaton
    var rules = arrayOf(
        Rule(1, 'a', 1),
        Rule(1, 'b', 2),
        Rule(2, 'b', 2)
    )

    var automaton = Automaton(arrayOf(1, 2), arrayOf(2), rules)
    var chain = "aaabaaabbbbbb"

    println("First automaton: ${automaton.checkChain(chain, 1)}")
    println("\n======================================================================\n")

    // second automaton
    rules = arrayOf(
        Rule(1, 'a', 1),
        Rule(1, 'b', 2),
        Rule(2, 'a', 2),
        Rule(2, 'b', 1)
    )

    automaton = Automaton(arrayOf(1, 2), arrayOf(2), rules)
    chain = "ababaabbb"

    println("Second automaton: ${automaton.checkChain(chain, 1)}")
    println("\n======================================================================\n")

    // third automaton
    rules = arrayOf(
            Rule(1, ' ', 2),
            Rule(1, ' ', 4),
            Rule(2, 'a', 3),
            Rule(3, 'a', 3),
            Rule(3, 'b', 3),
            Rule(4, 'b', 5),
            Rule(5, 'a', 5),
            Rule(5, 'b', 5)
    )

    automaton = Automaton(arrayOf(1, 2, 3, 4, 5), arrayOf(3, 5), rules)
    chain = "aabba"

    println("Third automaton - first chain: ${automaton.checkChain(chain, 1)}")

    chain = "babba"

    println("Third automaton - second chain: ${automaton.checkChain(chain, 1)}")
    println("\n======================================================================\n")

    // fourth automaton
    rules = arrayOf(
            Rule(1, ' ', 2),
            Rule(1, ' ', 4),
            Rule(2, 'a', 3),
            Rule(3, 'a', 3),
            Rule(3, 'b', 3),
            Rule(4, 'b', 4),
            Rule(4, 'a', 4),
            Rule(4, 'b', 5)
    )

    automaton = Automaton(arrayOf(1, 2, 3, 4, 5), arrayOf(3, 5), rules)
    chain = "baabba"

    println("Fourth automaton - first chain: ${automaton.checkChain(chain, 1)}")

    chain = "babbababa"

    println("Fourth automaton - second chain: ${automaton.checkChain(chain, 1)}")
    println("\n======================================================================\n")

    // fifth automaton
    rules = arrayOf(Rule(1, 'a', 1))
    automaton = Automaton(arrayOf(1), arrayOf(1), rules)
    chain = "aaaaaaaaaaaaa"

    println("Fifth automaton: ${automaton.checkChain(chain, 1)}")
    println("\n======================================================================\n")

    // sixth automaton
    rules = arrayOf(Rule(1, ' ', 2))
    automaton = Automaton(arrayOf(1, 2), arrayOf(2), rules)
    chain = " "

    println("Sixth automaton: ${automaton.checkChain(chain, 1)}")
    println("\n======================================================================\n")

    // seventh automaton
    rules = arrayOf(
            Rule(1, 'a', 3),
            Rule(1, 'b', 2),
            Rule(3, 'a', 4),
            Rule(3, 'b', 5)
    )
    automaton = Automaton(arrayOf(1, 2, 3, 4, 5), arrayOf(4, 5), rules)
    chain = "ab"

    println("Seventh automaton: ${automaton.checkChain(chain, 1)}")
    println("\n======================================================================\n")

    // eighth automaton
    rules = arrayOf(
            Rule(1, 'a', 1),
            Rule(1, 'b', 1),
            Rule(1, 'a', 2),
            Rule(1, ' ', 3),
            Rule(2, 'a', 3),
            Rule(3, 'a', 3),
            Rule(3, 'b', 3),
            Rule(3, 'b', 4),
            Rule(4, 'b', 5),
            Rule(5, 'a', 5),
            Rule(5, 'b', 5),
            Rule(5, ' ', 1)
    )
    automaton = Automaton(arrayOf(1, 2, 3, 4, 5), arrayOf(3, 5), rules)
    chain = "abaaabbb"

    println("Eighth automaton: ${automaton.checkChain(chain, 1)}")
    println("\n======================================================================\n")
}