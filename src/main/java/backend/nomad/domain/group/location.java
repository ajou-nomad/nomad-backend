package backend.nomad.domain.group;

import lombok.Getter;

import javax.persistence.Embeddable;

@Embeddable
@Getter
public class location {
    private Double latitude;
    private Double longitude;
    private String address;
    private String building;

    public location(Double latitude, Double longitude, String address, String building) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.address = address;
        this.building = building;
    }
}
