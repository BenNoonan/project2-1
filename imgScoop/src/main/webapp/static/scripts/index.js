var app = angular.module('imgScoopApi', [ 'ui.router', 'ngFileUpload' ]);

app.controller("MainCtrl", function($rootScope, $scope, $http, $window) {
	// Creating threads here
	// Logging in here
	$rootScope.loggedin = JSON.parse($window.localStorage.getItem('loggedin'))
	$scope.login = function() {
		$http({
			method : "POST",
			url : "/login",
			params : {
				"username" : $scope.username,
				"password" : $scope.password
			}
		}).then(function(value) {
			$window.localStorage.setItem('loggedin', JSON.stringify(value.data))
			$rootScope.loggedin = JSON.parse($window.localStorage.getItem('loggedin'));
		}, function(reason) {
			// Invalid username or password
			console.log("failed" + reason);
		}, function(value) {
			console.log("default" + value);
		});
	};
	$scope.logout = function() {
		$http({
			method : "POST",
			url : "/logout"
		}).then(function(value) {
			$window.localStorage.removeItem('loggedin');
			$rootScope.loggedin = null;
		});
	};
	console.log("In the main ctrl");
});

app.controller('ForumCtrl', function($http, $scope) {
	$scope.count = 1;
	$scope.bool = 1;
	$http({
		method : 'GET',
		url : 'thread/page/1'
	}).then(function(response) {
		$scope.displayAllThread = response.data;
	});
	$scope.pageNext = function() {
		$scope.count++;
		$http({
			method : 'GET',
			url : 'thread/page/' + $scope.count
		}).then(function(response) {
			$scope.displayAllThread = response.data;
			if ($scope.displayAllThread.length < 15) {
				$scope.bool = -1;
			}
		});
	}
	$scope.pagePrev = function() {
		$scope.count--;
		if ($scope.count <= 1) {
			$scope.bool = 1;
		}
		$http({
			method : 'GET',
			url : 'thread/page/' + $scope.count
		}).then(function(response) {
			$scope.displayAllThread = response.data;
		});
	}
	$scope.createThread = function() {
		$http({
			method : 'POST',
			url : '/thread',
			params: {
				title: $scope.title
			}
		}).then(function(response) {
			//Take me to that thread
			$scope.newThread = response.data;
			window.location.href = window.location.origin + "/#!/thread?id=" + 
									$scope.newThread.id;
		});
	}
	$scope.deleteThread = function(id) {
		console.log("DELETE " + id);
		$http({
			method : 'DELETE',
			url : '/thread',
			params: {
				id: id
			}
		}).then(function(response) {
			//Fresh page
			
			//console.log(response);
			window.location.reload();
		});
	}
});

app.controller("ThreadCtrl", function($scope, $rootScope, Upload, $timeout,
		$http, $stateParams) {
	console.log("ThreadCtrl");
	$http({
		method : 'GET',
		url : 'thread/id=' + $stateParams.id
	}).then(function(response) {
		$scope.threadContent = response.data[0];
		console.log("Response Get" + $scope.threadContent);
	})
	//Image uploading and posting
	$scope.createPost = function(body, image) {
		console.log(image + " " + body);
		if (image == undefined) {
			console.log("About to post no image");
			$http({
				method: 'POST',
				url: '/post',
				params: {
					body: body,
					thread: $scope.threadContent.id
				}
			}).then(function(value) {
				window.location.reload();
				console.log("success");
			});
		} else {
			console.log("about to upload image");
			image.upload = Upload.upload({
				method : 'POST',
				url : '/post/img',
				data : {
					body : body,
					image : image,
					thread : $scope.threadContent.id
				}
			});
			image.upload.then(function(value) {
				$timeout(function() {
					image.result = value.data;
					window.location.reload();
					console.log("Image result success timeout" + image.result);
				});
			}, function(reason) {
				console.log("reason");
				console.log(reason);
			}, function(value) {
				console.log("something else");
				console.log(value);
			})
		}
	}
});

app.controller("ProfileCtrl", function($scope, $http, $stateParams) {
	$http({
		method : 'GET',
		url : '/post/user/' + $stateParams.username
	}).then(function(response) {
		$scope.posts = response.data;
	});
	$http({
		method : 'GET',
		url : '/user/' + $stateParams.username
	}).then(function(response) {
		$scope.userInfo = response.data;
		console.log($scope.userInfo);
	});
});

app.controller('SignupCtrl', function($rootScope, $scope, $http, $window) {
	$scope.signup = function(){
		$http({
			method: 'POST',
			url: '/user',
			data: $scope.create
		}).then(function(response){
			console.log(response);
		});
	}
});

app.config(function($stateProvider, $urlRouterProvider) {
	// To default to the threads page
	$urlRouterProvider.when('', '/forum');
	$stateProvider.state({
		name : 'profile',
		url : '/profile/:username',
		templateUrl : '/pages/profile.html',
		controller : 'ProfileCtrl'
	}).state({
		name : 'signup',
		url : '/signup',
		templateUrl: '/pages/signup.html',
		controller: 'SignupCtrl'
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