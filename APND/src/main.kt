fun main() {
    // L = {a^2n b^n / n belongs to Nat}
    var rules = arrayOf(
        Rule(1, 'a', 'I', 2, "AI"),
        Rule(2, 'a', 'A', 1, "A"),
        Rule(1, 'a', 'A', 2, "AA"),
        Rule(1, 'b', 'A', 3, ""),
        Rule(3, 'b', 'A', 3, ""),
        Rule(3, 'b', 'I', 4, "I")
    )

    var automaton = Automaton(arrayOf(1, 2, 3, 4), arrayOf(4), rules)
    var chain = "aaaabb"

    println("First automaton: ${automaton.checkChain(chain)}")

    // L = {a^n b^2n / n belongs to Nat}
    rules = arrayOf(
        Rule(1, 'a', 'I', 1, "AAI"),
        Rule(1, 'a', 'A', 1, "AAA"),
        Rule(1, 'b', 'A', 2, ""),
        Rule(2, 'b', 'A', 2, ""),
        Rule(2, ' ', 'I', 3, "I")
    )

    automaton = Automaton(arrayOf(1, 2, 3), arrayOf(3), rules)
    chain = "aabbbba"

    println("Second automaton: ${automaton.checkChain(chain)}")
}