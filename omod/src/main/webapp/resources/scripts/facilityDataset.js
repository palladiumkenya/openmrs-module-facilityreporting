angular.module('facility', []).
controller('FacilityDataSetCtrl', ['$scope', '$window', '$location', '$timeout','$q',
    function ($scope, $window, $location, $timeout, $q) {
        var datim = OpenMRS.datim;
        var test = [
            {
                "reportName": "Datim_v1",
                "description": "Something here",
                "dataset": [
                    {
                        "datasetName": "dataset_1",
                        "indicators": [
                            {
                                "name": "females_pregnant",
                                "id": 200
                            },
                            {
                                "name": "females_delivered",
                                "id": 300
                            },
                            {
                                "name": "females_on_art",
                                "id": 400
                            }
                        ]
                    },
                    {
                        "datasetName": "dataset_2",
                        "indicators": [
                            {
                                "name": "females_pregnant",
                                "id": 200
                            },
                            {
                                "name": "females_delivered",
                                "id": 300
                            },
                            {
                                "name": "females_on_art",
                                "id": 400
                            }
                        ]
                    }
                ]
            }
        ]
        $scope.init = function() {

            $timeout(function() {
                $q.all(test)
                    .then(function(results) {
                        $scope.reportList = results ;
                        console.log('results',results);
                    });

            },500);
        };

        $scope.typeValues = {};
        $scope.savReportDataSets = function () {

        }

    }]);
