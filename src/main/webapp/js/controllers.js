var odataControllers = angular.module('odataControllers', ['ngAnimate']);

odataControllers.config(['$httpProvider', function($httpProvider) {
        $httpProvider.defaults.useXDomain = true;
        delete $httpProvider.defaults.headers.common['X-Requested-With'];
    }
]);

odataControllers.factory('Data', function () {
    return { url: 'http://services.odata.org/Northwind/Northwind.svc/',category:'' };
});

odataControllers.controller('LevelOneController', ['$scope', '$http','$location','Data',function($scope, $http,$location,Data) {
  $scope.Data=Data;
  $http.post('/app/odata/getFirstLevelEntities',Data.url).success(function(data) {
    $scope.levelOneEntities = data;
  });
  $scope.openLevelTwo=function(category){
  	$scope.Data.category=category;
  	$location.path('/odata/leveltwo');
  }
}]);

odataControllers.controller('LevelTwoController', ['$scope', '$http','$routeParams','Data', function($scope, $http, $routeParams,Data) {
  $scope.Data = Data;
  $scope.objToPost = {};
  $scope.objToPost.endPoint = Data.url;
  $scope.objToPost.category = Data.category;
  
  $http.post('/app/odata/getSecondLevelEntities', $scope.objToPost).success(function(data) {
   		$scope.levelTwoEntities = data;
  });
}]);

odataControllers.controller('HomeController',['$scope','$http','$location','Data',function($scope, $http,$location,Data) {
	$scope.Data=Data;
	$scope.label='URL of the oData Service';
	$scope.catalog={};
	$http.get('/app/odata/catalog').success(function(data) {
		$scope.catalog = data;
	});
    $scope.openService=function(input){
      $scope.Data.url = input;
      $location.path('/odata/levelone');
    }
}]);


