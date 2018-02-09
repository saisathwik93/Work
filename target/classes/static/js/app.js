var app = angular.module('app', ['ngRoute','ngResource','ui-notification','ui.bootstrap']);
app.config(function($routeProvider){
    $routeProvider
        .when('/admin',{
            templateUrl: '/views/admin.html',
            controller: 'adminController'
        })
        .when('/user',{
            templateUrl: '/views/user.html',
            controller: 'userController'
        })
        .when('/',{
            template: '',
            controller: 'loginController'
        }).when('/login',{
        	templateUrl: '/views/login.html',
            controller: 'adminLoginController'
        })
        .otherwise(
            { redirectTo: '/'}
        );
});

app.factory('dataShare',function($rootScope){
	  var service = {};
	  service.data = false;
	  
	  service.sendData = function(data){
	      this.data = data;
	      $rootScope.$broadcast('data_shared');
	  };
	  service.getData = function(){
	    return this.data;
	  };
	  
	  return service;
	});

app.constant('config', {
    apiLocalUrl: 'http://localhost:8080/',
    apiHttpUrl: 'http://10.18.30.29:8081/springboot-angularjs-credo/'
});



