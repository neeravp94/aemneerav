package com.aem.geeks.core.models.impl;

import com.adobe.cq.export.json.ComponentExporter;
import com.adobe.cq.export.json.ExporterConstants;
import com.aem.geeks.core.models.HelloWorld;
import com.aem.geeks.core.utils.Constants;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.models.annotations.*;
import org.apache.sling.models.annotations.injectorspecific.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;

@Model(adaptables = SlingHttpServletRequest.class,
        adapters = {HelloWorld.class, ComponentExporter.class},
        resourceType = HelloWorldImpl.RESOURCE_TYPE,
        defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL
)

//@Exporter(name = Constants.EXPORTER_NAME, extensions = Constants.EXPORTER_EXTENSION)
@Exporter(
        name = ExporterConstants.SLING_MODEL_EXPORTER_NAME,
        extensions = ExporterConstants.SLING_MODEL_EXTENSION
)

public class HelloWorldImpl implements HelloWorld{
    private static final Logger LOG = LoggerFactory.getLogger(HelloWorldImpl.class);
    final protected static String RESOURCE_TYPE="aemgeeks/components/content/helloworld";


    @SlingObject(injectionStrategy = InjectionStrategy.OPTIONAL)
    protected  SlingHttpServletRequest request;

   // static final String RESOURCE_TYPE = "aemgeeks/components/content/helloworld"

    @Inject
    @Via("resource")
    String message;

    @ValueMapValue
    private String text;

    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public String getText() {
        return text;
    }

    @Override
    public String getExportedType(){
        return RESOURCE_TYPE;
    }
}
