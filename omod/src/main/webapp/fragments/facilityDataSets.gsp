<%
    ui.includeJavascript("uicommons", "angular.min.js")
    ui.includeJavascript("uicommons", "angular-app.js")
    ui.includeJavascript("uicommons", "angular-resource.min.js")
    ui.includeJavascript("uicommons", "angular-common.js")
    ui.includeJavascript("uicommons", "angular-ui/ui-bootstrap-tpls-0.11.2.js")
    ui.includeJavascript("uicommons", "ngDialog/ngDialog.js")
    ui.includeJavascript("facilityreporting", "facilityDataset.js")

%>
<script >
    window.OpenMRS = window.OpenMRS || {};
    window.OpenMRS.datim = ${datim}
</script>


<div id="create-facility-datasets" ng-controller="FacilityDataSetCtrl" ng-init='init()'>

    <div ng-repeat="control in reportList" >
        <div class="card">
        <div class="card-body">

        <div ng-repeat = " data in control.dataset">
            <div class="form-group row">
                <h4>{{data.datasetName}}</h4>
                <div ng-repeat ="indicator in data.indicators">
                    <table>
                        <thead>
                        <tr>
                            <th width="25%"></th>
                            <th width="15%"></th>
                            <th width="60%"></th>
                        </tr>
                        </thead>
                        <tbody>
                        <tr>
                            <td>
                                {{indicator.name}}:
                            </td>
                            <td>
                                <input class="form-control" type="number" ng-model="typeValues[indicator.id]">

                            </td>
                            <td></td>
                        </tr>
                        </tbody>
                    </table>

                </div>

            </div>
        </div>
        </div>
        </div>
        <div>
            <button ng-click ="savReportDataSets">Save</button>
        </div>
</div>

    </div>

<script type="text/javascript">
    angular.bootstrap('#create-facility-datasets', ['facility']);
</script>

