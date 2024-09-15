package org.sitecenter.geoip.model;

import java.io.Serializable;

public class GeoIP implements Serializable {
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

    public GeoIP() {
    }

    public String getIpAddress() {
        return this.ipAddress;
    }

    public String getCountry() {
        return this.country;
    }

    public String getCountryIsoCode() {
        return this.countryIsoCode;
    }

    public String getCity() {
        return this.city;
    }

    public String getFullLocation() {
        return this.fullLocation;
    }

    public Double getLatitude() {
        return this.latitude;
    }

    public Double getLongitude() {
        return this.longitude;
    }

    public boolean isFound() {
        return this.found;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setCountryIsoCode(String countryIsoCode) {
        this.countryIsoCode = countryIsoCode;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setFullLocation(String fullLocation) {
        this.fullLocation = fullLocation;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public void setFound(boolean found) {
        this.found = found;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof GeoIP)) return false;
        final GeoIP other = (GeoIP) o;
        if (!other.canEqual((Object) this)) return false;
        final Object this$ipAddress = this.getIpAddress();
        final Object other$ipAddress = other.getIpAddress();
        if (this$ipAddress == null ? other$ipAddress != null : !this$ipAddress.equals(other$ipAddress)) return false;
        final Object this$country = this.getCountry();
        final Object other$country = other.getCountry();
        if (this$country == null ? other$country != null : !this$country.equals(other$country)) return false;
        final Object this$countryIsoCode = this.getCountryIsoCode();
        final Object other$countryIsoCode = other.getCountryIsoCode();
        if (this$countryIsoCode == null ? other$countryIsoCode != null : !this$countryIsoCode.equals(other$countryIsoCode))
            return false;
        final Object this$city = this.getCity();
        final Object other$city = other.getCity();
        if (this$city == null ? other$city != null : !this$city.equals(other$city)) return false;
        final Object this$fullLocation = this.getFullLocation();
        final Object other$fullLocation = other.getFullLocation();
        if (this$fullLocation == null ? other$fullLocation != null : !this$fullLocation.equals(other$fullLocation))
            return false;
        final Object this$latitude = this.getLatitude();
        final Object other$latitude = other.getLatitude();
        if (this$latitude == null ? other$latitude != null : !this$latitude.equals(other$latitude)) return false;
        final Object this$longitude = this.getLongitude();
        final Object other$longitude = other.getLongitude();
        if (this$longitude == null ? other$longitude != null : !this$longitude.equals(other$longitude)) return false;
        if (this.isFound() != other.isFound()) return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof GeoIP;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $ipAddress = this.getIpAddress();
        result = result * PRIME + ($ipAddress == null ? 43 : $ipAddress.hashCode());
        final Object $country = this.getCountry();
        result = result * PRIME + ($country == null ? 43 : $country.hashCode());
        final Object $countryIsoCode = this.getCountryIsoCode();
        result = result * PRIME + ($countryIsoCode == null ? 43 : $countryIsoCode.hashCode());
        final Object $city = this.getCity();
        result = result * PRIME + ($city == null ? 43 : $city.hashCode());
        final Object $fullLocation = this.getFullLocation();
        result = result * PRIME + ($fullLocation == null ? 43 : $fullLocation.hashCode());
        final Object $latitude = this.getLatitude();
        result = result * PRIME + ($latitude == null ? 43 : $latitude.hashCode());
        final Object $longitude = this.getLongitude();
        result = result * PRIME + ($longitude == null ? 43 : $longitude.hashCode());
        result = result * PRIME + (this.isFound() ? 79 : 97);
        return result;
    }

    public String toString() {
        return "GeoIP(ipAddress=" + this.getIpAddress() + ", country=" + this.getCountry() + ", countryIsoCode=" + this.getCountryIsoCode() + ", city=" + this.getCity() + ", fullLocation=" + this.getFullLocation() + ", latitude=" + this.getLatitude() + ", longitude=" + this.getLongitude() + ", found=" + this.isFound() + ")";
    }
}
