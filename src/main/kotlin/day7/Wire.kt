package day7

import day7.GateType.*

class Wire: SignalProvider {
    private val unresolved = mutableSetOf<SignalProvider>()
    private val dependencyTo = mutableSetOf<SignalProvider>()
    private val dependencyOf = mutableSetOf<Wire>()
    private var type = UNKNOWN

    override var value: UShort = 0U

    fun isRawInput(): Boolean = dependencyTo.all { it is Source }

    fun setRawInput(input: UShort) {
        dependencyTo.clear()
        unresolved.clear()

        type = SOURCE
        dependencyTo.add(Source(input))
    }

    fun setType(type: GateType) {
        this.type = type
    }

    fun addDependencyToSource(dependency: Source) {
        dependencyTo.add(dependency)
    }

    fun addDependencyToWire(dependency: Wire) {
        dependencyTo.add(dependency)
        unresolved.add(dependency)
    }

    fun addDependencyOf(dependency: Wire) {
        dependencyOf.add(dependency)
    }

    fun resolveRawInput() {
        value = resolveValue()

        for (dependency in dependencyOf) {
            dependency.resolveDependency(this)
        }
    }

    fun resolveDependency(wire: Wire) {
        unresolved.remove(wire)
        if (unresolved.isNotEmpty()) {
            return
        }

        value = resolveValue()

        for (dependency in dependencyOf) {
            dependency.resolveDependency(this)
        }
    }

    private fun resolveValue() = when (type) {
            SOURCE -> dependencyTo.first().value
            NOT -> dependencyTo.first().value.inv()
            AND -> dependencyTo.first().value and dependencyTo.last().value
            OR -> dependencyTo.first().value or dependencyTo.last().value
            LSHIFT -> (dependencyTo.first().value.toUInt() shl dependencyTo.last().value.toInt()).toUShort()
            RSHIFT -> (dependencyTo.first().value.toUInt() shr dependencyTo.last().value.toInt()).toUShort()
            UNKNOWN -> throw Exception("Not possible")
    }

    fun resetDependencies() {
        unresolved.clear()
        dependencyTo
            .filterIsInstance<Wire>()
            .forEach { unresolved.add(it) }
    }
}