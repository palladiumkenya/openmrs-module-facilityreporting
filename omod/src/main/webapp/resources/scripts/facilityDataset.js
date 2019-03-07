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
                                "id": 200,
                                "value":36
                            },
                            {
                                "name": "females_delivered",
                                "id": 300,
                                "value":30
                            },
                            {
                                "name": "females_on_art",
                                "id": 400,
                                "value":""
                            }
                        ]
                    },
                    {
                        "datasetName": "dataset_2",
                        "dataset_id":2,
                        "indicators": [
                            {
                                "name": "pregnant",
                                "id": 202,
                                "value":16
                            },
                            {
                                "name": "vmmc",
                                "id": 301,
                                "value":38
                            },
                            {
                                "name": "currently_in_care",
                                "id": 407,
                                "value":33
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
        $scope.singleDatasetValues = {};
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
        function generatePayloadSingleDataSet(rs) {
            var payload = [];
            var name = rs.datasetName;
            var id =rs.dataset_id;
            for (var t =0; t < rs.indicators.length; ++t)  {
                var data = rs.indicators[t];
                for (var r in data) {
                    if (data.hasOwnProperty(r)) {
                        data['value'] = $scope.singleDatasetValues[data.id];
                        data['dataset_id'] = id;
                        data['datasetName'] = name;
                    }
                }

                payload.push(data);

            }
            _.each(payload, function(o) {
                if (o.value === undefined) {
                    o.value = "";
                }

            });
            return payload;

        }

        $scope.closeEnterDataDialogModal = function() {
            $scope.singleDatasetValues = {};
            $('#enterDataSingle').modal('hide');
            $('#editSingleDataset').modal('hide');
        };
        $scope.closeViewDataSetReport = function() {
            $('#viewDatasetReport').modal('hide');
        };
        $scope.viewReportDataSets = function (res) {
            $scope.singleDatasetView = res

        };
        $scope.captureDataForSingleDataset = function (res) {
            $scope.singleEntryDataset = res

        }
        $scope.editResultsDatasetDialog = function (res) {
            $scope.editDataset = res
        }
        $scope.saveSingleDataSetReport = function () {
            $scope.createDatasetResultsObj = generatePayloadSingleDataSet($scope.singleEntryDataset);
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

        }
        
        $scope.editSingleDataset =function () {
            var payload = [];
            var name = $scope.editDataset.datasetName;
            var id = $scope.editDataset.dataset_id;
            for (var t =0; t < $scope.editDataset.indicators.length; ++t)  {
                var data = $scope.editDataset.indicators[t];
                for (var r in data) {
                    if (data.hasOwnProperty(r)) {
                        data['value'] = $scope.singleDatasetValues[data.id];
                        data['dataset_id'] = id;
                        data['datasetName'] = name;
                    }
                }

                payload.push(data);

            }
            console.log('this is the edit payload',payload);

            /*_.each(payload, function(o) {
                if (o.value === undefined) {
                    o.value = "";
                }

            });*/
            return payload;
            
        }

    }]);
