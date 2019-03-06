<%
    ui.decorateWith("kenyaemr", "standardPage", [layout: "sidebar" ])
    def menuItems = [
            [ label: "Back to home", iconProvider: "kenyaui", icon: "buttons/back.png", label: "Back to home", href: ui.pageLink("kenyaemr", "userHome") ]
    ]
    ui.includeCss("facilityreporting", "table_formatter.css")
%>
<div class="ke-page-sidebar">
    <div class="ke-panel-frame">
        ${ ui.includeFragment("kenyaui", "widget/panelMenu", [ heading: "Navigation", items: menuItems ]) }
    </div>
</div>

<div class="ke-page-content">
        <h2>Facility Reports</h2>
        <div class="clear"></div>

        <% if (reports) { %>
        <table class="simple-table">
            <tr>
                <th align="left">Report Name</th>
                <th align="left">Description</th>
                <th align="left">Mapping</th>
                <th align="left">Actions</th>
            </tr>
        <% reports.each { report -> %>

        <tr>
                <td>${report.name}</td>

                <td>${report.description ?: ""}</td>

                <td>${report.mapping ?: ""}</td>
                <td class="column-five">
                    <button
                            onclick="ui.navigate('${ ui.pageLink("facilityreporting", "reportDatasetsList", [reportId: report.id, returnUrl: ui.thisUrl() ])}')">
                        <img src="${ui.resourceLink("kenyaui", "images/glyphs/view.png")}"/> View Datasets
                    </button>
                </td>
        </tr>
        <% } %>
        </table>
        <% } else { %>

        <div>No reports available</div>
        <% } %>
    <div>
            <button class="addConfiguration" name="addConfiguration" type="button"
                    onclick="ui.navigate('${ ui.pageLink("facilityreporting", "newReportConfigurationForm", [ returnUrl: ui.thisUrl() ])}')">
                <img src="${ui.resourceLink("kenyaui", "images/glyphs/add.png")}"/> Add Report Configuration
            </button>
    </div>
    <div>
        ${ui.includeFragment("facilityreporting", "facilityDataSets", ["patient": ""])}

    </div>

</div>
