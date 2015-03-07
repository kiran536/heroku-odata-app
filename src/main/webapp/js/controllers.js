var odataControllers = angular.module('odataControllers', ['ngAnimate','ngProgress']);

odataControllers.run(function($rootScope, ngProgress) {
  $rootScope.$on('$routeChangeStart', function(ev,data) {
    ngProgress.start();
  });
  $rootScope.$on('$routeChangeSuccess', function(ev,data) {
    //ngProgress.complete();
  });
});

odataControllers.config(['$httpProvider', function($httpProvider) {
        $httpProvider.defaults.useXDomain = true;
        delete $httpProvider.defaults.headers.common['X-Requested-With'];
    }
]);

odataControllers.factory('Data', function () {
    return { url: 'http://services.odata.org/Northwind/Northwind.svc/',category:'' };
});

odataControllers.controller('LevelOneController', ['$scope', '$http','$location','Data','ngProgress',function($scope, $http,$location,Data,ngProgress) {
  $scope.Data=Data;
  ngProgress.start();
  $http.post('/app/odata/getFirstLevelEntities',Data.url).success(function(data) {
    $scope.levelOneEntities = data;
  }).error(function(data, status, headers, config) {
			$scope.errorobject=data;
		  });
  $scope.openLevelTwo=function(category){
  	$scope.Data.category=category;
  	ngProgress.complete();
  	$location.path('/odata/leveltwo');
  }
}]);

odataControllers.controller('LevelTwoController', ['$scope', '$http','$routeParams','Data','ngProgress', function($scope, $http, $routeParams,Data,ngProgress) {
  $scope.Data = Data;
  $scope.objToPost = {};
  $scope.objToPost.endPoint = Data.url;
  $scope.objToPost.category = Data.category;
  ngProgress.start();
  $http.post('/app/odata/getSecondLevelEntities', $scope.objToPost).success(function(data) {
   		$scope.levelTwoEntities = data;
   		ngProgress.complete();
  }).error(function(data, status, headers, config) {
			$scope.errorobject=data;
		  });
}]);

odataControllers.controller('HomeController',['$scope','$http','$location','Data','ngProgress',function($scope, $http,$location,Data,ngProgress) {
	$scope.Data=Data;
	$scope.label='URL of the oData Service';
	$scope.catalog={};
	ngProgress.start();
	$http.get('/app/odata/catalog').success(function(data) {
		$scope.catalog = data;
		ngProgress.complete();
	});
    $scope.openService=function(input){
      $scope.Data.url = input;
      $location.path('/odata/levelone');
    }
}]);


