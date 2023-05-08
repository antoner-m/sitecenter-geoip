# sitecenter-geoip
This is working project for detecting Country and City by given IP address.

Project consist two modules:
**geoip-common** - classes needed for running as Spring Service
**geoip-server** - classes for example standalone Rest api service

# How to use it in your spring project

Clone this project to your local folder and execute:

`mvn clean install`

Add this dependency to your maven pom.xml:
```
<dependency>
<groupId>org.sitecenter</groupId>
<artifactId>geoip-common</artifactId>
<version>0.0.1-SNAPSHOT</version>
<scope>compile</scope>
</dependency>
```

Download geoip database as described bellow. You should download files to resources dir of your project:
**./yourproject/src/main/resources/maxmind**.

Add this line to any @Configuration class in your project:

`@ComponentScan(basePackages = {"org.sitecenter.geoip.*"})`

Now you can use geo-service in any of your service or controller.

Example for controller:

```
@Autowired
private GeoIPLocationService geoIPLocationService;

public ModelAndView someMethod() {
    try {
        GeoIP ipLocation = geoIPLocationService.getIpLocation(ip);
        if (ipLocation != null && ipLocation.isFound()) 
            model.addObject("geo", ipLocation);
    } catch (IOException e) {
        e.printStackTrace();
    } catch (GeoIp2Exception e) {
        e.printStackTrace();
    }
}
```

# Start own server rest api geo ip service

Download geoip database as described bellow.
And start SpringGeoipApplication class. 

Now you can query database at: http://localhost:8080/api/geo/ip/IPADDRESS

For example:
`http://localhost:8080/api/geo/ip/8.8.8.8`

# Download geoip database

For work we need to download actual geoip database from MaxMind.

Signup for a MaxMind account at https://www.maxmind.com/en/geolite2/signup, which is now required to download even free/public GeoIP databases.

Once you’ve set up an account, login to your account portal and select “Download Files” to access the databases.
Then, you can download databases: **GeoLite2-City.mmdb** and **GeoLite2-Country.mmdb** to the folder **geoip-server/src/main/resources/maxmind**.
