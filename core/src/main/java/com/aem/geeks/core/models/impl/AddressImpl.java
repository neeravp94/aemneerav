package com.aem.geeks.core.models.impl;

import com.adobe.cq.export.json.ComponentExporter;
import com.aem.geeks.core.models.Address;
import com.aem.geeks.core.utils.Constants;
import com.day.cq.wcm.api.Page;
import com.day.cq.wcm.api.PageManager;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Exporter;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.Self;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.annotation.PostConstruct;

@Model(adaptables = SlingHttpServletRequest.class,
        resourceType = "aemgeeks/components/address",
        adapters = { Address.class, ComponentExporter.class },
        defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL
)

@Exporter(name = Constants.EXPORTER_NAME, extensions = Constants.EXPORTER_EXTENSION)
@JsonSerialize(as = Address.class)
public class AddressImpl implements Address,ComponentExporter{

    private static final Logger LOG = LoggerFactory.getLogger(AddressImpl.class);
    String RESOURCE_TYPE = "aemgeeks/components/address";

    @ValueMapValue
    String country;

    @ValueMapValue
    String state;

    @ValueMapValue
    String pin;

    @Self
    private SlingHttpServletRequest request;

    private Page currentPage;

    @PostConstruct
    protected void init() {
        if (request != null) {
            Resource resource = request.getResource();
            PageManager pageManager = resource.getResourceResolver().adaptTo(PageManager.class);
            if (pageManager != null) {
                currentPage = pageManager.getContainingPage(resource);
                LOG.info("Initializing current page :{}", currentPage);
                if (currentPage == null) {
                    LOG.warn("Could not resolve currentPage from resource: {}", resource.getPath());
                } else {
                    LOG.info("Resolved currentPage: {}", currentPage.getPath());
                }
            } else {
                LOG.error("PageManager was null — couldn't resolve currentPage.");
            }
        }
    }

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

    @Override
    public String getPageTitle() {
        if (currentPage != null) {
            LOG.info("Setting title in model ... {} ...", currentPage.getTitle());
            return currentPage.getTitle();
        } else {
            LOG.warn("currentPage is null in getPageTitle()");
            return null;
        }
    }

    @Override
    public String getName() {
        LOG.info("Setting name in model ... {} ...",currentPage.getName());
        return currentPage != null ? currentPage.getName() : null;
    }

    @Override
    public String getExportedType() {
        return RESOURCE_TYPE;
    }
}
