var app = angular.module('imgScoopApi', ['ui.router'])

app.config(function($stateProvider, $urlRouterProvider){
    
    $stateProvider
		.state({
		    name: 'index',
		    url: '/',
		    templateUrl: '/'
		})
		.state('profile', {})
		.state({
		    name: 'threads',
		    url: '/threads',
		    templateUrl: '/threads.html'
		  });
})