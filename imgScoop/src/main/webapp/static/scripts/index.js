var app = angular.module('imgScoopApi', [ 'ui.router' ])

app.controller("MainCtrl", function($scope) {
	// Creating threads here
	// Logging in here
	$scope.clickUsername = function(username) {
		$scope.clicked = username;
	}
	console.log("In the main ctrl");
});

app.controller("ThreadCtrl", function() {
	// Fetching threads for the pages here
	console.log("In the thread ctrl");
});

app.controller("ProfileCtrl", function($scope, $http, $stateParams) {
	// Viewing an individual profile here
	// When we remove the json ignores, we can get remove this first http call
	// and keep the second to get this specific user by his user name
	console.log();
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