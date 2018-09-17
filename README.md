Poc to test bonita rest api extension including an axis2 client


## Output

Build using Maven: `mvn package`.

The build generates
* a directory which contains a `lib` subfolder to have a quick view of dependencies required by the axis2 client.
* a zip which is the Rest API Extension


## Deploy and use the Rest API Extension

After deploying the Rest API extension, it is available by performing a GET with a authenticated user with `process_management`
permission on http://localhost:8080/bonita/API/extension/poc-axis2?isoCode=FR

Pass the [ISO_3166-1](https://en.wikipedia.org/wiki/ISO_3166-1) code of the country as `isoCode` query parameter to get some information about a country.



## Client WS

Stubs are generated using the Axis2 generator. They are wrapped in a `CountryInfoService` class.

## Services

We rely on public webservices for the tests.

wsdl taken from
* CountryInfoService.wsdl: http://webservices.oorsprong.org/websamples.countryinfo/CountryInfoService.wso?WSDL
* ndfdXML.wsdl: https://graphical.weather.gov/xml/

other service that may be used: see https://jansipke.nl/examples-of-public-soap-web-services/
