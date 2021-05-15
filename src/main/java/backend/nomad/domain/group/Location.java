package backend.nomad.domain.group;

import lombok.Getter;

import javax.persistence.Embeddable;

@Embeddable
@Getter
public class Location {
    private Double latitude;
    private Double longitude;
    private String address;
    private String building;

    public Location(Double latitude, Double longitude, String address, String building) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.address = address;
        this.building = building;
    }
}
