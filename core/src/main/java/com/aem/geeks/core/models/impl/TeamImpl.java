package com.aem.geeks.core.models.impl;

import com.adobe.cq.export.json.ComponentExporter;
import com.aem.geeks.core.models.Team;
import com.aem.geeks.core.utils.Constants;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Exporter;
import org.apache.sling.models.annotations.Model;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Model(adaptables = SlingHttpServletRequest.class,
        resourceType = "aemgeeks/components/team",
        adapters = { Team.class, ComponentExporter.class },
        defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL
)

@Exporter(name = Constants.EXPORTER_NAME, extensions = Constants.EXPORTER_EXTENSION)
@JsonSerialize(as = Team.class)
public class TeamImpl implements Team,ComponentExporter{

    private static final Logger LOG = LoggerFactory.getLogger(TeamImpl.class);
    String RESOURCE_TYPE = "aemgeeks/components/address";

    @Inject
    Resource componentResource;

    //private Page currentPage;
/*

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
*/

    @Override
    public String getExportedType() {
        return RESOURCE_TYPE;
    }

    @Override
    public List<Map<String, String>> getTeamMember() {
        List<Map<String,String>> teamMembersMap = new ArrayList<>();
        try{
            Resource teamMembers = componentResource.getChild("teammember");
            if(teamMembers!=null){
                for(Resource teamMember: teamMembers.getChildren()){
                    Map<String,String> team = new HashMap<>();
                    team.put("membername",teamMember.getValueMap().get("membername",String.class));
                    team.put("memberdesignation",teamMember.getValueMap().get("memberdesignation",String.class));
                    teamMembersMap.add(team);
                }
            }
        }
        catch (Exception e){
            LOG.error("Error while fetching team members {}", e.getMessage());
        }


        return teamMembersMap;
    }
}
