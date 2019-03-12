<%
    ui.decorateWith("kenyaemr", "standardPage", [layout: "sidebar" ])
    def back = ui.pageLink("facilityreporting", "reportDatasetsList", [reportId:report.id])
    def menuItems = [
            [ label: "Back to home", iconProvider: "kenyaui", icon: "buttons/back.png", label: "Back to dataset list", href: back ]
    ]
    ui.includeCss("facilityreporting", "table_formatter.css")
%>
<div class="ke-page-sidebar">
    <div class="ke-panel-frame">
        ${ ui.includeFragment("kenyaui", "widget/panelMenu", [ heading: "Navigation", items: menuItems ]) }
    </div>
</div>

<div class="ke-page-content">


    <div>
        ${ui.includeFragment("facilityreporting", "showDatasets", ["datasetId": dataset.id,"reportId": report.id, "returnUrl":back])}

    </div>


</div>
