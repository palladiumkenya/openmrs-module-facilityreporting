<%
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
<script >
    window.OpenMRS = window.OpenMRS || {};

        jq(document).ready(function () {

            jq('.saveData').click(function () {
                payload = {
                    "DataSetResults": result

                };
                jq.getJSON('${ ui.actionLink("facilityreporting", "singleReportDataSets", "saveDataset") }',
                    {
                        'payload': JSON.stringify(payload)
                    })
                    .success(function (data) {
                        payload = {};
                        window.location.reload(true);
                    })
                    .error(function (xhr, status, err) {
                        console.log('AJAX error ' + JSON.stringify(xhr));
                        console.log("response text: " + JSON.stringify(xhr.statusText));

                    })
            });


        });
</script>



<div id="new-datasets-entry" ng-controller="FacilityDataSetCtrl" ng-init='init()'>

    <!-- single dataset data entry -->
    <div class="modal fade" id="enterDataSingle" tabindex="-1" role="dialog" style="font-size:16px;">
        <div class="modal-dialog modal-dialog-centered" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="enterDataSingleModalLabel">Enter Data for {{singleEntryDataset.datasetName}}</h5>
                    <button type="button" aria-label="Close" ng-click="closeEnterDataDialogModal()">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>

                <div class="modal-body"  id="modalEnterData">
                    <div class="table-responsive">
                    <table class="table table-striped tables">
                        <tr ng-repeat ="indicator in singleEntryDataset.indicators">
                            <td>
                                {{indicator.name}}:
                            </td>
                            <td>
                                <input class="form-control" type="number" ng-model="singleDatasetValues[indicator.id]">

                            </td>
                            <td></td>
                        </tr>
                    </table>



                </div>
                <div class="modal-footer">
                    <button type="button" ng-click="closeEnterDataDialogModal()">Close</button>
                    <button type="button"  data-dismiss="modal2" ng-click="saveSingleDataSetReport()">Save</button>
                </div>
            </div>
        </div>
    </div>



</div>


</div>




<script type="text/javascript">
    angular.bootstrap('#new-datasets-entry', ['facility']);
</script>

