# Some automaton non-deterministic algorithm

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