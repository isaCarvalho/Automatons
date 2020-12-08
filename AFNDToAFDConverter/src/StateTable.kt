class StateTable
{
    private val stateTableItems = arrayListOf<StateTableItem>()

    fun addItem(item: StateTableItem) {
        stateTableItems.add(item)
    }

    fun getItems() : Array<StateTableItem> = stateTableItems.toTypedArray()

    override fun toString(): String {
        var str = "STATE |\tA\t|\tB\t|\n"

        stateTableItems.forEach {
            str += "${it.state}\t|\t${it.getNewState()}\t|\t${it.getNewState(false)}\n"
        }

        return str
    }
}