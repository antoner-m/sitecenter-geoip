package org.sitecenter.geoip.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class GeoIP {
    private String ipAddress;
    private String country;
    private String countryIsoCode;
    private String city;
    private String fullLocation;
    private Double latitude;
    private Double longitude;
    private boolean found;
    public GeoIP(String ipAddress, boolean found) {
        this.ipAddress = ipAddress;
        this.found = found;
    }
}
