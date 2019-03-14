//@author dsaavedra

apimock = (function() {

	var mockdata = [];

	
	var myService = {
			getCinemaByName : function(name, callback) {
				var cinema = mockdata[name];
				if(cinema) callback(null, cinema);
				else callback("No cinema");			
			},
			getCinemaById : function(){
				
			},
			MAX_CINEMAS: 1			
		}
	
	return myService;
})

var CinemaServices = apimok();
CinemaServices.getCinemaByName = 1;
CinemaServices.getCinemaByName("inedy", function(error, result){
	
})