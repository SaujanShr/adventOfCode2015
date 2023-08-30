package day9

class Location(val name: String) {
    private val routes = mutableMapOf<Location, Int>()

    fun addRoute(location: Location, distance: Int) {
        routes[location] = distance
    }

    fun getConnectedLocations(): Set<Location> = routes.keys

    fun getDistanceToLocation(location: Location): Int = routes[location] ?: 0
}