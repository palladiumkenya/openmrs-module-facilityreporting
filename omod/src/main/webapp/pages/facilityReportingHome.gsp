<%
    ui.decorateWith("kenyaemr", "standardPage", [layout: "sidebar" ])
    def menuItems = [
            [ label: "Back to home", iconProvider: "kenyaui", icon: "buttons/back.png", label: "Back to home", href: ui.pageLink("kenyaemr", "userHome") ]
    ]
%>

<div class="ke-page-sidebar">
    <div class="ke-panel-frame">
        ${ ui.includeFragment("kenyaui", "widget/panelMenu", [ heading: "Navigation", items: menuItems ]) }
    </div>
</div>

<div class="ke-page-content">
    <div>
        <h3>Facility Reporting Configuration</h3>
        <table id="log_table">
            <thead>
            <tr>
                <th>Report Name</th>
                <th>Description</th>
                <th>Actions</th>
            </tr>
            </thead>
            <tbody class='scrollable'>
            <% if (reports) { %>
            <% reports.each { report -> %>
            <tr>
                <td>${ report.name }</td>
                <td>${ report.description }</td>
                <td><button>View Configuration</button> <button>Enter Data</button></td>
            </tr>
            <% } %>
            <% } else { %>
            <tr>
                <td colspan="4">No reports available</td>
            </tr>
            <tr>
                <td colspan="4"><input type="button" value="Add Report Configuraton"/></td>
            </tr>
            <% } %>
            <tr>
                <td colspan="2">
                    <button class="addTrace" name="addTrace" type="button"
                            onclick="ui.navigate('${ ui.pageLink("facilityreporting", "newReportConfigurationForm", [ returnUrl: ui.thisUrl() ])}')">
                        <img src="${ui.resourceLink("kenyaui", "images/glyphs/add.png")}"/> Add Configuration
                    </button>
                </td>
            </tr>
            </tbody>
        </table>
    </div>
</div>
