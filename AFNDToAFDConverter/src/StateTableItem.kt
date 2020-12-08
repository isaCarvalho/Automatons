class StateTableItem(val state : String, val goingToA : MutableSet<Int>, val goingToB : MutableSet<Int>)
{
    fun getNewState(goingToA : Boolean = true) : String {
        return if (goingToA)
            this.goingToA.joinToString(",")
        else
            this.goingToB.joinToString(",")
    }
}