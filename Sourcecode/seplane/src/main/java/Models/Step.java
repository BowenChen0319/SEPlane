
package Models;

import Toolbox.Directions;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.awt.event.ActionEvent;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;


public class Step {

    @SerializedName("geocoded_waypoints")
    @Expose
    private List<GeocodedWaypoint> geocodedWaypoints = null;
    @SerializedName("routes")
    @Expose
    private List<Route> routes = null;
    @SerializedName("status")
    @Expose
    private String status;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Step() {
    }

    /**
     * 
     * @param routes
     * @param geocodedWaypoints
     * @param status
     */
    public Step(List<GeocodedWaypoint> geocodedWaypoints, List<Route> routes, String status) {
        super();
        this.geocodedWaypoints = geocodedWaypoints;
        this.routes = routes;
        this.status = status;
    }

    public List<GeocodedWaypoint> getGeocodedWaypoints() {
        return geocodedWaypoints;
    }

    public void setGeocodedWaypoints(List<GeocodedWaypoint> geocodedWaypoints) {
        this.geocodedWaypoints = geocodedWaypoints;
    }

    public List<Route> getRoutes() {
        return routes;
    }

    public void setRoutes(List<Route> routes) {
        this.routes = routes;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("geocodedWaypoints", geocodedWaypoints).append("routes", routes).append("status", status).toString();
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
        private List<Step_> steps = null;
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
        public Leg(Distance distance, Duration duration, String endAddress, EndLocation endLocation, String startAddress, StartLocation startLocation, List<Step_> steps, List<Object> trafficSpeedEntry, List<Object> viaWaypoint) {
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

        public List<Step_> getSteps() {
            return steps;
        }

        public void setSteps(List<Step_> steps) {
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

    public static class Northeast {

        @SerializedName("lat")
        @Expose
        private Float lat;
        @SerializedName("lng")
        @Expose
        private Float lng;

        /**
         * No args constructor for use in serialization
         *
         */
        public Northeast() {
        }

        /**
         *
         * @param lng
         * @param lat
         */
        public Northeast(Float lat, Float lng) {
            super();
            this.lat = lat;
            this.lng = lng;
        }

        public Float getLat() {
            return lat;
        }

        public void setLat(Float lat) {
            this.lat = lat;
        }

        public Float getLng() {
            return lng;
        }

        public void setLng(Float lng) {
            this.lng = lng;
        }

        @Override
        public String toString() {
            return new ToStringBuilder(this).append("lat", lat).append("lng", lng).toString();
        }

    }

    public static class OverviewPolyline {

        @SerializedName("points")
        @Expose
        private String points;

        /**
         * No args constructor for use in serialization
         *
         */
        public OverviewPolyline() {
        }

        /**
         *
         * @param points
         */
        public OverviewPolyline(String points) {
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

    public static class Route {

        @SerializedName("bounds")
        @Expose
        private Bounds bounds;
        @SerializedName("copyrights")
        @Expose
        private String copyrights;
        @SerializedName("legs")
        @Expose
        private List<Leg> legs = null;
        @SerializedName("overview_polyline")
        @Expose
        private OverviewPolyline overviewPolyline;
        @SerializedName("summary")
        @Expose
        private String summary;
        @SerializedName("warnings")
        @Expose
        private List<Object> warnings = null;
        @SerializedName("waypoint_order")
        @Expose
        private List<Object> waypointOrder = null;

        /**
         * No args constructor for use in serialization
         *
         */
        public Route() {
        }

        /**
         *
         * @param summary
         * @param overviewPolyline
         * @param copyrights
         * @param legs
         * @param warnings
         * @param bounds
         * @param waypointOrder
         */
        public Route(Bounds bounds, String copyrights, List<Leg> legs, OverviewPolyline overviewPolyline, String summary, List<Object> warnings, List<Object> waypointOrder) {
            super();
            this.bounds = bounds;
            this.copyrights = copyrights;
            this.legs = legs;
            this.overviewPolyline = overviewPolyline;
            this.summary = summary;
            this.warnings = warnings;
            this.waypointOrder = waypointOrder;
        }

        public Bounds getBounds() {
            return bounds;
        }

        public void setBounds(Bounds bounds) {
            this.bounds = bounds;
        }

        public String getCopyrights() {
            return copyrights;
        }

        public void setCopyrights(String copyrights) {
            this.copyrights = copyrights;
        }

        public List<Leg> getLegs() {
            return legs;
        }

        public void setLegs(List<Leg> legs) {
            this.legs = legs;
        }

        public OverviewPolyline getOverviewPolyline() {
            return overviewPolyline;
        }

        public void setOverviewPolyline(OverviewPolyline overviewPolyline) {
            this.overviewPolyline = overviewPolyline;
        }

        public String getSummary() {
            return summary;
        }

        public void setSummary(String summary) {
            this.summary = summary;
        }

        public List<Object> getWarnings() {
            return warnings;
        }

        public void setWarnings(List<Object> warnings) {
            this.warnings = warnings;
        }

        public List<Object> getWaypointOrder() {
            return waypointOrder;
        }

        public void setWaypointOrder(List<Object> waypointOrder) {
            this.waypointOrder = waypointOrder;
        }

        @Override
        public String toString() {
            return new ToStringBuilder(this).append("bounds", bounds).append("copyrights", copyrights).append("legs", legs).append("overviewPolyline", overviewPolyline).append("summary", summary).append("warnings", warnings).append("waypointOrder", waypointOrder).toString();
        }

    }

    public static class Southwest {

        @SerializedName("lat")
        @Expose
        private Float lat;
        @SerializedName("lng")
        @Expose
        private Float lng;

        /**
         * No args constructor for use in serialization
         *
         */
        public Southwest() {
        }

        /**
         *
         * @param lng
         * @param lat
         */
        public Southwest(Float lat, Float lng) {
            super();
            this.lat = lat;
            this.lng = lng;
        }

        public Float getLat() {
            return lat;
        }

        public void setLat(Float lat) {
            this.lat = lat;
        }

        public Float getLng() {
            return lng;
        }

        public void setLng(Float lng) {
            this.lng = lng;
        }

        @Override
        public String toString() {
            return new ToStringBuilder(this).append("lat", lat).append("lng", lng).toString();
        }

    }

    public static class StartLocation {

        @SerializedName("lat")
        @Expose
        private Float lat;
        @SerializedName("lng")
        @Expose
        private Float lng;

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
        public StartLocation(Float lat, Float lng) {
            super();
            this.lat = lat;
            this.lng = lng;
        }

        public Float getLat() {
            return lat;
        }

        public void setLat(Float lat) {
            this.lat = lat;
        }

        public Float getLng() {
            return lng;
        }

        public void setLng(Float lng) {
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
        private Float lat;
        @SerializedName("lng")
        @Expose
        private Float lng;

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
        public StartLocation_(Float lat, Float lng) {
            super();
            this.lat = lat;
            this.lng = lng;
        }

        public Float getLat() {
            return lat;
        }

        public void setLat(Float lat) {
            this.lat = lat;
        }

        public Float getLng() {
            return lng;
        }

        public void setLng(Float lng) {
            this.lng = lng;
        }

        @Override
        public String toString() {
            return new ToStringBuilder(this).append("lat", lat).append("lng", lng).toString();
        }

    }

    public static class Step_ {

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
        @SerializedName("maneuver")
        @Expose
        private String maneuver;

        /**
         * No args constructor for use in serialization
         *
         */
        public Step_() {
        }

        /**
         *
         * @param duration
         * @param htmlInstructions
         * @param distance
         * @param startLocation
         * @param endLocation
         * @param polyline
         * @param maneuver
         * @param travelMode
         */
        public Step_(Distance_ distance, Duration_ duration, EndLocation_ endLocation, String htmlInstructions, Polyline polyline, StartLocation_ startLocation, String travelMode, String maneuver) {
            super();
            this.distance = distance;
            this.duration = duration;
            this.endLocation = endLocation;
            this.htmlInstructions = htmlInstructions;
            this.polyline = polyline;
            this.startLocation = startLocation;
            this.travelMode = travelMode;
            this.maneuver = maneuver;
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

        public String getManeuver() {
            return maneuver;
        }

        public void setManeuver(String maneuver) {
            this.maneuver = maneuver;
        }

        @Override
        public String toString() {
            return new ToStringBuilder(this).append("distance", distance).append("duration", duration).append("endLocation", endLocation).append("htmlInstructions", htmlInstructions).append("polyline", polyline).append("startLocation", startLocation).append("travelMode", travelMode).append("maneuver", maneuver).toString();
        }

    }

    public static class GeocodedWaypoint {

        @SerializedName("geocoder_status")
        @Expose
        private String geocoderStatus;
        @SerializedName("place_id")
        @Expose
        private String placeId;
        @SerializedName("types")
        @Expose
        private List<String> types = null;

        /**
         * No args constructor for use in serialization
         *
         */
        public GeocodedWaypoint() {
        }

        /**
         *
         * @param types
         * @param placeId
         * @param geocoderStatus
         */
        public GeocodedWaypoint(String geocoderStatus, String placeId, List<String> types) {
            super();
            this.geocoderStatus = geocoderStatus;
            this.placeId = placeId;
            this.types = types;
        }

        public String getGeocoderStatus() {
            return geocoderStatus;
        }

        public void setGeocoderStatus(String geocoderStatus) {
            this.geocoderStatus = geocoderStatus;
        }

        public String getPlaceId() {
            return placeId;
        }

        public void setPlaceId(String placeId) {
            this.placeId = placeId;
        }

        public List<String> getTypes() {
            return types;
        }

        public void setTypes(List<String> types) {
            this.types = types;
        }

        @Override
        public String toString() {
            return new ToStringBuilder(this).append("geocoderStatus", geocoderStatus).append("placeId", placeId).append("types", types).toString();
        }

    }

    public static class Bounds {

        @SerializedName("northeast")
        @Expose
        private Northeast northeast;
        @SerializedName("southwest")
        @Expose
        private Southwest southwest;

        /**
         * No args constructor for use in serialization
         *
         */
        public Bounds() {
        }

        /**
         *
         * @param southwest
         * @param northeast
         */
        public Bounds(Northeast northeast, Southwest southwest) {
            super();
            this.northeast = northeast;
            this.southwest = southwest;
        }

        public Northeast getNortheast() {
            return northeast;
        }

        public void setNortheast(Northeast northeast) {
            this.northeast = northeast;
        }

        public Southwest getSouthwest() {
            return southwest;
        }

        public void setSouthwest(Southwest southwest) {
            this.southwest = southwest;
        }

        @Override
        public String toString() {
            return new ToStringBuilder(this).append("northeast", northeast).append("southwest", southwest).toString();
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

    public static class EndLocation {

        @SerializedName("lat")
        @Expose
        private Float lat;
        @SerializedName("lng")
        @Expose
        private Float lng;

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
        public EndLocation(Float lat, Float lng) {
            super();
            this.lat = lat;
            this.lng = lng;
        }

        public Float getLat() {
            return lat;
        }

        public void setLat(Float lat) {
            this.lat = lat;
        }

        public Float getLng() {
            return lng;
        }

        public void setLng(Float lng) {
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
        private Float lat;
        @SerializedName("lng")
        @Expose
        private Float lng;

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
        public EndLocation_(Float lat, Float lng) {
            super();
            this.lat = lat;
            this.lng = lng;
        }

        public Float getLat() {
            return lat;
        }

        public void setLat(Float lat) {
            this.lat = lat;
        }

        public Float getLng() {
            return lng;
        }

        public void setLng(Float lng) {
            this.lng = lng;
        }

        @Override
        public String toString() {
            return new ToStringBuilder(this).append("lat", lat).append("lng", lng).toString();
        }

    }
}
