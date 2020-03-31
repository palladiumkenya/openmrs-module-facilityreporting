<%
    ui.decorateWith("kenyaui", "panel", [heading: (command.original ? "Edit" : "Add") + " Indicator Definition", frameOnly: true])

    def indicatorName = [
            [
                    [object: command, property: "name", label: "Indicator Name *"]

            ]
    ]

    def mappingInfo = [
            [

                    [object: command, property: "mapping", label: "DHIS2 Indicator Mapping *"]

            ]
    ]

%>
<script type="text/javascript" >
    jq(document).ready(function() {
        jq("#btnBack").click(function(){
            ui.navigate('${ ui.pageLink("facilityreporting", "reportIndicatorsList", [datasetId: dataset.id, returnUrl: ui.thisUrl()]) }');
        });
    });

</script>

<form id="facility-report-dataset-indicator-form" method="post"
      action="${ui.actionLink("facilityreporting", "reportIndicatorForm", "saveReportIndicatorForm")}">
    <% if (command.original) { %>
    <input type="hidden" name="id" value="${command.original.id}"/>
    <% } %>
    <input type="hidden" name="dataset" value="${dataset.id}"/>
    <div class="ke-panel-content">

        <div class="ke-form-globalerrors" style="display: none"></div>

        <div class="ke-form-instructions">
            <strong>*</strong> indicates a required field
        </div>

        <fieldset>
            <legend>Name</legend>

            <% indicatorName.each { %>
            ${ui.includeFragment("kenyaui", "widget/rowOfFields", [fields: it])}
            <% } %>
        </fieldset>

        <fieldset>
            <legend>Description</legend>
            <table>
                <tr>
                    <td class="ke-field-label">Description</td>
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

            <button type="button" id="btnBack"><img
                    src="${ui.resourceLink("kenyaui", "images/glyphs/cancel.png")}"/> Cancel</button>

        </div>
    </div>
</form>


<script type="text/javascript">

    //On ready
    jQuery(function () {


        jQuery('#facility-report-dataset-indicator-form .cancel-button').click(function () {
            ui.navigate('${ config.returnUrl }');
        });
        kenyaui.setupAjaxPost('facility-report-dataset-indicator-form', {
            onSuccess: function (data) {
                if (data.indicatorId) {
                    <% if (config.returnUrl) { %>
                    ui.navigate('${ config.returnUrl }');
                    <% } else { %>
                    ui.navigate('facilityreporting', 'viewReportDefinition', {indicatorId: reportId});
                    <% } %>
                } else {
                    kenyaui.notifyError('Saving contact tracing was successful, but with unexpected response');
                }
            }
        });


    }); // end of jQuery initialization bloc


</script>