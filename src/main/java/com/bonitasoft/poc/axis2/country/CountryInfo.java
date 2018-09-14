package com.bonitasoft.poc.axis2.country;


import java.util.List;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@ToString
@Builder
@Getter
@RequiredArgsConstructor
public class CountryInfo {

    private final String ISOCode;

    private final String name;

    private final String capitalCity;

    private final String phoneCode;

    private final String continentCode;

    private final String currencyISOCode;

    private final String countryFlagUrl;

    private final List<Language> languages;

}
