angular.module('facility', []).
controller('FacilityDataSetCtrl', ['$scope', '$window', '$location', '$timeout','$q',
    function ($scope, $window, $location, $timeout, $q) {
        var datasetLists = OpenMRS.datasetLists;
        var singleDataset = OpenMRS.singleDataset;
        var dataNodes = OpenMRS.dataNodes;
        var editDatasetPayload = OpenMRS.editDatasetPayload;
        var datasetHstoryPayload= OpenMRS.datasetHstoryPayload;

        window.datasetPayload = [];

        $scope.init = function() {


            $timeout(function() {
                $q.all(datasetLists)
                    .then(function(results) {
                        $scope.reportList = results ;
                    });

            },10);

            $timeout(function() {
                $q.all(singleDataset)
                    .then(function(results) {
                        $scope.singleDatasetValue = results ;
                    });

            },100);

            $timeout(function() {
                $q.all(dataNodes)
                    .then(function(results) {
                        $scope.dataNodes = results ;
                    });

            },100);

            $timeout(function() {
                $q.all(editDatasetPayload)
                    .then(function(results) {
                        $scope.editDatasetsValue = results ;
                    });

            },100);

            $timeout(function() {
                $q.all(datasetHstoryPayload)
                    .then(function(results) {
                        $scope.datasetHistoryList = results ;
                    });

            },100);
        };

        $scope.typeValues = {};
        $scope.singleDatasetValues = {};
        $scope.showErrorToast ='';
        $scope.savReportDataSets = function () {
            $scope.startDate = angular.element('#startDate').val();
            $scope.endDate = angular.element('#endDate').val();
            var currentDate = new Date();
            $scope.givenDate = new Date($scope.startDate);

            /*if($scope.givenDate > currentDate){
                $scope.showErrorToast = 'Start date is greater than the current date.';

                $('#orderError').modal('show');
                return;
            }*/

            if($scope.endDate < $scope.startDate){
                $scope.showErrorToast = 'End date can not be before start date';

                $('#orderError').modal('show');
                return;
            }
            if($scope.startDate === '') {
                $scope.showErrorToast = 'Please provide start date';

                $('#orderError').modal('show');
                return;
            }
            if($scope.endDate === '') {
                $scope.showErrorToast = 'Please provide end date';

                $('#orderError').modal('show');
                return;
            }
            $scope.startDate = $scope.startDate.substring(0, 10);
            $scope.endDate = $scope.endDate.substring(0, 10);


            $scope.createDatasetResultsObj = generateDatasetResultsObj($scope.reportList);

            $scope.result = $scope.createDatasetResultsObj.reduce(function(r, item) {
                var current = r.hash[item.datasetName];

                if(!current) {
                    current = r.hash[item.datasetName] = {
                        datasetName: item.datasetName,
                        datasetId:item.dataset_id,
                        startDate:$scope.startDate,
                        endDate:$scope.endDate,
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
            datasetPayload = $scope.result;
        }

        function generateDatasetResultsObj(res) {
            var payload = [];
                for (var i = 0; i < res.length; ++i) {
                    var indicator = res[i].indicators;
                    var id = res[i].dataset_id;
                    var name = res[i].datasetName;

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

            _.each(payload, function(o) {
                if (o.value === undefined) {
                    o.value = "";
                }

            });

            return payload;

        }
        function generatePayloadSingleDataSet(rs) {
            var payload = [];
            var name = rs[0].datasetName;
            var id =rs[0].dataset_id;
            for (var t =0; t < rs[0].indicators.length; ++t)  {
                var data = rs[0].indicators[t];
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

        $scope.closeModal = function() {
            $('#orderError').modal('hide');

        }
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

            $scope.startDate = angular.element('#startDate').val();
            $scope.endDate = angular.element('#endDate').val();
            var currentDate = new Date();
            $scope.givenDate = new Date($scope.startDate);

            if($scope.startDate === '') {
                $scope.showErrorToast = 'Please provide start date';

                $('#orderError').modal('show');
                return;
            }
            if($scope.endDate === '') {
                $scope.showErrorToast = 'Please provide end date';

                $('#orderError').modal('show');
                return;
            }
            $scope.startDate = $scope.startDate.substring(0, 10);
            $scope.endDate = $scope.endDate.substring(0, 10);
            $scope.createDatasetResultsObj = generatePayloadSingleDataSet($scope.singleDatasetValue);
            $scope.result = $scope.createDatasetResultsObj.reduce(function(r, item) {
                var current = r.hash[item.datasetName];

                if(!current) {
                    current = r.hash[item.datasetName] = {
                        datasetName: item.datasetName,
                        datasetId:item.dataset_id,
                        startDate:$scope.startDate,
                        endDate:$scope.endDate,
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

            datasetPayload = $scope.result;

        }
        $scope.getReportDataset = function() {
            $scope.startDate = angular.element('#startDate').val();
            $scope.endDate = angular.element('#endDate').val();
            $scope.startDate = $scope.startDate.substring(0, 10);
            $scope.endDate = $scope.endDate.substring(0, 10);

            dateObject = {
                startDate:$scope.startDate,
                endDateDate:$scope.endDate
            };
            datasetPayload.push(dateObject);

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

            return payload;
            
        }

    }]);
