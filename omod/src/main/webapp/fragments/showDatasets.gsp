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


    <div id="singleData" ng-controller="FacilityDataSetCtrl" ng-init='init()'>
        <fieldset class=" scheduler-border">
            <legend class="scheduler-border"> Dataset Reporting History</legend>

        <div class="table-responsive" style="padding-top: 30px">
            <div class="table-responsive">
                <table class="table table-striped tables">
                    <tr>
                        <th>Start Date</th>
                        <th>End Date</th>
                        <th>Actions</th>
                    </tr>
                    <tr ng-repeat=" data in datasetHistoryList">
                        <td>
                            {{data.startDate}}
                        </td>
                        <td>
                            {{data.endDate}}
                        </td>
                        <td>
                            <button type="button" data-toggle="modal" data-target="#viewDatasetReport"
                                    onclick="ui.navigate('${ ui.pageLink("facilityreporting", "viewReportData", [ reportId: report.id, datasetId:dataset, returnUrl: ui.thisUrl() ])}')">View Data
                            </button>
                            <button type="button" class="fa fa-edit fa-1x"
                                    onclick="ui.navigate('${ ui.pageLink("facilityreporting", "editDataset", [ reportId: report.id, datasetId:dataset, returnUrl: ui.thisUrl() ])}')">

                                Edit</button>
                        </td>
                        </td>
                    </tr>

                </table>




            </div>


        </div>
        <div>

        </div>
        </fieldset>
        <!--Error Modal -->
        <div class="modal fade" id="orderError" tabindex="-1" role="dialog" style="font-size:16px;">
            <div class="modal-dialog modal-dialog-centered" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="exampleModalLabel">Server Error</h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>

                    <div class="modal-body" id="modal-text">
                        {{showErrorToast}}
                    </div>
                    <div class="modal-footer">
                        <button type="button"  data-dismiss="modal2" ng-click="closeModal()">Close</button>
                    </div>
                </div>
            </div>
        </div>


    </div>




</div>
<script type="text/javascript">
    angular.bootstrap('#singleData', ['facility']);
</script>
