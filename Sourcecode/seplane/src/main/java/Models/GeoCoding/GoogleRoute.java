
package Models.GeoCoding;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class GoogleRoute {

    @SerializedName("routes")
    @Expose
    private List<Route> routes = null;

    /**
     * No args constructor for use in serialization
     * 
     */
    public GoogleRoute() {
    }

    /**
     * 
     * @param routes
     */
    public GoogleRoute(List<Route> routes) {
        super();
        this.routes = routes;
    }

    public List<Route> getRoutes() {
        return routes;
    }

    public void setRoutes(List<Route> routes) {
        this.routes = routes;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("routes", routes).toString();
    }

    public static class Duration_ {

        @SerializedName("text")
        @Expose
        private String text;
        @SerializedName("value")
        @Expose
        private Integer value;

        /**
         * No args constructor for use in serialization
         *
         */
        public Duration_() {
        }

        /**
         *
         * @param text
         * @param value
         */
        public Duration_(String text, Integer value) {
            super();
            this.text = text;
            this.value = value;
        }

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }

        public Integer getValue() {
            return value;
        }

        public void setValue(Integer value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return new ToStringBuilder(this).append("text", text).append("value", value).toString();
        }

    }

    public static class Distance_ {

        @SerializedName("text")
        @Expose
        private String text;
        @SerializedName("value")
        @Expose
        private Integer value;

        /**
         * No args constructor for use in serialization
         *
         */
        public Distance_() {
        }

        /**
         *
         * @param text
         * @param value
         */
        public Distance_(String text, Integer value) {
            super();
            this.text = text;
            this.value = value;
        }

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }

        public Integer getValue() {
            return value;
        }

        public void setValue(Integer value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return new ToStringBuilder(this).append("text", text).append("value", value).toString();
        }

    }

    public static class Distance {

        @SerializedName("text")
        @Expose
        private String text;
        @SerializedName("value")
        @Expose
        private Integer value;

        /**
         * No args constructor for use in serialization
         *
         */
        public Distance() {
        }

        /**
         *
         * @param text
         * @param value
         */
        public Distance(String text, Integer value) {
            super();
            this.text = text;
            this.value = value;
        }

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }

        public Integer getValue() {
            return value;
        }

        public void setValue(Integer value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return new ToStringBuilder(this).append("text", text).append("value", value).toString();
        }

    }

    public static class Step {

        @SerializedName("distance")
        @Expose
        private Distance_ distance;
        @SerializedName("duration")
        @Expose
        private Duration_ duration;
        @SerializedName("end_location")
        @Expose
        private EndLocation_ endLocation;
        @SerializedName("html_instructions")
        @Expose
        private String htmlInstructions;
        @SerializedName("polyline")
        @Expose
        private Polyline polyline;
        @SerializedName("start_location")
        @Expose
        private StartLocation_ startLocation;
        @SerializedName("travel_mode")
        @Expose
        private String travelMode;

        /**
         * No args constructor for use in serialization
         *
         */
        public Step() {
        }

        /**
         *
         * @param duration
         * @param htmlInstructions
         * @param distance
         * @param startLocation
         * @param endLocation
         * @param polyline
         * @param travelMode
         */
        public Step(Distance_ distance, Duration_ duration, EndLocation_ endLocation, String htmlInstructions, Polyline polyline, StartLocation_ startLocation, String travelMode) {
            super();
            this.distance = distance;
            this.duration = duration;
            this.endLocation = endLocation;
            this.htmlInstructions = htmlInstructions;
            this.polyline = polyline;
            this.startLocation = startLocation;
            this.travelMode = travelMode;
        }

        public Distance_ getDistance() {
            return distance;
        }

        public void setDistance(Distance_ distance) {
            this.distance = distance;
        }

        public Duration_ getDuration() {
            return duration;
        }

        public void setDuration(Duration_ duration) {
            this.duration = duration;
        }

        public EndLocation_ getEndLocation() {
            return endLocation;
        }

        public void setEndLocation(EndLocation_ endLocation) {
            this.endLocation = endLocation;
        }

        public String getHtmlInstructions() {
            return htmlInstructions;
        }

        public void setHtmlInstructions(String htmlInstructions) {
            this.htmlInstructions = htmlInstructions;
        }

        public Polyline getPolyline() {
            return polyline;
        }

        public void setPolyline(Polyline polyline) {
            this.polyline = polyline;
        }

        public StartLocation_ getStartLocation() {
            return startLocation;
        }

        public void setStartLocation(StartLocation_ startLocation) {
            this.startLocation = startLocation;
        }

        public String getTravelMode() {
            return travelMode;
        }

        public void setTravelMode(String travelMode) {
            this.travelMode = travelMode;
        }

        @Override
        public String toString() {
            return new ToStringBuilder(this).append("distance", distance).append("duration", duration).append("endLocation", endLocation).append("htmlInstructions", htmlInstructions).append("polyline", polyline).append("startLocation", startLocation).append("travelMode", travelMode).toString();
        }

    }

    public static class Duration {

        @SerializedName("text")
        @Expose
        private String text;
        @SerializedName("value")
        @Expose
        private Integer value;

        /**
         * No args constructor for use in serialization
         *
         */
        public Duration() {
        }

        /**
         *
         * @param text
         * @param value
         */
        public Duration(String text, Integer value) {
            super();
            this.text = text;
            this.value = value;
        }

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }

        public Integer getValue() {
            return value;
        }

        public void setValue(Integer value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return new ToStringBuilder(this).append("text", text).append("value", value).toString();
        }

    }

    public static class StartLocation {

        @SerializedName("lat")
        @Expose
        private Double lat;
        @SerializedName("lng")
        @Expose
        private Double lng;

        /**
         * No args constructor for use in serialization
         *
         */
        public StartLocation() {
        }

        /**
         *
         * @param lng
         * @param lat
         */
        public StartLocation(Double lat, Double lng) {
            super();
            this.lat = lat;
            this.lng = lng;
        }

        public Double getLat() {
            return lat;
        }

        public void setLat(Double lat) {
            this.lat = lat;
        }

        public Double getLng() {
            return lng;
        }

        public void setLng(Double lng) {
            this.lng = lng;
        }

        @Override
        public String toString() {
            return new ToStringBuilder(this).append("lat", lat).append("lng", lng).toString();
        }

    }

    public static class Route {

        @SerializedName("legs")
        @Expose
        private List<Leg> legs = null;

        /**
         * No args constructor for use in serialization
         *
         */
        public Route() {
        }

        /**
         *
         * @param legs
         */
        public Route(List<Leg> legs) {
            super();
            this.legs = legs;
        }

        public List<Leg> getLegs() {
            return legs;
        }

        public void setLegs(List<Leg> legs) {
            this.legs = legs;
        }

        @Override
        public String toString() {
            return new ToStringBuilder(this).append("legs", legs).toString();
        }

    }

    public static class Leg {

        @SerializedName("distance")
        @Expose
        private Distance distance;
        @SerializedName("duration")
        @Expose
        private Duration duration;
        @SerializedName("end_address")
        @Expose
        private String endAddress;
        @SerializedName("end_location")
        @Expose
        private EndLocation endLocation;
        @SerializedName("start_address")
        @Expose
        private String startAddress;
        @SerializedName("start_location")
        @Expose
        private StartLocation startLocation;
        @SerializedName("steps")
        @Expose
        private List<Step> steps = null;
        @SerializedName("traffic_speed_entry")
        @Expose
        private List<Object> trafficSpeedEntry = null;
        @SerializedName("via_waypoint")
        @Expose
        private List<Object> viaWaypoint = null;

        /**
         * No args constructor for use in serialization
         *
         */
        public Leg() {
        }

        /**
         *
         * @param duration
         * @param distance
         * @param startLocation
         * @param startAddress
         * @param trafficSpeedEntry
         * @param viaWaypoint
         * @param steps
         * @param endAddress
         * @param endLocation
         */
        public Leg(Distance distance, Duration duration, String endAddress, EndLocation endLocation, String startAddress, StartLocation startLocation, List<Step> steps, List<Object> trafficSpeedEntry, List<Object> viaWaypoint) {
            super();
            this.distance = distance;
            this.duration = duration;
            this.endAddress = endAddress;
            this.endLocation = endLocation;
            this.startAddress = startAddress;
            this.startLocation = startLocation;
            this.steps = steps;
            this.trafficSpeedEntry = trafficSpeedEntry;
            this.viaWaypoint = viaWaypoint;
        }

        public Distance getDistance() {
            return distance;
        }

        public void setDistance(Distance distance) {
            this.distance = distance;
        }

        public Duration getDuration() {
            return duration;
        }

        public void setDuration(Duration duration) {
            this.duration = duration;
        }

        public String getEndAddress() {
            return endAddress;
        }

        public void setEndAddress(String endAddress) {
            this.endAddress = endAddress;
        }

        public EndLocation getEndLocation() {
            return endLocation;
        }

        public void setEndLocation(EndLocation endLocation) {
            this.endLocation = endLocation;
        }

        public String getStartAddress() {
            return startAddress;
        }

        public void setStartAddress(String startAddress) {
            this.startAddress = startAddress;
        }

        public StartLocation getStartLocation() {
            return startLocation;
        }

        public void setStartLocation(StartLocation startLocation) {
            this.startLocation = startLocation;
        }

        public List<Step> getSteps() {
            return steps;
        }

        public void setSteps(List<Step> steps) {
            this.steps = steps;
        }

        public List<Object> getTrafficSpeedEntry() {
            return trafficSpeedEntry;
        }

        public void setTrafficSpeedEntry(List<Object> trafficSpeedEntry) {
            this.trafficSpeedEntry = trafficSpeedEntry;
        }

        public List<Object> getViaWaypoint() {
            return viaWaypoint;
        }

        public void setViaWaypoint(List<Object> viaWaypoint) {
            this.viaWaypoint = viaWaypoint;
        }

        @Override
        public String toString() {
            return new ToStringBuilder(this).append("distance", distance).append("duration", duration).append("endAddress", endAddress).append("endLocation", endLocation).append("startAddress", startAddress).append("startLocation", startLocation).append("steps", steps).append("trafficSpeedEntry", trafficSpeedEntry).append("viaWaypoint", viaWaypoint).toString();
        }

    }

    public static class Polyline {

        @SerializedName("points")
        @Expose
        private String points;

        /**
         * No args constructor for use in serialization
         *
         */
        public Polyline() {
        }

        /**
         *
         * @param points
         */
        public Polyline(String points) {
            super();
            this.points = points;
        }

        public String getPoints() {
            return points;
        }

        public void setPoints(String points) {
            this.points = points;
        }

        @Override
        public String toString() {
            return new ToStringBuilder(this).append("points", points).toString();
        }

    }

    public static class EndLocation {

        @SerializedName("lat")
        @Expose
        private Double lat;
        @SerializedName("lng")
        @Expose
        private Double lng;

        /**
         * No args constructor for use in serialization
         *
         */
        public EndLocation() {
        }

        /**
         *
         * @param lng
         * @param lat
         */
        public EndLocation(Double lat, Double lng) {
            super();
            this.lat = lat;
            this.lng = lng;
        }

        public Double getLat() {
            return lat;
        }

        public void setLat(Double lat) {
            this.lat = lat;
        }

        public Double getLng() {
            return lng;
        }

        public void setLng(Double lng) {
            this.lng = lng;
        }

        @Override
        public String toString() {
            return new ToStringBuilder(this).append("lat", lat).append("lng", lng).toString();
        }

    }

    public static class EndLocation_ {

        @SerializedName("lat")
        @Expose
        private Double lat;
        @SerializedName("lng")
        @Expose
        private Double lng;

        /**
         * No args constructor for use in serialization
         *
         */
        public EndLocation_() {
        }

        /**
         *
         * @param lng
         * @param lat
         */
        public EndLocation_(Double lat, Double lng) {
            super();
            this.lat = lat;
            this.lng = lng;
        }

        public Double getLat() {
            return lat;
        }

        public void setLat(Double lat) {
            this.lat = lat;
        }

        public Double getLng() {
            return lng;
        }

        public void setLng(Double lng) {
            this.lng = lng;
        }

        @Override
        public String toString() {
            return new ToStringBuilder(this).append("lat", lat).append("lng", lng).toString();
        }

    }

    public static class StartLocation_ {

        @SerializedName("lat")
        @Expose
        private Double lat;
        @SerializedName("lng")
        @Expose
        private Double lng;

        /**
         * No args constructor for use in serialization
         *
         */
        public StartLocation_() {
        }

        /**
         *
         * @param lng
         * @param lat
         */
        public StartLocation_(Double lat, Double lng) {
            super();
            this.lat = lat;
            this.lng = lng;
        }

        public Double getLat() {
            return lat;
        }

        public void setLat(Double lat) {
            this.lat = lat;
        }

        public Double getLng() {
            return lng;
        }

        public void setLng(Double lng) {
            this.lng = lng;
        }

        @Override
        public String toString() {
            return new ToStringBuilder(this).append("lat", lat).append("lng", lng).toString();
        }

    }
}
