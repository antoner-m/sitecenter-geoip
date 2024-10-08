package org.sitecenter.geoip.service;

import com.maxmind.geoip2.model.CountryResponse;
import org.sitecenter.geoip.model.GeoIP;
import com.maxmind.geoip2.DatabaseReader;
import com.maxmind.geoip2.exception.GeoIp2Exception;
import com.maxmind.geoip2.model.CityResponse;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.InetAddress;
import java.util.Objects;

import static java.util.Objects.nonNull;

@Service
public class GeoIPLocationServiceImpl implements GeoIPLocationService {

    private final DatabaseReader cityDatabaseReader;
    private final DatabaseReader countryDatabaseReader;
    private static final String UNKNOWN = "UNKNOWN";

    public GeoIPLocationServiceImpl(
            @Qualifier("cityDatabaseReader")
            DatabaseReader cityDatabaseReader,
            @Qualifier("countryDatabaseReader")
            DatabaseReader countryDatabaseReader) {
        this.cityDatabaseReader = cityDatabaseReader;
        this.countryDatabaseReader = countryDatabaseReader;
    }


    /**
     * get user position by ip address
     *
     * @param ip String ip address
     * @return UserPositionDTO model
     * @throws IOException     if local database city not exist
     * @throws GeoIp2Exception if cannot get info by ip address
     */
    @Override
    public GeoIP getIpLocation(String ip) throws IOException, GeoIp2Exception {

        GeoIP position = new GeoIP();
        String location;

        InetAddress ipAddress = InetAddress.getByName(ip);

        position.setIpAddress(ip);
        position.setFound(false);

        String continent = "";
        String country = "";
        String countryCode = "";
        String city = "";

        CityResponse cityResponse = cityDatabaseReader.city(ipAddress);
        if (nonNull(cityResponse) && nonNull(cityResponse.getCity())) {
            position.setFound(true);
            city = cityResponse.getCity().getName();

            if (cityResponse.getLocation() != null
                    && cityResponse.getLocation().getLatitude() != null
                    && cityResponse.getLocation().getLongitude() != null) {
                position.setLatitude(cityResponse.getLocation().getLatitude());
                position.setLongitude(cityResponse.getLocation().getLongitude());
            }

            continent = (cityResponse.getContinent() != null) ? cityResponse.getContinent().getName() : "";
            if (cityResponse.getCountry() != null) {
                country = cityResponse.getCountry().getName();
                countryCode = cityResponse.getCountry().getIsoCode();
            } else {
                CountryResponse countryResponse = countryDatabaseReader.country(ipAddress);
                if (nonNull(countryResponse) && nonNull(countryResponse.getCountry())) {
                    country = countryResponse.getCountry().getName();
                    countryCode = countryResponse.getCountry().getIsoCode();
                }
            }
        } else {
            CountryResponse countryResponse = countryDatabaseReader.country(ipAddress);
            if (nonNull(countryResponse) && nonNull(countryResponse.getCountry())) {
                position.setFound(true);
                country = countryResponse.getCountry().getName();
                countryCode = countryResponse.getCountry().getIsoCode();
                continent = countryResponse.getContinent() == null ? "" : countryResponse.getContinent().getName();
            }
        }
        location = String.format("%s, %s, %s", Objects.toString(continent, ""), Objects.toString(country, ""), Objects.toString(city, ""));

        position.setCity(cityResponse.getCity().getName());
        position.setCountry(country);
        position.setCountryIsoCode(countryCode);
        position.setFullLocation(location);

        return position;
    }
}
