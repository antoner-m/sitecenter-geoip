package org.sitecenter.geoip.client;

import org.sitecenter.geoip.model.GeoIP;

import java.util.concurrent.CompletableFuture;

/**
 * Usage:
 *     @Autowired
 *     WebClient whoisWebClient;
 *
 *     @Bean
 *     GeoIPClient geoIPClient() {
 *         return new GeoIPClientImpl(whoisWebClient);
 *     }
 */
public interface GeoIPClient {
    CompletableFuture<GeoIP> findByIp(String ip);
}
