<%

    ui.includeCss("facilityreporting", "table_formatter.css")
    ui.includeJavascript("uicommons", "angular.min.js")
    ui.includeJavascript("uicommons", "angular-app.js")
    ui.includeJavascript("uicommons", "angular-resource.min.js")
    ui.includeJavascript("uicommons", "angular-common.js")
    ui.includeJavascript("uicommons", "angular-ui/ui-bootstrap-tpls-0.11.2.js")
    ui.includeJavascript("uicommons", "ngDialog/ngDialog.js")
    ui.includeJavascript("uicommons", "ngDialog/ngDialog.js")
    ui.includeJavascript("facilityreporting", "bootstrap.min.js")
    ui.includeJavascript("facilityreporting", "facilityDataset.js")


    ui.includeCss("uicommons", "ngDialog/ngDialog.min.css")
    ui.includeCss("uicommons", "styleguide/jquery-ui-1.9.2.custom.min.css")
    ui.includeCss("facilityreporting", "index.css")
    ui.includeCss("facilityreporting", "facilityReporting.css")

    ui.includeCss("facilityreporting", "bootstrap.min.css")

%>
<script type="text/javascript" >
    window.OpenMRS = window.OpenMRS || {};
    window.OpenMRS.datasetHstoryPayload = ${datasetHstoryPayload}
</script>




<div class="ke-page-content">


    <div id="singleData">
        <fieldset class=" scheduler-border">
            <legend class="scheduler-border"> ${dataset.name} Dataset Reporting History</legend>

        <div class="table-responsive" style="padding-top: 30px">
            <div class="table-responsive">
                <table class="table table-striped tables">
                    <tr>
                        <th>Start Date</th>
                        <th>End Date</th>
                        <th>Actions</th>
                    </tr>
                    <%if (datasetHstoryPayload) { %>
                    <% datasetHstoryPayload.each { %>
                    <tr>
                        <td>
                            ${it.startDate}
                        </td>
                        <td>
                            ${it.endDate}
                        </td>
                        <td>
                            <button type="button" data-toggle="modal" data-target="#viewDatasetReport"
                                    onclick="ui.navigate('${ ui.pageLink("facilityreporting", "viewReportData", [ dataId: it.dataId,"reportId": report.id,"datasetId": dataset.id, returnUrl: ui.thisUrl() ])}')">
                                <img src="${ui.resourceLink("kenyaui", "images/glyphs/view.png")}"/>View Data
                            </button>
                            <button type="button" class="fa fa-edit fa-1x"
                                    onclick="ui.navigate('${ ui.pageLink("facilityreporting", "editDataset", [ dataId: it.dataId,"reportId": report.id, "datasetId": dataset.id, returnUrl: ui.thisUrl() ])}')">

                                <img src="${ui.resourceLink("kenyaui", "images/glyphs/edit.png")}"/>   Edit</button>
                        </td>
                        </td>
                    </tr>
                    <% } }%>

                </table>




            </div>


        </div>
        <div>

        </div>
        </fieldset>
    </div>




</div>
<script type="text/javascript">

</script>
