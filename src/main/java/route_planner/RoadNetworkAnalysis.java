package route_planner;


import java.util.*;

/**
 * Utility class that provides analysis methods for junctions (cities) and roads.
 */
public class RoadNetworkAnalysis {

    /**
     * Represents the road network as a mapping between junctions and roads.
     */
    private final Map<Junction, Road> roadNetwork;

    public RoadNetworkAnalysis(Map<Junction, Road> connections) {
        this.roadNetwork = connections;
    }

    /**
     * Finds all cities (junctions) in the same province as the given city.
     * Returns an empty list if no cities match.
     */
    public List<Junction> citiesInSameProvince(Junction city)  {
        // TODO: Implement this method using Java Streams
        List <Junction> junctions = new ArrayList<>();

        this.roadNetwork.forEach((junction, road) -> {junctions.add(junction);});

        return junctions.stream().filter(j -> j.getProvince().equals(city.getProvince()))
                .filter(j -> !j.equals(city)).toList();

        return null;
    }

    /**
     * Calculates the total length of all roads.
     */
    public double totalRoadLength() {
        // TODO: Implement this method using Java Streams


        return this.roadNetwork.values().stream().mapToDouble(Road::getLength).sum();
    }

    /**
     * Returns the names of the top 5 most populated cities in the road network.
     */
    public List<String> top5CityNamesByPopulation() {
        // TODO: Implement this method using Java Streams

       return
               // Stream from all the cities
               this.roadNetwork.keySet().stream()
                       // sorted on the population and the reverse is that the biggest numbers comes on top.
                       .sorted(Comparator.comparing(Junction::getPopulation).reversed())
                       // a limit of the 5 biggest numbers
                       .limit(5)
                       // Returns only the city name
                       .map(Junction::getName)
                       .toList();
    }

    /**
     * Finds the total length of all roads starting from cities with population above a threshold.
     */
    public double totalLengthFromBigCities(int minPopulation) {
        // TODO: Implement this method using Java Streams
        return roadNetwork.entrySet().stream()
                .filter(entry -> entry.getKey().getPopulation() >= minPopulation)
                .mapToDouble(entry -> entry.getValue().getLength())
                .sum();
    }

    /**
     * Calculates the total length of all roads for each province.
     *
     * @return a map where the key is the province name and the value is the total
     * length of roads in that province; provinces with no roads will
     * not appear in the map.
     */
    public Map<String, Double> totalRoadLengthPerProvince() {
        // TODO: Implement this method using Java Streams

        return null;
    }


    /**
     * Returns all roads where the speed limit is higher than the average speed limit.
     */
    public List<Road> roadsFasterThanAverage() {
//        // TODO: Implement this method using Java Streams
//        double average  = this.roadNetwork.values().stream()
//                .mapToDouble(Road::getMaxSpeed).average().getAsDouble();
//
//
//        return this.roadNetwork.keySet().stream()
//                .filter(r -> r  average)
//                .;
    }

    /**
     * Finds all provinces that have more than X cities.
     * Use Collectors.groupingBy(), Collectors.counting(),
     */
    public List<String> provincesWithMoreThanXCities(int x) {
        // TODO: Implement this method using Java Streams

        return null;
    }
}
