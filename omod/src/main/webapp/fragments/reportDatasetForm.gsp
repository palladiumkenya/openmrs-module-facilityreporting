<%
    ui.decorateWith("kenyaui", "panel", [heading: (command.original ? "Edit" : "Add") + " Dataset Definition", frameOnly: true])

    def datasetName = [
            [
                    [object: command, property: "name", label: "Dataset Name"]

            ]
    ]

    def mappingInfo = [
            [

                    [object: command, property: "mapping", label: "DHIS2 Dataset Mapping"]

            ]
    ]

%>

<form id="facility-report-dataset-form" method="post"
      action="${ui.actionLink("facilityreporting", "reportDatasetForm", "saveReportDatasetForm")}">
    <% if (command.original) { %>
    <input type="hidden" name="id" value="${command.original.id}"/>
    <% } %>
    <input type="hidden" name="report" value="${report.id}"/>
    <div class="ke-panel-content">

        <div class="ke-form-globalerrors" style="display: none"></div>

        <div class="ke-form-instructions">
            <strong>*</strong> indicates a required field
        </div>

        <fieldset>
            <legend>Name</legend>

            <% datasetName.each { %>
            ${ui.includeFragment("kenyaui", "widget/rowOfFields", [fields: it])}
            <% } %>
        </fieldset>

    <fieldset>
        <legend>Description</legend>
        <table>
            <tr>
                <td class="ke-field-label">Dataset Description</td>
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
                <img src="${ui.resourceLink("kenyaui", "images/glyphs/ok.png")}"/> ${command.original ? "Save Changes" : "Create Report Dataset"}
            </button>

            <button type="button" class="cancel-button"><img
                    src="${ui.resourceLink("kenyaui", "images/glyphs/cancel.png")}"/> Cancel</button>

        </div>
    </div>
</form>


<script type="text/javascript">

    //On ready
    jQuery(function () {


        jQuery('#facility-report-dataset-form .cancel-button').click(function () {
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