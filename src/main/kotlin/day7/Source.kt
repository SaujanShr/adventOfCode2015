package day7

class Source: SignalProvider {
    override var value: UShort = 0U

    constructor(value: UShort) {
        this.value = value
    }
}