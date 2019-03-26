<%
    ui.decorateWith("kenyaemr", "standardPage", [layout: "sidebar" ])
    def menuItems = [
            [ label: "Back to home", iconProvider: "kenyaui", icon: "buttons/back.png", label: "Back to dataset list", href: returnUrl ]
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
        ${ui.includeFragment("facilityreporting", "singleReportDataSets", ["datasetId": dataset.id,"reportId": report.id])}

    </div>


</div>
