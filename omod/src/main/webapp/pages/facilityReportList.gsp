<%
    ui.decorateWith("kenyaemr", "standardPage")
    ui.includeCss("facilityreporting", "TreantJs/Treant.css")
    ui.includeCss("facilityreporting", "TreantJs/collapsable.css")
    ui.includeCss("facilityreporting", "TreantJs/vendor/perfect-scrollbar/perfect-scrollbar.css")
    ui.includeJavascript("facilityreporting", "TreantJs/vendor/raphael.js")
    ui.includeJavascript("facilityreporting", "TreantJs/Treant.js")
    ui.includeJavascript("facilityreporting", "TreantJs/jquery.easing.js")
    ui.includeJavascript("facilityreporting", "TreantJs/collapsable.js")

%>

<style>
div.grid {
    display: block;
}

div.grid div {
    float: left;
    height: 30px;
}

div.column-one {
    width: 200px;
}

div.column-two {
    width: 200px;
}

div.column-three {
    width: 200px;
}

div.column-four {
    width: 120px;
}

div.clear {
    clear: both;
}

.col-header {
    font-weight: bold;
    font-size: 14px;
}

div.section-title {
    color: black;
    font-weight: bold;
    display: block;
    width: 550px;
    float: left;
    font-size: 16px;
}
</style>

<div class="ke-page-sidebar">
    <div class="ke-panel-frame">
        ${ui.includeFragment("kenyaui", "widget/panelMenu", [heading: "Navigation", items: menuItems])}
    </div>
</div>

<div class="ke-page-content">

    <div id="program-tabs" class="ke-tabs">
        <div class="ke-tabmenu">
            <div class="ke-tabmenu-item" data-tabid="contact_list">List of Facility Reports</div>
        </div>

        <div class="ke-tab" data-tabid="report_list">
            <div class="ke-panel-frame">
                <div class="ke-panel-heading">Facility Reports</div>

                <div class="ke-panel-content">
                    <div class="section-title"></div>

                    <div class="clear"></div>

                    <% if (reports) { %>
                    <div class="grid">

                        <div class="column-one col-header">Report Name</div>

                        <div class="column-two col-header">Description</div>

                        <div class="column-three col-header">DHIS2 Mapping name</div>

                       <div class="column-four col-header"></div>

                    </div>

                    <div class="clear"></div>

                    <% reports.each { rel -> %>

                    <div class="ke-stack-item ke-navigable">
                        <div class="grid">

                            <div class="column-one">${rel.name}</div>

                            <div class="column-two">${rel.description ?: ""}</div>

                            <div class="column-three">${rel.mapping}</div>

                            <div class="column-four">
                                <button type="button"
                                        onclick="ui.navigate('${ ui.pageLink("facilityreporting", "facilityReportDatasetsList", [ reports: rel.id,  returnUrl: ui.thisUrl() ])}')">
                                    <img src="${ui.resourceLink("kenyaui", "images/glyphs/view.png")}"/>view details
                                </button>
                            </div>

                            <div class="column-five">

                                <button type="button"
                                        onclick="ui.navigate('${ ui.pageLink("facilityreporting", "newReportConfigurationForm", [ reports: rel.id, returnUrl: ui.thisUrl() ])}')">
                                    <img src="${ui.resourceLink("kenyaui", "images/glyphs/edit.png")}"/> Edit
                                </button>
                            </div>

                        </div>

                        <div class="clear"></div>

                    </div>
                    <% }
                    } else { %>
                    No report found
                    <% } %>
                </div>

                <div class="clear"></div>

            </div>

            <div align="center">

                <button type="button" class="addReport"
                        onclick="ui.navigate('${ ui.pageLink("facilityreporting", "newReportConfigurationForm", [ returnUrl: ui.thisUrl() ])}')">
                    <img src="${ui.resourceLink("kenyaui", "images/glyphs/add.png")}"
                         style="display:none;"/>Add Report
                </button>

            </div>

        </div>

    </div>

</div>
