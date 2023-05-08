package org.sitecenter.geoip.model;

import lombok.Data;

@Data
public class GeoIP {
    private String ipAddress;
    private String country;
    private String countryIsoCode;
    private String city;
    private String fullLocation;
    private Double latitude;
    private Double longitude;
    private boolean found;

}
