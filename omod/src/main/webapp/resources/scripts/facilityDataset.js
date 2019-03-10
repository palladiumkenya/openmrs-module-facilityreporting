angular.module('facility', []).
controller('FacilityDataSetCtrl', ['$scope', '$window', '$location', '$timeout','$q',
    function ($scope, $window, $location, $timeout, $q) {
        var datasetLists = OpenMRS.datasetLists;
        var singleDataset = OpenMRS.singleDataset;
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

            },10);
        };

        $scope.typeValues = {};
        $scope.singleDatasetValues = {};
        $scope.showErrorToast ='';
        $scope.savReportDataSets = function () {
            $scope.startDate = angular.element('#startDate').val();
            $scope.endDate = angular.element('#endDate').val();
            var currentDate = new Date();
            $scope.givenDate = new Date($scope.startDate);

            if($scope.givenDate > currentDate){
                $scope.showErrorToast = 'Start date is greater than the current date.';

                $('#orderError').modal('show');
                return;
            }

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
            /*$scope.givenDate = new Date($scope.startDate);

            if($scope.givenDate > currentDate){
                $scope.showErrorToast = 'Start date is greater than the current date.';

                $('#orderError').modal('show');
                return;
            }

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
            }*/
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
            /*_.each(payload, function(o) {
                if (o.value === undefined) {
                    o.value = "";
                }

            });*/
            return payload;
            
        }

    }]);
