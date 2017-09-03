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
	$scope.pageNext = function() {
		$scope.count++;
	}
	console.log($scope.count);
	$http({	
		method: 'GET',
		url: 'thread/page/1'//+$scope.count
	})
	.then(function(response) {
		$scope.displayAllThread = response.data;
	});
});

/*angular.module('imgScoopApi')
.controller('pageCtrl', function($scope) {
	console.log("Made pager");
	
	$scope.count = 1;
	$scope.pageNext = function() {
		$scope.count++;
	}
	console.log($scope.count);
});*/