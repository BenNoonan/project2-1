var app = angular.module('imgScoopApi', [ 'ui.router', 'ngFileUpload' ]);

sortFunc = function sortByTime(arr) {
	return arr.sort(function(x, y) {
		return y.posts[y.posts.length - 1].id - x.posts[x.posts.length - 1].id;
	});
}
sortPosts = function(arg) {
	return arg.sort(function(a, b) {
		return a.id > b.id;
	});
}

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
		}).then(
				function(value) {
					$window.localStorage.setItem('loggedin', JSON
							.stringify(value.data))
					$rootScope.loggedin = JSON.parse($window.localStorage
							.getItem('loggedin'));
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
});

app.controller('ForumCtrl', function($http, $scope) {
	$scope.count = 1;
	$scope.bool = 1;
	$http({
		method : 'GET',
		url : 'thread/page/1'
	}).then(function(response) {
		$scope.displayAllThread = sortFunc(response.data);
		$scope.displayAllThread.forEach(function(arg) {
			arg.posts = sortPosts(arg.posts)
		});
		console.log($scope.displayAllThread);
	});
	$scope.pageNext = function() {
		$scope.count++;
		$http({
			method : 'GET',
			url : 'thread/page/' + $scope.count
		}).then(function(response) {

			if (response.data.length < 15) {
				$scope.bool = -1;
			}
			$scope.displayAllThread = sortFunc(response.data);
			$scope.displayAllThread.forEach(function(arg) {
				arg.posts = sortPosts(arg.posts)
			});
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
			$scope.displayAllThread = sortFunc(response.data);
			$scope.displayAllThread.forEach(function(arg) {
				arg.posts = sortPosts(arg.posts)
			});
		});
	}
	$scope.createThread = function() {
		$http({
			method : 'POST',
			url : '/thread',
			params : {
				title : $scope.title
			}
		}).then(
				function(response) {
					// Take me to that thread
					$scope.newThread = response.data;
					window.location.href = window.location.origin
							+ "/#!/thread?id=" + $scope.newThread.id;
				});
	}
	$scope.deleteThread = function(f) {
		console.log(f)
		f.posts.forEach(function(post){
			$http({
				method : 'DELETE',
				url : '/post',
				params : {
					id : post.id
				}
			});
		});		
		$http({
			method : 'DELETE',
			url : '/thread',
			params : {
				id : f.id
			}
		}).then(function(response) {
			// Fresh page
			window.location.reload();
		});
	}
});

app.controller("ThreadCtrl", function($scope, $rootScope, Upload, $timeout,
		$http, $stateParams) {
	$http({
		method : 'GET',
		url : 'thread/id=' + $stateParams.id
	}).then(function(response) {
		$scope.threadContent = response.data[0];
		$scope.threadContent.posts = sortPosts($scope.threadContent.posts)
	})
	$scope.deletePost = function(id) {
		$http({
			method : 'DELETE',
			url : '/post',
			params : {
				id : id
			}
		}).then(function(response) {
			window.location.reload();
		});
	}
	// Image uploading and posting
	$scope.createPost = function(body, image) {
		if (image == undefined) {
			console.log("About to post no image");
			$http({
				method : 'POST',
				url : '/post',
				params : {
					body : body,
					thread : $scope.threadContent.id
				}
			}).then(function(value) {
				window.location.reload();
				console.log("success");
			}, function(reason) {
				console.log(reason)
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
				});
			}, function(reason) {
				console.log(reason);
			}, function(value) {
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
	$scope.signup = function() {
		$http({
			method : 'POST',
			url : '/user',
			data : $scope.create
		}).then(function(response) {
			window.location.href = window.location.origin
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
		templateUrl : '/pages/signup.html',
		controller : 'SignupCtrl'
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