package org.sitecenter.geoip.server;

import com.maxmind.geoip2.exception.GeoIp2Exception;
import org.junit.jupiter.api.Test;
import org.sitecenter.geoip.model.GeoIP;
import org.sitecenter.geoip.service.GeoIPLocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

@SpringBootTest
class SpringGeoipApplicationTests {
    @Autowired
    GeoIPLocationService geoIPLocationService;

    @Test
    void contextLoads() {

    }
    @Test
    void testIpLocationService() {
        assert(geoIPLocationService != null);
        GeoIP ipLocation = null;
        try {
            ipLocation = geoIPLocationService.getIpLocation("8.8.8.8");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (GeoIp2Exception e) {
            e.printStackTrace();
        }
        assert(ipLocation != null);
        assert(ipLocation.isFound());
        assert("8.8.8.8".equals(ipLocation.getIpAddress()));
        assert("US".equals(ipLocation.getCountryIsoCode()));
        assert("United States".equals(ipLocation.getCountry()));
        assert("Los Angeles".equals(ipLocation.getCity()));
    }

}
