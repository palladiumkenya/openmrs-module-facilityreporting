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
                        "dataset_id":1,
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
                        "dataset_id":2,
                        "indicators": [
                            {
                                "name": "females_pregnant",
                                "id": 202
                            },
                            {
                                "name": "females_delivered",
                                "id": 301
                            },
                            {
                                "name": "females_on_art",
                                "id": 407
                            }
                        ]
                    }
                ]
            }
        ];

        $scope.init = function() {


            $timeout(function() {
                $q.all(test)
                    .then(function(results) {
                        $scope.reportList = results ;
                    });

            },500);
        };

        $scope.typeValues = {};
        $scope.savReportDataSets = function () {
            $scope.createDatasetResultsObj = generateDatasetResultsObj($scope.reportList);
            $scope.result = $scope.createDatasetResultsObj.reduce(function(r, item) {
                var current = r.hash[item.datasetName];

                if(!current) {
                    current = r.hash[item.datasetName] = {
                        datasetName: item.datasetName,
                        datasetId:item.dataset_id,
                        indicators: []
                    };

                    r.arr.push(current);
                }

                current.indicators.push({
                    id: item.id,
                    name: item.name,
                    value: item.value
                });

                return r;
            }, { hash: {}, arr: [] }).arr;
            console.log("$scope.createDatasetResultsObj",$scope.result );
        }

        function generateDatasetResultsObj(res) {
            var payload = [];
            for (var n = 0; n < res.length; ++n ) {
                var ls = res[n].dataset;
                for (var i = 0; i < ls.length; ++i) {
                    var indicator = ls[i].indicators;
                    var id = ls[i].dataset_id;
                    var name = ls[i].datasetName;

                    for (var t =0; t < indicator.length; ++t)  {
                        var data = indicator[t];
                        for (var r in data) {
                            if (data.hasOwnProperty(r)) {
                                data['value'] = $scope.typeValues[data.id];
                                data['dataset_id'] = id;
                                data['datasetName'] = name;
                            }
                        }

                        payload.push(data);

                    }

                }

            }
            _.each(payload, function(o) {
                if (o.value === undefined) {
                    o.value = "";
                }

            });

            return payload;

        }

    }]);
