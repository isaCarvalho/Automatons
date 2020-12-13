# Some automatons algorithms

## Finite automaton non-deterministc algorithm

To use this, follow the example:

```kotlin
fun main() {
    val rules = arrayOf(
        Rule(1, 'a', 1),
        Rule(1, 'b', 2),
        Rule(2, 'b', 2)
    )

    val automaton = Automaton(arrayOf(1, 2), arrayOf(2), rules)
    val chain = "aabb"

    println("Automaton: ${automaton.checkChain(chain, 1)}")
}
```

## Pushdown automaton non-deterministc algorithm

```kotlin
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

    println("Automaton: ${automaton.checkChain(chain)}")
}
```

## Converte finite automaton non-deterministc in finite automaton deterministc algorithm

```kotlin
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
}
```

## Turing Machine

```kotlin
fun main() {

    val rules = arrayOf(
        Rule("q0", 'I', "q1", 'I', 'R'),
        Rule("q1", 'a', "q2", 'X', 'R'),
        Rule("q2", 'a', "q2", 'a', 'R'),
        Rule("q2", 'b', "q3", 'Y', 'R'),
        Rule("q3", 'b', "q4", 'Y', 'R'),
        Rule("q4", 'b', "q4", 'b', 'R'),
        Rule("q4", 'c', "q5", 'Z', 'L'),
        Rule("q5", 'b', "q5", 'b', 'L'),
        Rule("q5", 'Y', "q5", 'Y', 'L'),
        Rule("q5", 'a', "q5", 'a', 'L'),
        Rule("q5", 'X', "q1", 'X', 'R'),
        Rule("q2", 'Y', "q2", 'Y', 'R'),
        Rule("q4", 'Z', "q4", 'Z', 'R'),
        Rule("q5", 'Z', "q5", 'Z', 'L'),
        Rule("q1", 'Y', "q6", 'Y', 'R'),
        Rule("q6", 'Y', "q6", 'Y', 'R'),
        Rule("q6", 'Z', "q6", 'Z', 'R'),
        Rule("q6", 'c', "q7", 'Z', 'R'),
        Rule("q7", 'c', "q8", 'Z', 'R'),
        Rule("q8", 'B', "q9", 'F', 'R'),
    )

    println(TuringMachine(
        arrayOf("q0", "q1", "q2", "q3", "q4", "q5", "q6", "q7", "q8", "q9"),
        arrayOf("q9"),
        rules,
        arrayOf('I', 'X', 'Y', 'Z', 'B', 'F'),
        'B',
        'I',
        arrayOf('a', 'b', 'c'),
        "q0"
    ).checkChain("aabbbbcccc"))
}
```