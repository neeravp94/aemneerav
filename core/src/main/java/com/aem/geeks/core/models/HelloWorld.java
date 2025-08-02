package com.aem.geeks.core.models;

import com.adobe.cq.export.json.ComponentExporter;

import java.util.List;
import java.util.Map;

public interface HelloWorld  extends ComponentExporter {
    public String getMessage();
    public String getText();

    String getExportedType();
}
