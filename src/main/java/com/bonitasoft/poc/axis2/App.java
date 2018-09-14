package com.bonitasoft.poc.axis2;

import com.bonitasoft.poc.axis2.country.CountryInfo;
import com.bonitasoft.poc.axis2.country.CountryInfoService;

public class App {

    final CountryInfoService countryInfoService;

    public App(CountryInfoService countryInfoService) {
        this.countryInfoService = countryInfoService;
    }

    public static void main(String[] args) {
        App app = new App(CountryInfoService.countryInfoService());

        app.retrieveAndLogCountryInfos("FR");
        app.retrieveAndLogCountryInfos("USA");
        app.retrieveAndLogCountryInfos("DE");
        app.retrieveAndLogCountryInfos("GB");
    }

    private void retrieveAndLogCountryInfos(final String countryIsoCode) {
        System.out.println("Retrieving info for country '" + countryIsoCode + "'");
        final CountryInfo fullCountryInfo = countryInfoService.fullInfo(countryIsoCode);
        System.out.println("Response: " + fullCountryInfo);
    }

}
