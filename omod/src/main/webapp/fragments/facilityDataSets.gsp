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
<script type="text/javascript" >
    window.OpenMRS = window.OpenMRS || {};
    window.OpenMRS.datasetLists = ${datasetLists}

        jq = jQuery;

        jq(document).ready(function () {

        });
        jq(document).on('click','#button1',function(e) {
            console.log('payload==========dont click me');
            if(datasetPayload.length === 0) {
                return
            }
            if(givenDate > currentDate) {
                return
            }
            payload = {
                "dataSetResults": datasetPayload

            };
            console.log('payload==========', payload);
            jq.getJSON('${ ui.actionLink("facilityreporting", "facilityDataSets", "saveDataSetReport") }',
                {
                    'payload': JSON.stringify(payload) , 'datasetId':1, 'reportId': ${report.id}
                })
                .success(function (data) {
                    payload = {};
                 //   window.location.reload(true);
                })
                .error(function (xhr, status, err) {
                    console.log('AJAX error ' + JSON.stringify(xhr));
                    console.log("response text: " + JSON.stringify(xhr.statusText));

                })
        });
</script>

<div class="ke-page-content">
    <div class="ui-tabs">
<div id="create-facility-datasets" ng-controller="FacilityDataSetCtrl" ng-init='init()'>
    <div>
      Start Date: ${ui.includeFragment("kenyaui", "field/java.util.Date", [id: "startDate", formFieldName: "startDate"])}
      End Date: ${ui.includeFragment("kenyaui", "field/java.util.Date", [id: "endDate", formFieldName: "endDate"])}


    </div>

    <div>
        <div ng-repeat = "data in reportList">
            <div class="form-group row">
                <fieldset>
                <legend>{{data.datasetName}}</legend>
                <div ng-repeat ="indicator in data.indicators" class="table-responsive">
                    <table class="table table-striped tables">
                        <tr>
                            <td>
                                {{indicator.name}}:
                            </td>
                            <td>
                                <input class="form-control" type="number" ng-model="typeValues[indicator.id]">

                            </td>
                            <td></td>
                        </tr>
                    </table>

                </div>
                </fieldset>

            </div>
        </div>
        <div>
            <button type="button" ng-click="savReportDataSets()" class="saveData" id="button1"
            >Save</button>
        </div>
    </div>

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
</div>



<script type="text/javascript">
    angular.bootstrap('#create-facility-datasets', ['facility']);
</script>

