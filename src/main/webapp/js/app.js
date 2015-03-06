var myApp = angular.module('myApp', [
  'ngRoute',
  'odataControllers'
]);


myApp.config(['$routeProvider', function($routeProvider) {
  $routeProvider.
  when('/odata/levelone', {
    templateUrl: 'partials/levelone.html',
    controller: 'LevelOneController'
  }).
  when('/odata/level2', {
    templateUrl: 'partials/leveltwo.html',
    controller: 'LevelTwoController'
  }).
  when('/home', {
    templateUrl: 'partials/home.html',
    controller: 'HomeController'
  }).
  otherwise({
    redirectTo: '/home'
  });
}]);