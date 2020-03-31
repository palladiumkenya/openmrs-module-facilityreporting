<%
    ui.decorateWith("kenyaemr", "standardPage", [layout: "sidebar" ])
    def menuItems = [
            [ label: "Back to home", iconProvider: "kenyaui", icon: "buttons/back.png", label: "Back to Datasets", href: returnUrl ]
    ]
    ui.includeCss("facilityreporting", "table_formatter.css")
%>
<div class="ke-page-sidebar">
    <div class="ke-panel-frame">
        ${ ui.includeFragment("kenyaui", "widget/panelMenu", [ heading: "Navigation", items: menuItems ]) }
    </div>
</div>

<div class="ke-page-content">
        <h2>Indicators for ${dataset.name} Dataset</h2>
        <div class="clear"></div>

        <% if (indicators) { %>
        <table class="simple-table">
            <tr>
                <th align="left">Indicator Name</th>
                <th align="left">Description</th>
                <th align="left">Mapping</th>
                <th align="left">Actions</th>
            </tr>
        <% indicators.each { indicator -> %>

        <tr>
                <td>${indicator.name}</td>

                <td>${indicator.description ?: ""}</td>

                <td>${indicator.mapping ?: ""}</td>
                <td>
                    <button>
                        <img src="${ui.resourceLink("kenyaui", "images/glyphs/edit.png")}"/> Edit
                    </button>
                </td>
        </tr>
        <% } %>
        </table>
        <% } else { %>

        <div>No reports available</div>
        <% } %>
    <div style="padding-top: 10px">
            <button class="addConfiguration" name="addConfiguration" type="button"
                    onclick="ui.navigate('${ ui.pageLink("facilityreporting", "newReportIndicatorForm", [datasetId:dataset.id, returnUrl: ui.thisUrl() ])}')">
                <img src="${ui.resourceLink("kenyaui", "images/glyphs/add.png")}"/> Add Indicator
            </button>
    </div>
</div>
</div>
