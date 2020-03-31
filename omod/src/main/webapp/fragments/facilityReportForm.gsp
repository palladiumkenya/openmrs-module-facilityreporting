<%
    ui.decorateWith("kenyaui", "panel", [heading: (command.original ? "Edit" : "Add") + " Report Definition", frameOnly: true])

    def reportName = [
            [
                    [object: command, property: "name", label: "Report Name *"]

            ]
    ]

    def mappingInfo = [
            [

                    [object: command, property: "mapping", label: "DHIS2 Mapping *"]

            ]
    ]

%>

<form id="facility-report-form" method="post"
      action="${ui.actionLink("facilityreporting", "facilityReportForm", "saveReportForm")}">
    <% if (command.original) { %>
    <input type="hidden" name="id" value="${command.original.id}"/>
    <% } %>

    <div class="ke-panel-content">

        <div class="ke-form-globalerrors" style="display: none"></div>

        <div class="ke-form-instructions">
            <strong>*</strong> indicates a required field
        </div>

        <fieldset>
            <legend>Report Name</legend>

            <% reportName.each { %>
            ${ui.includeFragment("kenyaui", "widget/rowOfFields", [fields: it])}
            <% } %>
        </fieldset>

    <fieldset>
        <legend>Description</legend>
        <table>
            <tr>
                <td class="ke-field-label">Report Description</td>
            </tr>
            <tr>
                <td>
                    <textarea name="description" rows="5" cols="80">${(command.description != null)? command.description : ""}</textarea>
                </td>
            </tr>
        </table>
    </fieldset>
        <fieldset>
            <legend>DHIS2 Reporting</legend>
            <% mappingInfo.each { %>
            ${ui.includeFragment("kenyaui", "widget/rowOfFields", [fields: it])}
            <% } %>
        </fieldset>



        <div class="ke-panel-footer">
            <button type="submit">
                <img src="${ui.resourceLink("kenyaui", "images/glyphs/ok.png")}"/> ${command.original ? "Save Changes" : "Create Report Definition"}
            </button>

            <button type="button" class="cancel-button"><img
                    src="${ui.resourceLink("kenyaui", "images/glyphs/cancel.png")}"/> Cancel</button>

        </div>
    </div>
</form>


<script type="text/javascript">

    //On ready
    jQuery(function () {


        jQuery('#facility-report-form .cancel-button').click(function () {
            ui.navigate('${ config.returnUrl }');
        });
        kenyaui.setupAjaxPost('facility-report-form', {
            onSuccess: function (data) {
                if (data.reportId) {
                    <% if (config.returnUrl) { %>
                    ui.navigate('${ config.returnUrl }');
                    <% } else { %>
                    ui.navigate('facilityreporting', 'viewReportDefinition', {reportId: reportId});
                    <% } %>
                } else {
                    kenyaui.notifyError('Saving contact tracing was successful, but with unexpected response');
                }
            }
        });


    }); // end of jQuery initialization bloc


</script>