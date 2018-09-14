package com.bonitasoft.poc.axis2.country;

import static java.util.Arrays.stream;
import static java.util.stream.Collectors.toList;

import java.rmi.RemoteException;
import java.util.List;

import com.bonitasoft.poc.axis2.ws.CountryInfoServiceStub;
import org.apache.axis2.AxisFault;
import org.apache.axis2.client.Options;
import org.apache.axis2.transport.http.HTTPConstants;

public class CountryInfoService {

    private final CountryInfoServiceStub countryInfoService;

    public static CountryInfoService countryInfoService() {
        final CountryInfoService countryService = new CountryInfoService();
        countryService.configureClientStub();
        return countryService;
    }

    private void configureClientStub() {
        Options options = new Options();
        options.setProperty(HTTPConstants.SO_TIMEOUT, 10_000);
        options.setProperty(HTTPConstants.CONNECTION_TIMEOUT, 5_000);
        // ensure the content-length header is set (as the service required it)
        // see http://axis.apache.org/axis2/java/core/docs/http-transport.html and https://stackoverflow.com/a/12840272
        options.setProperty(HTTPConstants.CHUNKED, false);
        countryInfoService._getServiceClient().setOverrideOptions(options);
    }


    // prevent instantiation without the factory method
    private CountryInfoService() {
        try {
            countryInfoService = new CountryInfoServiceStub();
        } catch (AxisFault axisFault) {
            throw new RuntimeException("Fail initiliazing the country service", axisFault);
        }
    }

    public CountryInfo fullInfo(String countryIsoCode) {
        try {
            final CountryInfoServiceStub.FullCountryInfoResponse fullCountryInfoResponse = countryInfoService.fullCountryInfo(fullCountryInfo(countryIsoCode));
            return toCountryInfo(fullCountryInfoResponse.getFullCountryInfoResult());
        } catch (RemoteException e) {
            throw new RuntimeException("Error while getting information for country " + countryIsoCode, e);
        }
    }

    private static CountryInfo toCountryInfo(CountryInfoServiceStub.TCountryInfo orig) {
        return CountryInfo.builder()
                .ISOCode(orig.getSISOCode())
                .name(orig.getSName())
                .capitalCity(orig.getSCapitalCity())
                .phoneCode(orig.getSPhoneCode())
                .continentCode(orig.getSContinentCode())
                .currencyISOCode(orig.getSCurrencyISOCode())
                .countryFlagUrl(orig.getSCountryFlag())
                .languages(toLanguages(orig.getLanguages()))
                .build();
    }

    private static List<Language> toLanguages(CountryInfoServiceStub.ArrayOftLanguage origLanguages) {
        return stream(origLanguages.getTLanguage())
                .map(l -> new Language(l.getSISOCode(), l.getSName()))
                .collect(toList());
    }

    private static CountryInfoServiceStub.FullCountryInfo fullCountryInfo(final String countryIsoCode) {
        CountryInfoServiceStub.FullCountryInfo fullCountryInfo = new CountryInfoServiceStub.FullCountryInfo();
        fullCountryInfo.setSCountryISOCode(countryIsoCode);
        return fullCountryInfo;
    }

}
