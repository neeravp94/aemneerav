package com.aem.geeks.core.models.impl;

import com.aem.geeks.core.models.Address;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.*;
import javax.inject.Inject;

@Model(adaptables = Resource.class,
        adapters = Address.class,
        defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL
)

public class AddressImpl implements Address{

    @Inject
    String country;

    @Inject
    String state;

    @Inject
    String pin;

    @Override
    public String getCountry() {
        return country;
    }

    @Override
    public String getState() {
        return state;
    }

    @Override
    public String getPin() {
        return pin  ;
    }
}
