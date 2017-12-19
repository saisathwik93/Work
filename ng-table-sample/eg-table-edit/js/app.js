var app = angular.module('myApp', []);


app.controller("MainController",['$scope',function($scope){

   $scope.data = [{ firstName:"Anupesh",lastName:"Pachori",email:"test@gmail.com",
	                   project:"Otsuka",designation:"Software Engineer",empId:"10001"},
	              {firstName:"Anshul",lastName:"Tyagi",email:"test1@gmail.com",
	                   project:"Otsuka",designation:"Solution Architect",empId:"10002"                 
	              } ];


	$scope.empoyees = angular.copy( $scope.data);
	 $scope.enabledEdit =[];

    $scope.addEmployee = function(){
	    var emp ={ firstName:"",lastName:"",email:"",
	                   project:"",designation:"",empId:"",disableEdit:false};
		$scope.empoyees.push(emp);
		 $scope.enabledEdit[$scope.empoyees.length-1]=true;
	}
	$scope.editEmployee = function(index){
		console.log("edit index"+index);
		$scope.enabledEdit[index] = true;
	}
	$scope.deleteEmployee = function(index) {
		  $scope.empoyees.splice(index,1);
	}
	
	$scope.submitEmployee = function(){

		console.log("form submitted:"+angular.toJson($scope.empoyees ));
	}
	
}]);
