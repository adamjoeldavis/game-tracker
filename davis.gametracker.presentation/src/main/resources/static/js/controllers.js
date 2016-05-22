var app = angular.module('GameTrackerApp', [ 'ui.bootstrap' ]);

app.controller('TabController', function() {
	this.tabs = [
	  {
		  header: 'Games'
	  },
	  {
		  header: 'Systems'
	  }
	];
	
	this.activeTab = 0;
	
	this.systemTabActive = () => this.activeTab == 1;
	this.gameTabActive = () => this.activeTab == 0;
});

app.controller('GameListController', function($http) {
	this.games = [];
	
	this.list = function() {
		$http({ method: 'GET', url: '/games'})
			.success(function(data) { alert(data); });
	}
	
	this.list();
});

app.controller('SystemListController', function() {
	
});