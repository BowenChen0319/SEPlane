package Models;

public class Route {
    private String street;
    private int meters;

    public Route(String street, int meters) {
        this.street = street;
        this.meters = meters;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public int getMeters() {
        return meters;
    }

    public void setMeters(int meters) {
        this.meters = meters;
    }

    @Override
    public String toString() {
        return "Route{" +
                "street='" + street + '\'' +
                ", meters=" + meters +
                '}';
    }
}
