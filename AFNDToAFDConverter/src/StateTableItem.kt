class StateTableItem(val state : String, val goingToA : MutableSet<String>, val goingToB : MutableSet<String>)
{
    fun getNewState(goingToA : Boolean = true) : String {
        return if (goingToA)
            this.goingToA.joinToString(",")
        else
            this.goingToB.joinToString(",")
    }
}