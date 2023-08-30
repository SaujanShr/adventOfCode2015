package day7

enum class GateType {
    SOURCE,
    NOT,
    AND,
    OR,
    LSHIFT,
    RSHIFT,
    UNKNOWN;

    companion object {
        fun getValue(gate: List<String>): GateType {
            return when (gate.size) {
                1 -> SOURCE
                2 -> NOT
                3 -> when (gate[1]) {
                    "AND" -> AND
                    "OR" -> OR
                    "LSHIFT" -> LSHIFT
                    "RSHIFT" -> RSHIFT
                    else -> UNKNOWN
                }
                else -> UNKNOWN
            }
        }
    }
}