<%
    ui.decorateWith("kenyaemr", "standardPage", [layout: "sidebar" ])
    def back = ui.pageLink("facilityreporting", "facilityReportingHome", [ returnUrl: ui.thisUrl()])
    def menuItems = [
            [ label: "Back to home", iconProvider: "kenyaui", icon: "buttons/back.png", label: "Back to Reports", href: back ]
    ]
    ui.includeCss("facilityreporting", "table_formatter.css")
    def startFieldName = ""
    def endFieldName = ""

%>

<div class="ke-page-sidebar">
    <div class="ke-panel-frame">
        ${ ui.includeFragment("kenyaui", "widget/panelMenu", [ heading: "Navigation", items: menuItems ]) }
    </div>
</div>

<div class="ke-page-content">
        <h2>Datasets for ${report.name} Report (${report.description})</h2>
        <div class="clear"></div>

        <% if (datasets) { %>
        <table class="simple-table">
            <tr>
                <th align="left">Dataset Name</th>
                <th align="left">Description</th>
                <th align="left">Mapping</th>
                <th align="left">Actions</th>
            </tr>
        <% datasets.each { ds -> %>

        <tr>
                <td>${ds.name}</td>

                <td>${ds.description ?: ""}</td>

                <td>${ds.mapping ?: ""}</td>
                <td>
                    <button
                            onclick="ui.navigate('${ ui.pageLink("facilityreporting", "reportIndicatorsList", [datasetId: ds.id, returnUrl: ui.thisUrl() ])}')">
                        <img src="${ui.resourceLink("kenyaui", "images/glyphs/view.png")}"/> View Indicators
                    </button>
                    <button type="button"
                            onclick="ui.navigate('${ ui.pageLink("facilityreporting", "reportSingleDatasetEntryForm", [ reportId: report.id, datasetId: ds.id, returnUrl: ui.thisUrl() ])}')">Enter Data</button>
                    <button type="button" class="fa fa-edit fa-1x"
                            onclick="ui.navigate('${ ui.pageLink("facilityreporting", "showListDataset", [ reportId: report.id, datasetId: ds.id, returnUrl: ui.thisUrl() ])}')">
                        Reporting history</button>
                </td>
        </tr>
        <% } %>
        </table>
        <% } else { %>

        <div>No Datasets defined</div>
        <% } %>
    <div>

</div>
    <div style="padding-top: 10px">
            <button class="addConfiguration" name="addConfiguration" type="button"
                    onclick="ui.navigate('${ ui.pageLink("facilityreporting", "newReportDatasetForm", [reportId: report.id, returnUrl: ui.pageLink("facilityreporting", "reportDatasetList", [reportId: report.id, returnUrl:ui.pageLink("facilityreporting", "facilityReportingHome")]) ])}')">
                <img src="${ui.resourceLink("kenyaui", "images/glyphs/add.png")}"/> Add Dataset
            </button>


    </div>

</div>
