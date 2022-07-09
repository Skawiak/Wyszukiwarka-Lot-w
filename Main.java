import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        FlightDatabase database = new FlightDatabase();

        database.checkIfFlightExists("Paris", "Madrid");
        database.displayFlightsFromCity("Paris");
        database.displayFlightsToCity("Warsaw");

        ArrayList<String> cities = database.getCities();
        System.out.println(cities);
        Flight cheapestFlight = database.getCheapestFlight();
        System.out.println("Cheapest flight: " + cheapestFlight.getDetails());
        ArrayList<Journey> journeys = database.getFlights("Paris", "Porto");
        System.out.println(journeys);

    }
}



 class Flight{
    String arrival;
    String departure;
    int price;

    public Flight(String arrival, String departure, int price){
        this.arrival = arrival;
        this.departure = departure;
        this.price = price;
    }
     public String getDetails(){
         return "Flight from " + this.departure + " to " + this.arrival + " costs " + this.price;
     }
 }

 class FlightDatabase {
     ArrayList<Flight> flights = new ArrayList<Flight>();

     public FlightDatabase() { // baza lotów
         this.flights.add(new Flight("Berlin", "Tokyo", 1900));
         this.flights.add(new Flight("Paris", "Berlin", 290));
         this.flights.add(new Flight("Warsaw", "Paris", 1750));
         this.flights.add(new Flight("Madrid", "Berlin", 900));
         this.flights.add(new Flight("Berlin", "Warsaw", 700));
         this.flights.add(new Flight("Paris", "Madrid", 450));
         this.flights.add(new Flight("Porto", "Warsaw", 320));
         this.flights.add(new Flight("Madrid", "Porto", 420));
         this.flights.add(new Flight("Warsaw", "Madrid", 666));
     }

     public void checkIfFlightExists(String start, String end) { // sprawdzanie czy dany lot istanieje
         boolean notExists = true;
         for (Flight flight : this.flights) { // uproszczenie przejscia przez liste
             if (start.equals(flight.departure) && end.equals(flight.arrival)) {
                 System.out.println("Flight exists :)");
                 notExists = false;
                 break;
             }
         }
         if (notExists) {
             System.out.println("Flight with given parameters does not exists :(");
         }
     }

     public ArrayList<Flight> getFlightsFromCity(String city) {
         ArrayList<Flight> results = new ArrayList<Flight>();
         for (Flight flight : this.flights) {
             if (city.equals(flight.departure)) {
                 results.add(flight);
             }
         }
         return results;
     }

     public ArrayList<Flight> getFlightsToCity(String city) {
         ArrayList<Flight> results = new ArrayList<Flight>();
         for (Flight flight : this.flights) {
             if (city.equals(flight.arrival)) {
                 results.add(flight);
             }
         }
         return results;
     }

     public void displayFlights(ArrayList<Flight> results) {
         if (results.isEmpty()) {
             System.out.println("Flight is not found");
         }
         for (Flight flight : results) {
             System.out.println(flight.getDetails());
         }
     }

     public void displayFlightsFromCity(String city) {
         ArrayList<Flight> results = getFlightsFromCity(city);
         displayFlights(results);
     }

     public void displayFlightsToCity(String city) {
         ArrayList<Flight> results = getFlightsToCity(city);
         displayFlights(results);
     }

     public ArrayList<String> getCities() {
         ArrayList<String> cities = new ArrayList<String>();
         for (Flight flight : this.flights) { // uproszczenie przejscia przez liste
             if (!cities.contains(flight.departure)) {
                 cities.add(flight.departure);
             }
             if (!cities.contains(flight.arrival)) {
                 cities.add(flight.arrival);
             }
         }
         return cities;
     }

     public Flight getCheapestFlight(){ //sprawdzanie najtanszego lotu
         Flight cheapestFlight = null;
         for(Flight flight : this.flights){
             if(cheapestFlight == null || flight.price < cheapestFlight.price){
                 cheapestFlight = flight;
             }
         }
         return cheapestFlight;
     }

     public Flight getCheapestFlightFromCity(String city){ // zwracanie najtanszego lotu z danego miasta z wszesniejszej listy
         ArrayList<Flight> fromCity = getFlightsFromCity(city);
         Flight cheapestFlight = null;
         for(Flight flight : fromCity){
             if(cheapestFlight == null || flight.price < cheapestFlight.price){
                 cheapestFlight = flight;
             }
         }
         return cheapestFlight;
     }

     public ArrayList<Journey> getFlights(String start, String end){
         ArrayList<Flight> starting = getFlightsFromCity(start);
         ArrayList<Flight> ending = getFlightsToCity(end);
         ArrayList<Journey> results = new ArrayList<Journey>();
         for(Flight first : starting){
             for(Flight second : ending){
                 if(first.arrival.equals(second.departure)){
                     results.add(new Journey(first, second));

                 }
             }
         }
         return results;
     }

 }

 class Journey{
    Flight first;
    Flight second;

    public Journey(Flight first, Flight second){
        this.first = first;
        this.second = second;
    }

    public String toString(){
        return "Flight from " + first.departure + " to " + second.arrival + " with stop at " + first.arrival + " costs " + (first.price + second.price);
    }
 }



//TODO 1. spośród lotów z przesiadką zwróć tylko najtańszy - dodatek
//TODO 2. Długość lotu we Flight
//TODO 3. dodać 2 miasta i w zależnosci od tago czy lot jest z przesiadką czy bez zwrócic wybrany




