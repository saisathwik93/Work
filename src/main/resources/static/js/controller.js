app.controller('adminController', function($scope, dataShare, $location, $http, Notification, $uibModal, 
		config) {

	$scope.status = false;
	$scope.enabledEdit = [];
	if (dataShare.getData().data) {
		$scope.status = true;
		// use this info for collapsable bar
		for(var i=0;i<dataShare.getData().data.length;i++){
			dataShare.getData().data[i].previleges = dataShare.getData().data[i].previleges .split(',');
			dataShare.getData().data[i].actions = dataShare.getData().data[i].actions .split(',');
		}
		$scope.users = angular.copy(dataShare.getData().data);
	} else {
		$scope.status = false;
	}

	$scope.editEmployee = function(index,userData){
		$scope.user = userData;
		$scope.user.index = index;
		if ($scope.user) {
			var modalInstance = $uibModal.open({
				templateUrl: 'userModel.html',
				controller: 'ModalInstanceCtrl',
				resolve: {
					user: function () {
						return $scope.user;
					}
				}
			});
		}

		console.log("edit index"+index);
	}

});

// Admin login page
app.controller('adminLoginController',function($scope, $http, $location, dataShare) {
	console.log("Admin Page.");
	
	$scope.login = function() {
		var username = $scope.username;
		var password = $scope.password;
		var userData = JSON.stringify({
			username : username,
			password : password
		});
		var url = 'http://localhost:8080/getAdminUser';
		$http({
			url : url, // invoke service
			method : 'POST',
			data : userData
		}).then(function(response) {
			console.log(response)
			dataShare.sendData(response);
			$location.path('/admin');
		}, function(error) {
			console.log("ERROR")
		})
	}
});


//Normal User
app.controller('loginController',function($scope, $http, $location, config, dataShare, Notification) {
	var urlParams = new URLSearchParams(window.location.search);
	var username = urlParams.get('uname');
	var url = config.apiLocalUrl+'getUser' ;
	$http({
		url : url, // invoke service
		method : 'GET',
		params:{uname: username}		
	}).then(function(response) {
		console.log(response)
		dataShare.sendData(response);
		$location.path('/user');
	}, function(error) {
		if(username === null){
			$location.path('/login');
		}else{
			Notification.error({message: 'Please contact to help desk!', positionX: 'center'});
			console.log("ERROR")
		}
			
		
	})
});

app.controller("listController", ["$scope","dataShare",
                                  function($scope, dataShare) {
	$scope.data=  dataShare.getData().data;
	console.log(dataShare.getData());
	$scope.export = function(){
		html2canvas(document.getElementById('mytable'), {
			onrendered: function (canvas) {
				var data = canvas.toDataURL();
				var docDefinition = {
						content: [{
							image: data,
							width: 500,
						}]
				};
				pdfMake.createPdf(docDefinition).download("user.pdf");
			}
		});
	}
}
]);


app.controller('ModalInstanceCtrl', function($scope, $uibModalInstance, user, $http, Notification, dataShare,
		config) {

	$scope.userid = user.userid;
	$scope.username = user.username;
	$scope.roles = user.previleges;
	$scope.privileges = user.actions;
	$scope.jd =user.jd;
	$scope.years = user.years;

	$scope.update = function() {		
		if(user){
			var jd = $("input[name='jd[]']")
			.map(function(){return $(this).val();}).get();
			var years = $("input[name='years[]']")
			.map(function(){return $(this).val();}).get();
			//create user object to send request
			var userData = new Object();
			userData.name = user.username;
			userData.userid = user.userid;
			userData.jd = jd;
			userData.years = years;
			user.jd = jd;
			user.years =years;
			var url = config.apiLocalUrl+ 'updateUser';
			$http({
				url : url, // invoke service
				method : 'PUT',
				data : JSON.stringify(userData),
				headers: {
	                'Content-Type': 'application/json'
	            }
			}).then(function(response) {
				//dataShare.sendData();
				Notification.success({message: 'Data is successfully updated', positionX: 'center'});			
			}, function(error) {
				Notification.error({message: 'Error, while updating data, please try again', positionX: 'center'});
			})
		}
		$uibModalInstance.close();
	};


	$scope.validate = function(el){
		var ex = /^[0-9]+\.?[0-9]*$/;
		if(ex.test(el)==false){
			Notification.error({message: 'Please enter numeric or decimal value', positionX: 'center'});
			$('button').prop('disabled', true);
		}else{
			$('button').prop('disabled', false);
		}
	}

	$scope.cancel = function() {
		$uibModalInstance.dismiss('cancel');
	};
});


app.controller('userController', function($scope, dataShare, $location, $http, Notification, config) {


	if (dataShare.getData().data) {
		$scope.status = true;
		var user = dataShare.getData().data[0];
		$scope.userid = user.userid;
		$scope.username = user.username;
		$scope.roles = user.previleges;
		$scope.privileges = user.actions;
		$scope.jd =user.jd;
		$scope.years = user.years;
		$scope.pendingtrainings = user.pendingtrainings;
		$scope.pendingtrainings2 = user.pendingtrainings2;
		$scope.waivedtrainings = user.waivedtrainings;
		$scope.completedtrainings = user.completedtrainings;
	} else {
		$scope.status = false;
	}
	
	$scope.validate = function(el){
		var ex = /^[0-9]+\.?[0-9]*$/;
		if(ex.test(el)==false){
			Notification.error({message: 'Please enter numeric or decimal value', positionX: 'center'});
			$('button').prop('disabled', true);
		}else{
			$('button').prop('disabled', false);
		}
	}

	$scope.editUser = function(){
		var jd = $("input[name='jd[]']")
		.map(function(){return $(this).val();}).get();
		var years = $("input[name='years[]']")
		.map(function(){return $(this).val();}).get();
		//create user object to send request
		var userData = new Object();
		userData.name = user.username;
		userData.userid = user.userid;
		userData.jd = jd;
		userData.years = years;
		user.jd = jd;
		user.years =years;
		var url = config.apiLocalUrl+ 'updateUser';
		$http({
			url : url, // invoke service
			method : 'PUT',
			data : JSON.stringify(userData),
			headers: {
                'Content-Type': 'application/json'
            }
		}).then(function(response) {
			//dataShare.sendData();
			Notification.success({message: 'Data is successfully updated', positionX: 'center'});			
		}, function(error) {
			Notification.error({message: 'Error, while updating data, please try again', positionX: 'center'});
		})
	}

});