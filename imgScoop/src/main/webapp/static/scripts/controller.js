/*angular.module('imgScoopApi')
	.controller('listThreadAll', function($http, $scope) {
		console.log("I made it inside display all thread");
		
		$http({	
			method: 'GET',
			url: 'thread/all'
		})
		.then(function(response) {
			$scope.displayAllThread = response.data;
		});
		
	});*/

angular.module('imgScoopApi')
.controller('listThread', function($http, $scope) {
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
			console.log($scope.count);
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