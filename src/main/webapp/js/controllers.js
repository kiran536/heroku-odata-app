var odataControllers = angular.module('odataControllers', ['ngAnimate']);

odataControllers.config(['$httpProvider', function($httpProvider) {
        $httpProvider.defaults.useXDomain = true;
        delete $httpProvider.defaults.headers.common['X-Requested-With'];
    }
]);

odataControllers.factory('Data', function () {
    return { url: 'http://services.odata.org/Northwind/Northwind.svc/' };
});

odataControllers.controller('LevelOneController', ['$scope', '$http','Data',function($scope, $http,Data) {
  $scope.product = {};
  $scope.Data=Data;
  alert('Using url:'+Data.url);
  $http.post('/app/odata/getFirstLevelEntities',Data.url).success(function(data) {
    $scope.products = data;
  });
}]);

odataControllers.controller('LevelTwoController', ['$scope', '$http','$routeParams', function($scope, $http, $routeParams) {
  $http.post('/app/odata/getSecondLevelEntities').success(function(data) {
    $scope.products = data;
    $scope.whichItem = $routeParams.itemId;

    if ($routeParams.itemId > 0) {
      $scope.prevItem = Number($routeParams.itemId)-1;
    } else {
      $scope.prevItem = $scope.products.length-1;
    }

    if ($routeParams.itemId < $scope.products.length-1) {
      $scope.nextItem = Number($routeParams.itemId)+1;
    } else {
      $scope.nextItem = 0;
    }

  });
}]);

odataControllers.controller('HomeController',['$scope','$http','$location','Data',function($scope, $http,$location,Data) {
	$scope.Data=Data;
	$scope.label='URL of the oData Service';
}]);


