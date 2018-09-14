package com.bonitasoft.poc.axis2.country;


import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@RequiredArgsConstructor
@Getter
@ToString
public class Language {

    private final String ISOCode;

    private final String name;

}
