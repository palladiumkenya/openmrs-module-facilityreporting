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
    window.OpenMRS.datim = ${datim}
        jq(document).ready(function () {

            jq('.saveData').click(function () {
                payload = {
                    "DataSetResults": result

                };
                jq.getJSON('${ ui.actionLink("facilityreporting", "facilityDataSets", "saveDataset") }',
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
<div id="create-facility-datasets" ng-controller="FacilityDataSetCtrl" ng-init='init()'>

    <div ng-repeat="control in reportList" >
        <div ng-repeat = " data in control.dataset">
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
            <button type="button" ng-click="savReportDataSets()" class="saveData">Save</button>
        </div>
    </div>


</div>
    </div>
</div>



<script type="text/javascript">
    angular.bootstrap('#create-facility-datasets', ['facility']);
</script>

