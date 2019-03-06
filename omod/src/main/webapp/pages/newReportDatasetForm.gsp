<%
    ui.decorateWith("kenyaemr", "standardPage", [patient: patient, layout: "sidebar"])
    def menuItems = [
            [label: "Back to home", iconProvider: "kenyaui", icon: "buttons/back.png", label: "Back to home", href: ui.pageLink("facilityreporting", "facilityReportingHome")]
    ]
%>

<div class="ke-page-sidebar">
    <div class="ke-panel-frame">
        ${ui.includeFragment("kenyaui", "widget/panelMenu", [heading: "Navigation", items: menuItems])}
    </div>
</div>
<div class="ke-page-content">

    ${ ui.includeFragment("facilityreporting", "facilityReportForm", [returnUrl: ui.pageLink("facilityreporting", "facilityReportingHome")]) }

 </div>