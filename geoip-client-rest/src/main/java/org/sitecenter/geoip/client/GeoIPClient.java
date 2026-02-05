package org.sitecenter.geoip.client;

import org.sitecenter.geoip.model.GeoIP;

/**
 * Client interface for GeoIP location service.
 * Provides methods to lookup geographic information for IP addresses.
 */
public interface GeoIPClient {

    /**
     * Find geographic location information for the given IP address.
     *
     * @param ip the IP address to lookup (IPv4 or IPv6)
     * @return GeoIP information or null if not found or on error
     */
    GeoIP findByIp(String ip);
}
