(function() {

    var app = angular.module("app", []);

    app.controller("HttpCtrl", function($scope, $http) {
        var app = this;
        $scope.navTitle = '芸能人一覧';
        $scope.operation="";
        $scope.isSaveDisabled = true;
        $scope.isDeleteDisabled = true;

        var response = $http.get('/one-system/webapi/user/list');
        response.success(function(data) {
            $scope.actors = data;
            console.log("[main] # of items: " + data.length)
            angular.forEach(data, function(element) {
                console.log("[main] actor: " + element.name);
            });
        })
        response.error(function(data, status, headers, config) {
            alert("AJAX failed to get data, status=" + status);
        })


        $scope.getActor = function(id) {
            var response = $http.get('/one-system/webapi/user/get_by_id/'+ id );

            response.success(function(data) {
                $scope.actor = data;
                $scope.operation="update";
                $scope.isSaveDisabled = false;
                $scope.isDeleteDisabled = false;
            })

            response.error(function(data, status, headers, config) {
                alert("AJAX failed to get data, status=" + status);
            })
        };

        $scope.searchActor = function(name) {
            var app = this;
            $scope.navTitle = '検索';

            var response = $http.get('/one-system/webapi/user/list_by_name/' + name);
            response.success(function(data) {
                $scope.actors = data;
                $scope.$apply();

                console.log("[searchActor] # of items: " + data.length)
                angular.forEach(data, function(element) {
                    console.log("[searchActor] actor: " + element.name);
                });
            });

            response.error(function(data, status, headers, config) {
                alert("AJAX failed to get data, status=" + status);
            })
        };

        $scope.clearForm = function() {
            $scope.actor = {
                    id:'',
                    name:'',
                    birthName:'',
                    birthDay:'',
                    email:'',
                    image:'',
            };
        }

        $scope.addNew = function(element) {
            $scope.operation="create";
            $scope.clearForm();
            main.id.focus();
            $scope.isSaveDisabled = false;
            $scope.isDeleteDisabled = true;
        }

        $scope.saveActor = function(id) {
            $scope.jsonObj = angular.toJson($scope.actor, false);
            console.log("[update] data: " + $scope.jsonObj);

            if ($scope.operation == "update") {
                var response = $http.put('/one-system/webapi/user/update_by_id/' + id, $scope.jsonObj);
                response.success(function(data, status, headers, config) {
                    $scope.resetSearch();
                });

                response.error(function(data, status, headers, config) {
                    alert("AJAX failed to get data, status=" + status);
                })
            } else if ($scope.operation == "create") {
                var response = $http.post('/one-system/webapi/user/add', $scope.jsonObj);
                response.success(function(data, status, headers, config) {
                    $scope.resetSearch();
                });

                response.error(function(data, status, headers, config) {
                    alert("AJAX failed to get data, status=" + status);
                })
            }
        };

        $scope.deleteActor = function(id) {
            var response = $http.post('/one-system/webapi/user/delete_by_id/' + id);
            response.success(function(data, status, headers, config) {
                $scope.resetSearch();
            });

            response.error(function(data, status, headers, config) {
                alert("AJAX failed to get data, status=" + status);
            })
        };

        $scope.resetSearch = function(name) {
            var app = this;
            $scope.operation="";
            $scope.clearForm();
            $scope.isSaveDisabled = true;
            $scope.isDeleteDisabled = true;
            $scope.navTitle = '芸能人一覧';
            $scope.searchName = '';

            var response = $http.get('/one-system/webapi/user/list');
            response.success(function(data) {
                $scope.actors = data;
                $scope.$apply();
                console.log("[resetSearch] # of items: " + data.length)
            });

            response.error(function(data, status, headers, config) {
                alert("AJAX failed to get data, status=" + status);
            })
        };
    });
})();