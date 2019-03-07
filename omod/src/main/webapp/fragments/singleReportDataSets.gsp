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

<div class="ke-page-content">
    <div class="ui-tabs">
<div id="single-report-datasets" ng-controller="FacilityDataSetCtrl" ng-init='init()'>

    <!-- single report view -->
    <div ng-repeat="control in reportList">
    <div >
        <div class="form-group row">

                <div  class="table-responsive">
                    <table class="table table-striped tables">
                        <tr ng-repeat = "data in control.dataset">
                            <td>
                                {{data.datasetName}}
                            </td>
                            <td>
                                <button type="button" data-toggle="modal" data-target="#viewDatasetReport"
                                        ng-click="viewReportDataSets(data)">View</button>
                                <button type="button" ng-click="captureDataForSingleDataset(data)"
                                        data-toggle="modal" data-target="#enterDataSingle"
                                        class="saveData">Enter Data</button>
                                <button type="button" class="fa fa-edit fa-1x"
                                        data-toggle="modal" data-target="#editSingleDataset"
                                        ng-click="editResultsDatasetDialog(data)" style="cursor: pointer">Edit</button>

                            </td>
                        </tr>
                    </table>

                </div>

        </div>
    </div>
    </div>
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

    <!-- view data set report -->
    <div class="modal fade" id="viewDatasetReport" tabindex="-1" role="dialog" style="font-size:16px;">
        <div class="modal-dialog  modal-dialog-centered modal-lg"  role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="viewDatasetReportModalLabel">View Dataset Report</h5>
                    <button type="button" aria-label="Close" ng-click="closeViewDataSetReport()">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>

                <div class="modal-body"  id="modalviewDatasetReport">
                    <div class="table-responsive" >
                        <table class="table table-striped tables">
                            <tr>
                                <th>Dataset</th>
                                <th>Indicator</th>
                                <th>Value</th>
                            </tr>
                            <tr ng-repeat = "data in singleDatasetView.indicators">
                                <td>
                                  {{singleDatasetView.datasetName}}
                                </td>
                                <td>
                                    {{data.name}}
                                </td>
                                <td>
                                    {{data.value}}
                                </td>
                            </tr>

                        </table>

                    </div>

                </div>
                <div class="modal-footer">
                    <button type="button" ng-click="closeViewDataSetReport()">Close</button>
                </div>
            </div>
        </div>
    </div>

    <!-- single dataset Edit-->
    <div class="modal fade" id="editSingleDataset" tabindex="-1" role="dialog" style="font-size:16px;">
        <div class="modal-dialog modal-dialog-centered" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="editSingleDatasetModalLabel">Edit {{editDataset.datasetName}}</h5>
                    <button type="button" aria-label="Close" ng-click="closeEnterDataDialogModal()">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>

                <div class="modal-body"  id="modalEditData">
                    <div class="table-responsive">
                        <table class="table table-striped tables">
                            <tr ng-repeat ="indicator in editDataset.indicators">
                                <td>
                                    {{indicator.name}}:
                                </td>
                                <td>
                                    <input class="form-control" type="number" ng-model="indicator.value">

                                </td>
                                <td></td>
                            </tr>
                        </table>



                    </div>
                    <div class="modal-footer">
                        <button type="button" ng-click="closeEnterDataDialogModal()">Close</button>
                        <button type="button"  data-dismiss="modal2" ng-click="editSingleDataset()">Save</button>
                    </div>
                </div>
            </div>
        </div>



    </div>


</div>
    </div>
</div>



<script type="text/javascript">
    angular.bootstrap('#single-report-datasets', ['facility']);
</script>

