package org.sitecenter.geoip.server.web;

import org.sitecenter.geoip.model.GeoIP;
import org.sitecenter.geoip.service.GeoIPLocationService;
import com.maxmind.geoip2.exception.GeoIp2Exception;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class GeoIPController {

    private final GeoIPLocationService geoIPLocationService;

    public GeoIPController(GeoIPLocationService geoIPLocationService) {
        this.geoIPLocationService = geoIPLocationService;
    }

    @GetMapping("/api/geo/ip/{ipAddress}")
    public GeoIP getLocation(@PathVariable String ipAddress) throws IOException, GeoIp2Exception {
        return geoIPLocationService.getIpLocation(ipAddress);
    }
}
