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
    window.OpenMRS.singleDataset = ${singleDataset}

</script>




<div class="ke-page-content">


    <div id="viewData" ng-controller="FacilityDataSetCtrl" ng-init='init()'>
        
        <div>
            Start Date: ${ui.includeFragment("kenyaui", "field/java.util.Date", [id: "startDate", formFieldName: "startDate"])}
            End Date: ${ui.includeFragment("kenyaui", "field/java.util.Date", [id: "endDate", formFieldName: "endDate"])}


        </div>
        <div class="table-responsive" style="padding-top: 30px">
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

    </div>




</div>
<script type="text/javascript">
    angular.bootstrap('#viewData', ['facility']);
</script>
