package day7

import day7.GateType.*
import getInput

val INPUT = getInput("day7")

fun run1(): String {
    val circuit = getCircuit(INPUT)
    val wires = getWires(circuit)
    processSignals(wires)
    val signals = getSignals(wires)

    return signals["a"]!!.toString()
}

fun run2(): String {
    val circuit = getCircuit(INPUT)
    val wires = getWires(circuit)

    processSignals(wires)

    val aSignal = wires["a"]!!.value

    wires.values.forEach(Wire::resetDependencies)
    wires["b"]!!.setRawInput(aSignal)

    processSignals(wires)
    val signals = getSignals(wires)

    return signals["a"]!!.toString()
}

fun getCircuit(instructions: String): List<List<String>> {
    return instructions
        .split('\n')
        .map { it.split(" -> ") }
}

fun getWires(circuit: List<List<String>>): Map<String,Wire> {
    val gates = circuit
        .map { (input, output) ->
            Pair(output, input.split(' '))
        }
        .toMap()

    val wires = gates
        .map { (output, _) ->
            Pair(output, Wire())
        }
        .toMap()

    wires.forEach { (output, wire) ->
        val gate = gates[output]!!

        val gateType = GateType.getValue(gates[output]!!)
        wire.setType(gateType)

        when (gateType) {
            SOURCE -> when (val value = gate.first().toUShortOrNull()) {
                null -> setWireConnection(wire, wires[gate.first()]!!)
                else -> setSourceConnection(wire, value)
            }
            NOT -> when (val value = gate.last().toUShortOrNull()) {
                null -> setWireConnection(wire, wires[gate.last()]!!)
                else -> setSourceConnection(wire, value)
            }
            AND, OR, LSHIFT, RSHIFT -> {
                when (val value = gate.first().toUShortOrNull()) {
                    null -> setWireConnection(wire, wires[gate.first()]!!)
                    else -> setSourceConnection(wire, value)
                }
                when (val value = gate.last().toUShortOrNull()) {
                    null -> setWireConnection(wire, wires[gate.last()]!!)
                    else -> setSourceConnection(wire, value)
                }
            }
            UNKNOWN -> {}
        }
    }

    return wires
}

fun setWireConnection(wire: Wire, inputWire: Wire) {
    wire.addDependencyToWire(inputWire)
    inputWire.addDependencyOf(wire)
}

fun setSourceConnection(wire: Wire, value: UShort) {
    val source = Source(value)

    wire.addDependencyToSource(source)
}

fun processSignals(wires: Map<String,Wire>) {
    wires.values
        .filter(Wire::isRawInput)
        .forEach(Wire::resolveRawInput)
}

fun getSignals(wires: Map<String,Wire>): Map<String,UShort> {
    return wires
        .map { (output, wire) -> Pair(output, wire.value) }
        .toMap()
}
