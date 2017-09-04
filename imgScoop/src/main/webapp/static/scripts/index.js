var app = angular.module('imgScoopApi', [ 'ui.router' ])

app.controller("MainCtrl", function($scope) {
	// Creating threads here
	// Logging in here
	$scope.clickUsername = function(username) {
		$scope.clicked = username;
	}
	console.log("In the main ctrl");
});

app.controller('ForumCtrl', function($http, $scope) {
	console.log("I made it inside display all thread");
	
	$scope.count = 1;
	$scope.bool = 1;
	$http({	
		method: 'GET',
		url: 'thread/page/1'
	})
	.then(function(response) {
		$scope.displayAllThread = response.data;
	});
	$scope.pageNext = function() {
		$scope.count++;
		$http({	
			method: 'GET',
			url: 'thread/page/'+$scope.count
		})
		.then(function(response) {
			$scope.displayAllThread = response.data;
			if($scope.displayAllThread.length < 15){
				$scope.bool = -1;
			}
		});
	}
	$scope.pagePrev = function() {
		$scope.count--;
		if($scope.count <= 1){
			$scope.bool = 1;
		}
		$http({	
			method: 'GET',
			url: 'thread/page/'+$scope.count
		})
		.then(function(response) {
			$scope.displayAllThread = response.data;
		});
	}
	
});

app.controller("ThreadCtrl", function($scope, $http, $stateParams) {
	console.log("ThreadCtrl");
	$http({
		method : 'GET',
		url : 'thread/id='+$stateParams.id
	})
	.then(function(response) {
		$scope.threadContent = response.data[0];
		console.log("Response " + $scope.threadContent);
	})
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
	$urlRouterProvider.when('', '/forum');
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
		name : 'forum',
		url : '/forum',
		templateUrl : '/forum.html',
		controller : 'ForumCtrl'
	}).state({
		name : 'thread',
		url : '/thread?t=:id',
		templateUrl : '/pages/thread.html',
		controller : 'ThreadCtrl'
	});
})