package org.sitecenter.geoip.client;

import lombok.extern.slf4j.Slf4j;
import org.sitecenter.geoip.model.GeoIP;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.concurrent.CompletableFuture;

@Slf4j
public class GeoIPClientImpl implements GeoIPClient {

    @Value("${org.sitecenter.GEOIP_API_URL}")
    private String GEOIP_API_URL;

    private final WebClient webClient;

    public GeoIPClientImpl(WebClient webClient) {
        this.webClient = webClient;
    }

    @Override
    @Async
    public CompletableFuture<GeoIP> findByIp(String ip) {
//        log.debug("[{}]: GeoIPClientImpl find for ip.", ip);
        GeoIP result = webClient.get().uri(GEOIP_API_URL+"/api/geo/ip/"+ip)
                .retrieve().bodyToMono(GeoIP.class).block();
//        try {
//            log.debug("[{}]: GeoIPClientImpl find for ip. Sleeping...", ip);
//            Thread.sleep(3500);
//            log.debug("[{}]: GeoIPClientImpl find for ip. waking up.", ip);
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        }
//        log.debug("[{}]: GeoIPClientImpl find for ip. Finished.", ip);
        return CompletableFuture.completedFuture(result);
    }

}