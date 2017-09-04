var app = angular.module('imgScoopApi', [ 'ui.router' ])

app.controller("MainCtrl", function($rootScope, $scope, $http) {
	// Creating threads here
	// Logging in here
	$scope.login = function(){
		$http({
			method: "POST",
			url: "/login",
			params: {"username": $scope.username, "password": $scope.password}
		}).then(function(value) {
			$rootScope.loggedin = value.data;
			console.log($rootScope.loggedin);
		}, function(reason) {
			// Invalid username or password
			console.log("failed" + reason);
		}, function(value) {
			console.log("default" + value);
		});
	};
	$scope.logout = function(){
		$http({
			method: "POST",
			url: "/logout"
		}).then(function(value) {
			$rootScope.loggedin = undefined;
		});
	};
	console.log("In the main ctrl");
});

app.controller("ThreadCtrl", function() {
	// Fetching threads for the pages here
	console.log("In the thread ctrl");
});

app.controller("ProfileCtrl", function($scope, $http, $stateParams) {
	$http({
		method : 'GET',
		url : '/post/user/' + $stateParams.username
	}).then(function(response) {
		$scope.posts = response.data;
	}, function(error) {
		console.log('Error: ' + error);
	}, function(notify) {
		console.log('Notify: ' + notify);
	});
	$http({
		method : 'GET',
		url : '/user/' + $stateParams.username
	}).then(function(response) {
		$scope.userInfo = response.data;
		console.log($scope.userInfo);
	});
	console.log("In the profile ctrl");
});

app.config(function($stateProvider, $urlRouterProvider) {
	// To default to the threads page
	$urlRouterProvider.when('', '/threads');
	$stateProvider.state({
		// Useless state
		name : 'index',
		url : '/',
		templateUrl : '/'
	}).state({
		name : 'profile',
		url : '/profile/:username',
		templateUrl : '/pages/profile.html',
		controller : 'ProfileCtrl'
	}).state({
		name : 'threads',
		url : '/threads',
		templateUrl : '/threads.html',
		controller : 'ThreadCtrl'
	});
})